@echo off
echo -------------------begin to install model modules-------------------
cd %~dp0
cd ../Genius-model
rem	install genius-model-base
cd Base
echo ############################################################################## begin to install genius-model-base ......
call mvn clean install
rem	install genius-model-baseadmin
cd ../BaseAdmin
echo ############################################################################## begin to install genius-model-baseadmin ......
call mvn clean install
rem	install genius-model-portal
cd ../Portal
echo ############################################################################## begin to install genius-model-portal ......
call mvn clean install
rem	install genius-model-sns
cd ../Sns
echo ############################################################################## begin to install genius-model-sns ......
call mvn clean install
pause