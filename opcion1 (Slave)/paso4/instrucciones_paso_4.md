### Paso 5: Verificación

1. Conecta tu aplicación a ProxySQL en `localhost:6033` en lugar de conectarla directamente a MySQL.
2. Verifica que las consultas se redirigen al master y que en caso de falla, se redirigen automáticamente a la replica.

Con esta configuración, los usuarios estarán trabajando sobre la base de datos a través de ProxySQL, que gestionará la conmutación por error automáticamente en caso de que el master falle, sin que los usuarios lo perciban.

