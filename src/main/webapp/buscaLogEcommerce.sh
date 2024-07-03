#!/bin/bash
calendar=$(date +%Y-%m-%d' '%H:%M:%S)
# Agregar mensaje de registro al archivo script_execution.log
echo "${calendar}  Script buscar log ecommerce ejecutado con IP: ${1}" >> /opt/tomcat/webapps/dockpos/script_execution.log

# Variables de configuración
usuario="root"
clave="geocom"
archivo_log="/home/geocom/geopos/current/logs/ecommerce.log"
archivo_replica="ecommerce.log"
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
if [ "$2" == "-P" ]
then
    shift
    if [ "$2" == "" ]
    then
        echo "Uso: Debe especificar un numero -P numPed, Ejemplo: -P 2684538"
        exit 255
    else
        numero_pedido="$2"
    fi
fi

ruta_replica_local=/home/supervision_caja/"$numero_pedido"-"$archivo_replica"
# Copiar el archivo de log desde la máquina remota
CMD=$(ssh -q root@$ip "cd /home/geocom/geopos/current/logs/; awk '/************ ECOMMERCE ************/,/Enviando a procesar pedido/' /home/geocom/geopos/current/logs/ecommerce.log* | grep -B 10 -A 10 -w '${numero_pedido}'")
# Check if CMD command output is empty
if [ -z "$CMD" ]; then
  echo "No se encontró el resultado deseado para el numero de pedido :${numero_pedido}" >> "$ruta_replica_local"
else
  echo "$CMD" > "$ruta_replica_local"
fi
#scp -q "$usuario@$ip:$archivo_log" "$ruta_replica_local"
chown tomcat:tomcat "$ruta_replica_local"

# Eliminar líneas no deseadas del archivo de réplica
#sed -i '/GEOPos version:/d' "$ruta_replica_local"
#sed -i '/Cajera:/d' "$ruta_replica_local"
#sed -i '/Tipo de documento:/d' "$ruta_replica_local"
#sed -i '/ItemId:/d' "$ruta_replica_local"
#sed -i '/GEOPromotion/d' "$ruta_replica_local"
#sed -i 's/\(^[0-9]\{4\}-[0-9]\{2\}-[0-9]\{2\}\) S/\1/' "$ruta_replica_local"
#sed -i 's/^[0-9]\{4\}-[0-9]\{2\}-[0-9]\{2\} //' "$ruta_replica_local"
#sed -i 's/,([0-9]+) - / - /g' "$ruta_replica_local"
# Procesar y mostrar las líneas del archivo de réplica
#tail -f -n30 "$ruta_replica_local"  > "/home/supervision_caja/"${ip}"-sale.log" &
# Cambiar permisos del archivo de réplica
chmod a+r "$ruta_replica_local"
