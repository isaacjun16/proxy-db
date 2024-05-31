### Paso 2: Configurar MySQL InnoDB ClusterSet

Conéctate al nodo principal del clúster primario:

```sh
docker exec -it mysql_r1_n1 mysqlsh --uri root@mysql_r1_n1:3306
```

Luego, en la consola de MySQL Shell, ejecutamos los siguientes comandos:

```javascript
// Preparamos los nodos para crear los clusters y el clusterset (NOTA: cuando nos pregunte para reiniciar diremos que no, ya que al ser contenedores de docker tiene que hacerse manualmente)
\c root@mysql_r1_n1:3306
dba.configureInstance();

\c root@mysql_r1_n2:3306
dba.configureInstance();

\c root@mysql_r1_n3:3306
dba.configureInstance();

\c root@mysql_r2_n1:3306
dba.configureInstance();

\c root@mysql_r2_n2:3306
dba.configureInstance();

\c root@mysql_r2_n3:3306
dba.configureInstance();

\c root@mysql_r3_n1:3306
dba.configureInstance();

\c root@mysql_r3_n2:3306
dba.configureInstance();

\c root@mysql_r3_n3:3306
dba.configureInstance();

//Reinicar contenedores en Portainer

// Nos conectamos al Nodo 1 de la Region 1 para crear el closter principal
\c root@mysql_r1_n1:3306

// Creamos una base de datos de prueba
\sql CREATE DATABASE `tiendadb` /*!40100 DEFAULT CHARACTER SET utf8mb4 */ /*!80016 DEFAULT ENCRYPTION='N' */;

// Configurarmos el cluster para la region 1
var region1Cluster = dba.createCluster('Region1Cluster');
region1Cluster.addInstance('root@mysql_r1_n2:3306');
region1Cluster.addInstance('root@mysql_r1_n3:3306');

// Crear clusterSet
var clusterSet = region1Cluster.createClusterSet('clusterset');

// Configurar un cluster replicar en region 2
var region2Cluster = clusterSet.createReplicaCluster('mysql_r2_n1:3306', 'Region2Cluster');
region2Cluster.addInstance('root@mysql_r2_n2:3306');
region2Cluster.addInstance('root@mysql_r2_n3:3306');

// Configurar un cluster replicar en region 3
var region3Cluster = clusterSet.createReplicaCluster('mysql_r3_n1:3306', 'Region3Cluster');
region3Cluster.addInstance('root@mysql_r3_n2:3306');
region3Cluster.addInstance('root@mysql_r3_n3:3306');

// Verificamos el estado del clusters y clusterset creados
region1Cluster.status();
region2Cluster.status();
region3Cluster.status();
clusterSet.status();

// Cambiar el Nodo 2 como primario en el Cluster para la Region 1
region1Cluster.setPrimaryInstance('mysql_r1_n2:3306');
region1Cluster.status();

// Cambiar el cluster primario en el cluser set a la Region 2
clusterSet.setPrimaryCluster('Region2Cluster');
clusterSet.status();

// Obtenemos el Cluster set despues de una desconexion
\c root@mysql_r1_n1:3306
var clusterSet = dba.getClusterSet();
```


