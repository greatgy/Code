@echo off
echo -------------------begin to install server modules-------------------
cd %~dp0
cd ../Genius-server
rem	install genius-server-base
cd Base
echo ############################################################################## begin to install genius-server-base ......
call mvn clean install
rem	install genius-server-baseadmin
cd ../BaseAdmin
echo ############################################################################## begin to install genius-server-baseadmin ......
call mvn clean install
rem	install genius-server-portal
cd ../Portal
echo ############################################################################## begin to install genius-server-portal ......
call mvn clean install
rem	install genius-server-timer
cd ../Timer
echo ############################################################################## begin to install genius-server-timer ......
call mvn clean install
rem	install genius-server-validcode
cd ../ValidCode
echo ############################################################################## begin to install genius-server-validcode ......
call mvn clean install
pause