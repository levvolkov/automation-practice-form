# Автоматизация тестирования формы регистрации
[![Java CI](https://github.com/levvolkov/automation-practice-form/actions/workflows/ci.yml/badge.svg)](https://github.com/levvolkov/automation-practice-form/actions)
[![Passed](https://img.shields.io/badge/dynamic/json?url=https://levvolkov.github.io/automation-practice-form/widgets/summary.json&query=statistic.passed&label=Passed&color=green)]()
[![Failed](https://img.shields.io/badge/dynamic/json?url=https://levvolkov.github.io/automation-practice-form/widgets/summary.json&query=statistic.failed&label=Failed&color=red)]()
[![Allure Report](https://img.shields.io/badge/Allure_Report-View-green.svg)](https://levvolkov.github.io/automation-practice-form)

## 📌 О проекте

Комплексное решение для автоматизированного тестирования формы регистрации на [demoqa.com/automation-practice-form](https://demoqa.com/automation-practice-form), включающее:

- **Полный цикл тестирования** всех элементов формы
- **Интеграция с CI/CD** для автозапуска тестов и публикации отчетов
- **Гибкую архитектуру** на основе Page Object Model

**Технические особенности:**
- **Автоматический запуск тестов** при событиях Git (push/PR)
- **Использование Selenium WebDriver** для эмуляции действий пользователя
- **Генерация отчетов в Allure** с детализацией каждого шага
- **Генерация случайных данных** для проверки валидации формы

## 🧪 Покрытие тестами

- [x] **Полное заполнение формы** + проверка данных в модальном окне
- [x] **Заполнение только обязательных полей** + проверка пустых значений
- [x] **Закрытие модального окна** после отправки формы
- [x] **Валидация всех полей**:
    - Текстовые поля (ФИО, email, адрес)
    - Радиокнопки (пол)
    - Датапикер (день/месяц/год)
    - Выпадающие списки (предметы, штат/город)
    - Чекбоксы (хобби)
    - Загрузка файла

## 🛠 Технологии и инструменты

**Технологии:**

[![Java](https://img.shields.io/badge/Java-11-%23ED8B00?logo=openjdk&logoColor=white)](https://java.com)
[![Gradle](https://img.shields.io/badge/Gradle-7.6-%2302303A?logo=gradle)](https://gradle.org)
[![Selenium](https://img.shields.io/badge/Selenium-4.18.1-%2343B02A?logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![JUnit5](https://img.shields.io/badge/JUnit-5.9.1-%2325A162?logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![JavaFaker](https://img.shields.io/badge/JavaFaker-1.0.2-%23DD0031?logo=java&logoColor=white)](https://github.com/DiUS/java-faker)
[![Allure](https://img.shields.io/badge/Allure-2.29.1-%23FF6A00?logo=testinglibrary&logoColor=white)](https://docs.qameta.io/allure/)

**Инструменты:**

[![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-✓-%23000000?logo=intellijidea)](https://jetbrains.com/idea)
[![DevTools](https://img.shields.io/badge/Chrome_DevTools-✓-2671E5?logo=google-chrome&logoColor=white)](https://developer.chrome.com/docs/devtools/)

## 📁 Структура проекта

```Copy
automation-practice-form/
├── src/
│   └── test/
│       ├── java/
│       │   ├── data/             # Генерация и хранение тестовых данных
│       │   ├── pages/            # Page Object для формы и модального окона
│       │   └── tests/            # Тестовые сценарии
│       └── resources/images/     # Тестовые изображения для загрузки
├── build.gradle                  # Зависимости для Selenium, JUnit, Allure, Java Faker
└── README.md
```

## 🚀 Процедура запуска автотестов
1. **Клонируйте и откройте проект в IDEA:**
```bash
git clone https://github.com/levvolkov/automation-practice-form.git
```

2. **Запустите автотесты:**
```bash
./gradlew clean test
```

3. **Генерация просмотр отчета**
```bash
allure serve allure-results
```

> ⚠️ Примечание:
> По умолчанию тесты запускаются в headless-режиме (без открытия браузера).
> Если вы хотите видеть, как выполняются тесты в реальном окне браузера, закомментируйте или удалите строку:
> ```Java
> options.addArguments("--headless=new");
> ```
> Пример настройки в классе FormTest:
> ```java
> @BeforeAll
> static void setupClass() {
>     WebDriverManager.chromedriver().setup();
>     ChromeOptions options = new ChromeOptions();
>     // options.addArguments("--headless=new"); // Отключить для визуального режима
>     options.addArguments("--disable-gpu");
>     options.addArguments("--no-sandbox");
>     options.addArguments("--disable-dev-shm-usage");
>     driver = new ChromeDriver(options);
> }
> ```