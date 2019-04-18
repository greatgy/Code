#!/bin/sh

cd /data/web/WebLog/SuperGenius-Moral
/data/env/tomcats/moral/bin/catalina.sh stop -force
cd /data/web/WebLog/SuperGenius-Official
/data/env/tomcats/official/bin/catalina.sh stop -force
cd /data/web/WebLog/Sudoku-Admin
/data/env/tomcats/sudoku/bin/catalina.sh stop -force
#cd /data/web/WebLog/SuperGenius-Tpi
#/data/env/tomcats/tpi/bin/catalina.sh stop -force
cd /data/web/WebLog/SuperGenius-Admin
/data/env/tomcats/4/bin/catalina.sh stop -force
cd /data/web/WebLog/SuperGenius-Finance
/data/env/tomcats/5/bin/catalina.sh stop -force
cd /data/web/WebLog/SuperGenius-User
/data/env/tomcats/11user/bin/catalina.sh stop -force
cd /data/web/WebLog/SuperGenius-Manager
/data/env/tomcats/12manager/bin/catalina.sh stop -force
