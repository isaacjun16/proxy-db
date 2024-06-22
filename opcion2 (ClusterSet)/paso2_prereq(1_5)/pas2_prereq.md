### Step-by-Step Configuration

#### 1. Configure MySQL Nodes for Group Replication

For each MySQL server, we'll need to configure the MySQL Group Replication settings.

**Primary Node Configuration Example (`mysql_r1_n1`):**
```sql
SET SQL_LOG_BIN=0;
CREATE USER 'repl'@'%' IDENTIFIED BY 'password';
GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';
FLUSH PRIVILEGES;
SET SQL_LOG_BIN=1;

CHANGE MASTER TO MASTER_USER='repl', MASTER_PASSWORD='password' FOR CHANNEL 'group_replication_recovery';

SET GLOBAL group_replication_bootstrap_group=ON;
START GROUP_REPLICATION;
SET GLOBAL group_replication_bootstrap_group=OFF;

SELECT * FROM performance_schema.replication_group_members;
```

**Secondary Node Configuration Example (`mysql_r1_n2`, `mysql_r1_n3`):**
```sql
CHANGE MASTER TO MASTER_USER='repl', MASTER_PASSWORD='password' FOR CHANNEL 'group_replication_recovery';

START GROUP_REPLICATION;

SELECT * FROM performance_schema.replication_group_members;
```

Repeat similar steps for the nodes in Cluster 2.