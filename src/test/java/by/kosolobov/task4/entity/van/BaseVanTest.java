package by.kosolobov.task4.entity.van;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.packages.BigPackage;
import by.kosolobov.task4.factory.VanFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

class BaseVanTest {

    @Test
    void load() {
        BasePackage pkg1 = new BigPackage();
        BasePackage pkg2 = new BigPackage();
        BasePackage pkg3 = new BigPackage();
        List<BasePackage> packages = new ArrayList<>();
        packages.add(pkg1);
        packages.add(pkg2);
        packages.add(pkg3);

        VanFactory factory = new VanFactory();
        BaseVan van = factory.getSmallVan();

        van.loadAll(packages);

        assertSame(pkg1, van.unload());
        assertSame(pkg2, van.unload());
        assertSame(null, van.unload());
    }
}