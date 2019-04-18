@echo off
echo -------------------begin to deploy model modules-------------------
cd %~dp0
cd ../Genius-model
rem	deploy genius-model-base
cd Base
echo ############################################################################## begin to deploy genius-model-base ......
call mvn deploy
rem	deploy genius-model-baseadmin
cd ../BaseAdmin
echo ############################################################################## begin to deploy genius-model-baseadmin ......
call mvn deploy
rem	deploy genius-model-portal
cd ../Portal
echo ############################################################################## begin to deploy genius-model-portal ......
call mvn deploy
rem	deploy genius-model-sns
cd ../Sns
echo ############################################################################## begin to deploy genius-model-sns ......
call mvn deploy
pause