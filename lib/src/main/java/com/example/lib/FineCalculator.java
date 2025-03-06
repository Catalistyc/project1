package com.example.lib;

public class FineCalculator {
    public static double calculateFine(int year, int daysLate) {
        if (year < 1900) {
            return 500 * daysLate;
        } else if (year < 2000) {
            return 100 * daysLate;
        } else {
            return 50 * daysLate;
        }
    }
}