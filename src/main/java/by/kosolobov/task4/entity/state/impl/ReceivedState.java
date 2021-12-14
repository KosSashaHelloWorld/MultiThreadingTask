package by.kosolobov.task4.entity.state.impl;

import by.kosolobov.task4.entity.packages.Box;
import by.kosolobov.task4.entity.state.BoxState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReceivedState implements BoxState {
    private static final Logger log = LogManager.getLogger(ReceivedState.class);

    @Override
    public void next(Box pkg) {
        log.log(Level.WARN, "Box was already received.");
    }

    @Override
    public void prev(Box pkg) {
        pkg.setState(new DeliveredState());
    }

    @Override
    public void printStatus() {
        log.log(Level.INFO, "Box was received");
    }

    @Override
    public String toString() {
        return "received";
    }
}
