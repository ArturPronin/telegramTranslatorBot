package com.testbots;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeyboardBot {

    public InlineKeyboardMarkup getKeyboardOne(HashMap<Integer, String> numberKeys) {
        numberKeys.replace(1, "  ●  ");
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInlineRu = new ArrayList<>();
        rowInlineRu.add(InlineKeyboardButton.builder().text("Русский\uD83C\uDDF7\uD83C\uDDFA").callbackData("source-ru").build());
        rowInlineRu.add(InlineKeyboardButton.builder().text("Русский\uD83C\uDDF7\uD83C\uDDFA").callbackData("ru").build());

        List<InlineKeyboardButton> rowInlineEn = new ArrayList<>();
        rowInlineEn.add(InlineKeyboardButton.builder().text("Английский\uD83C\uDDEC\uD83C\uDDE7").callbackData("source-en").build());
        rowInlineEn.add(InlineKeyboardButton.builder().text("Английский\uD83C\uDDEC\uD83C\uDDE7").callbackData("en").build());

        List<InlineKeyboardButton> rowInlineEs = new ArrayList<>();
        rowInlineEs.add(InlineKeyboardButton.builder().text("Испанский\uD83C\uDDEA\uD83C\uDDF8").callbackData("source-es").build());
        rowInlineEs.add(InlineKeyboardButton.builder().text("Испанский\uD83C\uDDEA\uD83C\uDDF8").callbackData("es").build());

        List<InlineKeyboardButton> rowInlineZh = new ArrayList<>();
        rowInlineZh.add(InlineKeyboardButton.builder().text("Китайский\uD83C\uDDE8\uD83C\uDDF3").callbackData("source-zh").build());
        rowInlineZh.add(InlineKeyboardButton.builder().text("Китайский\uD83C\uDDE8\uD83C\uDDF3").callbackData("zh").build());

        List<InlineKeyboardButton> rowInlineBe = new ArrayList<>();
        rowInlineBe.add(InlineKeyboardButton.builder().text("Белорусский\uD83C\uDDE7\uD83C\uDDFE").callbackData("source-be").build());
        rowInlineBe.add(InlineKeyboardButton.builder().text("Белорусский\uD83C\uDDE7\uD83C\uDDFE").callbackData("be").build());

        List<InlineKeyboardButton> rowInlineUk = new ArrayList<>();
        rowInlineUk.add(InlineKeyboardButton.builder().text("Украинский\uD83C\uDDFA\uD83C\uDDE6").callbackData("source-uk").build());
        rowInlineUk.add(InlineKeyboardButton.builder().text("Украинский\uD83C\uDDFA\uD83C\uDDE6").callbackData("uk").build());


        rowsInline.add(rowInlineRu);
        rowsInline.add(rowInlineEn);
        rowsInline.add(rowInlineEs);
        rowsInline.add(rowInlineZh);
        rowsInline.add(rowInlineBe);
        rowsInline.add(rowInlineUk);
        rowsInline.add(getKeyboardPages(numberKeys));
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public InlineKeyboardMarkup getKeyboardTwo(HashMap<Integer, String> numberKeys) {
        numberKeys.replace(2, "  ●  ");
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInlineOne = new ArrayList<>();
        rowInlineOne.add(InlineKeyboardButton.builder().text("Французский\uD83C\uDDEB\uD83C\uDDF7").callbackData("source-fr").build());
        rowInlineOne.add(InlineKeyboardButton.builder().text("Французский\uD83C\uDDEB\uD83C\uDDF7").callbackData("fr").build());

        List<InlineKeyboardButton> rowInlineTwo = new ArrayList<>();
        rowInlineTwo.add(InlineKeyboardButton.builder().text("Турецкий\uD83C\uDDF9\uD83C\uDDF7").callbackData("source-tr").build());
        rowInlineTwo.add(InlineKeyboardButton.builder().text("Турецкий\uD83C\uDDF9\uD83C\uDDF7").callbackData("tr").build());

        List<InlineKeyboardButton> rowInlineThree = new ArrayList<>();
        rowInlineThree.add(InlineKeyboardButton.builder().text("Чешский\uD83C\uDDE8\uD83C\uDDFF").callbackData("source-cs").build());
        rowInlineThree.add(InlineKeyboardButton.builder().text("Чешский\uD83C\uDDE8\uD83C\uDDFF").callbackData("cs").build());

        List<InlineKeyboardButton> rowInlineFour = new ArrayList<>();
        rowInlineFour.add(InlineKeyboardButton.builder().text("Итальянский\uD83C\uDDEE\uD83C\uDDF9").callbackData("source-it").build());
        rowInlineFour.add(InlineKeyboardButton.builder().text("Итальянский\uD83C\uDDEE\uD83C\uDDF9").callbackData("it").build());

        List<InlineKeyboardButton> rowInlineFive = new ArrayList<>();
        rowInlineFive.add(InlineKeyboardButton.builder().text("Шведский\uD83C\uDDF8\uD83C\uDDEA").callbackData("source-sv").build());
        rowInlineFive.add(InlineKeyboardButton.builder().text("Шведский\uD83C\uDDF8\uD83C\uDDEA").callbackData("sv").build());

        List<InlineKeyboardButton> rowInlineSix = new ArrayList<>();
        rowInlineSix.add(InlineKeyboardButton.builder().text("Японский\uD83C\uDDEF\uD83C\uDDF5").callbackData("source-ja").build());
        rowInlineSix.add(InlineKeyboardButton.builder().text("Японский\uD83C\uDDEF\uD83C\uDDF5").callbackData("ja").build());

        rowsInline.add(rowInlineOne);
        rowsInline.add(rowInlineTwo);
        rowsInline.add(rowInlineThree);
        rowsInline.add(rowInlineFour);
        rowsInline.add(rowInlineFive);
        rowsInline.add(rowInlineSix);
        rowsInline.add(getKeyboardPages(numberKeys));
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public InlineKeyboardMarkup getKeyboardThree (HashMap<Integer, String> numberKeys) {
        numberKeys.replace(3, "  ●  ");
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInlineOne = new ArrayList<>();
        rowInlineOne.add(InlineKeyboardButton.builder().text("Корейский\uD83C\uDDF0\uD83C\uDDF7").callbackData("source-ko").build());
        rowInlineOne.add(InlineKeyboardButton.builder().text("Корейский\uD83C\uDDF0\uD83C\uDDF7").callbackData("ko").build());

        List<InlineKeyboardButton> rowInlineTwo = new ArrayList<>();
        rowInlineTwo.add(InlineKeyboardButton.builder().text("Немецкий\uD83C\uDDE9\uD83C\uDDEA").callbackData("source-de").build());
        rowInlineTwo.add(InlineKeyboardButton.builder().text("Немецкий\uD83C\uDDE9\uD83C\uDDEA").callbackData("de").build());

        List<InlineKeyboardButton> rowInlineThree = new ArrayList<>();
        rowInlineThree.add(InlineKeyboardButton.builder().text("Польский\uD83C\uDDF5\uD83C\uDDF1").callbackData("source-pl").build());
        rowInlineThree.add(InlineKeyboardButton.builder().text("Польский\uD83C\uDDF5\uD83C\uDDF1").callbackData("pl").build());

        List<InlineKeyboardButton> rowInlineFour = new ArrayList<>();
        rowInlineFour.add(InlineKeyboardButton.builder().text("Румынский\uD83C\uDDF7\uD83C\uDDF4").callbackData("source-ro").build());
        rowInlineFour.add(InlineKeyboardButton.builder().text("Румынский\uD83C\uDDF7\uD83C\uDDF4").callbackData("ro").build());

        List<InlineKeyboardButton> rowInlineFive = new ArrayList<>();
        rowInlineFive.add(InlineKeyboardButton.builder().text("Датский\uD83C\uDDE9\uD83C\uDDF0").callbackData("source-da").build());
        rowInlineFive.add(InlineKeyboardButton.builder().text("Датский\uD83C\uDDE9\uD83C\uDDF0").callbackData("da").build());

        List<InlineKeyboardButton> rowInlineSix = new ArrayList<>();
        rowInlineSix.add(InlineKeyboardButton.builder().text("Эстонский\uD83C\uDDEA\uD83C\uDDEA").callbackData("source-et").build());
        rowInlineSix.add(InlineKeyboardButton.builder().text("Эстонский\uD83C\uDDEA\uD83C\uDDEA").callbackData("et").build());

        rowsInline.add(rowInlineOne);
        rowsInline.add(rowInlineTwo);
        rowsInline.add(rowInlineThree);
        rowsInline.add(rowInlineFour);
        rowsInline.add(rowInlineFive);
        rowsInline.add(rowInlineSix);
        rowsInline.add(getKeyboardPages(numberKeys));
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public InlineKeyboardMarkup getKeyboardFour(HashMap<Integer, String> numberKeys) {
        numberKeys.replace(4, "  ●  ");
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        rowsInline.add(getKeyboardPages(numberKeys));
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public List<InlineKeyboardButton> getKeyboardPages(HashMap<Integer, String> numberKeys) {
        List<InlineKeyboardButton> rowInlinePages = new ArrayList<>();
        rowInlinePages.add(InlineKeyboardButton.builder().text(numberKeys.get(1)).callbackData("page-one").build());
        rowInlinePages.add(InlineKeyboardButton.builder().text(numberKeys.get(2)).callbackData("page-two").build());
        rowInlinePages.add(InlineKeyboardButton.builder().text(numberKeys.get(3)).callbackData("page-three").build());
        rowInlinePages.add(InlineKeyboardButton.builder().text(numberKeys.get(4)).callbackData("page-four").build());
        return rowInlinePages;
    }

    public HashMap<Integer, String> getNumberKeys() {
        HashMap<Integer, String> numberKeys = new HashMap<>();
        numberKeys.put(1, "  1  ");
        numberKeys.put(2, "  2  ");
        numberKeys.put(3, "  3  ");
        numberKeys.put(4, "  4  ");
        return numberKeys;
    }



    public InlineKeyboardMarkup getKeyboardHelp() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInlineMenu = new ArrayList<>();
        rowInlineMenu.add(InlineKeyboardButton.builder().text("В меню⚙️").callbackData("back-help").build());

        rowsInline.add(rowInlineMenu);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public InlineKeyboardMarkup getKeyboardVoice() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInlineVoice = new ArrayList<>();
        rowInlineVoice.add(InlineKeyboardButton.builder().text("Послушать").callbackData("voice").build());

        rowsInline.add(rowInlineVoice);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

    public InlineKeyboardMarkup getKeyboardKeys(ResultSet resultKeys) throws SQLException {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        String keys = "";
        String nameKey = "";
        while (resultKeys.next()) {
            keys = resultKeys.getString("keys");
            nameKey = resultKeys.getString("nameKey");
            List<InlineKeyboardButton> rowInlineAds = new ArrayList<>();
            rowInlineAds.add(InlineKeyboardButton
                    .builder().text(nameKey).url(keys).build());
            rowsInline.add(rowInlineAds);
        }

        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }

}
