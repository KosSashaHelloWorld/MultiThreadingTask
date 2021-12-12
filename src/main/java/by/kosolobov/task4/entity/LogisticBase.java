package by.kosolobov.task4.entity;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.van.BaseVan;
import by.kosolobov.task4.sevice.LogisticBaseService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LogisticBase extends Thread{
    private static final Logger log = LogManager.getLogger(LogisticBase.class);
    private static final LogisticBaseService service = new LogisticBaseService();
    private static final TimeUnit checkTime = TimeUnit.SECONDS;
    private final List<BasePackage> mainStorage = new ArrayList<>();
    private final List<BasePackage> temporalStorage = new ArrayList<>();
    private List<BaseVan> garage = new ArrayList<>();
    private BaseVan currentVan;

    public List<BasePackage> getMainStorage() {
        return mainStorage.subList(0, mainStorage.size());
    }

    public List<BasePackage> getTemporalStorage() {
        return temporalStorage.subList(0, temporalStorage.size());
    }

    public LogisticBase() {
        start();
    }

    public List<BaseVan> getGarage() {
        return garage;
    }

    public BaseVan getCurrentVan() {
        return currentVan;
    }

    public void setCurrentVan(BaseVan currentVan) {
        this.currentVan = currentVan;
    }

    private void unloadVan() {
        while (currentVan.hasPackages()) {
            BasePackage pkg = currentVan.unload();
            temporalStorage.add(pkg);
        }

        log.log(Level.INFO, "{} unloaded.", currentVan);
    }

    public void loadVan() {
        for (BasePackage pkg : mainStorage) {
            currentVan.load(pkg);
        }

        log.log(Level.INFO, "{} loaded.", currentVan);

        if (!garage.isEmpty()) {
            currentVan = garage.get(0);
            garage.remove(currentVan);
        } else {
            currentVan = null;
        }
    }

    public void deliverVanTo(BaseVan van, LogisticBase destination) {
        van.deliverTo(destination);
    }

    @Override
    public void run() {
        while (true) {
            if (currentVan == null && garage.isEmpty()) {
                try {
                    checkTime.sleep(3);
                } catch (InterruptedException e) {
                    log.log(Level.ERROR, "Base-{} was interrupted: {}", getId(), e.getMessage());
                }
            } else if (currentVan == null) {
                currentVan = garage.get(0);
                garage.remove(currentVan);
                unloadVan();
                currentVan = null;
            } else {
                unloadVan();
                currentVan = null;
            }
        }
    }

    public void execute() {
        service.execute(this);
    }
}











