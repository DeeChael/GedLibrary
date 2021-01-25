package net.deechael.ged.library.potion;

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

    public GPotionEffect(PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles, boolean icon) {
        this.type = type.getName();
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambient = ambient;
        this.particles = particles;
        this.icon = icon;
    }

    public boolean isAmbient() {
        return ambient;
    }

    public boolean hasParticles() {
        return particles;
    }

    public boolean hasIcon() {
        return icon;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public PotionEffectType getType() {
        return PotionEffectType.getByName(this.type);
    }

    public int getLevel() {
        return getAmplifier();
    }

    public int getTime() {
        return getDuration();
    }

    public int getDuration() {
        return duration;
    }

    public PotionEffect toPotionEffect() {
        return new PotionEffect(PotionEffectType.getByName(this.type), duration, amplifier, ambient, particles, icon);
    }

    public static GPotionEffect fromPotionEffect(PotionEffect potionEffect) {
        return new GPotionEffect(potionEffect.getType(), potionEffect.getDuration(), potionEffect.getAmplifier(), potionEffect.isAmbient(), potionEffect.hasParticles(), potionEffect.hasIcon());
    }

}
