@echo off
REM Compila todo o codigo de src\ para a pasta out\
cd /d "%~dp0\.."
if exist out rmdir /s /q out
mkdir out
dir /s /b src\*.java > sources.txt
javac -encoding UTF-8 -cp "lib\*" -d out @sources.txt
del sources.txt
echo Compilacao concluida em .\out
