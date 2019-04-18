@echo off
echo -------------------begin to deploy core modules-------------------
cd %~dp0
cd ../Genius-core
rem	deploy genius-core-base
cd Base
echo ############################################################################## begin to deploy genius-core-base ......
call mvn deploy
rem	deploy genius-core-cache
cd ../Cache
echo ############################################################################## begin to deploy genius-core-cache ......
call mvn deploy
rem	deploy genius-core-session
cd ../Session
echo ############################################################################## begin to deploy genius-core-session ......
call mvn deploy
rem	deploy genius-core-serial
cd ../Serial
echo ############################################################################## begin to deploy genius-core-serial ......
call mvn deploy
rem	deploy genius-core-search
cd ../Search
echo ############################################################################## begin to deploy genius-core-search ......
call mvn deploy
rem	deploy genius-core-analyzer
cd ../Analyzer
echo ############################################################################## begin to deploy genius-core-analyzer......
call mvn deploy
pause