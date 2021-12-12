package by.kosolobov.task4.entity.packages;

import by.kosolobov.task4.entity.state.BasePackageState;

public interface BasePackage {
    void printStatus();
    void nextState();
    void prevState();
    void setState(BasePackageState newState);
    BasePackageState getState();
    int getWeight();
}
