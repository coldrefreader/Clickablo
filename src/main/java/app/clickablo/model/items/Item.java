package app.clickablo.model.items;

import app.clickablo.model.character.Stat;
import app.clickablo.model.shared_features.DamageType;
import lombok.Data;

import java.util.Map;

@Data
public class Item {

    private final String id;
    private final String name;
    private final ItemType type;
    private final EquipmentSlot slot;

    //Damage only for weapons
    private final Integer baseDamage;

    //Armour (stat) only for... armour (equipment)
    private final Integer baseArmour;

    private final Map<Stat, Integer> statBonuses;
    private final Map<String, Integer> skillBonuses;
    private final Map<DamageType, Double> resistanceBonuses;

    private final Double xpGainBonus;
    private final double goldGainBonus;
}
