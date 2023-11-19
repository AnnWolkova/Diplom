package ru.netology.test;

import com.codeborne.selenide.Configuration;
//import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.page.BuyByCreditPage;
import ru.netology.page.GeneralPage;
import ru.netology.sql.DbMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.sql.DbMethods.getResultSetRowCountForCredit;

public class BuyByCreditCardPageTest {

    @BeforeAll
    static void setUp() throws IOException {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1440x900";

        try(InputStream fis = BuyByCreditCardPageTest.class.getResourceAsStream("/application.properties"))
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
    @DisplayName("Проверка кредитной карты")
    void shouldBuyByCreditCard() {
        int numRows = getResultSetRowCountForCredit();
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.successfullyBuyByCredit();
        String status = Objects.requireNonNull(DbMethods.getStatusForCredit()).getStatus();
        Assertions.assertEquals("APPROVED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCountForCredit());
    }

    @Test //2
    void shouldCanceled() {
        int numRows = getResultSetRowCountForCredit();
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.canceledBuyByCredit();
        String status = Objects.requireNonNull(DbMethods.getStatusForCredit()).getStatus();
        Assertions.assertEquals("DECLINED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCountForCredit());
    }

    @Test //3
    void shouldErrorByFieldNumberOfCard() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByFieldCardNumberBuyByCredit();
    }

    @Test //4
    void shouldErrorByEmptyFieldNumberOfCard() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByEmptyFieldCardNumberBuyByCredit();
        assertTrue(BuyByCreditPage.isAlert("Номер карты", "Неверный формат"));
    }


    @Test //5
    void shouldErrorByEmptyFieldOwner() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByEmptyFieldOwnerBuyByCredit("00");
    }

    @Test //6
    void shouldCheckByEmptyFieldOwner() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorFieldOwnerBuyByCredit("");
        BuyByCreditPage.isAlert("Владелец", "Поле обязательно для заполнения");
    }

    @Test //7
    void shouldErrorByEmptyFieldYear() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.emptyFieldYearBuyByCredit("");
        assertTrue(BuyByCreditPage.isAlert("Год", "Неверный формат"));
    }

    @Test //8
    void shouldErrorByIncorrectValueFieldYear() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorValueByFieldYearBuyByCredit();
    }

    @Test //9
    void shouldErrorByEmptyFieldMonth() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.byEmptyFieldMonthBuyByCredit();
        assertTrue(BuyByCreditPage.isAlert("Месяц", "Неверный формат"));

    }

    @Test //10
    void shouldErrorByFieldMonth() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorValueByFieldMonthBuyByCredit();
    }

    @Test //11
    void shouldErrorByEmptyFieldCvvCvv() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByEmptyFieldCvcCvvBuyByCredit("");
        assertTrue(BuyByCreditPage.isAlert("CVC/CVV", "Неверный формат"));
    }


}