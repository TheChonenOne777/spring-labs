## Упражнение 4
### Разработка простейшего приложения.
#### Проект
_lab-4-core_
#### Цели упражнения
* Научиться создавать простейшее приложение с использованием Spring;
* Научиться использовать на практике основные приемы внедрения зависимостей
и связывания бинов при помощи Spring IoC контейнера;
* Научиться описывать и создавать контекст приложения;
* Научиться использовать автоматическое связывание;
#### Описание
Spring предоставляет возможность декларативного внедрения зависимостей между
классами и создания общего контекста приложения. Spring позволяет объявлять в качестве
бинов обычные java-классы (POJO). Существует возможность автоматического связывания
бинов, например, по типу или по имени.
#### Задачи упражнения
1. Описать и создать контекст приложения для существующих модельных классов
`UsualPerson`, `Country` и `Contact`;
2. Задать все значения полей всех классов так, чтобы тест `SimpleAppTest` успешно
выполнялся;
3. Установить все необходимые зависимости любым подходящим способом, также в
соответствии в тестом `SimpleAppTest`;
4. Создать контекст приложения;
5. Реализовать ситуацию: Человек, который проживает в конкретной стране и у него есть
несколько контактов. Задать все начальные значения для всех параметров и вывести
всю информацию об этом человеке. Для проверки использовать тест
`SimpleAppTest`;
#### Подробное руководство
##### Часть 1. Создание контекста приложения
1. Откройте проект lab-4-core;
2. Откройте файл {lab-4-core}src/test/resources/application-context.xml. В нем
уже есть начальное описание бинов country и person;
3. Откройте тест `SimpleAppTest`, метод `getExpectedPerson()`. Каких описаний
не хватает в существующем xml-файле для соответствия эталонному классу?
##### Часть 2. Задание значений полей
4. Задайте поля `height` и `isProgrammer` для класса `UsualPerson`;
5. Это делается в теге `<property name=… value=… >` внутри тега `<bean>`. Простые
`property` (`String`, `int`, `boolean` и тп) задаются значениями (`value=`), их
имена задаются атрибутом `name=`:  
```xml
<property name="height" value="1.78"/>
<property name="isProgrammer" value="true"/>
```
6. Задайте список контактов `Contacts`. Для этого в теге `property` с `name=contacts`
создается тег `list`, а внутри него теги `value`, в каждом из которых задается один
контакт
```xml
<property name="contacts">
  <list>
    <value>asd@asd.ru</value>
    <value>+7-234-456-67-89</value>
  </list>
</property>
```
##### Часть 3. Внедрение зависимостей
7. Кроме простых `property` необходимо также задать ссылки на другие классы. Какие
есть способы это сделать?
8. Один из способов связать бины между собой – указать явно ссылку на нужный бин
`<property name=… ref=…>`. Каковы достоинства и недостатки этого подхода?
9. Другой способ связывания – автоматически (autowire, не забудьте добавить
`<context:annotation-config/>`) для бина, например, по типу или по имени.
Каковы достоинства и недостатки этого подхода?
##### Часть 4. Создание контекста приложения
10. Откройте метод `@Before setUp()`.
11. В нем есть переменная типа `AbstractApplicationContext`. Ей присваивается
значение `ClassPathXmlApplicationContext`, параметром которого является
путь на диске к файлу с описанием контекста;
12. Чем эта декларация отличается от декларации в предыдущем примере?
##### Часть 5. Реализация тестовой ситуации
13. Откройте тест `SimpleAppTest`, метод `testInitPerson()`;
14. Получить бин, отвечающий за класс `UsualPerson` из контекста.
`context.getBean(имя бина)`. Обратите внимание, что этот метод вернет объект
типа `Object`. Его необходимо привести к типу `UsualPerson`;
15. Обратите внимание на альтернативные возможности получения бинов из контекста.
Для этого зайдите в документацию к классу `AbstractApplicationContext`: `{SPRING3-
HOME}/docs/javadoc-api/index.html` и изучите его API;
16. Вызовите метод `toString()` у объекта у только что полученного бина;
17. Выполните тест. Если все сделано правильно, то тест выполнится без ошибок, а в
консоль выведется вся информация о человеке;
##### Часть 6. Использование Spring TestContext Framework
18. Откройте класс `SpringTCFAppTest`;
19. Сравните его с предыдущим тестом (`SimpleAppTest`). В чем принципиальное отличие
этих классов?
20. К какой библиотеке принадлежит аннотация `@RunWith` ?
21. Изучите документацию к аннотации `@ContextConfiguration` (см. 10.3.5.2 `Context
management`, Spring Reference v.3.1.1). Какие еще варианты, указания контекста
приложения, она поддерживает?