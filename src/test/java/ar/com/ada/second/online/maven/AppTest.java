package ar.com.ada.second.online.maven;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void shouldAnswerWithTrueFail() {
        assertTrue(5 > 1);
    }
}
