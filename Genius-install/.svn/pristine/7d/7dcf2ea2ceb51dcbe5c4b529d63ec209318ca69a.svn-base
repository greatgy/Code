@echo off
echo -------------------begin to install XO modules-------------------
cd %~dp0
cd ../Genius-XO
rem	install genius-xo-base
cd Base
echo ############################################################################## begin to install genius-xo-base ......
call mvn clean install
rem	install genius-xo-baseadmin
cd ../BaseAdmin
echo ############################################################################## begin to install genius-xo-baseadmin ......
call mvn clean install
rem	install genius-xo-portal
cd ../Portal
echo ############################################################################## begin to install genius-xo-portal ......
call mvn clean install
rem	install genius-xo-mongodb
cd ../Mongodb
echo ############################################################################## begin to install genius-xo-mongodb ......
call mvn clean install
pause