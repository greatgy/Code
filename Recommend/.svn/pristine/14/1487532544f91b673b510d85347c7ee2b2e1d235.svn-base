# -*- coding: utf-8 -*-

# 导入必要模块
import pandas as pd
import func
from func import GL
import numpy as np
import time
import schedule
import logging
from pymongo import MongoClient

logging.basicConfig(filename='log.txt', level=logging.ERROR)

sql_dict, db_dict = func.read_dbinfo_xml()
host = sql_dict["mysqlhost"]
port = sql_dict["mysqlport"]
user = sql_dict["user"]
password = sql_dict["password"]
Mongoconn = MongoClient(sql_dict["mongohost"], int(sql_dict["mongoport"]))
t = time.time()


def mainFunc(dbname, table, mongodbname):
    db = Mongoconn.get_database(mongodbname)  # 连接mongodbname数据库，没有则自动创建
    words_set = db[table + '_words_set']  # 使用words_set集合，没有则自动创建
    recommend_set = db[table + '_recommend_set']

    datadf = func.getArticleFromMysql(host, port, user, password, dbname, table, 0)

    logging.error("Start splitWords %s" % dbname)
    dtList = []
    for index, html in enumerate(datadf['content'].values):
        content = func.removeHtml(html)
        dt = func.splitWords(datadf['uid'].values[index], content)
        dtList.append(dt)
    logging.error("End splitWords %s,Start save words_set" % dbname)
    if (len(dtList) > 0):
        func.saveList(dtList, words_set)
        logging.error("end save words_set %s" % dbname)

        # 建立语料库
        logging.error("------------------------------------------------------------")
        t = time.time()
        for x in func.getAll(words_set):
            x.pop('_id')
            x.pop('uid')
            dt = func.countTF_IDF(x)
            func.getKeyWords(dt)
        logging.error("End Calculation tf_idf %s,%f" % (dbname, time.time() - t))

        logging.error("------------------------------------------------------------")
        t = time.time()
        nparray = func.getVectorModel()
        logging.error("End Calculation getVectorModel %s,%f" % (dbname, time.time() - t))

        logging.error("************************************************************")
        t = time.time()
        cosMatrix = func.getMatrix(nparray)
        logging.error("End Calculation getMatrixByNew %s,%f" % (dbname, time.time() - t))

        logging.error("************************************************************")
        t = time.time()
        func.getRecommendDict(cosMatrix)
        logging.error("************************************************************")
        logging.error("End Calculation getRecommendDict %s,%f" % (dbname, time.time() - t))
        recommend_set.delete_many({})
        logging.error("************************************************************")
        logging.error("End Calculation %s,Start save recommend_set" % dbname)
        func.saveDict(GL.recommendDict, recommend_set)
        logging.error("end save words_set %s" % dbname)

    else:
        logging.error("no new data")


def boot():
    logging.error(time.strftime('%Y.%m.%d', time.localtime(time.time())))
    logging.error("Start ***** %f" % t)
    for dbname in list(db_dict.keys()):
        logging.error("Start analysis %s" % dbname)
        for tablename in db_dict[dbname]:
            mainFunc(dbname, tablename, dbname)
        logging.error("analysis %s complete" % dbname)
    Mongoconn.close()
    Mongoconn = None
    logging.error("Finansh *****")
    logging.error(time.strftime('%Y.%m.%d', time.localtime(time.time())))


schedule.every().day.at("00:01").do(boot)

while True:
    schedule.run_pending()

# boot()
