package utils;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class TestDataFactory {

    private static final Faker faker = new Faker(new Locale("vi"));

    public static String randomFirstName() {
        return faker.name().firstName();
    }

    public static String randomLastName() {
        return faker.name().lastName();
    }

    public static String randomPostalCode() {
        return faker.number().digits(5);
    }

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    public static Map<String, String> randomCheckoutData() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("firstName", randomFirstName());
        data.put("lastName", randomLastName());
        data.put("postalCode", randomPostalCode());
        return data;
    }
}