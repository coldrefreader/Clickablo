package app.clickablo.manager;

import app.clickablo.model.Character;
import app.clickablo.model.Zone;
import app.clickablo.model.monsters.Monster;
import app.clickablo.model.monsters.MonsterTemplate;


import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class GameManager {

    private final Map<String, MonsterTemplate> templatesByName;
    private final List<Zone> zones;
    private final Random rng = new Random();

    private Character currentCharacter;
    private Zone currentZone;
    private Monster currentMonster;

    public GameManager(List<MonsterTemplate> templates, List<Zone> zones) {
        this.templatesByName = templates.stream()
                .collect(Collectors.toMap(MonsterTemplate::getName, t -> t));
        this.zones = zones;
    }

    public void setCharacter(Character c) { this.currentCharacter = c; }
    public Character getCharacter() { return currentCharacter; }

    public void enterZone(Zone z) {
        this.currentZone = z;
        z.reset();
    }

    public Monster spawnNextMonster() {
        String tmplName = currentZone.peekNextTemplate();
        MonsterTemplate tmpl = templatesByName.get(tmplName);

        boolean isChamp = rng.nextDouble() < 0.1;
        boolean isRare = rng.nextDouble() < 0.05;
        List<Monster> companions = List.of();
        Monster m = tmpl.instantiate(isChamp, companions);
        currentMonster = m;
        if (!isRare) currentZone.advance();
        return m;
    }

    public void onMonsterDefeat() {
        currentCharacter.addExp(currentMonster.getExpReward());
        currentCharacter.addGold(currentMonster.getGoldReward());
    }

    public void saveCharacter() {}
    public void loadCharacter() {String savePath}

}
