@echo off
echo -------------------begin to install proj-supergenius-server-------------------
cd %~dp0
cd ../
cd ../Proj-supergenius-server
rem	install common
cd Common	
echo ############################################################################## begin to install Proj-supergenius-server-common ......
call mvn clean install
rem	install user
cd ../User
echo ############################################################################## begin to install Proj-supergenius-server-user ......
call mvn clean install
rem	install Admin
cd ../Admin
echo ############################################################################## begin to install Proj-supergenius-server-Admin ......
call mvn clean install
rem	install finance
cd ../Finance
echo ############################################################################## begin to install Proj-supergenius-server-Finance ......
call mvn clean install
rem	install official
cd ../Official
echo ############################################################################## begin to install Proj-supergenius-server-official ......
call mvn clean install
rem	install tpi
cd ../Tpi
echo ############################################################################## begin to install Proj-supergenius-server-tpi ......
call mvn clean install
rem	install moral
cd ../Moral
echo ############################################################################## begin to install Proj-supergenius-server-moral ......
call mvn clean install
rem	install account
cd ../Account
echo ############################################################################## begin to install Proj-supergenius-server-account ......
call mvn clean install
rem	install manager
cd ../Manager
echo ############################################################################## begin to install Proj-supergenius-server-manager ......
call mvn clean install
cd ../Enterpriser
echo ############################################################################## begin to install Proj-supergenius-server-enterpriser ......
call mvn clean install
rem	install wechat
cd ../Wechat
echo ############################################################################## begin to install Proj-supergenius-server-wechat ......
call mvn clean install
pause