
## Что здесь вообще есть

- **eureka-server** — registry, сервисы туда регистрируются
- **service2** — отдаёт свои данные: `GET /api/service2`
- **service1** — `GET /api/service1`: сам что-то добавляет и дергает service2 через discovery (не по IP, а по имени `service2`)
- **gateway** — единая точка входа, порт **8888**, маршруты на service1 и service2
- **tempo + prometheus + grafana** — чтобы было куда слать OTLP и где открыть трейсы (Prometheus нужен конфигу Tempo из примера Grafana)


## Что нужно установить

- **Docker** + Docker Compose 
- Для сборки **не в Docker**: **JDK 21** 

## Как запустить

Из корня репозитория:

```bash
docker compose up -d --build
```


## Как проверить, что работает

Основной сценарий из задания — запрос через gateway в service1, а тот уже ходит в service2:

```bash
curl http://localhost:8888/api/service1
```

Должен прийти JSON, где есть и своё от service1, и вложенный кусок от service2.

Прямо ко второму сервису через тот же gateway (если надо):

```bash
curl http://localhost:8888/api/service2
```

Регистраци можно посмотреть: http://localhost:8761

## Трейсы (Tempo / Grafana)

- Grafana: http://localhost:3000 (у меня включён анонимный доступ, сразу можно зайти)
- Дальше **Explore** → датасорс **Tempo** → можно поискать по сервисам вроде `gateway`, `service1`, `service2`

Сначала лучше один раз дернуть `curl` на `/api/service1`, потом смотреть трейсы — так точно что-то попадёт в Tempo.

## Порты

| Куда | Порт |
|------|------|
| Gateway (то, куда ходит «пользователь») | 8888 |
| Eureka | 8761 |
| Grafana | 3000 |
| Tempo (HTTP API) | 3200 |
| OTLP (если вдруг с хоста слать) | 4317, 4318 |
| Prometheus | 9090 |

Порты **service1** и **service2** наружу не выставлял — по задумке с хоста ходим только на gateway 


## Структура папок (кратко)

```
eureka-server/   — registry
gateway/         — Spring Cloud Gateway
service1/        — агрегирует ответ + вызов service2
service2/        — простой REST
infra/           — tempo.yaml, prometheus, provisioning Grafana
docker-compose.yml
Dockerfile       — сборка одного модуля через ARG MODULE
```

---
