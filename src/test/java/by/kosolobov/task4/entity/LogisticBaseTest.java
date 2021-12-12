package by.kosolobov.task4.entity;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.packages.impl.BigPackage;
import by.kosolobov.task4.entity.van.BaseVan;
import by.kosolobov.task4.entity.van.SmallVan;
import by.kosolobov.task4.factory.PackageFactory;
import by.kosolobov.task4.factory.VanFactory;
import by.kosolobov.task4.sevice.VanService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LogisticBaseTest {

    @Test
    void deliverVanTo() {
        BasePackage pkg1 = new BigPackage();
        BasePackage pkg2 = new BigPackage();

        BaseVan van = new SmallVan();

        van.load(pkg1);
        van.load(pkg2);

        LogisticBase base = new LogisticBase();

        van.deliverTo(base);

        assertTrue(base.getTemporalStorage().contains(pkg1));
        assertTrue(base.getTemporalStorage().contains(pkg2));
    }

    @Test
    void multiDeliver() throws InterruptedException {
        PackageFactory packageFactory = new PackageFactory();
        List<BasePackage> packages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            packages.add(packageFactory.getRandomPackage());
        }

        VanFactory vanFactory = new VanFactory();
        List<BaseVan> vans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            vans.add(vanFactory.getRandomVan());
        }

        for (BaseVan van : vans) {
            van.loadAll(packages);
        }

        LogisticBase base = new LogisticBase();
        for (BaseVan van : vans) {
            van.setDestination(base);
            van.execute();
        }

        Thread.sleep(15000);

        System.out.println(base.getTemporalStorage());
        System.out.println(base.getTemporalStorage().size());
        System.out.println(base.getGarage());
    }
}