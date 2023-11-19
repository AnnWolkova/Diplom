package ru.netology.test;

import com.codeborne.selenide.Configuration;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.BuyByCreditPage;
import ru.netology.page.GeneralPage;
import ru.netology.sql.DbMethods;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.sql.DbMethods.getResultSetRowCountForCard;

public class BuyByCardTest {
    @BeforeAll
    static void setUp() throws IOException {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1440x900";

        try(InputStream fis = BuyByCardTest.class.getResourceAsStream("/application.properties"))
        {
            Properties properties = new Properties();
            properties.load(fis);
            for (Object key : properties.keySet()) {
                String value = properties.getProperty((String) key);
                System.setProperty((String) key, value);
            }
        }
    }

    @BeforeEach
    void openSetUp() {
        open("http://localhost:8080");
    }

    @Test //1
    void shouldBuyByCreditCard() {
        int numRows = getResultSetRowCountForCard();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCard();
        buyByCreditPage.successfullyBuyByCard();
        String status = Objects.requireNonNull(DbMethods.getStatusForCard()).getStatus();
        Assertions.assertEquals("APPROVED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCountForCard());
    }

    @Test  //2
    void shouldCanceled() {
        int numRows = getResultSetRowCountForCard();
        val generalPage = new GeneralPage();
        val buyByCreditPage = generalPage.buyByCard();
        buyByCreditPage.canceledBuyByCard();
        String status = Objects.requireNonNull(DbMethods.getStatusForCard()).getStatus();
        Assertions.assertEquals("DECLINED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCountForCard());
    }

    @Test //3
    void shouldErrorByFieldNumberOfCard() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.errorByFieldCardNumberBuyByCard();
    }

    @Test  //4
    void shouldErrorByEmptyFieldNumberOfCard() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.errorByEmptyFieldCardNumberBuyByCard();
        assertTrue(BuyByCreditPage.isAlert("Номер карты", "Неверный формат"));
    }


    @Test //5
    void shouldErrorByFieldOwner() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.errorByEmptyFieldOwnerBuyByCard("строчные буквы не заглавные");
    }

    @Test //6
    void shouldCheckByEmptyFieldOwner() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.errorFieldOwnerBuyByCard("");
        BuyByCreditPage.isAlert("Владелец", "Поле обязательно для заполнения");
    }

    @Test //7
    void shouldErrorByEmptyFieldYear() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.emptyFieldYearBuyByCard();
        assertTrue(BuyByCreditPage.isAlert("Год", "Неверный формат"));
    }

    @Test  //8
    void shouldErrorByIncorrectValueFieldYear() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.errorValueByFieldYearBuyByCard();
    }

    @Test //9
    void shouldErrorByEmptyFieldMonth() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.byEmptyFieldMonthBuyByCard("");
        assertTrue(BuyByCreditPage.isAlert("Месяц", "Неверный формат"));

    }

    @Test //10
    void shouldErrorByFieldMonth() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.errorValueByFieldMonthBuyByCard();
    }

    @Test //11
    void shouldErrorByEmptyFieldCvvCvv() {
        val generalPage = new GeneralPage();
        val buyByCard = generalPage.buyByCard();
        buyByCard.errorByEmptyFieldCvcCvvBuyByCard();
        assertTrue(BuyByCreditPage.isAlert("CVC/CVV", "Неверный формат"));
    }

}
