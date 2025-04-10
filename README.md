# Запуск микросервиса

Для запуска потребуется сделать `gradle build` и загрузить image для PostgreSQL - `postgres:latest` и CassandraDB - `cassandra:latest`, а также создать image микросервиса

```shell
docker build -t transaction-limit-service:latest . 
```

После этого просто ввести команду запуска docker-compose.yaml

```shell
docker-compose up -d 
```
