@echo off
echo -------------------begin to deploy server modules-------------------
cd %~dp0
cd ../Genius-server
rem	deploy genius-server-base
cd Base
echo ############################################################################## begin to deploy genius-server-base ......
call mvn deploy
rem	deploy genius-server-baseadmin
cd ../BaseAdmin
echo ############################################################################## begin to deploy genius-server-baseadmin ......
call mvn deploy
rem	deploy genius-server-portal
cd ../Portal
echo ############################################################################## begin to deploy genius-server-portal ......
call mvn deploy
rem	deploy genius-server-timer
cd ../Timer
echo ############################################################################## begin to deploy genius-server-timer ......
call mvn deploy
rem	deploy genius-server-validcode
cd ../ValidCode
echo ############################################################################## begin to deploy genius-server-validcode ......
call mvn deploy
pause