#!/bin/bash

calendar=$(date +%Y-%m-%d' '%H:%M:%S)
echo "${calendar} Script ejecutado con IP: ${1}" >> /opt/tomcat/webapps/visionboletas/script_execution.log

# Variables de configuración
usuario="root"
clave="geocom"
archivo_log="/home/geocom/geopos/current/logs/sale.log"
archivo_replica="replica.log"
ip="$1"
ruta_replica_local="/home/supervision_caja/${ip}-${archivo_replica}"

function capturar_interrupcion() {
  calendar=$(date +%Y%m%d'_'%H%M%S)
  rm "$ruta_replica_local"
  ssh "$usuario@$ip" "pkill -f 'tail -f $archivo_log'"
  echo "_________________________________"
  echo " "
  echo "Conexion finalizada. ¡Hasta luego!"
  echo "_________________________________"
  exit 0
}

trap capturar_interrupcion SIGINT

# Copiar el archivo de log desde la máquina remota
scp -q "$usuario@$ip:$archivo_log" "$ruta_replica_local"
chown tomcat:tomcat "$ruta_replica_local"

# Eliminar líneas no deseadas del archivo de réplica
sed -i '/GEOPos version:/d' "$ruta_replica_local"
sed -i '/Cajera:/d' "$ruta_replica_local"
sed -i '/Tipo de documento:/d' "$ruta_replica_local"
sed -i '/ItemId:/d' "$ruta_replica_local"
sed -i '/GEOPromotion/d' "$ruta_replica_local"
sed -i 's/\(^[0-9]\{4\}-[0-9]\{2\}-[0-9]\{2\}\) S/\1/' "$ruta_replica_local"
sed -i 's/^[0-9]\{4\}-[0-9]\{2\}-[0-9]\{2\} //' "$ruta_replica_local"
#sed -i 's/^[0-9]\{4\}-[0-9]\{2\}-[0-9]\{2\}/2023-08-17/' "$ruta_replica_local"
# Procesar y mostrar las líneas del archivo de réplica
tail -f -n30 "$ruta_replica_local"  > "/home/supervision_caja/"${ip}"-sale.log" &
# Cambiar permisos del archivo de réplica
chmod a+r "$ruta_replica_local"

