package by.kosolobov.task4.entity.state.impl;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.state.BasePackageState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeliveredState implements BasePackageState {
    private static final Logger log = LogManager.getLogger(DeliveredState.class);

    @Override
    public void next(BasePackage pkg) {
        pkg.setState(new ReceivedState());
    }

    @Override
    public void prev(BasePackage pkg) {
        pkg.setState(new OrderedState());
    }

    @Override
    public void printStatus() {
        log.log(Level.INFO, "Package delivered to office, waiting for receiver.");
    }

    @Override
    public String toString() {
        return String.format("delivered");
    }
}
