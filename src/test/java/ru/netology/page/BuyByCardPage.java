package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class BuyByCardPage {
    private final SelenideElement monthCardExpired = $(By.cssSelector("[placeholder='08']"));
    private final SelenideElement yearCardExpired = $(By.cssSelector("[placeholder='22']"));
    private final SelenideElement ownerCard = $(By.xpath("//span[text()='Владелец']/following-sibling::span/input"));
    private final SelenideElement cvcCvvCard = $(By.cssSelector("[placeholder='999']"));
    private final SelenideElement cardNumber = $(By.cssSelector("[placeholder='0000 0000 0000 0000']"));
    private final SelenideElement buttonContinue = $(By.cssSelector("div:nth-of-type(4) .button__text"));
    private final SelenideElement popupSuccessfully = $(By.xpath("//div[text()='Операция одобрена Банком.']"));
    private final SelenideElement errorFieldNumberOfCard = $(By.xpath("//span[text()='Неверный формат']"));
    private final SelenideElement errorFieldMonth = $(By.xpath("//span[text()='Неверно указан срок действия карты']"));
    private final SelenideElement errorFieldYear = $(By.xpath("//span[text()='Истёк срок действия карты']"));
    private final SelenideElement errorFieldOwner = $(By.xpath("//span[text()='Поле обязательно для заполнения']"));

    public BuyByCardPage() {
        SelenideElement checkBuyByCard = $(By.xpath("//h3[text()='Оплата по карте']"));
        checkBuyByCard.shouldBe(Condition.visible);
    }



    public void successfullyBuyByCard() {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        popupSuccessfully.shouldBe(Condition.visible, Duration.ofSeconds(12));
    }

    public void canceledBuyByCard() {
        cardNumber.setValue(DataHelper.getCardNumberDeclined());
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void errorByFieldCardNumberBuyByCard() {
        cardNumber.setValue(DataHelper.getCardNumberFail());
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldNumberOfCard.shouldBe(Condition.visible);
    }

    public void errorByEmptyFieldCardNumberBuyByCard() {
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void byEmptyFieldMonthBuyByCard(String month) {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        monthCardExpired.setValue(month);
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void errorValueByFieldMonthBuyByCard() {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        yearCardExpired.setValue(DataHelper.generateValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldMonth.shouldBe(Condition.visible);
    }

    public void emptyFieldYearBuyByCard() {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
    }

    public void errorValueByFieldYearBuyByCard() {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        yearCardExpired.setValue(DataHelper.generateNotValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateValidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldYear.shouldBe(Condition.visible);
    }

    public void errorByEmptyFieldOwnerBuyByCard(String owner) {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        yearCardExpired.setValue(DataHelper.generateNotValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(owner);
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldOwner.shouldBe(Condition.visible);
    }

    public void errorFieldOwnerBuyByCard(String cardOwner) {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        yearCardExpired.setValue(DataHelper.generateNotValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(cardOwner);
        cvcCvvCard.setValue(DataHelper.generateCVC());
        buttonContinue.click();
        errorFieldOwner.shouldBe(Condition.visible);
    }

    public void errorByEmptyFieldCvcCvvBuyByCard() {
        cardNumber.setValue(DataHelper.getCardNumberApproved());
        yearCardExpired.setValue(DataHelper.generateNotValidYearCardExpired());
        monthCardExpired.setValue(DataHelper.generateInvalidMonthCardExpired());
        ownerCard.setValue(DataHelper.generateOwnerName());
        buttonContinue.click();
        errorFieldOwner.shouldBe(Condition.visible);
    }


}
