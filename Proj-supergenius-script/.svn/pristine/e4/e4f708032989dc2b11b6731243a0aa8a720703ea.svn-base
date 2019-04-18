#!/bin/sh

# Author:chenminchang
# Date:11:29 2016/9/21
# start the App server

echo "begin to start up at $(date)"

cd /home/web/WebLog/SuperGenius.Init
/home/env/search/solr/bin/solr start -c
cd /home/web/WebLog/SuperGenius.Init
/home/env/nginx/sbin/nginx -c /home/env/nginx/conf/nginx.conf
/home/env/nginx/sbin/nginx -s reload


echo "start up finished."
echo ""