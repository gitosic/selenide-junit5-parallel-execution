package org.example;

import com.codeborne.selenide.SelenideElement;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;
import static org.example.pages.TestPages.dataInTableCountOfColumns;
import static org.example.pages.TestPages.dataInTableCountOfRows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsefulMethods {

    public static void verificationDoubleRowsByGreaterThen(String testData) {
        dataInTableCountOfRows().get(0).shouldBe(visible);
        int rows = dataInTableCountOfRows().size();
        for (int i = 0; i < rows; i++) {
            List<String> list = new ArrayList<>();
            for (SelenideElement element : dataInTableCountOfColumns(i)) {
                list.add(element.getText());
                $(element).scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}").click();
            }

            assertThat(list).anySatisfy(element -> {
                Double elementUIToCompare = null;
                Double elementTestDataToCompare = null;
                try {
                    NumberFormat format = NumberFormat.getInstance(Locale.US);
                    Number elementFromUI = format.parse(element);
                    Number testDataFromCase = format.parse(testData);
                    elementUIToCompare = elementFromUI.doubleValue();
                    elementTestDataToCompare = testDataFromCase.doubleValue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                assertThat(elementUIToCompare).isGreaterThan(elementTestDataToCompare);
            });
        }
    }

    public static void verificationRowForDate(String testData, String operator) {
        dataInTableCountOfRows().get(0).shouldBe(visible);
        int rows = dataInTableCountOfRows().size();

        for (int i = 0; i < rows; i++) {
            List<Integer> list = new ArrayList<>();
            List<String> listStrings = new ArrayList<>();
            for (SelenideElement element : dataInTableCountOfColumns(i)) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date1 = sdf.parse(element.getText());
                    Date date2 = sdf.parse(testData);
                    int result = date1.compareTo(date2);
                    listStrings.add(element.getText());
                    list.add(result);
                } catch (ParseException e) {
                    e.getStackTrace();
                }
            }
            switch (operator) {
                case "=":
                    assertThat(list).as("No items are the same. The list expects " + testData + " actually "
                            + "the list contains: " + listStrings).contains(0);
                    break;
                case ">=":
                    assertTrue(list.contains(1) || list.contains(0),
                            "The list expects element greater or equal than " + testData + " actually the list " +
                                    "contains: " + listStrings);
                    break;
                case "<=":
                    assertTrue(list.contains(-1) || list.contains(0),
                            "The list expects element less or equal than " + testData + " actually the list " +
                                    "contains: " + listStrings);
                    break;
            }
        }
    }
}
