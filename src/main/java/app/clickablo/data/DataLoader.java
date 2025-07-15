package app.clickablo.data;

import app.clickablo.model.Zone;
import app.clickablo.model.monsters.MonsterTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataLoader {

    private final ObjectMapper mapper = new ObjectMapper();

    public List<MonsterTemplate> loadMonsterTemplates() throws Exception {
        try (InputStream in = getClass().getResourceAsStream("/data/monsters.json")) {
            return mapper.readValue(in, new TypeReference<>() {});
        }
    }

    public List<Zone> loadZones() throws Exception {
        try (InputStream in = getClass().getResourceAsStream("/data/zones.json")) {
            Map<String, List<String>> raw = mapper.readValue(in, new TypeReference<>() {});

            List<Zone> zones = new ArrayList<>();
            for (Map.Entry<String, List<String>> entry : raw.entrySet()) {
                String zoneName = entry.getKey();
                List<String> encounters = entry.getValue();
                zones.add(new Zone(zoneName, encounters));
            }

            return zones;
        }
    }
}
