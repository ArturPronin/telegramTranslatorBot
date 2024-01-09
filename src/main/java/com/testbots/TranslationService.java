package com.testbots;

import yandex.cloud.api.ai.translate.v2.TranslationServiceGrpc;
import yandex.cloud.api.ai.translate.v2.TranslationServiceOuterClass;
import yandex.cloud.sdk.ServiceFactory;
import yandex.cloud.sdk.auth.Auth;
import yandex.cloud.sdk.auth.provider.CredentialProvider;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;

public class TranslationService {
    static String IAM_TOKEN;
    static final String FOLDER_ID = "your folder id yandex cloud";

    public void setIamToken(String iamToken) {
        IAM_TOKEN = iamToken;
    }


    public String translate(String messageText, Long id) throws SQLException {
        TranslationServiceOuterClass.TranslateResponse response = createTranslationService().translate(buildTranslateRequest(messageText, id));
        String translation = response.getTranslations(0).getText();
        return translation;
    }

    private TranslationServiceOuterClass.TranslateRequest buildTranslateRequest(String messageText, Long id) throws SQLException {
        ConnectDatabase database = new ConnectDatabase();

        return TranslationServiceOuterClass.TranslateRequest.newBuilder()
                .setSourceLanguageCode(database.getSourceLanguage(id))
                .setTargetLanguageCode(database.getTargetLanguage(id))
                .setFormat(TranslationServiceOuterClass.TranslateRequest.Format.PLAIN_TEXT)
                .addTexts(messageText)
                .setFolderId(FOLDER_ID)
                .build();
    }

    private TranslationServiceOuterClass.DetectLanguageRequest buildLanguageRequest(String text) {
        return TranslationServiceOuterClass.DetectLanguageRequest.newBuilder()
                .setText(text)
                .setFolderId(FOLDER_ID)
                .build();
    }

    private TranslationServiceGrpc.TranslationServiceBlockingStub createTranslationService() {
        CredentialProvider iamTokenProvider = Auth.iamTokenBuilder()
                .token(IAM_TOKEN)
                .build();

        ServiceFactory factory = ServiceFactory.builder()
                .credentialProvider(iamTokenProvider)
                .requestTimeout(Duration.ofMinutes(1))
                .build();
        TranslationServiceGrpc.TranslationServiceBlockingStub translationService = factory.create(TranslationServiceGrpc.TranslationServiceBlockingStub.class, TranslationServiceGrpc::newBlockingStub);
        return translationService;
    }

    public String detectLanguage(String messageText) {
        String detectedLanguage = createTranslationService().detectLanguage(buildLanguageRequest(messageText)).toString().replace("language_code: ", "").replace("\"", "").trim();
        return detectedLanguage;
    }

}
