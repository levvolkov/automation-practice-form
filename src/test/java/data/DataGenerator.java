package data;

import com.github.javafaker.Faker;

import java.time.Year;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import static data.RegistrationData.*;


/**
 * Фабрика для генерации реалистичных тестовых данных с использованием {@link com.github.javafaker.Faker}.
 * <p>
 * Все методы потокобезопасны. Примеры использования:
 * <ul>
 *   <li>{@link RegistrationData} - заполнение тестовых данных</li>
 * </ul>
 */
public class DataGenerator {

    private static final Faker faker = new Faker();

    /** Справочник штатов и соответствующих городов. <p> Формат: {@code Map<Штат, List<Городов>>} */
    private static final Map<String, List<String>> STATE_CITIES_MAP = new ConcurrentHashMap<>();


    /** Генерирует имя.
     * @return Случайное имя (например: "John"). */
    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    /** Генерирует фамилию.
     * @return Случайная фамилия (например: "Doe"). */
    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    /** Генерирует адрес почты.
     * @return Случайная почта (например: "test@example.com"). */
    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    /** Генерирует пол студента.
     * @return Одно из значений: "Male", "Female", "Other". */
    public static String getRandomGenderOption() {
        return faker.options().option("Male", "Female", "Other");
    }

    /** Генерирует телефонный номер длиной {@value RegistrationData#PHONE_NUMBER_LENGTH} цифр.
     * @return Случайно сгенерированный номер телефона заданной длины. */
    public static String getRandomPhoneNumber() {
        return faker.phoneNumber().subscriberNumber(RegistrationData.PHONE_NUMBER_LENGTH);
    }

    /** Генерирует день с ведущим нулём в диапазоне
     * от {@value RegistrationData#MIN_DAY_IN_THE_MONTH} до {@value RegistrationData#MAX_DAY_IN_THE_MONTH}.
     * @return День в формате "dd". */
    public static String getRandomDayWithLeadingZero() {
        int day = ThreadLocalRandom.current()
                .nextInt(RegistrationData.MIN_DAY_IN_THE_MONTH, RegistrationData.MAX_DAY_IN_THE_MONTH);
        return String.format("%02d", day);
    }

    private static final List<String> MONTHS = Arrays.asList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );

    /** Генерирует месяц.
     * @return Случайный месяц. */
    public static String getRandomMonth() {
        return MONTHS.get(ThreadLocalRandom.current().nextInt(MONTHS.size()));
    }

    /** Генерирует год в диапазоне
     *  от {@value RegistrationData#MIN_AGE} до {@value RegistrationData#MAX_AGE} лет от текущего.
     * @return Случайный год. */
    public static String getRandomYear() {
        int currentYear = Year.now().getValue();
        return String.valueOf(ThreadLocalRandom.current().nextInt(
                currentYear - RegistrationData.MAX_AGE,
                currentYear - RegistrationData.MIN_AGE + 1
        ));
    }

    /** Генерирует случайный предмет.
     * @return Одно из значений: "Accounting", "Maths", "Arts", "Social Studies",
     * "Chemistry", "Computer Science", "Commerce", "Physics", "Economics". */
    public static String getRandomSubject() {
        return faker.options().option(
                "Accounting", "Maths", "Arts", "Social Studies",
                "Chemistry", "Computer Science", "Commerce", "Physics", "Economics");
    }

    /** Генерирует случайное хобби.
     * @return Одно из значений: "Sports", "Reading", "Music". */
    public static String getRandomHobbies() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    /** Генерирует случайное имя файла.
     * @return Одно из значений: {@value RegistrationData#FIRST_PICTURE},
     * {@value RegistrationData#SECOND_PICTURE}, {@value RegistrationData#THIRD_PICTURE}. */
    public static String getRandomPicture() {
        return faker.options().option(FIRST_PICTURE, SECOND_PICTURE, THIRD_PICTURE);
    }

    /** Генерирует адрес.
     * @return Случайный адрес (например: "850 Tromp Mall"). */
    public static String getRandomAddress() {
        return faker.address().streetAddress();
    }

    static {
        // Инициализация справочника штатов и городов
        STATE_CITIES_MAP.put("NCR", List.of("Delhi", "Gurgaon", "Noida"));
        STATE_CITIES_MAP.put("Uttar Pradesh", List.of("Agra", "Lucknow", "Merrut"));
        STATE_CITIES_MAP.put("Haryana", List.of("Karnal", "Panipat"));
        STATE_CITIES_MAP.put("Rajasthan", List.of("Jaipur", "Jaiselmer"));
    }

    /** Генерирует случайный штат.
     * @return Одно из значений: "NCR", "Uttar Pradesh", "Haryana", "Rajasthan". */
    public static String getRandomState() {
        return faker.options().option(STATE_CITIES_MAP.keySet().toArray(new String[0]));
    }

    /** Генерирует случайный город для указанного штата.
     * @param state Штат, для которого нужно выбрать город.
     * @return Название города.
     * @throws IllegalArgumentException если штат не найден. */
    public static String getRandomCityForState(String state) {
        List<String> cities = STATE_CITIES_MAP.get(state);
        if (cities == null || cities.isEmpty()) {
            throw new IllegalArgumentException("Unknown state: " + state);
        }
        return faker.options().option(cities.toArray(new String[0]));
    }
}