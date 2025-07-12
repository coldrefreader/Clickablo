package app.clickablo.model.monsters;

import app.clickablo.model.shared_features.DamageType;
import lombok.Data;

import java.util.Map;

@Data
public class MonsterTemplate {

    private final String name;
    private final String imagePath;

    private final int baseHealth;
    private final double baseRegen;

    private final Map<DamageType, Double> resistances;

    private final int weight; // probability of being picked


}
