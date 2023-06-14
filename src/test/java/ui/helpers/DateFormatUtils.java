package ui.helpers;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class DateFormatUtils {
    public static final String FORMAT_DATE_ISO = "yyyy-MM-dd";
    public static final DateTimeFormatter FORMATTER_DATE_ISO = DateTimeFormatter.ofPattern(FORMAT_DATE_ISO);

    public static long datesDifferenceInDays(long currentTimestamp, LocalDate valueDate) {
        LocalDate evictionDate = DateFormatUtils.convertFromTimestamp(ZoneOffset.UTC, currentTimestamp).toLocalDate();
        return datesDifferenceInDays(evictionDate, valueDate);
    }

    public static long datesDifferenceInDays(long currentTimestamp, LocalDateTime valueDate) {
        LocalDateTime evictionDate = DateFormatUtils.convertFromTimestamp(ZoneOffset.UTC, currentTimestamp);
        return datesDifferenceInDays(evictionDate, valueDate);
    }

    public static long datesDifferenceInDays(LocalDate currentDateTime, LocalDate valueDate) {
        return ChronoUnit.DAYS.between(valueDate, currentDateTime);
    }

    public static long datesDifferenceInDays(LocalDateTime currentDateTime, LocalDateTime valueDate) {
        return ChronoUnit.DAYS.between(valueDate, currentDateTime);
    }

    public static LocalDateTime convertFromTimestamp(ZoneId zoneId, long timeStamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), zoneId);
    }

    public static LocalDateTime convertFromMicros(ZoneId zoneId, long timeStampMicros) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(TimeUnit.MICROSECONDS.toMillis(timeStampMicros)), zoneId);
    }

    public static LocalDate getISOFormattedDate(String testData) throws DateTimeException {
        return LocalDate.parse(testData, FORMATTER_DATE_ISO);
    }

    public static LocalDate toLocalDate(long timeStamp) {
        return convertFromTimestamp(ZoneOffset.UTC, timeStamp).toLocalDate();
    }

}