package org.gedstudio.library.bukkit.entity;

import org.bukkit.Material;
import org.gedstudio.library.bukkit.EnumHand;
import org.gedstudio.library.bukkit.GLocation;
import org.gedstudio.library.bukkit.chat.GText;
import org.gedstudio.library.bukkit.command.GSender;
import org.gedstudio.library.bukkit.inventory.GInventory;
import org.gedstudio.library.bukkit.inventory.GItem;
import org.gedstudio.library.bukkit.scoreboard.GScoreBoard;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.gedstudio.library.bukkit.skin.Skin;

import java.util.UUID;

public interface GPlayer extends GSender {

    /**
     * To set a item cool down to player
     * <p>Cool down time unit is tick (20 ticks = 1 second)</p>
     * @param itemType item
     * @param cd cool down
     */
    void setItemCoolDown(Material itemType, int cd);

    /**
     * To let the player swing his/her arm (Just animation)
     * @param enumHand arm to swing
     */
    void swingArm(EnumHand enumHand);

    /**
     * To get the ping of player
     * @return player's ping
     */
    int ping();

    /**
     * To send actionbar to player
     * @param gText message
     */
    void sendActionbar(GText gText);

    /**
     * To send actionbar to player
     * <p>Time unit is tick (20 ticks = 1 second)</p>
     * @param gText message
     * @param fadeIn fadeIn time default is 10
     * @param stay stay time default is 70
     * @param fadeOut fadeOut time default is 20
     */
    void sendActionbar(GText gText, int fadeIn, int stay, int fadeOut);

    /**
     * To send title to player
     * @param gText message
     */
    void sendTitle(GText gText);

    /**
     * To send title to player
     * <p>Time unit is tick (20 ticks = 1 second)</p>
     * @param gText message
     * @param fadeIn fadeIn time default is 10
     * @param stay stay time default is 70
     * @param fadeOut fadeOut time default is 20
     */
    void sendTitle(GText gText, int fadeIn, int stay, int fadeOut);

    /**
     * To send sub title to player
     * @param gText message
     */
    void sendSubTitle(GText gText);

    /**
     * To send sub title to player
     * <p>Time unit is tick (20 ticks = 1 second)</p>
     * @param gText message
     * @param fadeIn fadeIn time default is 10
     * @param stay stay time default is 70
     * @param fadeOut fadeOut time default is 20
     */
    void sendSubTitle(GText gText, int fadeIn, int stay, int fadeOut);

    /**
     * To send title with sub title
     * @param title title message
     * @param subTitle sub title message
     */
    void sendTitleWithSubTitle(GText title, GText subTitle);

    /**
     * To send title with sub title
     * <p>Title and sub title use same time</p>
     * <p>Time unit is tick (20 ticks = 1 second)</p>
     * @param title title message
     * @param subTitle sub title message
     * @param fadeIn fadeIn time default is 10
     * @param stay stay time default is 70
     * @param fadeOut fadeOut time default is 20
     */
    void sendTitleWithSubTitle(GText title, GText subTitle, int fadeIn, int stay, int fadeOut);

    /**
     * To send title with sub title
     * <p>Title and sub title use different time</p>
     * <p>Time unit is tick (20 ticks = 1 second)</p>
     * @param title title message
     * @param titleFadeIn title fadeIn time default is 10
     * @param titleStay title stay time default is 70
     * @param titleFadeOut title fadeOut time default is 20
     * @param subTitle sub title message
     * @param subTitleFadeIn sub title fadeIn time default is 10
     * @param subTitleStay sub title stay time default is 70
     * @param subTitleFadeOut sub title fadeOut time default is 20
     */
    void sendTitleWithSubTitle(GText title, int titleFadeIn, int titleStay, int titleFadeOut, GText subTitle, int subTitleFadeIn, int subTitleStay, int subTitleFadeOut);

    /**
     * To clear the screen of player
     * <p>It will clear title, sub title and actionbar</p>
     */
    void clearScreen();

