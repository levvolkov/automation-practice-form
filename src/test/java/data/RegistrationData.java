package data;

/**
 * Контейнер тестовых данных для формы регистрации.
 * <p>
 * Все данные генерируются автоматически при загрузке класса через методы:
 * {@link DataGenerator}
 */
public class RegistrationData {

    // =============================================
    // 1. Константы для настройки генерации данных
    // =============================================

    /** Длина генерируемого телефонного номера (цифр) */
    public static final int PHONE_NUMBER_LENGTH = 10;

    /** Границы дней месяца для генерации даты [1-29] */
    public static final int MIN_DAY_IN_THE_MONTH = 1, MAX_DAY_IN_THE_MONTH = 29;

    /** Границы для генерации года рождения от текущего [100-15] */
    public static final int MAX_AGE = 100, MIN_AGE = 15;

    /** Имя файла изображения в формате ".png" (должен находиться в {@code src/test/resources/images/}) */
    public static final String FIRST_PICTURE = "control.png";

    /** Имя файла изображения в формате ".jpg" (должен находиться в {@code src/test/resources/images/}) */
    public static final String SECOND_PICTURE = "qa.jpg";

    /** Имя файла изображения в формате ".webp" (должен находиться в {@code src/test/resources/images/}) */
    public static final String THIRD_PICTURE = "qa_testing.webp";


    // =============================================
    // 2. Сгенерированные тестовые данные
    // =============================================

    /** Случайное имя. <p> Метод генерации: {@link DataGenerator#getRandomFirstName()} */
    public static String firstName = DataGenerator.getRandomFirstName();

    /** Случайная фамилия. <p> Метод генерации: {@link DataGenerator#getRandomLastName()} */
    public static String lastName = DataGenerator.getRandomLastName();

    /** Случайная почта. <p> Метод генерации: {@link DataGenerator#getRandomEmail()} */
    public static String email = DataGenerator.getRandomEmail();

    /** Случайный пол. <p> Метод генерации: {@link DataGenerator#getRandomGenderOption()} */
    public static String gender = DataGenerator.getRandomGenderOption();

    /** Случайный номер телефона. <p> Метод генерации: {@link DataGenerator#getRandomPhoneNumber()} */
    public static String mobilePhone = DataGenerator.getRandomPhoneNumber();

    /** Случайный день рождения. <p> Метод генерации: {@link DataGenerator#getRandomDayWithLeadingZero()} */
    public static String dayOfBirth = DataGenerator.getRandomDayWithLeadingZero();

    /** Случайный месяц рождения. <p> Метод генерации: {@link DataGenerator#getRandomMonth()} */
    public static String monthOfBirth = DataGenerator.getRandomMonth();

    /** Случайный год рождения. <p> Метод генерации: {@link DataGenerator#getRandomYear()} */
    public static String yearOfBirth = DataGenerator.getRandomYear();

    /** Случайный предмет из доступных. <p> Метод генерации: {@link DataGenerator#getRandomSubject()} */
    public static String subject = DataGenerator.getRandomSubject();

    /** Случайное хобби из доступных. <p> Метод генерации: {@link DataGenerator#getRandomHobbies()} */
    public static String hobbies = DataGenerator.getRandomHobbies();

    /** Случайный формат изображения. <p> Метод генерации: {@link DataGenerator#getRandomPicture()} */
    public static String picture = DataGenerator.getRandomPicture();

    /** Случайный адрес. <p> Метод генерации: {@link DataGenerator#getRandomAddress()} */
    public static String address = DataGenerator.getRandomAddress();

    /** Случайный штат из доступных. <p> Метод генерации: {@link DataGenerator#getRandomState()} */
    public static String state = DataGenerator.getRandomState();

    /** Случайный город в соответствии с выбранным штатом. <p> Метод генерации: {@link DataGenerator#getRandomCityForState(String)} */
    public static String city = DataGenerator.getRandomCityForState(state);
}