package by.kosolobov.task4.entity.van;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.packages.BigPackage;
import by.kosolobov.task4.factory.VanFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseVanTest {

    @Test
    void load() {
        BasePackage pkg1 = new BigPackage();
        BasePackage pkg2 = new BigPackage();
        BasePackage pkg3 = new BigPackage();

        VanFactory factory = new VanFactory();
        BaseVan van = factory.getSmallVan();

        van.load(pkg1);
        van.load(pkg2);
        van.load(pkg3);

        assertSame(pkg1, van.unload());
        assertSame(pkg2, van.unload());
        assertSame(null, van.unload());
    }
}