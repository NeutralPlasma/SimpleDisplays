package eu.virtusdevelops.simpledisplays.nms;

import eu.virtusdevelops.simpledisplays.models.BlockEntityLocation;
import eu.virtusdevelops.simpledisplays.models.ItemSlot;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public abstract class Packets {
    abstract public void spawnTextEntity(Player player, int entityID, UUID uuid, BlockEntityLocation location);
    abstract public void spawnItemEntity(Player player, int entityID, UUID uuid, BlockEntityLocation location);
    abstract public void spawnBlockEntity(Player player, int entityID, UUID uuid, BlockEntityLocation location);


    abstract public void updateMetadata(Player player, int entityID, String text, int type, int opacity, int width, Color color,
                                        boolean shadow, boolean seeThru, boolean useDefaultBackground, int alignment, int brightness,
                                        float scale_x, float scale_y, float scale_z);



    abstract public void updateItemMetadata(Player player, int entityID, boolean shadow, int alignment, int brightness,
                                            float scale_x, float scale_y, float scale_z, ItemSlot itemSlot, ItemStack itemStack);


    abstract public void destroyEntity(Player player, int entityID);
}
