package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnyTest {

    @Test
    public void simpleTest() throws InterruptedException {
        System.err.println("Start simpleTest");
        Thread.sleep(100);
        System.err.println("End simpleTest");
        assert (2 == 2);
    }

    @Test
    public void simpleTest2() throws InterruptedException {
        Assertions.assertAll(
                () -> assertThat(1).withFailMessage("There are no messages").isGreaterThan(2),
                () -> assertThat(1).as("There are no messages").isGreaterThan(2)
        );
    }
}