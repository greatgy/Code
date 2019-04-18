
#start up sorl配置
server {
	listen       8087;
	default_type text/html;
	add_header Access-Control-Allow-Origin *;
	
	location /searchengine {
		proxy_pass http://localhost:8983/solr/allcloud/query;
	}
	location / {
		proxy_pass http://127.0.0.1:9096/;
		rewrite ^/(.*)/$ /$1 last;
	}
}


---------------启动solr:
安装：
先将E:\MyWork2\Proj-supergenius-finance\Web\src\main\resources\Third\Solr\mylib 放到solr安装根目录
将E:\MyWork2\Proj-supergenius-finance\Web\src\main\resources\Third\Solr\financecloud 放到\server\solr\configsets\目录下

启动：
D:\solr5.1.0>bin\solr.cmd start -c -f
访问：http://localhost:8983/solr

第一次执行下面的命令：
D:\solr5.1.0>server\scripts\cloud-scripts\zkcli.bat -zkhost localhost:9983 -cmd upconfig -confname financecloud -confdir server\solr\configsets\financecloud\conf 
创建collection：http://localhost:8983/solr/admin/collections?action=CREATE&name=financecloud&router.name=implicit&shards=shard1&replicationFactor=1&collection.configName=financecloud
修改配置后执行命令：
server\scripts\cloud-scripts\zkcli.bat -zkhost localhost:9983 -cmd upconfig -confname financecloud -confdir server\solr\configsets\financecloud\conf
http://localhost:8983/solr/admin/collections?action=RELOAD&name=financecloud		#重新加载collection

添加全栈搜索
http://localhost:8983/solr/admin/collections?action=DELETEALIAS&name=allcloud
http://localhost:8983/solr/admin/collections?action=CREATEALIAS&name=allcloud&collections=aicloud,startupcloud,financecloud,careercloud


---------------web访问：
http://localhost:8085

测试服务器部署操作
./solr start -c -f 

./server/scripts/cloud-scripts/zkcli.sh -zkhost 192.168.1.96:9983 -cmd upconfig -confname financecloud -confdir server/solr/configsets/financecloud/conf

http://192.168.1.96:8983/solr/admin/collections?action=CREATE&name=financecloud&router.name=implicit&shards=shard1&replicationFactor=1&collection.configName=financecloud

./server/scripts/cloud-scripts/zkcli.sh -zkhost 192.168.1.96:9983 -cmd upconfig -confname financecloud -confdir server/solr/configsets/financecloud/conf

http://192.168.1.96:8983/solr/admin/collections?action=RELOAD&name=financecloud
http://192.168.1.96:8983/solr/admin/collections?action=CREATEALIAS&name=allcloud&collections=careercloud,startupcloud,financecloud,aicloud

http://192.168.1.96:8983/solr/admin/collections?action=DELETEALIAS&name=allcloud
