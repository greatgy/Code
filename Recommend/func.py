# -*- encoding: utf-8 -*-
from __future__ import print_function, unicode_literals

import json
import requests
import re
import math
import pandas as pd
from pymongo import MongoClient
import pymysql
import numpy as np
from xml.dom.minidom import parse
import jieba
import jieba.analyse
import jieba.posseg


class MyGlobal:
    # 全局变量类
    def __init__(self):
        self.mycursor = []
        self.articleCount = 0
        self.speechList = ['n', 'nr', 'nr1', 'nrf', 'ns', 'nt', 'nz', 'nx', 't', 's', 'email', 'tel', 'id', 'ip', 'url']
        self.keyWords = set()
        self.uidList = []
        self.recommendDict = {}


GL = MyGlobal()


def splitWords(uid, sentence=''):
    # 分词
    sentence_seged = jieba.posseg.cut(sentence.strip())
    dt = {}
    for x in sentence_seged:
        if x.flag in GL.speechList:
            dt.setdefault(x.word.replace('.', ''), []).append(x.flag)  # key不能包含.
    for key in list(dt.keys()):
        length = len(dt[key])
        dt[key] = [length]
    dt['uid'] = uid
    return dt


def removeHtml(html):
    # 移除html标签
    dr = re.compile(r'<[^>]+>', re.S)
    content = dr.sub('', html)
    return content


def countTF_IDF(dt):
    # 计算TF_IDF
    sumValue = 0  # 文章总词数
    for value in list(dt.values()):
        sumValue += value[0]
    for key in list(dt.keys()):
        tf = dt[key][0] / sumValue
        count = 0  # 包含该词语的文章数
        for i in GL.mycursor:
            if key in i:
                count = count + 1
        idf = math.log(GL.articleCount / (count))
        tf_idf = tf * idf
        dt[key].append(tf_idf)
    return dt


def getKeyWords(dt):
    # 创建语料库
    dt = sorted(dt.items(), key=lambda x: x[1][1], reverse=True)
    count = 1
    for key in dt:
        if count <= 20:
            GL.keyWords.add(key[0])
        count = count + 1


def saveList(dtList, words_set):
    # 存储文章的所有分词
    words_set.insert_many(dtList)


def saveDict(dt, recommend_set):
    # 存储文章的推荐列表
    recommend_set.insert_one(dt)


def getAll(words_set):
    # 从mongodb中查询所有文章
    GL.mycursor = list(words_set.find())
    GL.articleCount = len(GL.mycursor)
    GL.keyWords = set()
    GL.uidList = []
    GL.recommendDict = {}
    return list(words_set.find())


def getArticleFromMysql(host, port, user, password, dbname, table, isfirst):
    # 从mysql中查询文章
    mysqlconn = pymysql.connect(host=host, port=int(port), user=user, password=password, db=dbname, charset='utf8')
    if isfirst == 1:  # 取出所有的文章
        sql = '''
            select oid,uid,content from %s where status = 1;
            ''' % table
    else:  # 取出昨天到今天的发布文章
        sql = '''
            select oid,uid,content from %s where status = 1 and DATE_SUB(CURDATE(), INTERVAL 1 DAY) <= DATE(createtime);
            ''' % table
    return pd.read_sql(sql, mysqlconn)


def getVector(dt):
    # 获得向量

    nz = np.zeros(len(GL.keyWords), dtype=np.int)
    for i, v in enumerate(GL.keyWords):
        if v in dt:
            nz[i] = dt[v][0]
    return nz


def getCos(a, b):
    # 计算cos值
    num = np.dot(a, b.T)
    denom = np.linalg.norm(a) * np.linalg.norm(b)
    if denom == 0.0:
        return 0.0
    cos = num / denom
    return cos


def getVectorModel():
    # 建立向量模型
    vectorList = []
    for x in GL.mycursor:
        k = getVector(x)
        if np.sum(k) == 0:
            pass
        else:
            vectorList.append(getVector(x))
            GL.uidList.append(x['uid'])

    return np.array(vectorList)


def getMatrix(nparray):
    A = nparray
    B = A.transpose()
    Z = np.dot(A, B)
    SqA = A**2
    Sqarray = np.array([np.sqrt(np.sum(SqA, axis=1))])
    Sq = Sqarray.reshape([len(Sqarray[0])])
    Sqsum = np.empty((len(A), len(Sq)))
    for i in range(len(A)):
        Sqsum[i] = Sq
    SqsumT = Sqsum.transpose()
    M = Sqsum * SqsumT
    cos = Z * (M**-1)
    return cos


def getRecommendDict(cosMatrix):
    # 填充推荐列表
    for i, m in enumerate(cosMatrix):
        d = list(zip(GL.uidList, m))
        d = sorted(d, key=lambda x: x[1], reverse=True)
        s = list(map(lambda x: x[0], d[1:11]))
        GL.recommendDict[GL.uidList[i]] = s


def countTF(dt):
    # 计算TF(无用函数，留作备用)
    sumValue = 0
    for value in list(dt.values()):
        sumValue += value[0]
    for key in list(dt.keys()):
        tf = dt[key][0] / sumValue
        dt[key].append(tf)
    return dt


def countIDF(dt):
    # 计算IDF(无用函数，留作备用)
    for key in list(dt.keys()):
        count = 0
        for i in GL.mycursor:
            if key in i:
                count = count + 1
        idf = math.log(GL.articleCount / (count))
        dt[key].append(idf)
    return dt


def read_dbinfo_xml():
    sql_dict = {}
    db_dict = {}
    # 读取配置文件路径
    dom = parse("config.xml")
    # 获取文件元素对象
    document = dom.documentElement
    # 读取配置文件中数据
    sqldata = document.getElementsByTagName("sqldata")
    dbList = document.getElementsByTagName("db")
    # 获取数据库基本信息
    host_list = sqldata[0].getElementsByTagName("mysqlhost")
    port_list = sqldata[0].getElementsByTagName("mysqlport")
    user_list = sqldata[0].getElementsByTagName("user")
    password_list = sqldata[0].getElementsByTagName("password")
    mongohost_list = sqldata[0].getElementsByTagName("mongohost")
    mongoport_list = sqldata[0].getElementsByTagName("mongoport")

    # 将获取的数据放入字典中
    host = host_list[0].childNodes[0].data
    port = port_list[0].childNodes[0].data
    user = user_list[0].childNodes[0].data
    password = password_list[0].childNodes[0].data
    mongohost = mongohost_list[0].childNodes[0].data
    mongoport = mongoport_list[0].childNodes[0].data

    sql_dict["mysqlhost"] = host
    sql_dict["mysqlport"] = port
    sql_dict["user"] = user
    sql_dict["password"] = password
    sql_dict["mongohost"] = mongohost
    sql_dict["mongoport"] = mongoport

    # 获取数据库名字和表名
    for i, x in enumerate(dbList):
        dbname_list = x.getElementsByTagName("dbname")
        tablename_list = x.getElementsByTagName("tablename")
        dbname = dbname_list[0].childNodes[0].data
        tablename = tablename_list[0].childNodes[0].data
        db_dict.setdefault(dbname, []).append(tablename)

    return sql_dict, db_dict
