package by.kosolobov.task4.factory;

import by.kosolobov.task4.entity.van.BaseVan;
import by.kosolobov.task4.entity.van.BigVan;
import by.kosolobov.task4.entity.van.MediumVan;
import by.kosolobov.task4.entity.van.SmallVan;

import java.util.Random;

public class VanFactory {
    private static final Random random = new Random();

    public BaseVan getBigVan() {
        return new BigVan();
    }

    public BaseVan getMediumVan() {
        return new MediumVan();
    }

    public BaseVan getSmallVan() {
        return new SmallVan();
    }

    public BaseVan getRandomVan() {
        switch (random.nextInt(3)) {
            case 0 -> {
                return new BigVan();
            }
            case 1 -> {
                return new MediumVan();
            }
            default -> {
                return new SmallVan();
            }
        }
    }
}
