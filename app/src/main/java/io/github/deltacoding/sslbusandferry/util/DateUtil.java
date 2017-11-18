package io.github.deltacoding.sslbusandferry.util;

import android.util.Log;

import java.util.Calendar;

public class DateUtil {

    public static String convertToString(int year, int month, int day) {
        String text = "";

        // Formatting day
        if (day < 10) {
            text += "0" + day;
        } else {
            text += day;
        }
        text += ".";

        // Formatting month
        if(month < 9) {
            text += "0" + (month + 1);
        } else {
            text += (month + 1);
        }

        text += "." + year;

        return text;
    }

    public static boolean isDateToday(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH);
        int currentDay = c.get(Calendar.DAY_OF_MONTH);

        String currentDate = DateUtil.convertToString(currentYear, currentMonth, currentDay);
        String paramDate = DateUtil.convertToString(year, month, day);

        if(currentDate.equals(paramDate)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidDate(String date) {
        String[] values = date.split("\\.");
        if(values.length == 3) {
            int year = Integer.parseInt(values[2]);
            int month = Integer.parseInt(values[1]) - 1;
            int day = Integer.parseInt(values[0]);
            if(year > 0) {
                if(month >= 0 && month <= 11) {
                    if(month == 0 || month == 2 || month == 4 || month == 6
                            || month == 7 || month == 9 || month == 11) {
                        if(day >= 1 && day <= 31) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if(month == 3 || month == 5 || month == 8 || month == 10) {
                        if(day >= 1 && day <= 30) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        if(year % 4 == 0) {
                            if(day >= 1 && day <= 29) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            if(day >= 1 && day <= 28) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
