# Order system

---

## Application

### Order Service

#### URL
- Swagger: http://localhost:8080/swagger-ui/index.html
- h2-console: http://localhost:8080/h2-console


#### Env
- in memory
```
SPRING_PROFILES_ACTIVE=inmem;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUG
```
- kafka single
```
SPRING_DATASOURCE_HOST=localhost;SPRING_DATASOURCE_PORT=3306;SPRING_DATASOURCE_DATABASE=orderdb;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=mysql;SPRING_PROFILES_ACTIVE=kafka-single-cluster;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUG
```

- kafka multi
```
SPRING_DATASOURCE_HOST=localhost;SPRING_DATASOURCE_PORT=3306;SPRING_DATASOURCE_DATABASE=orderdb;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=mysql;SPRING_PROFILES_ACTIVE=kafka-multiple-cluster;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUG
```

- axon
```
SPRING_DATASOURCE_HOST=localhost;SPRING_DATASOURCE_PORT=3306;SPRING_DATASOURCE_DATABASE=orderdb;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=mysql;SPRING_PROFILES_ACTIVE=axon;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUG
```

- kafka single sasl
```
SPRING_DATASOURCE_HOST=localhost;SPRING_DATASOURCE_PORT=3306;SPRING_DATASOURCE_DATABASE=orderdb;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=mysql;SPRING_PROFILES_ACTIVE=kafka-single-sasl;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUG
```

And set JVM parameter and set password to kafka_jaas.conf
```
# Linux
-Djava.security.auth.login.config={absolute-path}/config/kafka/kafka_jaas.conf

# Windows
-Djava.security.auth.login.config={absolute-path}\config\kafka\kafka_jaas.conf
```

### Warehouse Service
#### Env
- in memory
```
SPRING_PROFILES_ACTIVE=inmem;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUG
```

- kafka single
```
SPRING_DATASOURCE_HOST=localhost;SPRING_DATASOURCE_PORT=3306;SPRING_DATASOURCE_DATABASE=warehousedb;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=mysql;SPRING_PROFILES_ACTIVE=kafka-single-cluster;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUG
```

- kafka multiple
```
SPRING_DATASOURCE_HOST=localhost;SPRING_DATASOURCE_PORT=3306;SPRING_DATASOURCE_DATABASE=warehousedb;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=mysql;SPRING_PROFILES_ACTIVE=kafka-multiple-cluster;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUG
```

- axon
```
SPRING_DATASOURCE_HOST=localhost;SPRING_DATASOURCE_PORT=3306;SPRING_DATASOURCE_DATABASE=warehousedb;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=mysql;SPRING_PROFILES_ACTIVE=axon;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUG
```

- kafka single-sasl
```
SPRING_DATASOURCE_HOST=localhost;SPRING_DATASOURCE_PORT=3306;SPRING_DATASOURCE_DATABASE=warehousedb;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=mysql;SPRING_PROFILES_ACTIVE=kafka-single-sasl;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUG
```

And set JVM parameter and set password to kafka_jaas.conf
```
# Linux
-Djava.security.auth.login.config={absolute-path}/config/kafka/kafka_jaas.conf

# Windows
-Djava.security.auth.login.config={absolute-path}\config\kafka\kafka_jaas.conf
```

### Payment Service
#### Env
- in memory
```
SPRING_PROFILES_ACTIVE=inmem;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUG
```
- kafka single
```
SPRING_DATASOURCE_HOST=localhost;SPRING_DATASOURCE_PORT=3306;SPRING_DATASOURCE_DATABASE=paymentdb;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=mysql;SPRING_PROFILES_ACTIVE=kafka-single-cluster;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUG
```

- kafka multiple
```
SPRING_DATASOURCE_HOST=localhost;SPRING_DATASOURCE_PORT=3306;SPRING_DATASOURCE_DATABASE=paymentdb;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=mysql;SPRING_PROFILES_ACTIVE=kafka-multiple-cluster;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUG
```

- axon
```
SPRING_DATASOURCE_HOST=localhost;SPRING_DATASOURCE_PORT=3306;SPRING_DATASOURCE_DATABASE=paymentdb;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=mysql;SPRING_PROFILES_ACTIVE=axon;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUG
```

- kafka single-sasl
```
SPRING_DATASOURCE_HOST=localhost;SPRING_DATASOURCE_PORT=3306;SPRING_DATASOURCE_DATABASE=paymentdb;SPRING_DATASOURCE_USERNAME=root;SPRING_DATASOURCE_PASSWORD=mysql;SPRING_PROFILES_ACTIVE=kafka-single-sasl;LOGBACK_LOGGER_COM_EXAMPLE_LEVEL=DEBUGm
```

And set JVM parameter and set password to kafka_jaas.conf
```
# Linux
-Djava.security.auth.login.config={absolute-path}/config/kafka/kafka_jaas.conf

# Windows
-Djava.security.auth.login.config={absolute-path}\config\kafka\kafka_jaas.conf
```
## API

### Standard
1. Add item
    ```shell
    curl --location --request POST 'localhost:8180/api/items' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name" : "test-item-name",
        "nr" : 3
    }'
    ```

2. Order  
    {item-id}: The resulting ItemId from step 1
    ```shell
    curl --location --request POST 'localhost:8080/api/orders' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "accountId" : "test-account-id",
        "items" : [
            {
                "itemId" : "{item-id}",
                "nr" : 1
            }
        ]
    }'
    ```

---

## Infrastructure

### Docker

#### Kafka Single Cluster

```shell
$ docker compose -f docker-compose-kafka-single-cluster.yaml up -d
```

```shell
$ docker compose -f docker-compose-kafka-single-cluster.yaml down --rmi all --volumes --remove-orphans
```

#### Kafka Multiple Cluster

```shell
$ docker compose -f docker-compose-kafka-multiple-cluster.yaml up -d
```

```shell
$ docker compose -f docker-compose-kafka-multiple-cluster.yaml down --rmi all --volumes --remove-orphans
```

#### Axon
```shell
$ docker compose -f docker-compose-axon.yaml up -d
```

```shell
$ docker compose -f docker-compose-axon.yaml down --rmi all --volumes --remove-orphans
```

### Docker Hints

- cp-kafka
```shell
$ docker run --net order-system_network0 -it --rm confluentinc/cp-kafka bash
```

- mysql
```shell
$ docker exec -it mysql bash
```

### No docker

Run axon-server jar
https://docs.axoniq.io/reference-guide/getting-started/quick-start