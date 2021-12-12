package by.kosolobov.task4.entity.state;

import by.kosolobov.task4.entity.packages.BasePackage;

public interface BasePackageState {
    void next(BasePackage pkg);
    void prev(BasePackage pkg);
    void printStatus();
}
