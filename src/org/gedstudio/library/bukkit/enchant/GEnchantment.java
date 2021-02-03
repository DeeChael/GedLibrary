package org.gedstudio.library.bukkit.enchant;

import org.bukkit.enchantments.Enchantment;

import java.io.Serializable;

public enum GEnchantment implements Serializable {

    PROTECTION(Enchantment.PROTECTION_ENVIRONMENTAL),
    FIRE_PROTECTION(Enchantment.PROTECTION_FIRE),
    FEATHER_FALLING(Enchantment.PROTECTION_FALL),
    BLAST_PROTECTION(Enchantment.PROTECTION_EXPLOSIONS),
    PROJECTILE_PROTECTION(Enchantment.PROTECTION_PROJECTILE),
    RESPIRATION(Enchantment.OXYGEN),
    AQUA_AFFINITY(Enchantment.WATER_WORKER),
    THORNS(Enchantment.THORNS),
    DEPTH_STRIDER(Enchantment.DEPTH_STRIDER),
    FROST_WALKER(Enchantment.FROST_WALKER),
    BINDING_CURSE(Enchantment.BINDING_CURSE),
    SHARPNESS(Enchantment.DAMAGE_ALL),
    SMITE(Enchantment.DAMAGE_UNDEAD),
    BANE_OF_ARTHROPODS(Enchantment.DAMAGE_ARTHROPODS),
    KNOCKBACK(Enchantment.KNOCKBACK),
    FIRE_ASPECT(Enchantment.FIRE_ASPECT),
    LOOTING(Enchantment.LOOT_BONUS_MOBS),
    SWEEPING(Enchantment.SWEEPING_EDGE),
    EFFICIENCY(Enchantment.DIG_SPEED),
    SILK_TOUCH(Enchantment.SILK_TOUCH),
    UNBREAKING(Enchantment.DURABILITY),
    FORTUNE(Enchantment.LOOT_BONUS_BLOCKS),
    POWER(Enchantment.ARROW_DAMAGE),
    PUNCH(Enchantment.ARROW_KNOCKBACK),
    FLAME(Enchantment.ARROW_FIRE),
    INFINITY(Enchantment.ARROW_INFINITE),
    LUCK_OF_THE_SEA(Enchantment.LUCK),
    LURE(Enchantment.LURE),
    LOYALTY(Enchantment.LOYALTY),
    IMPALING(Enchantment.IMPALING),
    RIPTIDE(Enchantment.RIPTIDE),
    CHANNELING(Enchantment.CHANNELING),
    MULTISHOT(Enchantment.MULTISHOT),
    QUICK_CHARGE(Enchantment.QUICK_CHARGE),
    PIERCING(Enchantment.PIERCING),
    MENDING(Enchantment.MENDING),
    VANISHING_CURSE(Enchantment.VANISHING_CURSE),
    SOUL_SPEED(Enchantment.SOUL_SPEED);

    private transient Enchantment enchantment;
    private String name;

    GEnchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
        this.name = this.enchantment.getKey().getKey();
    }

    /**
     * To get Bukkit Enchantment
     * @return Bukkit Enchantment
     */
    public Enchantment getHandle() {
        return this.enchantment;
    }

    /**
     * To get GEnchantment from Bukkit Enchantment
     * @param enchantment Bukkit Enchantment
     * @return GEnchantment
     */
    public static GEnchantment valueOf(Enchantment enchantment) {
        for (GEnchantment gEnchantment : GEnchantment.values()) {
            if (gEnchantment.getHandle().equals(enchantment)) {
                return gEnchantment;
            }
        }
        return PROTECTION;
    }

    /**
     * To get GEnchantment from the name of Bukkit Enchantment
     * <p>Bukkit Enchantment name has some difference with GEnchantment</p>
     * @param name the name of Bukkit Enchantment
     * @return GEnchantment
     */
    public static GEnchantment valueOfEnchantment(String name) {
        for (GEnchantment gEnchantment : GEnchantment.values()) {
            if (gEnchantment.getHandle().getKey().getKey().equals(name)) {
                return gEnchantment;
            }
        }
        return PROTECTION;
    }

}
