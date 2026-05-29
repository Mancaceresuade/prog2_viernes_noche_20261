#!/bin/bash
# Compilar y ejecutar GrafoGame (Codespace / Linux / Mac)
set -e
cd "$(dirname "$0")/src/juego"
mkdir -p bin
javac -encoding UTF-8 -d bin GrafoGame.java
cp -f grafo.html bin/
echo "OK. Ejecutar con:"
echo "  cd src/juego && java -cp bin juego.GrafoGame"
