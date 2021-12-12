package by.kosolobov.task4.entity;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.packages.impl.BigPackage;
import by.kosolobov.task4.entity.state.impl.DeliveredState;
import by.kosolobov.task4.entity.state.impl.OrderedState;
import by.kosolobov.task4.entity.state.impl.ReceivedState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasePackageTest {

    @Test
    void getState() {
        //verifying package can change state forward and back

        BasePackage pack = new BigPackage();

        assertTrue(pack.getState() instanceof OrderedState);
        pack.nextState();

        assertTrue(pack.getState() instanceof DeliveredState);
        pack.nextState();

        assertTrue(pack.getState() instanceof ReceivedState);
        pack.prevState();

        assertTrue(pack.getState() instanceof DeliveredState);
        pack.prevState();

        assertTrue(pack.getState() instanceof OrderedState);
    }

    @Test
    void checkLogs() {
        BasePackage pack = new BigPackage();

        pack.printStatus();
        pack.nextState();
        pack.printStatus();
        pack.nextState();
        pack.printStatus();
        pack.nextState();
        pack.printStatus();

        //without assertions: check logs
        assertTrue(true);
    }
}