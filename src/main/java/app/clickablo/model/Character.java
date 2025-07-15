package app.clickablo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class Character {

    private String name;
    private CharacterClass characterClass;

    private int level;
    private int exp;
    private int gold;

    private Map<Stat, Integer> stats;
    private List<Item> inventory;
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

}
