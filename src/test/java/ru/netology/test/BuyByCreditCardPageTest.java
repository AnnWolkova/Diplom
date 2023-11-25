package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import ru.netology.page.GeneralPage;

import static com.codeborne.selenide.Selenide.open;

public class BuyByCreditCardPageTest extends AbstractPageTest
{

    @BeforeEach
    void openSetUp() {
        open("http://localhost:8080");
        GeneralPage generalPage = new GeneralPage();
        page = generalPage.buyByCredit();
    }

    @Override
    protected String getRequestTableName() {
        return "credit_request_entity";
    }


//    @Test
//    @SneakyThrows
//    void printTableCredit() {
//        printTableValues("credit_request_entity");
//    }
//
//    @SneakyThrows
//    void printTableValues(String table) {
//        Connection conn = DbMethods.getConnection();
//        Statement stat = conn.createStatement();
//        ResultSet rs = stat.executeQuery("select * from " + table);
//        ResultSetMetaData md = rs.getMetaData();
//        for(int i=0; i<md.getColumnCount(); i++)
//        {
//            System.out.print(md.getColumnName(i+1) + " ");
//        }
//        System.out.println();
//
//
//        while(rs.next())
//        {
//            for(int i=0; i<md.getColumnCount(); i++)
//            {
//                String val = rs.getString(i + 1);
//                System.out.print(val + " ");
//            }
//            System.out.println();
//        }
//    }

}