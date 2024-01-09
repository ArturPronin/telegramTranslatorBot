package com.testbots;

import org.apache.shiro.session.Session;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.ChatPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.session.TelegramLongPollingSessionBot;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.toIntExact;

public class BotTranslator extends TelegramLongPollingSessionBot {

    private static final String TOKEN = "5461739511:AAEA_MB6mVLBu3e9dpChEaDlEJKC7fYC1C0";
    private static final String USERNAME = "translate_smartbot";
    private TranslationService translator;
    private ChangeLanguage language;
    private static ConnectDatabase database = new ConnectDatabase();

    public void setLanguage(ChangeLanguage language) {
        this.language = language;
    }

    public void setTranslator(TranslationService translator) {
        this.translator = translator;
    }

    public String getBotUsername() {
        return USERNAME;
    }

    public String getBotToken() {
        return TOKEN;
    }


    @Override
    public void onUpdateReceived(Update update, Optional<Session> optionalSession) {

        String answerHello = "⚙️<b>Привет, я бот переводчик.</b> \n" +
                "<b>Помощь:</b> /help \n";
        String answerStart = "-------------------------------------------------------------------------------------\n" +
                "<b>Слева</b> - язык сообщения, которое Вы пишите боту. \n" +
                "<b>Справа</b> - язык, на который будет сделан перевод.\n" +
                "------------------------------------------------------------------------------------- \n" +
                "<b>Языки:</b> \n";
        KeyboardBot keyboard = new KeyboardBot();
        HashMap<Integer, String> numberKeys = new HashMap<>();
        numberKeys.put(1, "  1  ");
        numberKeys.put(2, "  2  ");
        numberKeys.put(3, "  3  ");
        numberKeys.put(4, "  4  ");
        if (update.getMessage() != null && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            long adminId = 282676507;
            String name = update.getMessage().getFrom().getUserName();
            String messageText = update.getMessage().getText();
            /*translator.setSourceLanguage(translator.detectLanguage(messageText));*/
            if (messageText.equals("/start")) {
                String sourceLanguage = null;
                String targetLanguage = null;
                try {
                    sourceLanguage = language.textSourceLanguage(database.getSourceLanguage(chatId));
                    targetLanguage = language.textTargetLanguage(database.getTargetLanguage(chatId));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                SendMessage message = SendMessage
                        .builder()
                        .chatId(Long.toString(chatId))
                        .parseMode(ParseMode.HTML)
                        .text(answerHello + answerStart + sourceLanguage + "  →  " + targetLanguage)
                        .build();
                message.setReplyMarkup(keyboard.getKeyboardOne(keyboard.getNumberKeys()));
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                try {
                    database.connectDB(chatId, name);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (messageText.equals("/send") && adminId == update.getMessage().getFrom().getId()) {
                try {
                    String ads = database.selectAds();
                    ArrayList<Long> chatIds = database.selectChatId();
                    System.out.println(chatIds + "\n" + ads);
                    for (int i = 0; i < chatIds.size(); i++) {
                        String caption = ads;
                        File filePhoto = new File(database.selectPhoto());
                        InputFile photo = new InputFile(filePhoto, "photoAds");
                        SendPhoto message = SendPhoto
                                .builder()
                                .chatId(Long.toString(chatIds.get(i)))
                                .parseMode(ParseMode.HTML)
                                .photo(photo)
                                .caption(ads)
                                .build();
                        message.setReplyMarkup(keyboard.getKeyboardKeys(database.selectKeys()));
                        try {
                            execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (messageText.equals("/help")) {
                SendMessage message = SendMessage
                        .builder()
                        .chatId(Long.toString(chatId))
                        .parseMode(ParseMode.HTML)
                        .text("<b>---------------------------------------------------------------\n" +
                                "########  Помощь ########\n" +
                                "---------------------------------------------------------------</b>\n" +
                                "1⃣ Команда /start вызывает главное меню, в котором Вы можете выбрать языки перевода.\n" +
                                "2⃣ Язык для Вашего сообщения выбирать не обязательно. Он будет определен автоматически после того, как Вы отправите сообщение.\n" +
                                "3⃣ Команда /help вызывает меню с инструкциями.\n" +
                                "4⃣ Для обращения в техническую поддержку по вопросам бота:            \n" +
                                "  ▪️<i>перейти по ссылке https://t.me/anywore \n" +
                                "  ▪️или найти в поиске @anywore </i>\n" +
                                "5⃣ В случае возникновения ошибки при использовании бота, необходимо прислать на адрес, указанный в <b>П.4</b>, следующую информацию:\n" +
                                "  ▪️<i>Подробное (насколько возможно) описание проблемы. Что выбрали, что написали, какая была реакция от бота.\n" +
                                "  ▪️Уточнить, произошло это в личном чате с ботом или в беседе, в которую был добавлен бот.\n" +
                                "  ▪️Скриншот ошибки.\n" +
                                "  ▪️Дата и время возникновения ошибки.</i>\n" +
                                "6⃣ По вопросам рекламы обращаться по адресу, указанному в <b>П.4</b>")
                        .build();
                message.setReplyMarkup(keyboard.getKeyboardHelp());
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (messageText.contains("https://") || messageText.contains("http://")) {
                String sourceLanguage = null;
                String targetLanguage = null;
                String textTargetLanguage = null;
                String textSourceLanguage = null;
                try {
                    sourceLanguage = database.getSourceLanguage(chatId);
                    targetLanguage = database.getTargetLanguage(chatId);
                    textSourceLanguage = language.textSourceLanguage(sourceLanguage);
                    textTargetLanguage = language.textTargetLanguage(targetLanguage);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                SendMessage message = null;
                message = SendMessage
                        .builder()
                        .chatId(Long.toString(chatId))
                        .parseMode(ParseMode.HTML)
                        /*.text("<a href=\"https://translate.yandex.ru/translate?view=compact&url=" +
                                messageText + "&lang="+ sourceLanguage + "-" + targetLanguage + "\">Переведенный сайт ("
                                + textSourceLanguage + "  →  " + textTargetLanguage + ")</a>")*/
                        .text("<a href=\"https://translate.google.com/translate?sl=auto&tl="
                                + targetLanguage + "&u=" + messageText + "&client=webapp" + "\">Переведенный сайт ("
                                + "Автоматически" + "  →  " + textTargetLanguage + ")</a>")
                        .build();
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                int messageId = update.getMessage().getMessageId();
                SendMessage message = null;
                try {
                    String translateMessage = translator.translate(messageText, chatId);
                    message = SendMessage
                            .builder()
                            .chatId(Long.toString(chatId))
                            .replyToMessageId(messageId)
                            .text(translateMessage)
                            .build();
                    message.setReplyMarkup(keyboard.getKeyboardVoice());
                    try {
                        execute(message);
                        database.setLastMessage(translateMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } else if (update.hasCallbackQuery()) {
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            call_data.equals(Languages.RU.getTitle());

            if (call_data.equals("back-help")) {
                try {
                    callBackPages(keyboard.getKeyboardOne(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("ru")) {
                try {
                    callBackTarget("ru", keyboard.getKeyboardOne(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-ru")) {
                try {
                    callBackSource("ru", keyboard.getKeyboardOne(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("en")) {
                try {
                    callBackTarget("en", keyboard.getKeyboardOne(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-en")) {
                try {
                    callBackSource("en", keyboard.getKeyboardOne(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("es")) {
                try {
                    callBackTarget("es", keyboard.getKeyboardOne(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-es")) {
                try {
                    callBackSource("es", keyboard.getKeyboardOne(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("zh")) {
                try {
                    callBackTarget("zh", keyboard.getKeyboardOne(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-zh")) {
                try {
                    callBackSource("zh", keyboard.getKeyboardOne(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("be")) {
                try {
                    callBackTarget("be", keyboard.getKeyboardOne(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-be")) {
                try {
                    callBackSource("be", keyboard.getKeyboardOne(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("uk")) {
                try {
                    callBackTarget("uk", keyboard.getKeyboardOne(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-uk")) {
                try {
                    callBackSource("uk", keyboard.getKeyboardOne(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("fr")) {
                try {
                    callBackTarget("fr", keyboard.getKeyboardTwo(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-fr")) {
                try {
                    callBackSource("fr", keyboard.getKeyboardTwo(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("tr")) {
                try {
                    callBackTarget("tr", keyboard.getKeyboardTwo(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-tr")) {
                try {
                    callBackSource("tr", keyboard.getKeyboardTwo(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("cs")) {
                try {
                    callBackTarget("cs", keyboard.getKeyboardTwo(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-cs")) {
                try {
                    callBackSource("cs", keyboard.getKeyboardTwo(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("it")) {
                try {
                    callBackTarget("it", keyboard.getKeyboardTwo(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-it")) {
                try {
                    callBackSource("it", keyboard.getKeyboardTwo(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("sv")) {
                try {
                    callBackTarget("sv", keyboard.getKeyboardTwo(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-sv")) {
                try {
                    callBackSource("sv", keyboard.getKeyboardTwo(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("ja")) {
                try {
                    callBackTarget("ja", keyboard.getKeyboardTwo(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-ja")) {
                try {
                    callBackSource("ja", keyboard.getKeyboardTwo(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("ko")) {
                try {
                    callBackTarget("ko", keyboard.getKeyboardThree(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-ko")) {
                try {
                    callBackSource("ko", keyboard.getKeyboardThree(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("de")) {
                try {
                    callBackTarget("de", keyboard.getKeyboardThree(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-de")) {
                try {
                    callBackSource("de", keyboard.getKeyboardThree(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("pl")) {
                try {
                    callBackTarget("pl", keyboard.getKeyboardThree(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-pl")) {
                try {
                    callBackSource("pl", keyboard.getKeyboardThree(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("ro")) {
                try {
                    callBackTarget("ro", keyboard.getKeyboardThree(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-ro")) {
                try {
                    callBackSource("ro", keyboard.getKeyboardThree(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("da")) {
                try {
                    callBackTarget("da", keyboard.getKeyboardThree(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-da")) {
                try {
                    callBackSource("da", keyboard.getKeyboardThree(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("et")) {
                try {
                    callBackTarget("et", keyboard.getKeyboardThree(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("source-et")) {
                try {
                    callBackSource("et", keyboard.getKeyboardThree(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("page-one")) {
                try {
                    callBackPages(keyboard.getKeyboardOne(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("page-two")) {
                try {
                    callBackPages(keyboard.getKeyboardTwo(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("page-three")) {
                try {
                    callBackPages(keyboard.getKeyboardThree(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (call_data.equals("page-four")) {
                try {
                    callBackPages(keyboard.getKeyboardFour(numberKeys),
                            chat_id, message_id, answerHello, answerStart, database);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (call_data.equals("voice")) {
                try {
                    TextToSpeech.synthesizeText(database.getLastMessage(), database.getTargetLanguage(chat_id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File fileVoice = new File("A:/Users/user/Desktop/output.ogg");
                InputFile inputVoice = new InputFile(fileVoice, "output");
                SendVoice voice = SendVoice
                        .builder()
                        .chatId(Long.toString(chat_id))
                        .voice(inputVoice)
                        .build();
                try {
                    execute(voice);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void callBackTarget(String target, InlineKeyboardMarkup markupInline,
                               long chat_id, long message_id, String answerHello, String answerStart, ConnectDatabase database) throws SQLException {
        database.setTargetLanguage(chat_id, target);
        String sourceLanguage = language.textSourceLanguage(database.getSourceLanguage(chat_id));
        String targetLanguage = language.textTargetLanguage(database.getTargetLanguage(chat_id));
        EditMessageText new_message = EditMessageText
                .builder()
                .chatId(chat_id)
                .messageId(toIntExact(message_id))
                .parseMode(ParseMode.HTML)
                .text(answerHello + answerStart + sourceLanguage + "  →  " + targetLanguage)
                .build();
        new_message.setReplyMarkup(markupInline);
        try {
            execute(new_message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void callBackSource(String source, InlineKeyboardMarkup markupInline,
                               long chat_id, long message_id, String answerHello, String answerStart, ConnectDatabase database) throws SQLException {
        database.setSourceLanguage(chat_id, source);
        String sourceLanguage = language.textSourceLanguage(database.getSourceLanguage(chat_id));
        String targetLanguage = language.textTargetLanguage(database.getTargetLanguage(chat_id));
        EditMessageText new_message = EditMessageText
                .builder()
                .chatId(chat_id)
                .parseMode(ParseMode.HTML)
                .messageId(toIntExact(message_id))
                .text(answerHello + answerStart + sourceLanguage + "  →  " + targetLanguage)
                .build();
        new_message.setReplyMarkup(markupInline);
        try {
            execute(new_message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void callBackPages(InlineKeyboardMarkup markupInline, long chat_id, long message_id,
                              String answerHello, String answerStart, ConnectDatabase database) throws SQLException {
        String sourceLanguage = language.textSourceLanguage(database.getSourceLanguage(chat_id));
        String targetLanguage = language.textTargetLanguage(database.getTargetLanguage(chat_id));
        EditMessageText new_message = EditMessageText
                .builder()
                .chatId(chat_id)
                .messageId(toIntExact(message_id))
                .parseMode(ParseMode.HTML)
                .text(answerHello + answerStart + sourceLanguage + "  →  " + targetLanguage)
                .build();
        new_message.setReplyMarkup(markupInline);
        try {
            execute(new_message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}


