package com.testbots;

public class ChangeLanguage {
    String textSourceLanguage = "Не выбрано", textTargetLanguage = "Не выбрано";

    public String getTextSourceLanguage() {
        return textSourceLanguage;
    }

    public void setTextSourceLanguage(String textSourceLanguage) {
        this.textSourceLanguage = textSourceLanguage;
    }

    public String getTextTargetLanguage() {
        return textTargetLanguage;
    }

    public void setTextTargetLanguage(String textTargetLanguage) {
        this.textTargetLanguage = textTargetLanguage;
    }

    public String textSourceLanguage (String sourceLanguage) {
        if (sourceLanguage.equals("ru")) {
            return "Русский\uD83C\uDDF7\uD83C\uDDFA";
        } else if (sourceLanguage.equals("en")) {
            return "Английский\uD83C\uDDEC\uD83C\uDDE7";
        } else if (sourceLanguage.equals("es")) {
            return "Испанский\uD83C\uDDEA\uD83C\uDDF8";
        } else if (sourceLanguage.equals("zh")) {
            return "Китайский\uD83C\uDDE8\uD83C\uDDF3";
        } else if (sourceLanguage.equals("be")) {
            return "Белорусский\uD83C\uDDE7\uD83C\uDDFE";
        } else if (sourceLanguage.equals("uk")) {
            return "Украинский\uD83C\uDDFA\uD83C\uDDE6";
        } else if (sourceLanguage.equals("fr")) {
            return "Французский\uD83C\uDDEB\uD83C\uDDF7";
        } else if (sourceLanguage.equals("tr")) {
            return "Турецкий\uD83C\uDDF9\uD83C\uDDF7";
        } else if (sourceLanguage.equals("cs")) {
            return "Чешский\uD83C\uDDE8\uD83C\uDDFF";
        } else if (sourceLanguage.equals("it")) {
            return "Итальянский\uD83C\uDDEE\uD83C\uDDF9";
        } else if (sourceLanguage.equals("sv")) {
            return "Шведский\uD83C\uDDF8\uD83C\uDDEA";
        } else if (sourceLanguage.equals("ja")) {
            return "Японский\uD83C\uDDEF\uD83C\uDDF5";
        } else if (sourceLanguage.equals("ko")) {
            return "Корейский\uD83C\uDDF0\uD83C\uDDF7";
        } else if (sourceLanguage.equals("de")) {
            return "Немецкий\uD83C\uDDE9\uD83C\uDDEA";
        } else if (sourceLanguage.equals("pl")) {
            return "Польский\uD83C\uDDF5\uD83C\uDDF1";
        } else if (sourceLanguage.equals("ro")) {
            setTextSourceLanguage("Румынский\uD83C\uDDF7\uD83C\uDDF4");
        } else if (sourceLanguage.equals("da")) {
            return "Датский\uD83C\uDDE9\uD83C\uDDF0";
        } else if (sourceLanguage.equals("et")) {
            return "Эстонский\uD83C\uDDEA\uD83C\uDDEA";
        } else {
            return "Не выбрано";
        }
        return "Не выбрано";
    }

    public String textTargetLanguage (String targetLanguage) {
        if (targetLanguage == null) {
            return "Не выбрано";
        } else if (targetLanguage.equals("ru")) {
            return "Русский\uD83C\uDDF7\uD83C\uDDFA";
        } else if (targetLanguage.equals("en")) {
            return "Английский\uD83C\uDDEC\uD83C\uDDE7";
        } else if (targetLanguage.equals("es")) {
            return "Испанский\uD83C\uDDEA\uD83C\uDDF8";
        } else if (targetLanguage.equals("zh")) {
            return "Китайский\uD83C\uDDE8\uD83C\uDDF3";
        } else if (targetLanguage.equals("be")) {
            return "Белорусский\uD83C\uDDE7\uD83C\uDDFE";
        } else if (targetLanguage.equals("uk")) {
            return "Украинский\uD83C\uDDFA\uD83C\uDDE6";
        } else if (targetLanguage.equals("fr")) {
            return "Французский\uD83C\uDDEB\uD83C\uDDF7";
        } else if (targetLanguage.equals("tr")) {
            return "Турецкий\uD83C\uDDF9\uD83C\uDDF7";
        } else if (targetLanguage.equals("cs")) {
            return "Чешский\uD83C\uDDE8\uD83C\uDDFF";
        } else if (targetLanguage.equals("it")) {
            return "Итальянский\uD83C\uDDEE\uD83C\uDDF9";
        } else if (targetLanguage.equals("sv")) {
            return "Шведский\uD83C\uDDF8\uD83C\uDDEA";
        } else if (targetLanguage.equals("ja")) {
            return "Японский\uD83C\uDDEF\uD83C\uDDF5";
        } else if (targetLanguage.equals("ko")) {
            return "Корейский\uD83C\uDDF0\uD83C\uDDF7";
        } else if (targetLanguage.equals("de")) {
            return "Немецкий\uD83C\uDDE9\uD83C\uDDEA";
        } else if (targetLanguage.equals("pl")) {
            return "Польский\uD83C\uDDF5\uD83C\uDDF1";
        } else if (targetLanguage.equals("ro")) {
            return "Румынский\uD83C\uDDF7\uD83C\uDDF4";
        } else if (targetLanguage.equals("da")) {
            return "Датский\uD83C\uDDE9\uD83C\uDDF0";
        } else if (targetLanguage.equals("et")) {
            return "Эстонский\uD83C\uDDEA\uD83C\uDDEA";
        } else {
            return "Не выбрано";
        }
    }

}
