package com.swaglabs.data;

import java.util.stream.Stream;

public class CheckoutTestData {
    public static Stream<Object[]> checkoutTestData() {
        return Stream.of(
                new Object[]{"John", "Doe", "12345", true, "Checkout successful"},
                new Object[]{"", "Doe", "12345", false, "Error: First Name is required"},
                new Object[]{"John", "", "12345", false, "Error: Last Name is required"},
                new Object[]{"John", "Doe", "", false, "Error: Postal Code is required"},
                new Object[]{"", "", "", false, "Error: First Name is required"},
                new Object[]{"John", "Doe", "123", true, "Checkout successful"},
                new Object[]{"Mary-Jane", "O’Connor", "12345", true, "Checkout successful"}
        );
    }
}
