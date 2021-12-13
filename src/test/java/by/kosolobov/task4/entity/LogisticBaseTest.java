package by.kosolobov.task4.entity;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.packages.BigPackage;
import by.kosolobov.task4.entity.state.impl.DeliveredState;
import by.kosolobov.task4.entity.state.impl.OrderedState;
import by.kosolobov.task4.entity.state.impl.UnorderedState;
import by.kosolobov.task4.entity.van.BaseVan;
import by.kosolobov.task4.entity.van.SmallVan;
import by.kosolobov.task4.factory.PackageFactory;
import by.kosolobov.task4.factory.VanFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;

class LogisticBaseTest {

    @Test
    void deliverVanTo() {
        LogisticBase base = LogisticBase.getInstance();
        BasePackage pkg1 = new BigPackage();
        BasePackage pkg2 = new BigPackage();

        assertInstanceOf(UnorderedState.class, pkg1.getState());
        assertInstanceOf(UnorderedState.class, pkg2.getState());

        //Logistic Base ordering simulation
        pkg1.nextState();
        pkg2.nextState();

        assertInstanceOf(OrderedState.class, pkg1.getState());
        assertInstanceOf(OrderedState.class, pkg2.getState());

        BaseVan van = new SmallVan();
        van.load(pkg1);
        van.load(pkg2);
        van.deliverTo(base);

        await().until(() -> base.getMainGarage().size() == 1);

        assertSame(2, base.getTemporalStorage().size());
        assertInstanceOf(DeliveredState.class, base.getTemporalStorage().get(0).getState());
        assertInstanceOf(DeliveredState.class, base.getTemporalStorage().get(1).getState());

        base.getMainGarage().clear();
        base.getTemporalStorage().clear();
    }

    @Test
    void multiDeliver() {
        PackageFactory packageFactory = new PackageFactory();
        List<BasePackage> EXPECTED = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            EXPECTED.add(packageFactory.getRandomPackage());
        }
        var packages = EXPECTED.subList(0, EXPECTED.size());

        VanFactory vanFactory = new VanFactory();
        List<BaseVan> vans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            vans.add(vanFactory.getRandomVan());
        }

        LogisticBase base = LogisticBase.getInstance();
        for (BaseVan van : vans) {
            van.loadAll(packages);
            van.deliverTo(base);
        }

        await().until(() -> base.getMainGarage().size() == 5);

        assertSame(10, base.getTemporalStorage().size());

        base.getMainGarage().clear();
        base.getTemporalStorage().clear();
    }
}