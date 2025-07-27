package app.clickablo.model.character;

import app.clickablo.model.items.EquipmentSlot;
import app.clickablo.model.items.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class Character {

    private String name;
    private CharacterClass characterClass;

    private int level;
    private int exp;
    private int gold;

    private Map<Stat, Integer> stats;
    private Map<String, Integer> skillpoints;
    private List<Item> inventory;
    private final Map<EquipmentSlot, Item> equipment = new EnumMap<>(EquipmentSlot.class);
    private static final int START_XP_REQ = 500;
    private static final double XP_GROWTH_FACTOR = 1.35;
    private Map<String, Boolean> monstersDefeated;

    public Character(String name,
                     CharacterClass characterClass,
                     Map<Stat, Integer> baseStats) {
        this.name = name;
        this.characterClass = characterClass;
        this.level = 1;
        this.exp = 0;
        this.gold = 0;
        this.stats = new HashMap<>(baseStats);
        this.inventory = new ArrayList<>();
        this.monstersDefeated = new HashMap<>();
    }

    public void addExp(int amount) {
        exp += amount;
        while (exp >= xpForNextLevel()) {
            exp -= xpForNextLevel();
            level++;
        }
    }

    public int xpForNextLevel() {
        double req = START_XP_REQ * Math.pow(XP_GROWTH_FACTOR, level - 1);
        return (int) Math.floor(req);
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public int getClickDamage() {
        return stats.getOrDefault(Stat.STRENGTH, 1) / 2 + level; // Will need to add different scaling per class
    }

    public void markDefeated(String templateName) {
        monstersDefeated.put(templateName, true);
    }

    public boolean hasDefeated(String templateName) {
        return monstersDefeated.getOrDefault(templateName, false);
    }

    public void equip(Item item) {
        equipment.put(item.getSlot(), item);
    }

    public void unequip(Item item) {
        equipment.remove(item.getSlot());
    }

    public Item getEquipped(EquipmentSlot slot) {
        return equipment.get(slot);
    }

    public int getWeaponBaseDamage() {
        Item main = getEquipped(EquipmentSlot.MAIN_HAND);
        Item off = getEquipped(EquipmentSlot.OFF_HAND);
        Item twoHanded = getEquipped(EquipmentSlot.TWO_HANDED);

        if (twoHanded != null) {
            return twoHanded.getBaseDamage();
        }

        int dmg = 0;
        if (main != null) dmg += main.getBaseDamage();
        if (off != null) dmg += off.getBaseDamage();
        return dmg;
    }

    public int getTotalArmour() {
        int sum = 0;
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            Item it = getEquipped(slot);
            if (it != null && it.getBaseArmour() != null) {
                sum += it.getBaseArmour();
            }
        }
        return sum;
    }

    public int getStat(Stat stat) {
        int base = stats.getOrDefault(stat, 0);
        for (Item it: equipment.values()) {
             base += it.getStatBonuses().getOrDefault(stat, 0);
        }
        return base;
    }

    public int getSkillLevel(String skill) {
        int pt = skillpoints.getOrDefault(skill, 0);
        for (Item it: equipment.values()) {
            pt += it.getSkillBonuses().getOrDefault(skill, 0);
        }
        return pt;
    }

    public double getXpGainMultiplier() {
        double m = 1.0;
        for (Item it: equipment.values()) {
            if (it.getXpGainBonus() != null) m *= it.getXpGainBonus();
        }
        return m;
    }

    public double getGoldGainMultiplier() {
        double m = 1.0;
        for (Item it: equipment.values()) {
            if (it.getGoldGainBonus() != null) m *= it.getGoldGainBonus();
        }
        return m;
    }
}
