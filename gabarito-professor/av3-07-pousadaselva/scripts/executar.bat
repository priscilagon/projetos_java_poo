@echo off
REM Executa a aplicacao. Uso: scripts\executar.bat [ClassePrincipal]
cd /d "%~dp0\.."
if not exist out call scripts\compilar.bat
set CLASSE=%1
if "%CLASSE%"=="" set CLASSE=Main
java -Dfile.encoding=UTF-8 -cp "out;lib\*" %CLASSE%
