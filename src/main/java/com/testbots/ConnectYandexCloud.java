package com.testbots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConnectYandexCloud {
    //You need install Yandex Cloud CLI before using app
    public static final String commandCreateIamToken = "yc iam create-token";
    public String IamToken;

    public void setIamToken(String iamToken) {
        IamToken = iamToken;
    }

    public String getIamToken() {
        return IamToken;
    }

    public void createIamToken() throws IOException {
        Process process = Runtime.getRuntime().exec(commandCreateIamToken);
        setIamToken(printResults(process));
    }

    public String printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        String token = "";
        while ((line = reader.readLine()) != null) {
            token = line;
        }
        return token;
    }
}
