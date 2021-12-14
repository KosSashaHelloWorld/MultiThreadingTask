package by.kosolobov.task4.sevice;

import by.kosolobov.task4.entity.van.Van;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VanService {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public void execute(Van van) {
        executor.execute(van);
    }
}
