@echo off
echo -------------------begin to install proj-supergenius-xo-------------------
cd %~dp0
cd ../
cd ../Proj-supergenius-XO
rem	install common
cd Common	
echo ############################################################################## begin to install Proj-supergenius-XO-common ......
call mvn clean install
rem	install user
cd ../User
echo ############################################################################## begin to install Proj-supergenius-XO-user ......
call mvn clean install
rem	install Admin
cd ../Admin
echo ############################################################################## begin to install Proj-supergenius-XO-Admin ......
call mvn clean install
rem	install finance
cd ../Finance
echo ############################################################################## begin to install Proj-supergenius-XO-Finance ......
call mvn clean install
rem	install official
cd ../Official
echo ############################################################################## begin to install Proj-supergenius-XO-official ......
call mvn clean install
rem	install tpi
cd ../Tpi
echo ############################################################################## begin to install Proj-supergenius-XO-tpi ......
call mvn clean install
rem	install moral
cd ../Moral
echo ############################################################################## begin to install Proj-supergenius-XO-moral ......
call mvn clean install
rem	install account
cd ../Account
echo ############################################################################## begin to install Proj-supergenius-XO-account ......
call mvn clean install
rem	install manager
cd ../Manager
echo ############################################################################## begin to install Proj-supergenius-XO-manager ......
call mvn clean install
cd ../Enterpriser
echo ############################################################################## begin to install Proj-supergenius-XO-enterpriser ......
call mvn clean install
rem	install wechat
cd ../Wechat
echo ############################################################################## begin to install Proj-supergenius-XO-wechat ......
call mvn clean install
pause