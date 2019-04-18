==================
文章推荐项目
==================

工具版本
==============
：python：3.7.0
：pip:3.7.0


第三方库
==================
#!/bin/bash
pip install numpy
pip install pandas
pip install pymongo
pip install pymysql
pip install requests
pip install parse
pip install jieba
pip install schedule

定时任务
==================
crontab -e
0 23 * * * /app/recommend/shell/crontab.sh

定时任务脚本crontab.sh
==================
#!/bin/bash
cd /app/recommend/
/usr/local/python3/bin/python3 main.py

正式服务器执行脚本
==================
docker run -d -v /data/web/recommend/:/app/recommend --name recommend grandgeniusgroup/python tail -f /dev/null

sh piplist.sh


文件说明
==================
func.py：公用函数文件
main.py：主函数文件，默认调用getArticleFromMysql函数isfirst参数值为0，即指获取创建时间为24小时之内的文章
#firstmain.py：正式服务器中包含该文件，与主函数文件相同，只是getArticleFromMysql函数isfirst参数值为1，获取所有文章，执行前需清除mongodb中对应库下的article_words_set中的数据
config.xml：连接数据库配置文件
remade.rst:项目说明文件

V1.0.0 版本内容
==================
相关文章推荐功能：
   1、从mysql数据库查询文章
   2、使用bosonnlp插件进行分词（官网https://bosonnlp.com/）
   3、根据TF-IDF公式计算每个词的权重，根据权重排序筛选每篇20个关键词组成语料库
   4、根据语料库为每篇文章建立向量
   5、并将所有向量组成向量模型
   6、计算每个向量之间的cos值
   7、建立cos方阵
   8、根据cos值排序并对每篇文章取出十篇文章组成相关文章列表

V1.0.1 版本内容
==================
由于bosonnlp使用请求方式分词，会出现线程链接过多，消耗内存过大，占用网络带宽高等问题，故将bosonnlp分词器改为结巴分词。