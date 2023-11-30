package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringProcessorTest {

    @Test
    public void isStrongPassword() {
        StringProcessor processor = new StringProcessor();
        assertTrue(processor.isStrongPassword("Strong@123"));
        assertFalse(processor.isStrongPassword("Weakpass12"));
        assertFalse(processor.isStrongPassword("Short@1"));
        assertFalse(processor.isStrongPassword("Alllowercase@1"));
        assertFalse(processor.isStrongPassword("ALLUPPERCASE@1"));
        assertFalse(processor.isStrongPassword("Missing1Special"));
    }

    @Test
    public void calculateDigits() {
        StringProcessor processor = new StringProcessor();
        assertEquals(3, processor.calculateDigits("Hello123"));
        assertEquals(0, processor.calculateDigits("NoDigits"));
        assertEquals(4, processor.calculateDigits("4567"));
        assertEquals(1, processor.calculateDigits("Digit$1"));
        assertEquals(6, processor.calculateDigits("123 456"));
    }

    @Test
    public void calculateWords() {
        StringProcessor processor = new StringProcessor();
        assertEquals(4, processor.calculateWords("This is a sentence."));
        assertEquals(0, processor.calculateWords(""));
        assertEquals(1, processor.calculateWords("SingleWord"));
        assertEquals(2, processor.calculateWords("Multiple   Spaces"));
        assertEquals(3, processor.calculateWords(" Word1 Word2 Word3 "));
    }

    @Test
    public void calculateExpression() {
        StringProcessor processor = new StringProcessor();
//        assertEquals(13.0, processor.calculateExpression("3+5*2"), 0.001);
//        assertEquals(12.0, processor.calculateExpression("(4-2)*6"), 0.001);
//        assertEquals(2.0, processor.calculateExpression("10/(2+3)"), 0.001);
        assertEquals(8.0, processor.calculateExpression("2^3"), 0.001);
//        assertEquals(6.95, processor.calculateExpression("1.5*2.5+3.2"), 0.001);
    }
}