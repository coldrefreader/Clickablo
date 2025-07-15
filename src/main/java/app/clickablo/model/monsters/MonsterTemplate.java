package app.clickablo.model.monsters;

import app.clickablo.model.shared_features.DamageType;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
public class MonsterTemplate {

    String name;
    String imagePath;

    int baseHealth;
    double baseRegen;

    Map<DamageType, Double> resistances;

    int weight; // probability of being picked

    public Monster instantiate(int monsterLevel, boolean isChampion, List<Monster> companions) {

        double hpMultiplier = isChampion ? 1.5 : 1.0;
        double regenMultiplier = isChampion ? 1.5 : 1.0;

        int finalHp = (int) (baseHealth * hpMultiplier);
        double finalRegen = baseRegen * regenMultiplier;

        Monster m = new Monster(
                name,
                monsterLevel,
                finalHp,
                finalRegen,
                resistances,
                isChampion,
                companions
        );

        return m;
    }


}
