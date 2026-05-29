@echo off
cd /d "%~dp0src\juego"
if not exist bin mkdir bin
javac -encoding UTF-8 -d bin GrafoGame.java
copy /Y grafo.html bin\
echo OK. Ejecutar con:
echo   cd src\juego
echo   java -cp bin juego.GrafoGame
