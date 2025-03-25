package org.uma.api.utils;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private final Faker faker;
    private final Random random;

    public DataGenerator(Locale locale) {
        this.faker = new Faker(locale);
        this.random = new Random();
    }

    public DataGenerator() {
        this(Locale.getDefault());
    }

    public String generateFirstName() {
        return faker.name().firstName();
    }

    public String generateLastName() {
        return faker.name().lastName();
    }

    public String generateFullName() {
        return faker.name().fullName();
    }

    public String generateUserName() {
        return faker.name().username();
    }

    public String generateEmail() {
        return faker.internet().emailAddress();
    }

    public String generatePhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public String generateAddress() {
        return faker.address().fullAddress();
    }

    public String generateCity() {
        return faker.address().cityName();
    }

    public String generateState() {
        return faker.address().state();
    }

    public String generateZipCode() {
        return faker.address().zipCode();
    }

    public String generateCountry() {
        return faker.address().country();
    }

    public LocalDate generateLocalDate(int minYearsAgo, int maxYearsAgo) {
        int yearsAgo = random.nextInt(maxYearsAgo - minYearsAgo + 1) + minYearsAgo;
        return LocalDate.now().minusYears(yearsAgo).minusDays(random.nextInt(365));
    }

    public Date generateDate(int minYearsAgo, int maxYearsAgo) {
        return Date.from(generateLocalDate(minYearsAgo, maxYearsAgo).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public int generateRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public double generateRandomDouble(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    public boolean generateBoolean() {
        return random.nextBoolean();
    }

    public String generateCompany() {
        return faker.company().name();
    }

    public String generateJobTitle() {
        return faker.job().title();
    }

//    public String generateCreditCardNumber() {
//        return faker.finance().creditCardNumber();
//    }
//
//    public String generateCurrencyCode() {
//        return faker.finance().currency().code();
//    }
//
//    public String generateCurrencyName() {
//        return faker.finance().currency().name();
 //   }

    public String generateUuid() {
        return java.util.UUID.randomUUID().toString();
    }

    public String generateAnimalName() {
        return faker.animal().name();
    }

    public String generateFoodName() {
        return faker.food().dish();
    }

    public String generateColorName() {
        return faker.color().name();
    }

    public String generateBookTitle() {
        return faker.book().title();
    }
}