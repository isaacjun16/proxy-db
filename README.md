# MySQL InnoDB ClusterSet con Docker Compose y HAProxy

Este proyecto configura un MySQL InnoDB ClusterSet distribuido en tres regiones diferentes, con tres nodos en cada región. Utiliza Docker Compose para la orquestación de los contenedores y HAProxy para el balanceo de carga y alta disponibilidad. Al final se proporciona un ejemplo de cómo conectar una aplicación Spring Boot para lograr alta disponibilidad y recuperación de desastres.

## Requisitos previos

- Docker
- Docker Compose

## Estructura del proyecto

- `crud/app/crud-site`: Aplicación CRUD desarrollada en Spring Boot para demo de la configuración del Cluster.
- `opcion1 (Slave)`: Configuración basica de dos servidores conectados como Master y Salve usando ProxySQL.
- `opcion2 (ClusterSet)`: Configuración basica de 9 servidores conectados como Nodos a un ClusterSet con 3 Cluster cada uno representando 1 región diferente.
- `Prerequisitos Opcionales`: Tecnologias recomendas para este tutorial.

## Estructura del Opción 2
 
- `paso1/compose.yml`: Define los servicios Docker para los nodos MySQL y HAProxy en cada región.
- `paso1/haproxy/haproxy_r1.cfg`: Configuración de HAProxy para la región 1.
- `paso1/haproxy/haproxy_r2.cfg`: Configuración de HAProxy para la región 2.
- `paso1/haproxy/haproxy_r3.cfg`: Configuración de HAProxy para la región 3.
- `paso2/paso2.md`: Configuración de MySQL InnoDB ClusterSet

## Comandos para Opción 2

### Levantar los contenedores (Paso 1)

Despues de revisar el archivo `compose.yml` ejecuta el siguiente comando en la terminal dentro del directorio donde esta el archivo antes mensionado para levantar los contenedores:

```sh
docker-compose up -d
```

### Configurar MySQL InnoDB ClusterSet (Paso 2)

Es necesario configurar el ClusterSet. Puedes hacerlo conectándote al nodo principal del clúster primario:

```sh
docker exec -it mysql_r1_n1 mysqlsh --uri root@mysql_r1_n1:3306
```

## Diagrama ClusterSet

[Diagrama](https://github.com/isaacjun16/proxy-db/blob/cb06d7b57afc2a14c40d675c2e5015e79e3afd56/cluster_docker.drawio.png)
