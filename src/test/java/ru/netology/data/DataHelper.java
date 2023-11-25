package ru.netology.data;

import com.github.javafaker.Faker;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.*;


public class DataHelper {
    private DataHelper() {
    }

    @NotNull
    public static String getCardNumberApproved() {
        return "4444 4444 4444 4441";
    }

    @NotNull
    public static String getCardNumberDeclined() {
        return "4444 4444 4444 4442";
    }

    @NotNull
    public static String getCardNumberDeclined2() {
        return "4444 4444 4444 4443";
    }

    @NotNull
    public static String getCardNumberFail() {
        return "0000 0000 0000 000";
    }

    public static String generateValidMonthCardExpired() {
        return "12";
    }

    public static String generateInvalidMonthCardExpired() {
        ArrayList<String> months = new ArrayList<>(Arrays.
                asList("14", "13"));
        Collections.shuffle(months);
        return months.get(1);
    }

    public static String generateValidYearCardExpired() {
        int year = LocalDate.now().getYear()%100;
        LinkedList<String> years = new LinkedList<>(Arrays.asList(
                String.valueOf(year),
                String.valueOf(year+1),
                String.valueOf(year+2)
        ));
        Collections.shuffle(years);
        return years.getFirst();
    }

    public static String generateNotValidYearCardExpired() {
        int year = LocalDate.now().getYear()%100;
        LinkedList<String> years = new LinkedList<>(Arrays.asList(
                String.valueOf(year-1),
                String.valueOf(year-2),
                String.valueOf(year-3)
        ));
        Collections.shuffle(years);
        return years.getFirst();
    }

    public static String generateOwnerName() {
        Faker faker = new Faker(new Locale("ru_RU"));
        return faker.name().fullName();
    }

    public static String enterOwnerManually(String enterOwner) {
        return enterOwner;
    }

    public static String generateCVC() {
        Faker faker = new Faker();
        String num = faker.code().imei().substring(0, 3);
        return num;
    }


}