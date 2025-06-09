package pages;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ModalPage {
    private final WebDriverWait wait;

    private final By modalWindow = By.cssSelector(".modal-content");
    private final By closeButton = By.id("closeLargeModal");
    private static final String RESULT_ROW_XPATH = "//*[@class='table-responsive']//*[contains(text(), '%s')]/..";

    public ModalPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Проверяет результат в таблице модального окна
     * @param label Название поля
     * @param value Ожидаемое значение
     */
    public void verifyFieldHasValue(String label, String value) {
        Allure.step(label + ": \"" + value + "\"");
        By locator = By.xpath(String.format(RESULT_ROW_XPATH, label));
        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (!row.getText().contains(value)) {
            throw new AssertionError("Ожидаемый текст '" + value + "' не найден в строке: " + row.getText());
        }
    }

    /**
     * Проверяет, что в строке таблицы нет значения только название поля
     * @param label Название поля
     */
    public void verifyFieldIsEmpty(String label) {
        Allure.step(label + ":");
        By locator = By.xpath(String.format(RESULT_ROW_XPATH, label));
        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        if (!row.getText().trim().equals(label)) {
            throw new AssertionError("Строка должна быть пустой, но содержит: " + row.getText());
        }
    }

    public void closeModal() {
        Allure.step("Закрытие модального окна");
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
    }

    public boolean isModalClosed() {
        Allure.step("Проверка, что модальное окно закрыто");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(modalWindow));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}