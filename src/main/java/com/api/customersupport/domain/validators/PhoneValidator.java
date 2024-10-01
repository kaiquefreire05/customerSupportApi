package com.api.customersupport.domain.validators;

import java.util.regex.Pattern;

public class PhoneValidator {
    // Attributes
    private static final String PHONE_REGEX = "^(\\(\\d{2}\\)\\s?)?\\d{4,5}[-\\s]?\\d{4}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    // Method
    public static boolean isValidPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }
}
