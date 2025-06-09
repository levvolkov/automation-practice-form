package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.FormPage;
import pages.ModalPage;

import static data.RegistrationData.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic(value = "«Форма практики»")
public class FormTest {
    private static WebDriver driver;
    private FormPage formPage;
    private ModalPage modalPage;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
    }

    @AfterAll
    static void teardownClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    void setupTest() {
        driver.get("https://demoqa.com/automation-practice-form");
        formPage = new FormPage(driver);
        formPage.bannerDrop();
        modalPage = new ModalPage(driver);
    }

    @Test
    @DisplayName("Отправить форму со всеми полями и проверить данные в модальном окне")
    void submitFormWithAllFieldsAndVerifyModal() {
        formPage.fillFirstName(firstName)
                .fillLastName(lastName)
                .fillUserEmail(email)
                .selectGender(gender)
                .fillPhoneNumber(mobilePhone)
                .chooseDate(dayOfBirth, monthOfBirth, yearOfBirth)
                .addSubjects(subject)
                .selectHobbies(hobbies)
                .uploadFile(picture)
                .enterCurrentAddress(address)
                .selectStateAndCity(state, city)
                .submitForm();

        assertAll(
                () -> modalPage.verifyFieldHasValue("Student Name", firstName + " " + lastName),
                () -> modalPage.verifyFieldHasValue("Student Email", email),
                () -> modalPage.verifyFieldHasValue("Gender", gender),
                () -> modalPage.verifyFieldHasValue("Mobile", mobilePhone),
                () -> modalPage.verifyFieldHasValue("Date of Birth",
                        String.format("%s %s,%s", dayOfBirth, monthOfBirth, yearOfBirth)),
                () -> modalPage.verifyFieldHasValue("Subjects", subject),
                () -> modalPage.verifyFieldHasValue("Hobbies", hobbies),
                () -> modalPage.verifyFieldHasValue("Picture", picture),
                () -> modalPage.verifyFieldHasValue("Address", address),
                () -> modalPage.verifyFieldHasValue("State and City", state + " " + city)
        );
    }

    @Test
    @DisplayName("Отправить форму с обязательными полями и проверить данные в модальном окне")
    void submitFormWithRequiredFieldsAndVerifyModal() {
        formPage.fillFirstName(firstName)
                .fillLastName(lastName)
                .selectGender(gender)
                .fillPhoneNumber(mobilePhone)
                .chooseDate(dayOfBirth, monthOfBirth, yearOfBirth)
                .submitForm();

        assertAll(
                () -> modalPage.verifyFieldHasValue("Student Name", firstName + " " + lastName),
                () -> modalPage.verifyFieldIsEmpty("Student Email"),
                () -> modalPage.verifyFieldHasValue("Gender", gender),
                () -> modalPage.verifyFieldHasValue("Mobile", mobilePhone),
                () -> modalPage.verifyFieldHasValue("Date of Birth",
                        String.format("%s %s,%s", dayOfBirth, monthOfBirth, yearOfBirth)),
                () -> modalPage.verifyFieldIsEmpty("Subjects"),
                () -> modalPage.verifyFieldIsEmpty("Hobbies"),
                () -> modalPage.verifyFieldIsEmpty("Picture"),
                () -> modalPage.verifyFieldIsEmpty("Address"),
                () -> modalPage.verifyFieldIsEmpty("State and City")
        );
    }

    @Test
    @DisplayName("Система должна закрывать модальное окно")
    void shouldCloseTheModalWindow() {
        submitFormWithAllFieldsAndVerifyModal();
        modalPage.closeModal();
        assertTrue(modalPage.isModalClosed(), "Модальное окно не закрылось");
    }
}