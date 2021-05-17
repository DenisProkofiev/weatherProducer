package com.den.weather.enams;

public enum DurationEnum {
    ONE_DAY,
    TWO_DAYS,
    THREE_DAYS,
    FOUR_DAYS,
    FIVE_DAYS;

    public static DurationEnum TypeCheck(String typeString) {
        DurationEnum durationEnum = null;
        for (DurationEnum value : DurationEnum.values()) {
            if (value == DurationEnum.valueOf(typeString)) {
                durationEnum = value;
            }
        }
        return durationEnum;
    }
}
