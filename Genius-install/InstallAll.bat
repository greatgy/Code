@echo off
echo --------------------------------------begin to install all modules--------------------------------------
cd %~dp0
rem	install genius-deps
echo ############################################################################## begin to install all genius-deps modules ......
call InstallDeps
cd %~dp0
rem	install genius-core
echo ############################################################################## begin to install all genius-core modules ......
call InstallCore
cd %~dp0
rem	install genius-model
echo ############################################################################## begin to install all genius-model modules ......
call InstallModel
cd %~dp0
rem	install genius-XO
echo ############################################################################## begin to install all genius-xo modules ......
call InstallXO
cd %~dp0
rem	install genius-server
echo ############################################################################## begin to install all genius-server modules ......
call InstallServer
