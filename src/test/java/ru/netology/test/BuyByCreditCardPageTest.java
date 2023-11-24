package ru.netology.test;

import com.codeborne.selenide.Configuration;
//import lombok.val;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.DbUtils;
import org.junit.jupiter.api.*;
import org.testng.annotations.DataProvider;
import ru.netology.page.BuyByCreditPage;
import ru.netology.page.GeneralPage;
import ru.netology.sql.DbMethods;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
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

        try(InputStream fis = BuyByCreditCardPageTest.class.getResourceAsStream("/db.properties"))
        {
            Properties properties = new Properties();
            properties.load(fis);
            for (Object key : properties.keySet()) {
                String value = properties.getProperty((String) key);
                System.setProperty((String) key, value);
            }
        }
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @BeforeEach
    void openSetUp() {
        open("http://localhost:8080");
    }



    @Test
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

    @Test
    void shouldCanceled() {
        int numRows = getResultSetRowCountForCredit();
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.canceledBuyByCredit();
        String status = Objects.requireNonNull(DbMethods.getStatusForCredit()).getStatus();
        Assertions.assertEquals("DECLINED", status);
        Assertions.assertEquals(numRows + 1, getResultSetRowCountForCredit());
    }

    @Test
    void shouldErrorByFieldNumberOfCard() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByFieldCardNumberBuyByCredit();
    }

    @Test
    void shouldErrorByEmptyFieldNumberOfCard() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByEmptyFieldCardNumberBuyByCredit();
        assertTrue(BuyByCreditPage.isAlert("Номер карты", "Неверный формат"));
    }


    @Test
    void shouldErrorByEmptyFieldOwner() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByEmptyFieldOwnerBuyByCredit("00");
    }

    @Test
    void shouldCheckByEmptyFieldOwner() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorFieldOwnerBuyByCredit("");
        BuyByCreditPage.isAlert("Владелец", "Поле обязательно для заполнения");
    }

    @Test
    void shouldErrorByEmptyFieldYear() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.emptyFieldYearBuyByCredit("");
        assertTrue(BuyByCreditPage.isAlert("Год", "Неверный формат"));
    }

    @Test
    void shouldErrorByIncorrectValueFieldYear() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorValueByFieldYearBuyByCredit();
    }

    @Test
    void shouldErrorByEmptyFieldMonth() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.byEmptyFieldMonthBuyByCredit();
        assertTrue(BuyByCreditPage.isAlert("Месяц", "Неверный формат"));

    }

    @Test
    void shouldErrorByFieldMonth() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorValueByFieldMonthBuyByCredit();
    }

    @Test
    void shouldErrorByEmptyFieldCvvCvv() {
        GeneralPage generalPage = new GeneralPage();
        BuyByCreditPage buyByCreditPage = generalPage.buyByCredit();
        buyByCreditPage.errorByEmptyFieldCvcCvvBuyByCredit("");
        assertTrue(BuyByCreditPage.isAlert("CVC/CVV", "Неверный формат"));
    }

    @Test
    @SneakyThrows
    void printTableCredit() {
        printTableValues("credit_request_entity");
    }

    @SneakyThrows
    void printTableValues(String table) {
        Connection conn = DbMethods.getConnection();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select * from " + table);
        ResultSetMetaData md = rs.getMetaData();
        for(int i=0; i<md.getColumnCount(); i++)
        {
            System.out.print(md.getColumnName(i+1) + " ");
        }
        System.out.println();


        while(rs.next())
        {
            for(int i=0; i<md.getColumnCount(); i++)
            {
                String val = rs.getString(i + 1);
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

}