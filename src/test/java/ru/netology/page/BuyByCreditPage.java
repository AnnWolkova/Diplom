package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.TimeoutException;
import org.openqa.selenium.By;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BuyByCreditPage {

    private final SelenideElement monthCardExpired = $(By.cssSelector("[placeholder='08']"));
    private final SelenideElement yearCardExpired = $(By.cssSelector("[placeholder='22']"));
    private final SelenideElement ownerCard = $(By.xpath("//span[text()='Владелец']/following-sibling::span/input"));
    private final SelenideElement cvcCvvCard = $(By.cssSelector("[placeholder='999']"));
    private final SelenideElement cardNumber = $(By.cssSelector("[placeholder='0000 0000 0000 0000']"));
    private final SelenideElement buttonContinue = $(By.cssSelector("div:nth-of-type(4) .button__text"));
    private final SelenideElement popupSuccessfully = $(By.xpath("//div[text()='Операция одобрена Банком.']"));
    private final SelenideElement popupErrorCanceledByBank = $(By.xpath("//div[text()='Ошибка! Банк отказал в проведении операции.']"));
    private final SelenideElement errorFieldNumberOfCard = $(By.xpath("//span[text()='Неверный формат']"));
    private final SelenideElement errorFieldMonth = $(By.xpath("//span[text()='Неверно указан срок действия карты']"));
    private final SelenideElement errorFieldYear = $(By.xpath("//span[text()='Истёк срок действия карты']"));
    private final SelenideElement errorFieldOwner = $(By.xpath("//span[text()='Поле обязательно для заполнения']"));


    public BuyByCreditPage() {
        SelenideElement checkBuyByCredit = $(byText("Кредит по данным карты"));
        checkBuyByCredit.shouldBe(Condition.visible);
    }


    public static boolean isAlert(String fieldName, String errorText) {
        try {
            $(By.xpath("//span[text() = '" + fieldName + "']//following::span[text() = '" + errorText + "']"))
                    .shouldBe(Condition.visible, Duration.ofSeconds(10));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    public void successfullyBuyByCredit() {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        popupSuccessfully.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void canceledBuyByCredit() {
        cardNumber.setValue(DataHelper.getCardNumber("4442"));
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void errorByFieldCardNumberBuyByCredit() {
        cardNumber.setValue(DataHelper.getCardNumber("1111"));
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldNumberOfCard.shouldBe(Condition.visible);
    }

    public void errorByEmptyFieldCardNumberBuyByCredit() {
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void byEmptyFieldMonthBuyByCredit() {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void errorValueByFieldMonthBuyByCredit() {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldMonth.shouldBe(Condition.visible);
    }

    public void emptyFieldYearBuyByCredit(String yearExpired) {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(yearExpired);
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void errorValueByFieldYearBuyByCredit() {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateNotValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldYear.shouldBe(Condition.visible);
    }

    public void errorByEmptyFieldOwnerBuyByCredit(String ownerName) {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateNotValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(ownerName);
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldOwner.shouldBe(Condition.visible);
    }

    public void errorFieldOwnerBuyByCredit(String cardOwner) {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateNotValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(cardOwner);
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldOwner.shouldBe(Condition.visible);
    }

    public void errorByEmptyFieldCvcCvvBuyByCredit(String cvcCvv) {
        cardNumber.setValue(DataHelper.getCardNumber("4441"));
        yearCardExpired.setValue(DataHelper.generateNotValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(cvcCvv);
        buttonContinue.click();
        errorFieldOwner.shouldBe(Condition.visible);
    }


}
