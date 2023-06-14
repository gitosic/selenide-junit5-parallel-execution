package ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.helpers.DateFormatUtils;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnyTest {

    @Test
    public void simpleTest() throws InterruptedException {
        System.err.println("Start simpleTest");
        Thread.sleep(100);
        System.err.println("End simpleTest");
        assert (2 == 2);
    }

    @Test
    public void testInRangeDates() {
        String testDataExpected = "2023-04-05;2023-04-07";
        String[] partsOfDates = testDataExpected.split(";");
        LocalDate dateFrom = DateFormatUtils.getISOFormattedDate(partsOfDates[0]);
        LocalDate dateTo = DateFormatUtils.getISOFormattedDate(partsOfDates[1]);
        System.out.println("dateFrom = " + dateFrom);
        System.out.println("dateTo = " + dateTo);

        String testDataActually = "2023-04-06";
        LocalDate dateFromUi = DateFormatUtils.getISOFormattedDate(testDataActually);
        int resultForFrom = dateFromUi.compareTo(dateFrom);
        int resultForTo = dateFromUi.compareTo(dateTo);

        assertTrue(resultForFrom == 0 || resultForFrom == 1 && resultForTo == -1 || resultForTo == 0,
                "The list expects element greater or equals than " + dateFrom + " and less or equals than "
                        + dateTo + ". Actually the column contains: " + dateFromUi);
    }

    @Test
    public void simpleTest2() throws InterruptedException {
        Assertions.assertAll(
                () -> assertThat(1).withFailMessage("There are no messages").isGreaterThan(2),
                () -> assertThat(1).as("There are no messages").isGreaterThan(2)
        );
    }

    @Test
    void testCucumber() {
        System.err.println("testCucumber");
        Assertions.assertEquals(1, 1);
    }

    @Test
    void assertNotNullTest() {
        System.err.println("Test - assertNotNull");
        String text = "bla-bla-bla";
        assertNotNull(text, "It must not be null");
    }

}