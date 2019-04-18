@echo off
echo --------------------------------------begin to deploy all modules--------------------------------------
cd %~dp0
rem	deploy genius-deps
echo ############################################################################## begin to deploy all genius-deps modules ......
call DeployDeps
cd %~dp0
rem	deploy genius-core
echo ############################################################################## begin to deploy all genius-core modules ......
call DeployCore
cd %~dp0
rem	deploy genius-model
echo ############################################################################## begin to deploy all genius-model modules ......
call DeployModel
cd %~dp0
rem	deploy genius-XO
echo ############################################################################## begin to deploy all genius-xo modules ......
call DeployXO
cd %~dp0
rem	deploy genius-server
echo ############################################################################## begin to deploy all genius-server modules ......
call DeployServer
