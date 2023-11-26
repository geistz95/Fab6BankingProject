package com.fab5.bankingapp.utility;

public interface NotFoundTypeExtractor {
    default String getSimplifiedNameOfExceptionOfNotFound() {
        String fullClassName = getClass().getSimpleName();
        StringBuilder cutClassName = new StringBuilder();
        boolean capitalExists = false;
        for (char c : fullClassName.toCharArray()) {
            if (Character.isUpperCase(c) && c != 'N') {
                if (capitalExists) {
                    cutClassName.append(" ").append(Character.toLowerCase(c));
                    continue;
                }
                cutClassName.append(c);
                capitalExists = true;
            } else if (c != 'N') {
                cutClassName.append(c);
            } else {
                break;
            }
        }
        return cutClassName.toString();
    }

    default String getSimplifiedNameOfExceptionOfNotFound2() {
        String fullClassName = getClass().getSimpleName();
        StringBuilder cutClassName = new StringBuilder();
        boolean capitalExists = false;
        for (char c : fullClassName.toCharArray()) {
            if (Character.isUpperCase(c) && c != 'N') {
                if (capitalExists) {
                    cutClassName.append(" ").append(c);
                    continue;
                }
                cutClassName.append(c);
                capitalExists = true;
            } else if (c != 'N') {
                cutClassName.append(c);
            } else {
                break;
            }
        }
        return cutClassName.toString();
    }

}
