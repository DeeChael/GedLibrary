package org.gedstudio.library.bukkit.potion;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.Serializable;

public class GPotionEffect implements Serializable {

    private final String type;
    private final int duration;
    private final int amplifier;
    private final boolean ambient;
    private final boolean particles;
    private final boolean icon;

    /**
     * For GItem can be serializable
     * @param type type
     * @param duration duration
     * @param amplifier amplifier
     * @param ambient ambient
     * @param particles particles
     * @param icon icon
     */
    public GPotionEffect(PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles, boolean icon) {
        this.type = type.getName();
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambient = ambient;
        this.particles = particles;
        this.icon = icon;
    }

    /**
     * To get if is ambient
     * @return boolean
     */
    public boolean isAmbient() {
        return ambient;
    }

    /**
     * To get if potion effect will show particles
     * @return boolean
     */
    public boolean hasParticles() {
        return particles;
    }

    /**
     * To get if potion effect will show icon
     * @return boolean
     */
    public boolean hasIcon() {
        return icon;
    }

    /**
     * To get the level of the potion effect
     * @return potion level
     */
    public int getAmplifier() {
        return amplifier;
    }

    /**
     * To get the potion effect type of the potion effect
     * @return potion effect type
     */
    public PotionEffectType getType() {
        return PotionEffectType.getByName(this.type);
    }

    /**
     * To get the level of the potion effect
     * @return potion level
     */
    public int getLevel() {
        return getAmplifier();
    }

    /**
     * To get the tiem of the potion effect
     * @return potion time
     */
    public int getTime() {
        return getDuration();
    }

    /**
     * To get the tiem of the potion effect
     * @return potion time
     */
    public int getDuration() {
        return duration;
    }

    /**
     * To cast GPotionEffect to Bukkit PotionEffect
     * @return Bukkit PotionEffect
     */
    public PotionEffect toPotionEffect() {
        return new PotionEffect(PotionEffectType.getByName(this.type), duration, amplifier, ambient, particles, icon);
    }

    /**
     * To get GPotionEffect from Bukkit PotionEffect
     * @param potionEffect Bukkit PotionEffect
     * @return GPotionEffect
     */
    public static GPotionEffect fromPotionEffect(PotionEffect potionEffect) {
        return new GPotionEffect(potionEffect.getType(), potionEffect.getDuration(), potionEffect.getAmplifier(), potionEffect.isAmbient(), potionEffect.hasParticles(), potionEffect.hasIcon());
    }

}
