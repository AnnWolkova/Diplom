package ru.netology.data;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;


public class DataHelper {
    private DataHelper() {
    }


    @org.jetbrains.annotations.NotNull
    public static String getCardNumber(String enterLastForDigitsOfCard) {
        if (enterLastForDigitsOfCard.equals("4441"))
            return "4444 4444 4444 4441";
        if (enterLastForDigitsOfCard.equals("4442"))
            return "4444 4444 4444 4442";
        else return "0000 0000 0000 000";
    }

    public static String generateValidMonthCardExpired() {
        ArrayList<String> months = new ArrayList<>(Arrays.
                asList("04", "05", "06", "07", "08", "09", "10", "11", "12"));
        Collections.shuffle(months);
        return months.get(1);
    }

    public static String generateInvalidMonthCardExpired() {
        ArrayList<String> months = new ArrayList<>(Arrays.
                asList("14", "13"));
        Collections.shuffle(months);
        return months.get(1);
    }

    public static String enterMonthExpiredCardManually(String enterCardExpired) {
        return enterCardExpired;
    }

    public static String generateValidYearCardExpired() {
        ArrayList<String> years = new ArrayList<>(Arrays.asList("21", "22", "23"));
        Collections.shuffle(years);
        return years.get(1);
    }

    public static String enterYearExpiredCardManually(String enterYearExpired) {
        return enterYearExpired;
    }

    public static String generateNotValidYearCardExpired() {
        ArrayList<String> years = new ArrayList<>(Arrays.asList("19", "20", "00", "99"));
        Collections.shuffle(years);
        return years.get(1);
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