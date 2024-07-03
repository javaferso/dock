#!/bin/bash

calendar=$(date +%Y-%m-%d' '%H:%M:%S)
# Agregar mensaje de registro al archivo script_execution.log
echo "${calendar}  Script ver Boleta ejecutado con IP: ${1}" >> /opt/tomcat/webapps/dockpos/script_execution.log

# Variables de configuración
usuario="root"
clave="geocom"
archivo_log="/home/geocom/geopos/current/logs/audit.log"  # Ruta hacia el archivo audit.log
archivo_replica="audit.log"
ip="$1"
ruta_replica_local=/home/supervision_caja/"$ip"-"$archivo_replica"

# Función para capturar la señal de interrupción (Ctrl+C)
function capturar_interrupcion() {
  calendar=$(date +%Y%m%d'_'%H%M%S)
  # Eliminar el archivo de réplica
  rm "$ruta_replica_local"

  # Cerrar la conexión SSH
  ssh "$usuario@$ip" "pkill -f 'tail -f $archivo_log'"

  # Mostrar mensaje de salida
  echo "_________________________________"
  echo " "
  echo "Conexion finalizada. ¡Hasta luego!"
  echo "_________________________________"
  exit 0
}
# Capturar la señal SIGINT (Ctrl+C)
trap capturar_interrupcion SIGINT

# Establecer la conexión SSH y ejecutar el comando 'tail -f' en segundo plano
#ssh "${usuario}@${ip}" "tail ${archivo_log}" > "$ruta_replica_local" &
scp -q "${usuario}@${ip}:${archivo_log}" "$ruta_replica_local"
# Modificar el tamaño de letra en el archivo replica

#chown tomcat:tomcat $ruta_replica_local
# Archivo de lectura
tail -n 100 "$ruta_replica_local"  > "/home/supervision_caja/${ip}-ultBoleta.log" &
chown tomcat:tomcat "/home/supervision_caja/${ip}-ultBoleta.log"
