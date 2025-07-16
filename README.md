# -Book-Accounting-Service
Тестовое задание для KODE

## Описание подхода к разработке
Разработка Book Accounting Service началась с продумывания структуры.
Код был разбит на определённые слои, каждый из которых выполняет свою роль:

- **Слой Models (`Author.java`, `Book.java`)**: Этот слой отвечает за создание сущностей, которые представляют таблицы в базе данных.
Сущности определяют структуру данных с использованием аннотаций JPA (например, `@Entity`, `@Id`, `@Column`), обеспечивает отображение объектов на таблицы H2 базы.


- **Слой Repositories (`AuthorRepository.java`, `BookRepository.java`)**: Этот слой предоставляет интерфейсы для взаимодействия с базой данных.
На основе Spring Data JPA репозитории расширяют `JpaRepository`, обеспечивая базовые операции CRUD (create, read, update, delete).
Это упрощает доступ к данным без написания сложных SQL-запросов.


- **Слой Services (`AuthorService.java`, `BookService.java`)**: Этот слой содержит бизнес-логику приложения. 
Сервисы реализуют методы для добавления, обновления и удаления данных, а также включают базовую валидацию (например, проверку пустых полей `name` и `title`).


- **Слой Controllers (`AuthorController.java`, `BookController.java`)**: Этот слой отвечает за обработку HTTP-запросов и формирование ответов. 
Контроллеры используют аннотации `@RestController` и `@RequestMapping` для определения эндпоинтов (GET, POST, PUT, DELETE), 
возвращая данные в формате JSON. Они также обрабатывают параметры (например, пагинацию через `page` и `size`) и маршрутизируют запросы к сервисам.

Для работы взята H2 in-memory база данных, настроенная в `application.properties`.

Swagger интегрирован через `OpenApiConfig.java` для автоматической генерации документации API - `http://localhost:8080/swagger-ui/index.html#/`, также далее представлены примеры запросов.
Unit-тесты (`AuthorControllerTest.java`, `BookControllerTest.java`) созданы с использованием `@WebMvcTest` и Mockito, проверяя каждый эндпоинт.

## Запросы

## Authors

- **Method**: `GET`
- **Endpoint**: `http://localhost:8080/authors/1`
- **Body**:
  ```json
  None

- **Method**: `POST`
- **Endpoint**: `http://localhost:8080/authors`
- **Body**:
  ```json
  {
    "name": "Лев Толстой",
    "birthYear": 1828
  }

- **Method**: `PUT`
- **Endpoint**: `http://localhost:8080/authors/1`
- **Body**:
  ```json
  {
    "name": "Лев Николаевич Толстой",
    "birthYear": 1828
  }

- **Method**: `DELETE`
- **Endpoint**: `http://localhost:8080/authors/1`
- **Body**:
  ```json
  None

- **Method**: `GET`
- **Endpoint**: `http://localhost:8080/authors?page=0&size=10`
- **Body**:
  ```json
  None

## Books

- **Method**: `GET`
- **Endpoint**: `http://localhost:8080/books/1`
- **Body**:
  ```json
  None

- **Method**: `POST`
- **Endpoint**: `http://localhost:8080/books`
- **Body**:
  ```json
  {
    "title": "Война и мир",
    "publicationYear": 1865,
    "genre": "Роман",
    "author": {"id": 1}
  }

- **Method**: `PUT`
- **Endpoint**: `http://localhost:8080/books/1`
- **Body**:
  ```json
  {
    "title": "Война и мир (обновлено)",
    "publicationYear": 1865,
    "genre": "Роман",
    "author": {"id": 1}
  }
  

- **Method**: `DELETE`
- **Endpoint**: `http://localhost:8080/books/1`
- **Body**:
  ```json
  None

- **Method**: `GET`
- **Endpoint**: `http://localhost:8080/books`
- **Body**:
  ```json
  None