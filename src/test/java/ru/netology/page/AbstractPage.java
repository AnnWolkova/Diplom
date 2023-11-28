package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.jetbrains.annotations.NotNull;
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
        setData(
                DataHelper.getCardNumberApproved(),
                DataHelper.generateValidYearCardExpired(),
                DataHelper.generateValidMonthCardExpired(),
                DataHelper.generateOwnerName(),
                DataHelper.generateCVC()
        );
        buttonContinue.click();
    }

    public void canceledBuyByCredit() {
        setData(
                DataHelper.getCardNumberDeclined(),
                DataHelper.generateValidYearCardExpired(),
                DataHelper.generateValidMonthCardExpired(),
                DataHelper.generateOwnerName(),
                DataHelper.generateCVC()
        );
        buttonContinue.click();
    }

    public void errorByFieldCardNumberBuyByCredit() {
        setData(
                DataHelper.getCardNumberFail(),
                DataHelper.generateValidYearCardExpired(),
                DataHelper.generateValidMonthCardExpired(),
                DataHelper.generateOwnerName(),
                DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void errorByEmptyFieldCardNumberBuyByCredit() {
        setData(
                null,
                DataHelper.generateValidYearCardExpired(),
                DataHelper.generateValidMonthCardExpired(),
                DataHelper.generateOwnerName(),
                DataHelper.generateCVC()
        );
        buttonContinue.click();
    }

    public void byEmptyFieldMonthBuyByCredit() {
        setData(
                DataHelper.getCardNumberApproved(),
                DataHelper.generateValidYearCardExpired(),
                null,
                DataHelper.generateOwnerName(),
                DataHelper.generateCVC()
        );
        buttonContinue.click();
    }

    public void errorValueByFieldMonthBuyByCredit() {
        setData(
                DataHelper.getCardNumberApproved(),
                DataHelper.generateValidYearCardExpired(),
                DataHelper.generateInvalidMonthCardExpired(),
                DataHelper.generateOwnerName(),
                DataHelper.generateCVC()
        );
        buttonContinue.click();
    }

    public void emptyFieldYearBuyByCredit(String yearExpired) {
        setData(
                DataHelper.getCardNumberApproved(),
                yearExpired,
                DataHelper.generateInvalidMonthCardExpired(),
                DataHelper.generateOwnerName(),
                DataHelper.generateCVC()
        );
        buttonContinue.click();
    }

    public void errorValueByFieldYearBuyByCredit() {
        setData(
                DataHelper.getCardNumberApproved(),
                DataHelper.generateNotValidYearCardExpired(),
                DataHelper.generateValidMonthCardExpired(),
                DataHelper.generateOwnerName(),
                DataHelper.generateCVC()
        );
        buttonContinue.click();
    }

    public void errorByEmptyFieldOwnerBuyByCredit(String ownerName) {
        setData(
                DataHelper.getCardNumberApproved(),
                DataHelper.generateValidYearCardExpired(),
                DataHelper.generateValidMonthCardExpired(),
                ownerName,
                DataHelper.generateCVC()
        );
        buttonContinue.click();
    }

    public void errorByEmptyFieldCvcCvvBuyByCredit(String cvcCvv) {
        setData(
                DataHelper.getCardNumberApproved(),
                DataHelper.generateNotValidYearCardExpired(),
                DataHelper.generateInvalidMonthCardExpired(),
                DataHelper.generateOwnerName(),
                cvcCvv
        );
        buttonContinue.click();
    }

    private void setData(@NotNull String cardNumber, String year, String month, String ownerName, String cvcCvv) {
        if(cardNumber!=null) this.cardNumber.setValue(cardNumber);
        if(year!=null) yearCardExpired.setValue(year);
        if(month!=null) monthCardExpired.setValue(month);
        if(ownerName!=null) ownerCard.setValue(ownerName);
        if(cvcCvv!=null) cvcCvvCard.setValue(cvcCvv);
    }

    public void assertBankMessage(String expectedMessage, String errorMessage) {
        $(By.xpath("//div[text()='" + expectedMessage + "']")).shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    public void assertAlert(String fieldName, String errorText) {
        $(By.xpath("//span[text() = '" + fieldName + "']//following::span[text() = '" + errorText + "']"))
                .shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    public void assertAlertMessage(String fieldName, String expectedErrorMessage) {
        assertAlert(fieldName, expectedErrorMessage);
    }
}
