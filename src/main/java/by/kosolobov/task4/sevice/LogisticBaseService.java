package by.kosolobov.task4.sevice;

import by.kosolobov.task4.entity.LogisticBase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogisticBaseService {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public void execute(LogisticBase base) {
        executor.execute(base);
    }
}
