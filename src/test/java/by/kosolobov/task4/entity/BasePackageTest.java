package by.kosolobov.task4.entity;

import by.kosolobov.task4.entity.packages.Box;
import by.kosolobov.task4.entity.state.impl.DeliveredState;
import by.kosolobov.task4.entity.state.impl.OrderedState;
import by.kosolobov.task4.entity.state.impl.ReceivedState;
import by.kosolobov.task4.entity.state.impl.UnorderedState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BasePackageTest {

    @Test
    void getState() {
        //verifying package can change state forward and back

        Box pack = new Box();

        assertInstanceOf(UnorderedState.class, pack.getState());
        pack.nextState();
        assertInstanceOf(OrderedState.class, pack.getState());
        pack.nextState();
        assertInstanceOf(DeliveredState.class, pack.getState());
        pack.nextState();
        assertInstanceOf(ReceivedState.class, pack.getState());

        pack.prevState();
        assertInstanceOf(DeliveredState.class, pack.getState());
        pack.prevState();
        assertInstanceOf(OrderedState.class, pack.getState());
        pack.prevState();
        assertInstanceOf(UnorderedState.class, pack.getState());
    }

    @Test
    void checkLogs() {
        Box pack = new Box();

        pack.prevState();
        pack.printStatus();
        pack.nextState();
        pack.printStatus();
        pack.nextState();
        pack.printStatus();
        pack.nextState();
        pack.printStatus();
        pack.nextState();

        //without assertions: check logs
        assertTrue(true);
    }
}