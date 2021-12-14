package by.kosolobov.task4.entity.van;

public enum VanType {
    EXPRESS {
        @Override
        public int getStorageLimit() {
            return 80;
        }

        @Override
        public int getSpeed() {
            return 200;
        }
    },
    SMALL {
        @Override
        public int getStorageLimit() {
            return 200;
        }

        @Override
        public int getSpeed() {
            return 120;
        }
    },
    MEDIUM {
        @Override
        public int getStorageLimit() {
            return 500;
        }

        @Override
        public int getSpeed() {
            return 90;
        }
    },
    BIG {
        @Override
        public int getStorageLimit() {
            return 2000;
        }

        @Override
        public int getSpeed() {
            return 60;
        }
    },
    DEFAULT {
        @Override
        public int getStorageLimit() {
            return 500;
        }

        @Override
        public int getSpeed() {
            return 120;
        }
    };

    public abstract int getStorageLimit();
    public abstract int getSpeed();
}
