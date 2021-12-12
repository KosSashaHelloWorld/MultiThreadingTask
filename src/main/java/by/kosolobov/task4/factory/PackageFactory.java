package by.kosolobov.task4.factory;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.packages.impl.BigPackage;
import by.kosolobov.task4.entity.packages.impl.MediumPackage;
import by.kosolobov.task4.entity.packages.impl.SmallPackage;

import java.util.Random;

public class PackageFactory {
    private static final Random random = new Random();

    public BasePackage getBigPackage() {
        return new BigPackage();
    }

    public BasePackage getMediumPackage() {
        return new MediumPackage();
    }

    public BasePackage getSmallPackage() {
        return new SmallPackage();
    }

    public BasePackage getRandomPackage() {
        switch (random.nextInt(3)) {
            case 0 -> {
                return new BigPackage();
            }
            case 1 -> {
                return new MediumPackage();
            }
            default -> {
                return new SmallPackage();
            }
        }
    }
}
