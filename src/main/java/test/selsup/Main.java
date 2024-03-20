package test.selsup;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        CrptApi crptApi = new CrptApi(TimeUnit.MINUTES, 3);

        String documentJson = "{\"description\": { \"participantInn\": \"1234567890\" }, \"doc_id\": \"123\", \"doc_status\": \"created\", \"doc_type\": \"LP_INTRODUCE_GOODS\", \"importRequest\": true, \"owner_inn\": \"0987654321\", \"participant_inn\": \"1234567890\", \"producer_inn\": \"0987654321\", \"production_date\": \"2020-01-23\", \"production_type\": \"type\", \"products\": [ { \"certificate_document\": \"certificate\", \"certificate_document_date\": \"2020-01-23\", \"certificate_document_number\": \"12345\", \"owner_inn\": \"0987654321\", \"producer_inn\": \"0987654321\", \"production_date\": \"2020-01-23\", \"tnved_code\": \"code\", \"uit_code\": \"code\", \"uitu_code\": \"code\" } ], \"reg_date\": \"2020-01-23\", \"reg_number\": \"123\"}";
        String signature = "signature";

        new Thread(() -> {
            try {
                for (int j = 0; j < 5; j++) {
                    crptApi.createDocument(documentJson, signature);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int j = 0; j < 5; j++) {
                    crptApi.createDocument(documentJson, signature);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
