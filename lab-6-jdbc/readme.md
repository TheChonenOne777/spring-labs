## Упражнение 6
### Использование JDBC в Spring при работе с данными
#### Проект
_lab-6-jdbc_
#### Цели упражнения
Научиться использовать JDBC совместно со Spring. Научиться выполнять основные
операции (SELECT, INSERT, UPDATE) JDBC через Spring.
#### Описание
Spring предоставляет вспомогательное API для работы с JDBC.
Для работы с JDBC через Spring необходимо настроить DataSource. Это делается в
`application-context.xml`.
В качестве базы данных используется HSQLDB.
#### Задачи упражнения
1. Получить и напечатать полный список всех стран, используя `CountryDao`. Все ли поля
класса `Country` корректно заполняются? Для проверки использовать тест `JdbcTest`,
метод `testCountryList`;
2. Исправить `CountryRowMapper` так, чтобы заполнялись все поля `Country`;
3. Получить и напечатать полный список стран, названия которых начинаются с буквы A.
Для проверки использовать тест `JdbcTest`, метод `testCountryListStartsWithA`.
4. Изменить название какой-либо страны в базе. Для проверки использовать тест
`JdbcTest`, метод `testCountryChange` (необходимо изменить эталонную страну 
в этом тесте);
#### Подробное руководство
##### Часть 1. Получение полного списка стран
1. Откройте проект _lab-6-jdbc_;
2. Откройте директорию {lab-6-jdbc}/lib. Какие библиотеки добавились в этом
проекте? Почему?
3. Откройте файл с конфигурацией `application-context.xml`. Проанализируйте
конфигурации всех бинов;
4. Откройте класс `CountryDao`. Реализуйте в нем метод `getCountryList()`, так
чтобы он возвращал полный список всех стран. В качестве примера используйте уже
существующие методы;
5. Для этого необходимо получить `JdbcTemplate` и у него вызвать метод
`query(String s, RowMapper rowMapper)`;
6. В качестве sql-запроса можно использовать `GET_ALL_COUNTRIES_SQL`;
7. В качестве RowMapper’а – `COUNTRY_ROW_MAPPER`;
8. Откройте тест `JdbcTest`, запустите на выполнение метод `testCountryList`;
9. Правильно ли выполнился тест? Почему?
##### Часть2. Исправление RowMapper’a
10. Откройте класс `CountryRowMapper`. Что необходимо сделать, чтобы заполнялись
все поля `Country`?
11. В метод `mapRow` необходимо задать `codeName` для `Country`, по аналогии с `id` и
`name`;
12. Выполните тест `JdbcTest`, метод `testCountryList` еще раз;
##### Часть 3 Получение списка стран, начинающихся с буквы A
13. Откройте класс `CountryDao`, метод `getCountryListStartWith()`. Что такое
`NamedParameterJdbcTemplate`? Чем он отличается от `JdbcTemplate`?
14. Откройте тест `JdbcTest`, метод `testCountryListStartsWithA`. Правильно ли
выполняется этот тест?
15. Попробуйте теперь закоментировать аннотацию `@DirtiesContext` к некоторым
тестам (в разных комбинациях). Как теперь выполняются тесты? Почему?
#### Часть 4 Изменение названия страны
16. Откройте класс `CountryDao`;
17. Реализуйте метод для изменения названия страны по известному `codeName` –
`updateCountryName`. В качестве sql-запроса можно использовать
`UPDATE_COUNTRY_NAME_SQL_1`, `UPDATE_COUNTRY_NAME_SQL_2`.
Должно получиться:
```java
getJdbcTemplate().execute(
UPDATE_COUNTRY_NAME_SQL_1 + newCountryName + "'" +
UPDATE_COUNTRY_NAME_SQL_2 + codeName + "'");
```
18. Для проверки в тесте `JdbcTest`, метод `testCountryChange` создайте страну с
новым названием и известным `codeName`.
19. Как можно изменить `application-context.xml` и `JdbcTest` так, чтобы не было
необходимости вызывать `countryDao.loadCountries()`; в `@Before` методе?
В чем преимущество этого подхода? (Дополнительная информация для ответа на вопрос:
п.13.8 Embedded database support, Spring Reference);