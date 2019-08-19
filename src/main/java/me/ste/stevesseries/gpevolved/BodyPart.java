package me.ste.stevesseries.gpevolved;

import java.util.function.Function;
import java.util.function.Supplier;

public class BodyPart {
    private int durability;
    private int maxDurability;
    private int level;
    private Function<BodyPart, Integer> maxDurabilityPerLevel;
    private boolean levelingEnabled;

    public BodyPart(int durability, int maxDurability, int level, Function<BodyPart, Integer> maxDurabilityPerLevel, boolean levelingEnabled) {
        this.durability = Math.min(durability, maxDurability);
        this.maxDurability = maxDurability;
        this.level = levelingEnabled ? level : -1;
        this.maxDurabilityPerLevel = maxDurabilityPerLevel;
        this.levelingEnabled = levelingEnabled;
    }

    public BodyPart(int maxDurability, Function<BodyPart, Integer> maxDurabilityPerLevel, boolean levelingEnabled) {
        this(maxDurability, maxDurability, 0, maxDurabilityPerLevel, levelingEnabled);
    }

    public int getDurability() {
        return durability;
    }
    public void setDurability(int durability) {
        this.durability = durability;
    }
    public int getMaxDurability() {
        return maxDurability;
    }
    public int getRealMaxDurability() {
        return maxDurability + getLevelMaxDurability();
    }
    public void setMaxDurability(int maxDurability) {
        this.maxDurability = maxDurability;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        if(!isLevelingEnabled()) return;
        this.level = level;
    }
    public void levelUp() {
        if(!isLevelingEnabled()) return;
        level++;
    }
    public int getLevelMaxDurability() {
        return getMaxDurabilityPerLevel() * level;
    }
    public int getMaxDurabilityPerLevel() {
        return maxDurabilityPerLevel.apply(this);
    }
    public Function<BodyPart, Integer> getMaxDurabilityPerLevelCalculator() {
        return maxDurabilityPerLevel;
    }
    public void setMaxDurabilityPerLevelCalculator(Function<BodyPart, Integer> maxDurabilityPerLevel) {
        this.maxDurabilityPerLevel = maxDurabilityPerLevel;
    }
    public boolean isLevelingEnabled() {
        return levelingEnabled;
    }
    public void setLevelingEnabled(boolean levelingEnabled) {
        if(this.levelingEnabled != levelingEnabled) {
            if(levelingEnabled) {
                this.level = 0;
            }
            else {
                this.level = -1;
            }
            this.levelingEnabled = levelingEnabled;
        }
    }
}