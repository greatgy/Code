#!/bin/sh

# Author:YuYingJie
# Date:16:50 2015/10/21
# Modify:chenminchang 
# Date:14:45 2016/09/29
# start the App server

echo "begin to start up at $(date)"


cd /data/web/DBLog/SuperGenius.Init
/data/env/mongodb/startmongod.sh
cd /data/web/DBLog/SuperGenius.Init
/data/env/redis-2.6.14/src/redis-server /data/env/redis-2.6.14/redis.conf
cd /data/web/DBLog/SuperGenius.Init
/usr/local/memcached/bin/memcached -d -m 2048 -p 11211 -u root
echo "start up finished."
echo ""

