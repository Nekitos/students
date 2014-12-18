@ECHO OFF

SET JAVA_EXE=%JAVA_HOME%\bin\java.exe

IF NOT EXIST "%JAVA_EXE%" GOTO error

SET APP_BIN_DIR=%~dp0
SET APP_HOME=%APP_BIN_DIR%\..

for /f tokens^=2-5^ delims^=.-_^" %%j in ('"%JAVA_EXE%" -fullversion 2^>^&1') do set "jver=%%j%%k%%l"
IF NOT %jver% GTR 160 GOTO error

"%JAVA_EXE%" -jar "%APP_HOME%"\lib\students.jar

GOTO end

:error
ECHO "Cannot start the program"
ECHO "Please install java version 1.6 or higher"
ECHO "And set environment variable JAVA_HOME"

:java_version_lt_16
ECHO "Java version lower than 1.6. Couldn't start the program"

:end