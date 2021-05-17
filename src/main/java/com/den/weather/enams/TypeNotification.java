package com.den.weather.enams;

public enum TypeNotification{
    EMAIL,
    SMS;

    public static TypeNotification TypeCheck(String typeString) {
        TypeNotification typeNotification = null;

        for (TypeNotification value : TypeNotification.values()) {
            if (value == TypeNotification.valueOf(typeString)) {
                typeNotification = value;
            }
        }

        return typeNotification;
    }
}
