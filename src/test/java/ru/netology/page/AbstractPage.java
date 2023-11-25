package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.TimeoutException;
import org.openqa.selenium.By;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractPage {
    private final SelenideElement monthCardExpired = $(By.cssSelector("[placeholder='08']"));
    private final SelenideElement yearCardExpired = $(By.cssSelector("[placeholder='22']"));
    private final SelenideElement ownerCard = $(By.xpath("//span[text()='" + "Владелец" + "']/following-sibling::span/input"));
    private final SelenideElement cvcCvvCard = $(By.cssSelector("[placeholder='999']"));
    private final SelenideElement cardNumber = $(By.cssSelector("[placeholder='0000 0000 0000 0000']"));
    private final SelenideElement buttonContinue = $(By.cssSelector("div:nth-of-type(4) .button__text"));

    public abstract String getExpectedPageTitle();

    public void assertPageTitle(String pageTitle) {
        SelenideElement checkBuyByCard = $(By.xpath("//h3[text()='" + pageTitle + "']"));
        try {
            checkBuyByCard.shouldBe(Condition.visible);
        } catch (Throwable e) {
            throw new AssertionError("Не найден заголовок '" + pageTitle + "'", e);
        }
    }

    public void successfullyBuyByCredit() {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void canceledBuyByCredit() {
        cardNumber.setValue(DataHelper.getCardNumberDeclined());
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void errorByFieldCardNumberBuyByCredit() {
        cardNumber.setValue(DataHelper.getCardNumberFail());
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void errorByEmptyFieldCardNumberBuyByCredit() {
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void byEmptyFieldMonthBuyByCredit() {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void errorValueByFieldMonthBuyByCredit() {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void emptyFieldYearBuyByCredit(String yearExpired) {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        yearCardExpired.setValue(yearExpired);
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void errorValueByFieldYearBuyByCredit() {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        yearCardExpired.setValue(DataHelper.generateNotValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void errorByEmptyFieldOwnerBuyByCredit(String ownerName) {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(ownerName);
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void errorByEmptyFieldCvcCvvBuyByCredit(String cvcCvv) {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        yearCardExpired.setValue(DataHelper.generateNotValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(cvcCvv);
        buttonContinue.click();
    }

    public void assertBankMessage(String expectedMessage, String errorMessage) {
        try {
            $(By.xpath("//div[text()='" + expectedMessage + "']")).shouldBe(Condition.visible, Duration.ofSeconds(30));
        } catch (Exception e) {
            throw new AssertionError(errorMessage, e);
        }
    }

    public boolean isAlert(String fieldName, String errorText) {
        try {
            $(By.xpath("//span[text() = '" + fieldName + "']//following::span[text() = '" + errorText + "']"))
                    .shouldBe(Condition.visible, Duration.ofSeconds(30));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void assertAlertMessage(String fieldName, String expectedErrorMessage) {
        String message = "У поля '" + fieldName + "' должна появиться подпись '" + expectedErrorMessage + "'";
        boolean alert;
        try {
            alert = isAlert(fieldName, expectedErrorMessage);
        } catch (Throwable e) {
            throw new AssertionError(message, e);
        }

        assertTrue(alert, message);
    }
}
