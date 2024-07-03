#!/bin/bash
calendar=$(date +%Y-%m-%d' '%H:%M:%S)
# Agregar mensaje de registro al archivo script_execution.log
echo "${calendar}  Script buscar boleta ejecutado con IP: ${1}" >> /opt/tomcat/webapps/dockpos/script_execution.log
echo "${calendar}  Numero Boleta:        ${2}" >> /opt/tomcat/webapps/dockpos/script_execution.log
# Variables de configuración
usuario="root"
clave="geocom"
archivo_log="/home/geocom/geopos/current/logs/sale.log"
archivo_replica="ticket.log"
if [ "$1" == "-T" ]
then
    shift
    if [ "$1" == "" ]
    then
        echo "Uso: Debe especificar una ip -T numIP, Ejemplo: -T 10.22.33.121"
        exit 255
    else
        ip="$1"
    fi
fi
if [ "$2" == "-N" ]
then
    shift
    if [ "$2" == "" ]
    then
        echo "Uso: Debe especificar un numero de boleta, Ejemplo: -N 16745321"
        exit 255
    else
        numero_boleta="$2"
    fi
fi

ruta_replica_local=/home/supervision_caja/"$ip"-"$archivo_replica"
#CMD=$(ssh -q $usuario@$ip)
#ssh "$usuario@$ip" tail -n 20 "$archivo_log" > "$ruta_replica_local" &
CMD=$(ssh -q root@$ip "cd /home/geocom/geopos/current/logs/audit.log.*; awk '/INICIO IMPRESION DEL DOCUMENTO/,/FIN IMPRESION DEL DOCUMENTO/' /home/geocom/geopos/current/logs/audit.log | grep -B 20 -A 150 -w '${numero_boleta}'")
# Check if CMD command output is empty
if [ -z "$CMD" ]; then
  echo "No se encontró el resultado deseado para el numero de boleta :${numero_boleta}" >> "$ruta_replica_local"
else
  echo "$CMD" > "$ruta_replica_local"
fi
