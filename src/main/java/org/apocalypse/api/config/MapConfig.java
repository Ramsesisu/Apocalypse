package org.apocalypse.api.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MapConfig {
    private String name;
    private LocationConfig spawn;
    private List<String> monsters;
    private List<AreaConfig> areas;
    private List<String> loot;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class LocationConfig {
        private int x, y, z;
        private int yaw;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class AreaConfig {
        private String name;
        private LocationConfig location;
        private List<SpawnConfig> spawns;
        private LootConfig loot;
        private List<DoorConfig> doors;
        private List<ArmorStorageConfig> armorStorages;
        private List<WeaponStorageConfig> weaponStorages;

        @Getter
        @Setter
        @NoArgsConstructor
        public static class SpawnConfig {
            private LocationConfig location;
            private BarrierConfig barrier;

            @Getter
            @Setter
            @NoArgsConstructor
            public static class BarrierConfig {
                private LocationConfig start;
                private LocationConfig end;
            }
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class LootConfig {
            private LocationConfig location;
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class DoorConfig {
            private String name;
            private LocationConfig first;
            private LocationConfig second;
            private LocationConfig hologram;
            private int cost;
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class WeaponStorageConfig {
            private String name;
            private LocationConfig location;
            private String weapon;
            private int cost;
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class ArmorStorageConfig {
            private String name;
            private LocationConfig location;
            private List<String> armor;
            private int cost;
        }
    }
}