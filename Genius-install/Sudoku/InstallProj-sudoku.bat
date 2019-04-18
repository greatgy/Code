@echo off
echo --------------------------------------begin to install all proj-sudoku--------------------------------------
echo ############################################################################## begin to install all proj-sudoku-xo ......
cd %~dp0
cd E:\MyWork3\Proj-sudoku-xo\Web
call mvn clean install
echo ############################################################################## begin to install all proj-sudoku-server ......
cd %~dp0
cd E:\MyWork3\Proj-sudoku-server\Web
call mvn clean install
echo ############################################################################## begin to install Proj-sudoku-admin ......
cd %~dp0
cd E:\MyWork3\Proj-sudoku-admin\Web
call mvn clean install
pause