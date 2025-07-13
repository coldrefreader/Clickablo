package app.clickablo.model.monsters;

import app.clickablo.model.shared_features.DamageType;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MonsterTemplate {

    private final String name;
    private final String imagePath;

    private final int baseHealth;
    private final double baseRegen;

    private final Map<DamageType, Double> resistances;

    private final int weight; // probability of being picked

    public Monster instantiate(boolean isChampion, List<Monster> companions) {

        double hpMultiplier = isChampion ? 1.5 : 1.0;
        double regenMultiplier = isChampion ? 1.5 : 1.0;

        int finalHp = (int) (baseHealth * hpMultiplier);
        double finalRegen = baseRegen * regenMultiplier;

        Monster m = new Monster(
                name,
                finalHp,
                finalRegen,
                resistances,
                isChampion,
                companions
        );

        return m;
    }


}
