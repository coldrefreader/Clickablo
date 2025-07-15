package app.clickablo.model.monsters;

import app.clickablo.model.shared_features.DamageType;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Data
public class Monster {

    private final String id;
    private final String name;
    private final int level;

    private final int maxHealth;
    private int currentHealth;
    private final double regenPerSecond;
    private final Map<DamageType, Double> resistances;

    private final boolean champion;
    private final List<Monster> companions;

    public Monster(String name, int level,
                   int maxHp,
                   double regenPerSecond,
                   Map<DamageType, Double> resistances,
                   boolean champion,
                   List<Monster> companions) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.level = level;
        this.maxHealth = maxHp;
        this.currentHealth = maxHp;
        this.regenPerSecond = regenPerSecond;
        this.resistances = Map.copyOf(resistances);
        this.champion = champion;
        this.companions = List.copyOf(companions);
    }

    public void takeDamage(int rawDamage, DamageType type) {
        double multiplier = resistances.getOrDefault(type, 1.0);
        int effective = (int)(rawDamage * multiplier);
        currentHealth = Math.max(0, currentHealth - effective);
    }

    public void regenTick(double seconds) {
        if (currentHealth > 0) {
            currentHealth = Math.min(maxHealth, currentHealth + (int)(regenPerSecond * seconds));
        }
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }

    public int getGoldReward() {
        int min = level;
        int max = level * 6;
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

}
