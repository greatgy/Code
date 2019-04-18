@echo off
echo -------------------begin to install core modules-------------------
cd %~dp0
cd ../Genius-core
rem	install genius-core-base
cd Base
echo ############################################################################## begin to install genius-core-base ......
call mvn clean install
rem	install genius-core-cache
cd ../Cache
echo ############################################################################## begin to install genius-core-cache ......
call mvn clean install
rem	install genius-core-session
cd ../Session
echo ############################################################################## begin to install genius-core-session ......
call mvn clean install
rem	install genius-core-serial
cd ../Serial
echo ############################################################################## begin to install genius-core-serial ......
call mvn clean install
rem	install genius-core-search
cd ../Search
echo ############################################################################## begin to install genius-core-search ......
call mvn clean install
rem	install genius-core-analyzer
cd ../Analyzer
echo ############################################################################## begin to install genius-core-analyzer......
call mvn clean install
pause