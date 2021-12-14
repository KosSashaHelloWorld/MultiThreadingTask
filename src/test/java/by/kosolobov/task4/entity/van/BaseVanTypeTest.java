package by.kosolobov.task4.entity.van;

import by.kosolobov.task4.entity.packages.Box;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static by.kosolobov.task4.entity.packages.BoxType.BIG;
import static by.kosolobov.task4.entity.van.VanType.SMALL;
import static org.junit.jupiter.api.Assertions.assertSame;

class BaseVanTypeTest {

    @Test
    void load() {
        Box box1 = new Box(BIG);
        Box box2 = new Box(BIG);
        Box box3 = new Box(BIG);
        List<Box> boxes = new ArrayList<>();
        boxes.add(box1);
        boxes.add(box2);
        boxes.add(box3);

        Van van = new Van(SMALL);

        van.loadAll(boxes);

        assertSame(box1, van.unload());
        assertSame(box2, van.unload());
        assertSame(null, van.unload());
    }
}