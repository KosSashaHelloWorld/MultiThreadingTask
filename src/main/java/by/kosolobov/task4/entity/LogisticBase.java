package by.kosolobov.task4.entity;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.van.BaseVan;
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
    private final List<BasePackage> mainStorage = new ArrayList<>();
    private final List<BasePackage> temporalStorage = new ArrayList<>();
    private final List<BaseVan> temporalGarage = new ArrayList<>();
    private final List<BaseVan> mainGarage = new ArrayList<>();
    private BaseVan currentVan;

    private LogisticBase() {
    }

    public static LogisticBase getInstance() {
        locker.lock();
        if (instance == null) {
            instance = new LogisticBase();
        }
        locker.unlock();
        return instance;
    }

    public List<BasePackage> getMainStorage() {
        return mainStorage.subList(0, mainStorage.size());
    }

    public List<BasePackage> getTemporalStorage() {
        return temporalStorage.subList(0, temporalStorage.size());
    }

    public List<BaseVan> getMainGarage() {
        return mainGarage.subList(0, mainGarage.size());
    }

    public List<BaseVan> getTemporalGarage() {
        return temporalGarage.subList(0, temporalGarage.size());
    }

    public void unloadVan() {
        locker.lock();
        while (currentVan.hasPackages()) {
            BasePackage deliveredPackage = currentVan.unload();
            deliveredPackage.nextState();
            temporalStorage.add(deliveredPackage);
        }

        log.log(Level.INFO, "{} is unloaded.", currentVan);

        mainGarage.add(currentVan);
        currentVan = null;
        locker.unlock();
    }

    public void loadVan() {
        locker.lock();
        if (currentVan == null && mainGarage.isEmpty()) {
            log.log(Level.WARN, "There are no any free Van to load");
            return;
        } else if (currentVan == null) {
            currentVan = mainGarage.get(0);
            mainGarage.remove(currentVan);
        }

        currentVan.loadAll(mainStorage);

        log.log(Level.INFO, "{} is loaded.", currentVan);
        locker.unlock();
    }

    public void notifyVanArrived(BaseVan van) {
        locker.lock();
        if (currentVan == null) {
            currentVan = van;
        } else {
            temporalGarage.add(van);
        }
        unloadVan();
        locker.unlock();
    }

    @Override
    public String toString() {
        return "LogisticBase";
    }
}











