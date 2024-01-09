package com.testbots;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import sun.rmi.runtime.Log;

import java.io.*;


public class Main {


    public static void main(String[] args) throws TelegramApiException, IOException {

        final long timeInterval = 36000000;
        Runnable runnable = () -> {
            while (true) {

                ConnectYandexCloud connect = new ConnectYandexCloud();
                try {
                    connect.createIamToken();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TranslationService translator = new TranslationService();
                translator.setIamToken(connect.getIamToken());
                System.out.println(connect.getIamToken());


                TelegramBotsApi telegramBotsApi = null;
                ChangeLanguage language = new ChangeLanguage();
                try {
                    telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                try {
                    BotTranslator botTranslator = new BotTranslator();
                    botTranslator.setTranslator(translator);
                    botTranslator.setLanguage(language);
                    telegramBotsApi.registerBot(botTranslator);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(timeInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

}
