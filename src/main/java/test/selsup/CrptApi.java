package test.selsup;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CrptApi {
    private final Lock lock = new ReentrantLock();
    private final TimeUnit timeUnit;
    private final int requestLimit;
    private int requestCount = 0;
    private long lastRequestTime = System.currentTimeMillis();

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        this.timeUnit = timeUnit;
        this.requestLimit = requestLimit;
    }


    public void createDocument(String documentJson, String signature) throws InterruptedException {
        lock.lock();
        try {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastRequestTime;

            // Проверяем, прошел ли временной интервал
            if (elapsedTime >= timeUnit.toMillis(1)) {
                // Сбросить количество запросов, если пройден временной интервал
                requestCount = 0;
                lastRequestTime = currentTime;
            }

            // Проверяем лимит запросов
            if (requestCount >= requestLimit) {
                // Ждем следующего интервала времени
                Thread.sleep(timeUnit.toMillis(1) - elapsedTime);
                requestCount = 0;
                lastRequestTime = System.currentTimeMillis();
            }

            // Выполняем вызов API, в данном случае вывод в консоль
            performApiCall(documentJson, signature);

            requestCount++;
        } finally {
            lock.unlock();
        }
    }
    private void performApiCall(String documentJson, String signature) {
        System.out.println("Performing API call...");
        System.out.println("Document JSON: " + documentJson);
        System.out.println("Signature: " + signature);
    }
}
