package by.kosolobov.task4.entity;

import by.kosolobov.task4.entity.packages.Box;
import by.kosolobov.task4.entity.packages.BoxType;
import by.kosolobov.task4.entity.state.impl.DeliveredState;
import by.kosolobov.task4.entity.state.impl.OrderedState;
import by.kosolobov.task4.entity.state.impl.UnorderedState;
import by.kosolobov.task4.entity.van.Van;
import by.kosolobov.task4.entity.van.VanType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

class LogisticBaseTest {

    @Test
    void deliverVanTo() {
        LogisticBase base = LogisticBase.getInstance();
        Box box1 = new Box();
        Box box2 = new Box();

        assertInstanceOf(UnorderedState.class, box1.getState());
        assertInstanceOf(UnorderedState.class, box2.getState());

        //Logistic Base ordering simulation
        box1.nextState();
        box2.nextState();
        List<Box> packages = new ArrayList<>();
        packages.add(box1);
        packages.add(box2);

        assertInstanceOf(OrderedState.class, box1.getState());
        assertInstanceOf(OrderedState.class, box2.getState());

        Van van = new Van(VanType.SMALL);
        van.loadAll(packages);
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
        List<Box> packages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            packages.add(new Box(BoxType.BIG));
        }

        List<Van> vans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            vans.add(new Van(VanType.SMALL));
        }

        LogisticBase base = LogisticBase.getInstance();
        for (Van van : vans) {
            van.loadAll(packages);
            van.deliverTo(base);
        }

        assertTrue(packages.isEmpty());

        await().until(() -> base.getMainGarage().size() == 5);

        assertSame(10, base.getTemporalStorage().size());

        base.getMainGarage().clear();
        base.getTemporalStorage().clear();
    }
}