import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class JavaPro {
    public static void main(String[] args) {
        // Задание 1
        LocalDate birthday = LocalDate.of(1985, Month.JANUARY, 10);
        System.out.println("Дата рождения: " + birthday.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));

        // Задание 2
        DayOfWeek dayOfWeek = birthday.getDayOfWeek();
        System.out.println("Дата " + birthday.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + " - это " + dayOfWeek);

        // Задание 3
        LocalDate tenYearsAgo = birthday.minus(10, ChronoUnit.YEARS);
        System.out.println("Дата, уменьшенная на 10 лет: " + tenYearsAgo.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));

        // Задание 4
        Instant instant = Instant.parse("2022-12-19T06:55:30.00Z");
        System.out.println("Объект Instant: " + instant);

        // Задание 5
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Pacific/Midway"));
        System.out.println("ZonedDateTime для 'Pacific/Midway': " + zonedDateTime.format(DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm:ss z")));
    }
}
