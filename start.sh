#!/bin/bash

echo "Iniciando Oracle..."

/container-entrypoint.sh &

echo "Esperando banco subir..."

# espera ativa até o banco responder
until echo "SELECT 1 FROM dual;" | sqlplus -s system/123456@localhost:1521/XEPDB1
do
  sleep 5
  echo "Aguardando Oracle..."
done

echo "Banco pronto! Iniciando API..."

java -jar /app.jar