package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.page.AbstractPage;
import ru.netology.page.GeneralPage;
import ru.netology.sql.DbMethods;

import java.io.IOException;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;

public class BuyByCreditCardPageTest {

    protected AbstractPage page;

    @BeforeAll
    static void setUp() throws IOException {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1440x900";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void openSetUp() {
        open(System.getProperty("app.url"));
        GeneralPage generalPage = new GeneralPage();
        page = generalPage.buyByCredit();
    }

    protected String getRequestTableName() {
        return "credit_request_entity";
    }

    @Test
    @DisplayName("Проверка заголовка страницы")
    void shouldBeTitle() {
        page.assertPageTitle(page.getExpectedPageTitle());
    }

    @Test
    @DisplayName("Проверка доступности карты")
    void shouldBuyByCreditCardDb() {
        String tableName = getRequestTableName();
        int numRows = DbMethods.getResultSetRowCount(tableName);
        page.successfullyBuyByCredit();

        Assertions.assertEquals(numRows + 1, DbMethods.getResultSetRowCount(tableName), "Не добавилась запись в таблицу " + tableName);

        String status = Objects.requireNonNull(DbMethods.getStatusForCredit());
        Assertions.assertEquals("APPROVED", status);
    }

    @Test
    @DisplayName("Операция одобрена банком")
    void shouldBuyByCreditCardView() {
        page.successfullyBuyByCredit();
        page.assertBankMessage("Операция одобрена Банком.", "Отсутствует сообщение с подтверждением успешной банковской операции");
    }

    @Test
    void shouldCanceledDb() {
        String tableName = getRequestTableName();
        int numRows = DbMethods.getResultSetRowCount(tableName);
        page.canceledBuyByCredit();
        Assertions.assertEquals(numRows + 1, DbMethods.getResultSetRowCount(tableName), "Не добавилась запись в таблицу " + tableName);

        String status = Objects.requireNonNull(DbMethods.getStatusForCredit());
        Assertions.assertEquals("DECLINED", status);
    }

    @Test
    void shouldCanceledView() {
        page.canceledBuyByCredit();
        page.assertBankMessage("Ошибка! Банк отказал в проведении операции.", "Отсутствует сообщение об отказе банковской операции");
    }

    @Test
    void shouldErrorByFieldNumberOfCard() {
        page.errorByFieldCardNumberBuyByCredit();
        page.assertAlertMessage("Номер карты", "Неверный формат");
    }

    @Test
    void shouldErrorByEmptyFieldNumberOfCard() {
        page.errorByEmptyFieldCardNumberBuyByCredit();
        page.assertAlertMessage("Номер карты", "Неверный формат");
    }

    @Test
    void shouldCheckByErrorFieldOwner() {
        page.errorByEmptyFieldOwnerBuyByCredit("00");
        page.assertAlertMessage("Владелец", "Поле обязательно для заполнения");
    }

    @Test
    void shouldCheckByEmptyFieldOwner() {
        page.errorByEmptyFieldOwnerBuyByCredit("");
        page.assertAlertMessage("Владелец", "Поле обязательно для заполнения");
    }

    @Test
    void shouldErrorByEmptyFieldYear() {
        page.emptyFieldYearBuyByCredit("");
        page.assertAlertMessage("Год", "Неверный формат");
    }

    @Test
    void shouldErrorByIncorrectValueFieldYear() {
        page.errorValueByFieldYearBuyByCredit();
        page.assertAlertMessage("Год", "Истёк срок действия карты");
    }

    @Test
    void shouldErrorByEmptyFieldMonth() {
        page.byEmptyFieldMonthBuyByCredit();
        page.assertAlertMessage("Месяц", "Неверный формат");

    }

    @Test
    void shouldErrorByFieldMonth() {
        page.errorValueByFieldMonthBuyByCredit();
        page.assertAlertMessage("Месяц", "Неверно указан срок действия карты");
    }

    @Test
    void shouldErrorByEmptyFieldCvvCvv() {
        page.errorByEmptyFieldCvcCvvBuyByCredit("");
        page.assertAlertMessage("CVC/CVV", "Неверный формат");
    }
}