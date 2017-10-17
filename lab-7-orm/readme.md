## Упражнение 7
### Использование ORM в Spring при работе с данными
#### Проект
_lab-7-orm_
#### Цели упражнения
Научиться использовать ORM, на примере JPA (Hibernate v.4) при помощи Spring
#### Описание
Spring предоставляет поддержку различных ORM с применением одного и того же
подхода. Использование ORM через Spring упрощает тестирование, обработку исключений,
управление ресурсами. Spring поддерживает следующие ORM: JPA, Hibernate, JDO, iBATIS
SQL Maps, и др…  
В данном примере используется описание Entity-классов через аннотации, с
использованием javax.persistence (JPA v.2.0 : JSR-317; JPA v.2.1 : JSR-338).
#### Задачи упражнения
1. Создать и сконфигурировать `LocalContainerEntityManagerFactoryBean` на
основе существующего `DataSource`. В качестве `@Entity` задать
`lab.model.Country`;
2. Создать mapping для класса `Country`;
3. В классе `CountryJpaDaoImpl` реализовать метод сохранения страны,
`save(Country country)`. Сохранить страну в базе. Проверить при помощи теста
`CountryDaoImplTest`, `testSaveCountry()`;
4. В классе `CountryJpaDaoImpl` реализовать метод получения списка всех стран, метод
`getAllCountries()`. Получить список всех стран. Проверить при помощи теста
`CountryDaoImplTest`, `testGetAllCountries()`;
5. В классе `CountryJpaDaoImpl` реализовать метод получения страны по названию.
Получить страну по названию. Проверить при помощи теста `CountryDaoImplTest`,
`testGetCountryByName(String name)`;
#### Подробное руководство.
##### Часть 1. Создание и конфигурация LocalContainerEntityManagerFactoryBean.
1. Откройте файл с конфигурацией `application-context.xml`;
2. Создайте бин emf, в качестве класса укажите для него
`LocalContainerEntityManagerFactoryBean`;
3. Задайте ему `dataSource`, `persistenceUnitName`,
`persistenceProviderClass`  
У вас должна получиться следующая конфигурация:  
```xml
<bean id="emf" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
  <property name="loadTimeWeaver">
    <bean class="org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver" />
  </property>
  <property name="dataSource" ref="dataSource"></property>
  <property name="persistenceUnitName" value="springframework.lab.orm.jpa" />
  <property name="persistenceProviderClass" value="org.hibernate.ejb.HibernatePersistence"/>
</bean>
```
Укажите в каких пакетах Spring Framework должен вести поиск компонент (`@Entity`,
`@Repository`, etc.). Для этого добавьте в `application-context.xml` следующую строку:
`<context:component-scan base-package="lab.model, lab.dao" />`
##### Часть 2. Создание mapping’а класса Country;
4. Откройте класс `Country`. Добавьте аннотации `@Entity` и `@Table(name =
"COUNTRY")` перед объявлением класса;
5. Добавьте аннотации
```java
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
```
перед заданием метода `getId()`;
6. Добавьте аннотацию `@Column` перед заданием метода `getName()`;
7. Добавьте аннотацию `@Column(name="code_name")` перед описанием метода
`getCodeName()`;
8. В итоге должно получиться код, содержащий следующие строки:
```java
@Entity
@Table(name = "COUNTRY")
public class Country implements Serializable {
  private int id;
  private String name;
  private String codeName;
  
  public Country() { }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getId() {
    return id;
  }
  
  public void setId(int id) {this.id = id;}
  
  @Column(name = "NAME")
  public String getName() {
    return name;
  }
  
  @Column(name = "CODE_NAME")
  public String getCodeName() {
    return codeName;
  }
}
```
##### Часть 3. Сохранение страны
9. Откройте класс `CountryJpaDaoImpl`. Зачем этот класс наследуется от
`AbstractJpaDao` и имплементирует интерфейс `CountryDao`?
10. Откуда и каким образом получается `EntityManagerFactory` (переменная emf)?
11. В методе `save` получите `EntityManager`, метод `emf.createEntityManager()`;
12. Вызовите у `entityManager` метод `persist()` в рамках программной транзакции,
передав ему сохраняемый объект в качестве параметра;
13. Проверьте правильность реализации при помощи теста `CountryDaoImplTest`,
`testSaveCountry()`;
##### Часть 4. Получение списка всех стран
14. Откройте метод `getAllCountries()` в классе `CountryJpaDaoImpl`;
15. Получите `EntityManager`, вызовите у него метод `createQuery`, передав ему в
качестве параметра строку запроса и `Country.class`;
16. Проверьте правильность реализации при помощи теста `CountryDaoImplTest`,
`testGetAllCountries()`;
17. У вас должен получиться код, близкий к этому:
```java
List<Country> countryList = null;
EntityManager em = emf.createEntityManager();
if (em != null) {
  countryList = em.createQuery("from Country", Country.class).getResultList();
  em.close();
}
return countryList;
```
##### Часть 5. Получение страны по названию
18. Откройте метод `getCountryByName(String name)` в классе `CountryJpaDaoImpl`;
19. Создайте запрос для поиска и передайте его в полученный `EntityManager` следующим
образом:
```java
Country country = em.createQuery("SELECT c FROM Country c WHERE c.name LIKE :name",
                                  Country.class).setParameter("name", name).getSingleResult();
```
20. Проверьте правильность реализации при помощи теста `CountryDaoImpTest`,
`testGetCountryByName()`;
21. В итоге, у вас должен получиться код, близкий к следующему:
```java
EntityManager em = emf.createEntityManager();
Country country = null;
if (em != null) {
  country = em.createQuery("SELECT c FROM Country c WHERE c.name LIKE :name",
                            Country.class).setParameter("name", name).getSingleResult();
  em.close();
}
return country;
```
Дополнительные вопросы:
22. Какие еще стратегии получения первичного ключа (PK) возможны для
сущностей? (`@GeneratedValue(strategy = GenerationType.AUTO)`)
23. Переведите корневой логгер в режим `DEBUG` (для этого отредактируйте файл
log4j.properties)
24. Поэксперементируйте с различными стратегиями получения PK. Какая между
ними разница?