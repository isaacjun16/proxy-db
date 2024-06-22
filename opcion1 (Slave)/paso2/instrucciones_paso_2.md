### Paso 2: Configurar la replicación MySQL

Una vez que los contenedores están en marcha, configura la replicación MySQL.

1. **En el primario**:

Accede al contenedor master y configura la replicación:
```sh
docker exec -it mysql-primario bash
mysql -u root -p
```

En el cliente MySQL, ejecuta los siguientes comandos:
```sql
CREATE USER 'secundario'@'%' IDENTIFIED BY 'secundario_password';
GRANT REPLICATION SLAVE ON *.* TO 'secundario'@'%';
FLUSH PRIVILEGES;

FLUSH TABLES WITH READ LOCK;
SHOW MASTER STATUS;
```

Anota la salida de `SHOW MASTER STATUS;` ya que contiene la información necesaria para configurar el esclavo (Secundario).

2. **En el secundario**:

Accede al contenedor replica y configura la réplica:
```sh
docker exec -it mysql-secundario bash
mysql -u root -p
```

En el cliente MySQL, ejecuta los siguientes comandos (sustituye `master_log_file` y `master_log_pos` con los valores obtenidos del comando `SHOW MASTER STATUS;`):
```sql
CHANGE MASTER TO
MASTER_HOST='mysql-primario',
MASTER_USER='replica',
MASTER_PASSWORD='secundario_password',
MASTER_LOG_FILE='master_log_file',
MASTER_LOG_POS=master_log_pos;

START SLAVE;
```

