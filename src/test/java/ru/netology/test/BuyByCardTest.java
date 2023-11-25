package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import ru.netology.page.GeneralPage;

import static com.codeborne.selenide.Selenide.open;

public class BuyByCardTest extends AbstractPageTest{


    @BeforeEach
    void openSetUp() {
        open("http://localhost:8080");
        var generalPage = new GeneralPage();
        page = generalPage.buyByCard();
    }

    @Override
    protected String getRequestTableName() {
        return "payment_entity";
    }
}
