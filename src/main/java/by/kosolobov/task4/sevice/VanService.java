package by.kosolobov.task4.sevice;

import by.kosolobov.task4.entity.van.BaseVan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VanService {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public void execute(BaseVan van) {
        executor.execute(van);
    }
}
