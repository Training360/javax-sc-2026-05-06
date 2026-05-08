# Spring Cloud

```shell
docker run -d -e POSTGRES_DB=employees -e POSTGRES_USER=employees -e POSTGRES_PASSWORD=employees -p 5432:5432 --name employees-postgres postgres
```

```shell
docker run -d -p 8090:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin --name keycloak quay.io/keycloak/keycloak start-dev
```

```shell
docker run --name lgtm -p 3000:3000 -p 4040:4040 -p 4317:4317 -p 4318:4318 -p 9090:9090 -d grafana/otel-lgtm:latest
```
