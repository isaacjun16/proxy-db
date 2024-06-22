### Paso 5: Preparación ambiente (Opcional) [RECOMENDADO]

1. **Instalar Portainer**:

```sh
docker run -d -p 8000:8000 -p 9443:9443 --name portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce:sts
```

2. **Instalar MySQL Workbench**:

[Página oficial de MySQL Workbench](https://dev.mysql.com/downloads/workbench/)

3. **Instalar CygWin**:

[Página oficial de CygWin](https://www.cygwin.com/)

3. **Instalar Java JDK 17+**:

[Página oficial de Oracle JDK](https://www.oracle.com/java/technologies/downloads/#java17)

3. **Instalar Spring Boot IDE**:

[Página oficial de Spring Boot (Descargar Spring Tools 4 for Eclipse)](https://spring.io/tools)
