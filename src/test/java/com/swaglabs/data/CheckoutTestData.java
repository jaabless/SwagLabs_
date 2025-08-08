package com.swaglabs.data;

import java.util.stream.Stream;

public class CheckoutTestData {
    public static Stream<Object[]> validCheckoutTestData() {
        return Stream.of(
                new Object[]{"John", "Doe", "12345", true, "Checkout successful"}, //valid input
                new Object[]{"Mary-Jane", "O’Connor", "12345", true, "Checkout successful"}
        );
    }

    public static Stream<Object[]> invalidCheckoutTestData() {
        return Stream.of(
                new Object[]{"", "Doe", "12345", false, "Error: First Name is required"}, //missing first name
                new Object[]{"John", "", "12345", false, "Error: Last Name is required"}, //missing last name
                new Object[]{"John", "Doe", "", false, "Error: Postal Code is required"}, //missing postal code
                new Object[]{"", "", "", false, "Error: First Name is required"} //missing all fields
        );
    }
}
