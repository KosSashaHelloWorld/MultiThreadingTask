package by.kosolobov.task4.entity.state;

import by.kosolobov.task4.entity.packages.Box;

public interface BoxState {
    void next(Box pkg);
    void prev(Box pkg);
    void printStatus();
}
