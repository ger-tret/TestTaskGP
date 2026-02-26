# Hotel Management Service
RESTful API для управления информацией об отелях. Разработано в соответствии с техническим заданием (Java 21, Spring Boot 3, MapStruct, Liquibase).

## Технологии:
Java 21
Spring Boot 3.2.2
Spring Data JPA (H2 Database)
Liquibase (Миграции БД)
MapStruct (Маппинг DTO/Entity)
Lombok
SpringDoc OpenAPI (Swagger UI)
JUnit 5 / AssertJ / MockMvc (Тестирование)

## Запуск приложения:
Приложение запускается на порту 8092
mvn spring-boot:run


## Документация API
После запуска документация и интерактивный UI для тестирования доступны по адресу:
Swagger UI: http://localhost:8092/swagger-ui.html
OpenAPI Spec (JSON): http://localhost:8092/api-docs

## Доступ к базе данных (H2 Console)
Для просмотра таблиц в реальном времени:
URL: http://localhost:8092/h2-console
JDBC URL: jdbc:h2:mem:hoteldb

### User: test_user

### Password: testing

## Примеры использования (cURL)
### 1. Создание нового отеля (POST)
Возвращает статус 201 Created и заголовок Location.
curl -X POST http://localhost:8092/property-view/hotels \
-H "Content-Type: application/json" \
-d '{
    "name": "DoubleTree by Hilton Minsk",
    "description": "Luxurious rooms with stunning views...",
    "brand": "Hilton",
    "address": {
        "houseNumber": 9,
        "street": "Pobediteley Avenue",
        "city": "Minsk",
        "country": "Belarus",
        "postCode": "220004"
    },
    "contacts": {
        "phone": "+375 17 309-80-00",
        "email": "doubletreeminsk.info@hilton.com"
    },
    "arrivalTime": {
        "checkIn": "14:00",
        "checkOut": "12:00"
    }
}'

### 2. Получение списка всех отелей (GET)
curl -X GET http://localhost:8092/property-view/hotels


### 3. Поиск отелей (GET)
Поиск регистронезависимый. Пустые параметры игнорируются.
curl -X GET "http://localhost:8092/property-view/search?city=minsk&brand=hilton"


### 4. Обновление списка Amenities (PUT)
Возвращает полную обновленную информацию об отеле.
curl -X PUT http://localhost:8092/property-view/hotels/1/amenities \
-H "Content-Type: application/json" \
-d '["Free parking", "Free WiFi", "Fitness center"]'


### 5. Получение гистограммы (GET)
Параметры: brand, city, country, amenities.
curl -X GET http://localhost:8092/property-view/histogram/city


### Запуск тестов
mvn test


## Рекомендации для автоматизированной проверки
Убедитесь, что переменная среды JAVA_HOME указывает на JDK 21.
Все методы имеют общий префикс /property-view.
База данных инициализируется через Liquibase автоматически при старте.
