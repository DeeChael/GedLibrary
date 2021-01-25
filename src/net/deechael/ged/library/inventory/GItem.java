package net.deechael.ged.library.inventory;

import net.deechael.ged.library.GLocation;
import net.deechael.ged.library.GedKey;
import net.deechael.ged.library.GedLibrary;
import net.deechael.ged.library.attribute.GAttributeModifier;
import net.deechael.ged.library.block.banner.GPattern;
import net.deechael.ged.library.enchant.GEnchantment;
import net.deechael.ged.library.potion.GPotionData;
import net.deechael.ged.library.potion.GPotionEffect;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TropicalFish;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionEffect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GItem implements Serializable, Cloneable {

    //ItemStack
    //Won't save when GItem was saved into a file
    private transient ItemStack itemStack;

    //All Item
    private Material type = Material.AIR;
    private int amount = 1;
    private String displayName = null;
    private String localizedName = null;
    private int customModelData = 0;
    private boolean unbreakable = false;
    private HashSet<ItemFlag> itemFlags;
    private ArrayList<String> lore;
    private HashMap<GEnchantment, Integer> enchantments; //If item is enchantment book it will replace by stored enchantments
    private ArrayList<GAttributeModifier> attributes;

    //Damageable
    private int damage = 0;
    //Repairable
    private int repairCost = 0;

    //Skull Item
    private String skullOwner = null;
    //Potion Item
    private GPotionData basePotion = new GPotionData(null, false, false);
    private int potionColor = Color.WHITE.asRGB();
    private ArrayList<GPotionEffect> potionEffects; //If item is suspicious stew it will replace by suspicious stew's potion effects
    //Banner Item
    private ArrayList<GPattern> patterns;
    //Book Item
    private String title = null;
    private String author = null;
    private ArrayList<String> pages;
    private BookMeta.Generation generation = BookMeta.Generation.ORIGINAL;
    //Compass Item
    private GLocation lodestone = new GLocation(Bukkit.getWorlds().get(0).getSpawnLocation());
    private boolean lodestoneTracked = false;
    //Firework Item
    private ArrayList<FireworkEffect> fireworkEffects; //Firework Star has only one, so Firework Star's FireworkEffect is fireworkEffects.get(0);
    private int power = 0;
    //Knowledge Book Item
    private ArrayList<GedKey> recipes;
    //Leather Armor Item
    private int color = Color.WHITE.asRGB();
    //Spawn Egg Item
    private EntityType entityType = EntityType.ZOMBIE;
    //Fish Bucket Item
    private DyeColor bodyColor = DyeColor.WHITE;
    private TropicalFish.Pattern pattern = TropicalFish.Pattern.BRINELY;
    private DyeColor patternColor = DyeColor.WHITE;

    public GItem(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.type = this.itemStack.getType();
        this.amount = this.itemStack.getAmount();
        if (this.itemStack.getItemMeta().hasDisplayName()) {
            this.displayName = this.itemStack.getItemMeta().getDisplayName();
        }
        if (this.itemStack.getItemMeta().hasCustomModelData()) {
            this.customModelData = this.itemStack.getItemMeta().getCustomModelData();
        }
        this.itemFlags = new HashSet<>();
        for (ItemFlag itemFlag : this.itemStack.getItemMeta().getItemFlags()) {
            this.itemFlags.add(itemFlag);
        }
        this.unbreakable = this.itemStack.getItemMeta().isUnbreakable();
        this.lore = new ArrayList<>();
        if (this.itemStack.getItemMeta().hasLore()) {
            for (String line : this.itemStack.getItemMeta().getLore()) {
                this.lore.add(line);
            }
        }
        if (this.itemStack.getItemMeta().hasLocalizedName()) {
            this.localizedName = this.itemStack.getItemMeta().getLocalizedName();
        }
        this.enchantments = new HashMap<>();
        if (this.itemStack.getItemMeta().hasEnchants()) {
            for (Enchantment enchantment : this.itemStack.getItemMeta().getEnchants().keySet()) {
                this.enchantments.put(GEnchantment.valueOfEnchantment(enchantment.getKey().getKey()), this.itemStack.getItemMeta().getEnchants().get(enchantment));
            }
        }
        this.attributes = new ArrayList<>();
        if (this.itemStack.getItemMeta().hasAttributeModifiers()) {
            for (Attribute attribute : this.itemStack.getItemMeta().getAttributeModifiers().keySet()) {
                for (AttributeModifier attributeModifier : this.itemStack.getItemMeta().getAttributeModifiers().get(attribute)) {
                    this.attributes.add(GAttributeModifier.fromAttributeModifier(attribute, attributeModifier));
                }
            }
        }
        if (this.itemStack.getItemMeta() instanceof Damageable) {
            if (((Damageable) this.itemStack.getItemMeta()).hasDamage()) {
                this.damage = ((Damageable) this.itemStack.getItemMeta()).getDamage();
            }
        }
        if (this.itemStack.getItemMeta() instanceof Repairable) {
            if (((Repairable) this.itemStack.getItemMeta()).hasRepairCost()) {
                this.repairCost = ((Repairable) this.itemStack.getItemMeta()).getRepairCost();
            }
        }
        if (this.type.equals(Material.PLAYER_HEAD)) {
            if (((SkullMeta) this.itemStack.getItemMeta()).hasOwner()) {
                this.skullOwner = ((SkullMeta) this.itemStack.getItemMeta()).getOwningPlayer().getName();
            }
        }
        potionEffects = new ArrayList<>();
        if (this.type.equals(Material.POTION) || this.type.equals(Material.SPLASH_POTION) || this.type.equals(Material.LINGERING_POTION)) {
            if (((PotionMeta) this.itemStack.getItemMeta()).hasColor()) {
                this.potionColor = ((PotionMeta) this.itemStack.getItemMeta()).getColor().asRGB();
            }
            this.basePotion = GPotionData.fromPotionData(((PotionMeta) this.itemStack.getItemMeta()).getBasePotionData());
            if (((PotionMeta) this.itemStack.getItemMeta()).hasCustomEffects()) {
                for (PotionEffect potionEffect : ((PotionMeta) this.itemStack.getItemMeta()).getCustomEffects()) {
                    this.potionEffects.add(GPotionEffect.fromPotionEffect(potionEffect));
                }
            }
        }
        patterns = new ArrayList<>();
        if (this.type.equals(Material.BLACK_BANNER) ||
            this.type.equals(Material.BLUE_BANNER) ||
            this.type.equals(Material.BROWN_BANNER) ||
            this.type.equals(Material.CYAN_BANNER) ||
            this.type.equals(Material.GRAY_BANNER) ||
            this.type.equals(Material.GREEN_BANNER) ||
            this.type.equals(Material.LIGHT_BLUE_BANNER) ||
            this.type.equals(Material.LIGHT_GRAY_BANNER) ||
            this.type.equals(Material.LIME_BANNER) ||
            this.type.equals(Material.MAGENTA_BANNER) ||
            this.type.equals(Material.PINK_BANNER) ||
            this.type.equals(Material.PURPLE_BANNER) ||
            this.type.equals(Material.RED_BANNER) ||
            this.type.equals(Material.ORANGE_BANNER) ||
            this.type.equals(Material.WHITE_BANNER) ||
            this.type.equals(Material.YELLOW_BANNER)
        ) {
            for (Pattern pattern : ((BannerMeta) this.itemStack.getItemMeta()).getPatterns()) {
                this.patterns.add(GPattern.fromPattern(pattern));
            }
        }
        pages = new ArrayList<>();
        if (this.type.equals(Material.WRITABLE_BOOK) || this.type.equals(Material.WRITTEN_BOOK)) {
            if (((BookMeta) this.itemStack.getItemMeta()).hasTitle()) {
                this.title = ((BookMeta) this.itemStack.getItemMeta()).getTitle();
            }
            if (((BookMeta) this.itemStack.getItemMeta()).hasAuthor()) {
                this.author = ((BookMeta) this.itemStack.getItemMeta()).getAuthor();
            }
            if (((BookMeta) this.itemStack.getItemMeta()).hasPages()) {
                for (String page : ((BookMeta) this.itemStack.getItemMeta()).getPages()) {
                    this.pages.add(page);
                }
            }
            if (((BookMeta) this.itemStack.getItemMeta()).hasGeneration()) {
                this.generation = ((BookMeta) this.itemStack.getItemMeta()).getGeneration();
            }
        }
        if (this.type.equals(Material.COMPASS)) {
            if (((CompassMeta) this.itemStack.getItemMeta()).hasLodestone()) {
                this.lodestone = new GLocation(((CompassMeta) this.itemStack.getItemMeta()).getLodestone());
            }
            this.lodestoneTracked = ((CompassMeta) this.itemStack.getItemMeta()).isLodestoneTracked();
        }
        if (this.type.equals(Material.ENCHANTED_BOOK)) {
            this.enchantments.clear();
            if (((EnchantmentStorageMeta) this.itemStack.getItemMeta()).hasStoredEnchants()) {
                for (Enchantment enchantment : ((EnchantmentStorageMeta) this.itemStack.getItemMeta()).getStoredEnchants().keySet()) {
                    this.enchantments.put(GEnchantment.valueOf(enchantment), ((EnchantmentStorageMeta) this.itemStack.getItemMeta()).getStoredEnchants().get(enchantment));
                }
            }
        }
        this.fireworkEffects = new ArrayList<>();
        if (this.type.equals(Material.FIREWORK_STAR)) {
            if (((FireworkEffectMeta) this.itemStack.getItemMeta()).hasEffect()) {
                this.fireworkEffects.add(((FireworkEffectMeta) this.itemStack.getItemMeta()).getEffect());
            }
        }
        if (this.type.equals(Material.FIREWORK_ROCKET)) {
            if (((FireworkMeta) this.itemStack.getItemMeta()).hasEffects()) {
                for (FireworkEffect fireworkEffect : ((FireworkMeta) this.itemStack.getItemMeta()).getEffects()) {
                    this.fireworkEffects.add(fireworkEffect);
                }
            }
            this.power = ((FireworkMeta) this.itemStack.getItemMeta()).getPower();
        }
        if (this.type.equals(Material.KNOWLEDGE_BOOK)) {
            if (((KnowledgeBookMeta) this.itemStack.getItemMeta()).hasRecipes()) {
                for (NamespacedKey namespacedKey : ((KnowledgeBookMeta) this.itemStack.getItemMeta()).getRecipes()) {
                    recipes.add(new GedKey(namespacedKey.getKey()));
                }
            }
        }
        if (this.type.equals(Material.LEATHER_HELMET) ||
                this.type.equals(Material.LEATHER_CHESTPLATE) ||
                this.type.equals(Material.LEATHER_LEGGINGS) ||
                this.type.equals(Material.LEATHER_BOOTS)
        ) {
            this.color = ((LeatherArmorMeta) this.itemStack.getItemMeta()).getColor().asRGB();
        }
        //MapMeta
        //Idk how to solve MapView this interface
        //Because it doesn't extends java.io.Serializable

        if (this.itemStack.getItemMeta() instanceof SpawnEggMeta) {
            this.entityType = ((SpawnEggMeta) this.itemStack.getItemMeta()).getSpawnedType();
        }
        if (this.type.equals(Material.SUSPICIOUS_STEW)) {
            this.potionEffects.clear();
            if (((SuspiciousStewMeta) this.itemStack.getItemMeta()).hasCustomEffects()) {
                for (PotionEffect potionEffect : ((SuspiciousStewMeta) this.itemStack.getItemMeta()).getCustomEffects()) {
                    this.potionEffects.add(GPotionEffect.fromPotionEffect(potionEffect));
                }
            }
        }
        if (this.type.equals(Material.COD_BUCKET) ||
                this.type.equals(Material.PUFFERFISH_BUCKET) ||
                this.type.equals(Material.SALMON_BUCKET) ||
                this.type.equals(Material.TROPICAL_FISH_BUCKET)
        ) {
            this.bodyColor = ((TropicalFishBucketMeta) this.itemStack.getItemMeta()).getBodyColor();
            this.pattern = ((TropicalFishBucketMeta) this.itemStack.getItemMeta()).getPattern();
            this.patternColor = ((TropicalFishBucketMeta) this.itemStack.getItemMeta()).getPatternColor();
        }
    }

    public GItem(Material type) {
        this.itemStack = new ItemStack(type);
        this.type = type;
        this.amount = 1;
        this.itemFlags = new HashSet<>();
        this.lore = new ArrayList<>();
        this.enchantments = new HashMap<>();
        this.attributes = new ArrayList<>();
        this.potionEffects = new ArrayList<>();
        this.patterns = new ArrayList<>();
        this.pages = new ArrayList<>();
        this.fireworkEffects = new ArrayList<>();
    }

    public Material getType() {
        return type;
    }

    public void setType(Material type) {
        this.type = type;
        if (itemStack == null) {
            this.getHandle();
        }
        this.itemStack.setType(type);
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        if (itemStack == null) {
            this.getHandle();
        }
        this.itemStack.setAmount(amount);
    }

    public boolean hasDisplayName() {
        return this.localizedName != null;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
        if (itemStack == null) {
            this.getHandle();
        }
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        this.itemStack.setItemMeta(itemMeta);
    }

    public boolean hasLocalizedName() {
        return this.localizedName != null;
    }

    public String getLocalizedName() {
        return this.localizedName;
    }

    public void setLocalizedName(String name) {
        this.localizedName = name;
        if (itemStack == null) {
            this.getHandle();
        }
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setLocalizedName(name);
        this.itemStack.setItemMeta(itemMeta);
    }

    public boolean hasLore() {
        return this.lore.size() != 0;
    }

    public List<String> getLore() {
        return this.lore;
    }

    public void setLore(List<String> lore) {
        this.lore.clear();
        for (String line : lore) {
            this.lore.add(line);
        }
        if (itemStack == null) {
            this.getHandle();
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        this.itemStack.setItemMeta(itemMeta);
    }

    public ItemStack getHandle() {
        ItemStack itemStack = new ItemStack(this.type);
        itemStack.setAmount(this.amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (this.displayName != null) {
            itemMeta.setDisplayName(this.displayName);
        }
        if (this.localizedName != null) {
            itemMeta.setLocalizedName(this.localizedName);
        }
        if (this.lore.size() != 0) {
            itemMeta.setLore(this.lore);
        }
        if (this.customModelData != 0) {
            itemMeta.setCustomModelData(this.customModelData);
        }
        itemMeta.setUnbreakable(this.unbreakable);
        if (this.itemFlags.size() != 0) {
            for (ItemFlag itemFlag : this.itemFlags) {
                itemMeta.addItemFlags(itemFlag);
            }
        }
        if (this.attributes.size() != 0) {
            for (GAttributeModifier gAttributeModifier : attributes) {
                itemMeta.addAttributeModifier(gAttributeModifier.getAttribute(), gAttributeModifier.toAttributeModifier());
            }
        }
        if (itemMeta instanceof Damageable) {
            Damageable damageable = (Damageable) itemMeta;
            damageable.setDamage(this.damage);
            itemMeta = (ItemMeta) damageable;
        }
        if (itemMeta instanceof Repairable) {
            Repairable repairable = (Repairable) itemMeta;
            repairable.setRepairCost(this.repairCost);
            itemMeta = (ItemMeta) repairable;
        }
        if (this.skullOwner != null) {
            if (this.type.equals(Material.PLAYER_HEAD)) {
                SkullMeta skullMeta = (SkullMeta) itemMeta;
                skullMeta.setOwner(this.skullOwner);
                itemMeta = skullMeta;
            }
        }
        if (this.type.equals(Material.POTION) || this.type.equals(Material.SPLASH_POTION) || this.type.equals(Material.LINGERING_POTION)) {
            PotionMeta potionMeta = (PotionMeta) itemMeta;
            potionMeta.setBasePotionData(basePotion.getHandle());
            potionMeta.setColor(Color.fromRGB(this.color));
            if (this.potionEffects.size() != 0) {
                for (GPotionEffect gPotionEffect : this.potionEffects) {
                    potionMeta.addCustomEffect(gPotionEffect.toPotionEffect(), true);
                }
            }
        }
        if (this.type.equals(Material.BLACK_BANNER) ||
                this.type.equals(Material.BLUE_BANNER) ||
                this.type.equals(Material.BROWN_BANNER) ||
                this.type.equals(Material.CYAN_BANNER) ||
                this.type.equals(Material.GRAY_BANNER) ||
                this.type.equals(Material.GREEN_BANNER) ||
                this.type.equals(Material.LIGHT_BLUE_BANNER) ||
                this.type.equals(Material.LIGHT_GRAY_BANNER) ||
                this.type.equals(Material.LIME_BANNER) ||
                this.type.equals(Material.MAGENTA_BANNER) ||
                this.type.equals(Material.PINK_BANNER) ||
                this.type.equals(Material.PURPLE_BANNER) ||
                this.type.equals(Material.RED_BANNER) ||
                this.type.equals(Material.ORANGE_BANNER) ||
                this.type.equals(Material.WHITE_BANNER) ||
                this.type.equals(Material.YELLOW_BANNER)
        ) {
            BannerMeta bannerMeta = (BannerMeta) itemMeta;
            if (this.patterns.size() != 0) {
                for (GPattern gPattern : this.patterns) {
                    bannerMeta.addPattern(gPattern.toPattern());
                }
            }
            itemMeta = bannerMeta;
        }
        if (this.type.equals(Material.WRITABLE_BOOK) || this.type.equals(Material.WRITTEN_BOOK)) {
            BookMeta bookMeta = (BookMeta) itemMeta;
            if (this.title != null) {
                bookMeta.setTitle(this.title);
            }
            if (this.author != null) {
                bookMeta.setAuthor(this.author);
            }
            if (this.pages.size() != 0) {
                bookMeta.setPages(this.pages);
            }
            bookMeta.setGeneration(this.generation);
            itemMeta = bookMeta;
        }
        if (this.type.equals(Material.COMPASS)) {
            CompassMeta compassMeta = (CompassMeta) itemMeta;
            compassMeta.setLodestone(this.lodestone.getHandle());
            compassMeta.setLodestoneTracked(this.lodestoneTracked);
            itemMeta = compassMeta;
        }
        if (this.type.equals(Material.ENCHANTED_BOOK)) {
            EnchantmentStorageMeta enchantmentStorageMeta = (EnchantmentStorageMeta) itemMeta;
            if (this.enchantments.size() != 0) {
                for (GEnchantment gEnchantment : this.enchantments.keySet()) {
                    enchantmentStorageMeta.addStoredEnchant(gEnchantment.getHandle(), this.enchantments.get(gEnchantment), true);
                }
            }
            itemMeta = enchantmentStorageMeta;
        } else {
            if (this.enchantments.size() != 0) {
                for (GEnchantment gEnchantment : this.enchantments.keySet()) {
                    itemMeta.addEnchant(gEnchantment.getHandle(), this.enchantments.get(gEnchantment), true);
                }
            }
        }
        if (this.type.equals(Material.FIREWORK_STAR)) {
            FireworkEffectMeta fireworkEffectMeta = (FireworkEffectMeta) itemMeta;
            if (this.fireworkEffects.size() != 0) {
                fireworkEffectMeta.setEffect(this.fireworkEffects.get(0));
            }
            itemMeta = fireworkEffectMeta;
        }
        if (this.type.equals(Material.FIREWORK_ROCKET)) {
            FireworkMeta fireworkMeta = (FireworkMeta) itemMeta;
            if (this.fireworkEffects.size() != 0) {
                fireworkMeta.addEffects(this.fireworkEffects);
            }
            if (this.power != 0) {
                fireworkMeta.setPower(this.power);
            }
            itemMeta = fireworkMeta;
        }
        if (this.type.equals(Material.KNOWLEDGE_BOOK)) {
            KnowledgeBookMeta knowledgeBookMeta = (KnowledgeBookMeta) itemMeta;
            if (this.recipes.size() != 0) {
                for (GedKey gedKey : recipes) {
                    knowledgeBookMeta.addRecipe(gedKey.getHandle(GedLibrary.getInstance()));
                }
            }
            itemMeta = knowledgeBookMeta;
        }
        if (this.type.equals(Material.LEATHER_HELMET) ||
                this.type.equals(Material.LEATHER_CHESTPLATE) ||
                this.type.equals(Material.LEATHER_LEGGINGS) ||
                this.type.equals(Material.LEATHER_BOOTS)
        ) {
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemMeta;
            leatherArmorMeta.setColor(Color.fromRGB(this.color));
            itemMeta = leatherArmorMeta;
        }
        if (itemMeta instanceof SpawnEggMeta) {
            SpawnEggMeta spawnEggMeta = (SpawnEggMeta) itemMeta;
            spawnEggMeta.setSpawnedType(entityType);
            itemMeta = spawnEggMeta;
        }
        if (this.type.equals(Material.SUSPICIOUS_STEW)) {
            SuspiciousStewMeta suspiciousStewMeta = (SuspiciousStewMeta) itemMeta;
            if (this.potionEffects.size() != 0) {
                for (GPotionEffect gPotionEffect : this.potionEffects) {
                    suspiciousStewMeta.addCustomEffect(gPotionEffect.toPotionEffect(), true);
                }
            }
            itemMeta = suspiciousStewMeta;
        }
        if (this.type.equals(Material.COD_BUCKET) ||
                this.type.equals(Material.PUFFERFISH_BUCKET) ||
                this.type.equals(Material.SALMON_BUCKET) ||
                this.type.equals(Material.TROPICAL_FISH_BUCKET)
        ) {
            TropicalFishBucketMeta tropicalFishBucketMeta = (TropicalFishBucketMeta) itemMeta;
            tropicalFishBucketMeta.setBodyColor(bodyColor);
            tropicalFishBucketMeta.setPattern(pattern);
            tropicalFishBucketMeta.setPatternColor(this.patternColor);
            itemMeta = tropicalFishBucketMeta;
        }
        itemStack.setItemMeta(itemMeta);
        this.itemStack = itemStack;
        return itemStack;
    }

    public GItem clone() {
        return new GItem(this.getHandle().clone());
    }

}
