package by.kosolobov.task4.entity.state.impl;

import by.kosolobov.task4.entity.packages.Box;
import by.kosolobov.task4.entity.state.BoxState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeliveredState implements BoxState {
    private static final Logger log = LogManager.getLogger(DeliveredState.class);

    @Override
    public void next(Box pkg) {
        pkg.setState(new ReceivedState());
    }

    @Override
    public void prev(Box pkg) {
        pkg.setState(new OrderedState());
    }

    @Override
    public void printStatus() {
        log.log(Level.INFO, "Box was delivered to office, waiting for receiver.");
    }

    @Override
    public String toString() {
        return "delivered";
    }
}
