package com.testbots;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface Chat {

    void callBackSource();

    void callBackTarget();

    void callBackPages();
}
