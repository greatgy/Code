#!/bin/sh


# Author:LiJiacheng
# Date:13:59 2015/10/21
# Modify:chenminchang 
# Date:14:45 2016/09/29
# start the App server

echo "begin to start up 'date'"
mount -t nfs 192.168.1.96:/home/web/WebStatic/SuperGenius /data/web/WebStatic/SuperGenius
mount -t nfs 192.168.1.96:/home/web/WebStatic/SuperGenius-Data /data/web/WebStatic/SuperGenius-Data
#mount -t nfs 192.168.1.96:/home/web/WebStatic/Sudoku-Data /data/web/WebStatic/Sudoku-Data


cd /data/web/WebLog/SuperGenius-Official
/data/env/tomcats/official/bin/startup.sh
#cd /data/web/WebLog/SuperGenius-Tpi
#/data/env/tomcats/tpi/bin/startup.sh
cd /data/web/WebLog/SuperGenius-Moral
/data/env/tomcats/moral/bin/startup.sh
cd /data/web/WebLog/SuperGenius-Admin
/data/env/tomcats/4/bin/startup.sh
cd /data/web/WebLog/SuperGenius-Finance
/data/env/tomcats/5/bin/startup.sh
cd /data/web/WebLog/Sudoku-Admin
/data/env/tomcats/sudoku/bin/startup.sh
cd /data/web/WebLog/SuperGenius-User
/data/env/tomcats/11user/bin/startup.sh
cd /data/web/WebLog/SuperGenius-Manager
/data/env/tomcats/12manager/bin/startup.sh

echo "start up finished."
echo ""
