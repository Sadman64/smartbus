# Smart Bus System

Spring Boot REST API для управления микроклиматом умного автобуса.

## Технологии
- Java 21
- Spring Boot 3.5
- PostgreSQL
- JWT аутентификация
- Swagger UI

## Запуск
1. Установи PostgreSQL и создай базу данных `smartbus`
2. Настрой `application.properties`
3. Запусти `SmartbusApplication`
4. Swagger: http://localhost:8080/swagger-ui/index.html

## API
- `POST /api/auth/register` — регистрация
- `POST /api/auth/login` — вход
- `GET /api/buses` — список автобусов
- `POST /api/sensors/{busId}` — данные с датчика
- `GET /api/sensors/cabin/status?busId=1` — статус салона