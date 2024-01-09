package com.testbots;

public enum Languages {

    RU("ru"), SOURCE_RU("ru"), EN("en"), SOURCE_EN("en"), ES("es"), SOURCE_ES("es"),
    ZH("zh"), SOURCE_ZH("zh"), BE("be"), SOURCE_VE("be"), UK("uk"), SOURCE_UK("uk"),
    FR("fr"), SOURCE_FR("fr"), TR("tr"), SOURCE_TR("tr"), CS("cs"), SOURCE_CS("cs"),
    IT("it"), SOURCE_IT("it"), SV("sv"), SOURCE_SV("sv"), JA("ja"), SOURCE_JA("ja"),
    KO("ko"), SOURCE_KO("ko"), DE("de"), SOURCE_DE("de"), PL("pl"), SOURCE_PL("pl"),
    RO("ro"), SOURCE_RO("ro"), DA("da"), SOURCE_DA("da"), ET("et"), SOURCE_ET("et");

    private String title;

    Languages(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Languages{ title='" + title + '}';
    }
}

