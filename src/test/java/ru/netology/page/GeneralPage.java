package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class GeneralPage {

    private final SelenideElement buyWithCardButton = $(byText("Купить"));
    private final SelenideElement buyWithCreditButton = $(byText("Купить в кредит"));


    public BuyByCreditPage buyByCredit() {
        buyWithCreditButton.click();
        return new BuyByCreditPage();
    }

    public BuyByCardPage buyByCard() {
        buyWithCardButton.click();
        return new BuyByCardPage();
    }
}
