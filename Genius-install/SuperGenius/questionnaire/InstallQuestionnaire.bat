@echo off
cd  ../../../Proj-supergenius-XO
cd Questionnaire	
echo ############################################################################## begin to install Proj-supergenius-XO-Questionnaire ......
call mvn clean install

cd ../../Proj-supergenius-XO
cd Common	
echo ############################################################################## begin to install Proj-supergenius-XO-Common ......
call mvn clean install

cd ../../Proj-supergenius-XO
cd user
echo ############################################################################## begin to install Proj-supergenius-XO-user ......
call mvn clean install

cd ../../Proj-supergenius-server
cd Questionnaire
echo ############################################################################## begin to install Proj-supergenius-server-Questionnaire ......
call mvn clean install

cd ../../Proj-supergenius-server
cd user
echo ############################################################################## begin to install Proj-supergenius-server-user ......
call mvn clean install

cd ../../Proj-supergenius-questionnaire/Web
echo ############################################################################## begin to install Proj-supergenius-Questionnaire ......
call mvn clean install

cd ../../Proj-supergenius-user/Web
echo ############################################################################## begin to install Proj-supergenius-Questionnaire ......
call mvn clean install
pause