    /**
     * To reset the screen of player
     * <p>It will reset title, sub title and actionbar</p>
     */
    void resetScreen();

    /**
     * To set the scoreboard of the player
     * @param scoreboard scoreboard
     */
    void setScoreboard(GScoreBoard scoreboard);

    /**
     * To reset the scoreboard of player
     */
    void resetScoreboard();

    /**
     * To check if player has a scoreboard
     * @return boolean
     */
    boolean hasScoreboard();

    /**
     * To get the scoreboard of the player
     * <p>If player doesn't has any scoreboard it will return null</p>
     * @return scoreboard
     */
    GScoreBoard getScoreboard();

    /**
     * To cast the player to command sender
     * @return command sender
     */
    CommandSender getSender();

    /**
     * To hide a player
     * @param player player to hide
     */
    void hidePlayer(GPlayer player);

    /**
     * To show a player
     * @param player player to show
     */
    void showPlayer(GPlayer player);

    /**
     * To let the player die
     */
    void die();

    /**
     * To set player's name
     * @param name name to set
     */
    void setName(String name);

    /**
     * To get player's name
     * @return player's name
     */
    @Override
    String getName();

    /**
     * To set the display name of the player
     * @param name display name
     */
    void setDisplayName(String name);

    /**
     * To get the display name of the player
     * @return player's display name
     */
    String getDisplayName();

    /**
     * To set the player list name of the player
     * @param name player list name
     */
    void setPlayerListName(String name);

    /**
     * To get the player list name of the player
     * @return player's player list name
     */
    String getPlayerListName();

    /**
     * To get the item in player's main hand
     * @return item in player's main hand
     */
    GItem getItemInMainHand();

    /**
     * To get the item in player's off hand
     * @return item in player's off hand
     */
    GItem getItemInOffHand();

    /**
     * To get the helmet of the player
     * @return player's helmet
     */
    GItem getHelmet();

    /**
     * To get the chestplate of the player
     * @return player's chestplate
     */
    GItem getChestplate();

    /**
     * To get the leggings of the player
     * @return player's leggings
     */
    GItem getLeggings();

    /**
     * To get the boots of the player
     * @return player's boots
     */
    GItem getBoots();

    /**
     * To set the item in player's main hand
     * @param item item
     */
    void setItemInMainHand(GItem item);

    /**
     * To set the item in player's off hand
     * @param item item
     */
    void setItemInOffHand(GItem item);

    /**
     * To set player's helmet
     * @param item item
     */
    void setHelmet(GItem item);

    /**
     * To set player's chestplate
     * @param item item
     */
    void setChestplate(GItem item);

    /**
     * To set player's leggings
     * @param item item
     */
    void setLeggings(GItem item);

    /**
     * To set player's boots
     * @param item item
     */
    void setBoots(GItem item);

    /**
     * To add items into player's inventory
     * @param items items
     */
    void addItem(GItem... items);

    /**
     * To set the item in slot
     * @param slot slot
     * @param item item
     */
    void setItem(int slot, GItem item);

    /**
     * To get the item in slot
     * @param slot slot
     * @return the item in slot
     */
    GItem getItem(int slot);

    /**
     * To open an inventory to player
     * @param inventory inventory
     */
    void openInventory(GInventory inventory);

    /**
     * To close an inventory was opened by player
     */
    void closeInventory();

    /**
     * To get the inventory was opened by player
     * @return inventory
     */
    GInventory getOpenInventory();

    /**
     * To teleport player to location
     * @param location location
     */
    void teleport(GLocation location);

    /**
     * To teleport player to player
     * @param player player
     */
    void teleport(GPlayer player);

    /**
     * To get the location of the player
     * @return the location of the player
     */
    GLocation getLocation();

    /**
     * To get the uuid of the player
     * @return uuid
     */
    UUID getUniqueId();

    /**
     * To set the skin of the player
     * @param skin skin
     */
    void setSkin(Skin skin);

    /**
     * To cast GPlayer to Bukkit Player
     * @return Bukkit Player
     */
    @Override
    Player getHandle();

}
