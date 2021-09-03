@echo off

rem Guess TOMCAT_HOME if not defined
set CURRENT_DIR=%cd%
if not "%TOMCAT_HOME%" == "" goto gotHome
set TOMCAT_HOME=%CURRENT_DIR%
if exist "%TOMCAT_HOME%\bin\catalina.bat" goto okHome
cd ..
set TOMCAT_HOME=%cd%
cd %CURRENT_DIR%
:gotHome
if exist "%TOMCAT_HOME%\bin\catalina.bat" goto okHome
echo The TOMCAT_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
goto end
:okHome

java -classpath "%TOMCAT_HOME%\lib\hsqldb.jar" org.hsqldb.util.DatabaseManagerSwing

:end