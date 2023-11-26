package com.fab5.bankingapp.utility;

// This utility is only meant for classes that end with Exception
public interface ExceptionTypeExtractor {
    default String extractExceptionType() {
        String fullClassName = getClass().getSimpleName();
        StringBuilder generatedClassName = new StringBuilder();
        boolean capitalExists = false;
        for (char c : fullClassName.toCharArray()) {
            if(Character.isUpperCase(c)) {
                if (capitalExists) {
                    generatedClassName.append(" ");
                }
                capitalExists = true;
            }
            generatedClassName.append(c);
        }
        return generatedClassName.toString().trim();
    }
}
