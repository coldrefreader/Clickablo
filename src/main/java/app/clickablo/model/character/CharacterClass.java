package app.clickablo.model.character;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

@Getter
public enum CharacterClass {
    NECROMANCER    ("Essence", EnumSet.of(Stat.INTELLIGENCE)),
    ASSASSIN       ("Energy", EnumSet.of(Stat.DEXTERITY, Stat.WISDOM)),
    BARBARIAN      ("Rage", EnumSet.of(Stat.STRENGTH)),
    SORCERESS      ("Mana", EnumSet.of(Stat.INTELLIGENCE)),
    DRUID          ("Spirit", EnumSet.of(Stat.WISDOM)),
    PALADIN        ("Faith", EnumSet.of(Stat.STRENGTH, Stat.WISDOM)),
    AMAZON         ("Focus", EnumSet.of(Stat.DEXTERITY, Stat.WISDOM)),
    WIZARD         ("Arcane Power", EnumSet.of(Stat.INTELLIGENCE)),
    CRUSADER       ("Mana", EnumSet.of(Stat.STRENGTH, Stat.INTELLIGENCE)),
    WITCH_DOCTOR   ("Mana", EnumSet.of(Stat.INTELLIGENCE, Stat.WISDOM)),
    DEMON_HUNTER   ("Energy", EnumSet.of(Stat.DEXTERITY)),
    ;

    private final String resourceName;
    private final Set<Stat> scalingStats;

    CharacterClass(String resourceName, Set<Stat> scalingStats) {
        this.resourceName = resourceName;
        this.scalingStats = scalingStats;
    }

}
