package by.kosolobov.task4.entity;

import by.kosolobov.task4.entity.packages.Box;
import by.kosolobov.task4.entity.van.Van;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class LogisticBase {
    private static final Logger log = LogManager.getLogger(LogisticBase.class);
    private static final ReentrantLock locker = new ReentrantLock(true);
    private static LogisticBase instance = null;
    private final List<Box> mainStorage = new ArrayList<>();
    private final List<Box> temporalStorage = new ArrayList<>();
    private final List<Van> temporalGarage = new ArrayList<>();
    private final List<Van> mainGarage = new ArrayList<>();
    private Van currentVan;

    private LogisticBase() {
    }

    public static LogisticBase getInstance() {
        locker.lock();
        try {
            if (instance == null) {
                instance = new LogisticBase();
            }
        } finally {
            locker.unlock();
        }
        return instance;
    }

    public List<Box> getMainStorage() {
        return mainStorage.subList(0, mainStorage.size());
    }

    public List<Box> getTemporalStorage() {
        return temporalStorage.subList(0, temporalStorage.size());
    }

    public List<Van> getMainGarage() {
        return mainGarage.subList(0, mainGarage.size());
    }

    public List<Van> getTemporalGarage() {
        return temporalGarage.subList(0, temporalGarage.size());
    }

    public void unloadVan() {
        locker.lock();
        try {
            while (currentVan.hasPackages()) {
                Box deliveredPackage = currentVan.unload();
                deliveredPackage.nextState();
                temporalStorage.add(deliveredPackage);
            }

            log.log(Level.INFO, "{} is unloaded.", currentVan);

            mainGarage.add(currentVan);
            currentVan = null;
        } finally {
            locker.unlock();
        }
    }

    public void loadVan() {
        locker.lock();
        try {
            if (currentVan == null && mainGarage.isEmpty()) {
                log.log(Level.WARN, "There are no any free Van to load");
                return;
            } else if (currentVan == null) {
                currentVan = mainGarage.get(0);
                mainGarage.remove(currentVan);
            }

            currentVan.loadAll(mainStorage);

            log.log(Level.INFO, "{} is loaded.", currentVan);
        } finally {
            locker.unlock();
        }
    }

    public void notifyVanArrived(Van van) {
        locker.lock();
        try {
            if (currentVan == null) {
                currentVan = van;
            } else {
                temporalGarage.add(van);
            }
            unloadVan();
        } finally {
            locker.unlock();
        }
    }

    @Override
    public String toString() {
        return "LogisticBase";
    }
}











