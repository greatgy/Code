@echo off
echo --------------------------------------begin to install all proj-supergenius--------------------------------------
cd %~dp0
rem	install proj-supergenius-xo
echo ############################################################################## begin to install all proj-supergenius-xo ......
call InstallProj-supergenius-XO
cd %~dp0
rem	install proj-supergenius-server
echo ############################################################################## begin to install all proj-supergenius-server ......
call InstallProj-supergenius-server
cd %~dp0
cd ../
cd ../Proj-supergenius-admin/Web
rem	install Proj-supergenius-admin
echo ############################################################################## begin to install Proj-supergenius-admin ......
call mvn clean install
pause
cd %~dp0
cd ../
cd ../Proj-supergenius-finance/Web
rem	install Proj-supergenius-finance
echo ############################################################################## begin to install Proj-supergenius-finance ......
call mvn clean install
pause
cd %~dp0
cd ../
cd ../Proj-supergenius-official/Web
rem	install Proj-supergenius-official
echo ############################################################################## begin to install Proj-supergenius-official ......
call mvn clean install
pause
cd %~dp0
cd ../
cd ../Proj-supergenius-tpi/Web
rem	install Proj-supergenius-tpi
echo ############################################################################## begin to install Proj-supergenius-tpi ......
call mvn clean install
pause
cd %~dp0
cd ../
cd ../Proj-supergenius-moral/Web
rem	install Proj-supergenius-moral
echo ############################################################################## begin to install Proj-supergenius-moral ......
call mvn clean install
pause
cd %~dp0
cd ../
cd ../Proj-supergenius-account/Web
rem	install Proj-supergenius-account
echo ############################################################################## begin to install Proj-supergenius-account ......
call mvn clean install
pause
cd %~dp0
cd ../
cd ../Proj-supergenius-user/Web
rem	install Proj-supergenius-user
echo ############################################################################## begin to install Proj-supergenius-user ......
call mvn clean install
pause
cd %~dp0
cd ../
cd ../Proj-supergenius-manager/Web
rem	install Proj-supergenius-manager
echo ############################################################################## begin to install Proj-supergenius-manager ......
call mvn clean install
pause
cd %~dp0
cd ../
cd ../Proj-supergenius-enterpriser/Web
rem	install Proj-supergenius-enterpriser
echo ############################################################################## begin to install Proj-supergenius-enterpriser ......
call mvn clean install
pause
cd %~dp0
cd ../
cd ../Proj-supergenius-wechat/Web
rem	install Proj-supergenius-wechat
echo ############################################################################## begin to install Proj-supergenius-wechat ......
call mvn clean install
pause
