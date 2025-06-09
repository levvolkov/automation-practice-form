package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;


public class FormPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstName = By.cssSelector("#firstName");
    private final By lastName = By.cssSelector("#lastName");
    private final By userEmail = By.cssSelector("#userEmail");
    private final By phoneNumber = By.cssSelector("#userNumber");
    private final By dateOfBirthInput = By.cssSelector("#dateOfBirthInput");
    private final By yearDropdown = By.cssSelector(".react-datepicker__year-select");
    private final By monthDropdown = By.cssSelector(".react-datepicker__month-select");
    private static final String DAY_PICKER_PATTERN = ".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)";
    private final By subjectsInput = By.cssSelector("#subjectsInput");
    private final By subjectsMenu = By.cssSelector(".subjects-auto-complete__menu");
    private final By uploadFileInput = By.cssSelector("#uploadPicture");
    private final By currentAddress = By.cssSelector("#currentAddress");
    private final By selectStateDropdown = By.cssSelector("#state");
    private final By stateInput = By.cssSelector("#react-select-3-input");
    private final By selectCityDropdown = By.cssSelector("#city");
    private final By cityInput = By.cssSelector("#react-select-4-input");
    private final By submitButton = By.cssSelector("#submit");
    private final By dialogWindow = By.cssSelector(".modal-dialog");

    public FormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

//    private void scrollToElement(By locator) {
//        WebElement element = driver.findElement(locator);
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center', inline: 'nearest' });",
//                element
//        );
//    }

    public void bannerDrop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Удаление рекламного банера по ID
        js.executeScript("document.getElementById('fixedban').remove();");
        // Удаление всех footer элементов
        js.executeScript("document.querySelector('footer').remove();");
    }

    public FormPage fillFirstName(String name) {
        Allure.step("Ввести имя: \"" + name + "\"");
        driver.findElement(firstName).sendKeys(name);
        return this;
    }

    public FormPage fillLastName(String surname) {
        Allure.step("Ввести фамилию: \"" + surname + "\"");
        driver.findElement(lastName).sendKeys(surname);
        return this;
    }

    public FormPage fillUserEmail(String email) {
        Allure.step("Ввести почту: \"" + email + "\"");
        driver.findElement(userEmail).sendKeys(email);
        return this;
    }

    public FormPage selectGender(String gender) {
        Allure.step("Ввести пол: \"" + gender + "\"");
        driver.findElement(By.xpath("//label[contains(text(), '" + gender + "')]")).click();
        return this;
    }

    public FormPage fillPhoneNumber(String lengthOfNumbers) {
        Allure.step("Ввести номер телефона: \"" + lengthOfNumbers + "\"");
        driver.findElement(phoneNumber).sendKeys(lengthOfNumbers);
        return this;
    }

    public FormPage chooseDate(String day, String month, String year) {
        Allure.step("Ввести дату рождения: \"" + day + " " + month + " " + year + "\"");
        driver.findElement(dateOfBirthInput).click();
        new Select(driver.findElement(yearDropdown)).selectByVisibleText(year);
        new Select(driver.findElement(monthDropdown)).selectByVisibleText(month);
        String dayLocator = String.format(DAY_PICKER_PATTERN, day);
        driver.findElement(By.cssSelector(dayLocator)).click();
        return this;
    }

    public FormPage addSubjects(String... subjects) {
        Allure.step("Выбрать учебный предмет: \"" + String.join("\", \"", subjects) + "\"");
        for (String subject : subjects) {
            WebElement input = driver.findElement(subjectsInput);
            input.clear();
            input.sendKeys(subject);
            wait.until(ExpectedConditions.visibilityOfElementLocated(subjectsMenu));
            input.sendKeys(Keys.ENTER);
        }
        return this;
    }

    public FormPage selectHobbies(String hobbies) {
        Allure.step("Выбрать хобби: \"" + hobbies + "\"");
        WebElement hobbiesInput = driver.findElement(By.xpath("//label[text()='" + hobbies + "']"));
        hobbiesInput.click();
        return this;
    }

    public FormPage uploadFile(String filePath) {
        Allure.step("Загрузить файл: \"" + filePath + "\"");
        String absolutePath = Objects.requireNonNull(getClass().getClassLoader().getResource(filePath)).getFile();
        driver.findElement(uploadFileInput).sendKeys(absolutePath);
        return this;
    }

    public FormPage enterCurrentAddress(String address) {
        Allure.step("Ввести адрес: \"" + address + "\"");
        driver.findElement(currentAddress).sendKeys(address);
        return this;
    }

    public FormPage selectStateAndCity(String state, String city) {
        Allure.step("Выбрать штат: \"" + state + "\" и город \"" + city + "\"");
        driver.findElement(selectStateDropdown).click();
        WebElement fieldState = driver.findElement(stateInput);
        fieldState.sendKeys(state);
        fieldState.sendKeys(Keys.ENTER);
        driver.findElement(selectCityDropdown).click();
        WebElement fieldCity = driver.findElement(cityInput);
        fieldCity.sendKeys(city);
        fieldCity.sendKeys(Keys.ENTER);
        return this;
    }

    public void submitForm() {
        Allure.step("Отправить форму и получить модальное окно с результатами");
        driver.findElement(submitButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dialogWindow));
        new ModalPage(driver);
    }
}