package com.example.lib;


public class PenaltyCalculator {

    public static double calculatePenalty(int publicationYear, int daysLate) {
        double penaltyPerDay;

        if (publicationYear < 1900) {
            penaltyPerDay = 500.0;
        } else if (publicationYear < 2000) {
            penaltyPerDay = 100.0;
        } else {
            penaltyPerDay = 50.0;
        }

        return penaltyPerDay * daysLate;
    }
}