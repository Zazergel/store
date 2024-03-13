<div class="header" markdown="1" align="center">
</div>
<h1 align="center">STORE</h1>
  <p align="center">
  The homework project for <a href="https://t1.ru/internship/item/otkrytaya-shkola-dlya-java-razrabotchikov/">open school JAVA by T1</a>
  <br/><br/>
    <a href="https://github.com/Zazergel/store/issues">Report Bug</a> *
    <a href="https://github.com/Zazergel/store/issues">Request Feature</a>
  </p>
  <div class="header" markdown="1" align="center">

  ![Downloads](https://img.shields.io/github/downloads/Zazergel/store/total) 
  ![Contributors](https://img.shields.io/github/contributors/Zazergel/store?color=dark-green) 
  ![Forks](https://img.shields.io/github/forks/Zazergel/store?style=social) 
  ![Issues](https://img.shields.io/github/issues/Zazergel/store) 
</div>



## Микросервис-источник данных (Supplier Service)

### Описание
Микросервис-источник данных (Supplier Service) предоставляет информацию о продуктах, категориях и комментариях. Реализован с использованием Spring Boot и Spring JPA.

### API методы

#### Продукты
- `POST /products`: Создать новый продукт.
- `GET /products`: Получить список всех продуктов.
- `GET /products/search`: Поиск продуктов по названию, описанию, категории, максимальной цене, с возможностью комбинации параметров и указанием сортировки.
- `GET /products/{id}`: Получить информацию о продукте по его идентификатору.
- `PUT /products/{id}`: Обновить информацию о продукте.
- `DELETE /products/{id}`: Удалить продукт по его идентификатору.
- `POST /products/{id}/comment`: Создать комментарий к продукту.

#### Категории
- `POST /categories`: Создать новую категорию.
- `GET /categories`: Получить список всех категорий.
- `GET /categories/{id}`: Получить информацию о категории по ее идентификатору.
- `PUT /categories/{id}`: Обновить информацию о категории.
- `DELETE /categories/{id}`: Удалить категорию по ее идентификатору.

## Микросервис-потребитель данных (Consumer Service)

### Описание
Микросервис-потребитель данных (Consumer Service) обрабатывает информацию о продуктах, категориях и комментариях, используя RestTemplate для взаимодействия с API микросервиса-источника данных.

### Функциональность
- Получение списка всех продуктов и категорий и вывод информации о них.
- Добавление нового продукта с указанием id категории.
- Обновление информации о продукте.
- Удаление продукта по id.
- Фильтрация продуктов по различным критериям (цена, категория и т. д.).
- Поиск продуктов по их названию или описанию.
- Пагинация для списка продуктов.
- Валидация данных перед отправкой запросов на сервер.

#### Дополнительно
- Реализована загрузка тестовых данных в классе TestDataLoader.
- Протестировать API продукта можно с помощью POSTMAN-коллекции в директории postman.

## Установка и запуск
Убедитесь, что у вас установлен Docker актуальной версии.
1. Клонируйте репозиторий: `git clone https://github.com/Zazergel/store.git`
2. Перейдите в корневой каталог проекта.
3. Запустите приложение с помощью docker-compose.yml файла. 

## Authors

 **[Zazergel](https://github.com/Zazergel/)**
