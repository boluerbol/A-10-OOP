package org.example;

import java.util.regex.*;

public class StringProcessor {

    public boolean isStrongPassword(String password) {
        if (password == null || password.isEmpty() || password.length() < 8) {
            return false;
        }

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }

    public int calculateDigits(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return 0;
        }

        int count = 0;
        for (char c : sentence.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }

    public int calculateWords(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return 0;
        }

        String[] words = sentence.trim().split("\\s+");
        return words.length;
    }

    public double calculateExpression(String expression) {
        if (expression == null || expression.isEmpty()) {
            return 0.0;
        }

        try {
            return evaluateExpression(expression);
        } catch (Exception e) {
            System.out.println("Invalid expression format!");
            return 0.0;
        }
    }

    private double evaluateExpression(String expression) {
        return Double.parseDouble(new java.text.DecimalFormat("0.####").format(eval(expression)));
    }

    private double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                return x;
            }
        }.parse();
    }
}
