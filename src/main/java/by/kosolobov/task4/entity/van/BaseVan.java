package by.kosolobov.task4.entity.van;

import by.kosolobov.task4.entity.packages.BasePackage;
import by.kosolobov.task4.entity.LogisticBase;
import by.kosolobov.task4.sevice.VanService;
import by.kosolobov.task4.util.VanIdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BaseVan extends Thread {
    private static final Logger log = LogManager.getLogger(BaseVan.class);
    private static final VanService service = new VanService();
    private final int storageLimit;
    private final int speed;
    private final TimeUnit deliveringTime;
    private List<BasePackage> storage = new ArrayList<>();
    private LogisticBase destination = null;

    protected BaseVan(int storageLimit, int speed) {
        this.storageLimit = storageLimit;
        this.speed = speed;
        deliveringTime = TimeUnit.SECONDS;
    }

    public void setDestination(LogisticBase destination) {
        this.destination = destination;
    }

    public boolean hasSpaceFor(BasePackage pkg) {
        int stored = 0;
        for (BasePackage inVan : storage) {
            stored += inVan.getWeight();
        }

        return stored + pkg.getWeight() <= storageLimit;
    }

    public void load(BasePackage pkg) {
        if (hasSpaceFor(pkg)) {
            storage.add(pkg);
        }
    }

    public void loadAll(List<BasePackage> packages) {
        for (BasePackage pkg : packages) {
            load(pkg);
        }
        packages.removeAll(storage);
    }

    public void deliverTo(LogisticBase destination) {
        this.destination = destination;
        log.log(Level.INFO, "{} moving to Base-{}.", this, destination.getId());

        try {
            deliveringTime.sleep(600 / speed);
        } catch (InterruptedException e) {
            log.log(Level.ERROR, "{} was interrupted: {}", this, e.getMessage());

        }

        log.log(Level.INFO, "{} arrived to Base-{}", this, destination.getId());

        if (destination.getCurrentVan() == null) {
            destination.setCurrentVan(this);
        } else {
            destination.getGarage().add(this);
        }

        destination.execute();
    }

    public boolean hasPackages() {
        return !storage.isEmpty();
    }

    public BasePackage unload() {
        if (storage.isEmpty()) {
            return null;
        }
        BasePackage toUnload = storage.get(0);
        storage.remove(toUnload);
        toUnload.nextState();
        return toUnload;
    }

    @Override
    public void run() {
        while (destination == null) {
            try {
                deliveringTime.sleep(5);
            } catch (InterruptedException e) {
                log.log(Level.ERROR, "{} was interrupted: {}", this, e.getMessage());
            }
        }

        deliverTo(destination);
    }

    public void execute() {
        service.execute(this);
    }
}
