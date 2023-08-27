package eu.virtusdevelops.simpledisplays.nms;

import eu.virtusdevelops.simpledisplays.core.models.BlockEntityLocation;
import eu.virtusdevelops.simpledisplays.core.models.ItemSlot;
import io.papermc.paper.adventure.PaperAdventure;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Color;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_19_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.joml.Vector3f;

import java.util.List;
import java.util.UUID;

public class Packets_1_19_R1 extends Packets{
    @Override
    public void spawnTextEntity(Player player, int entityID, UUID uuid, BlockEntityLocation location) {

        ClientboundAddEntityPacket packet = new ClientboundAddEntityPacket(
                entityID,
                uuid,
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getPitch(),
                location.getRotation(),
                EntityType.TEXT_DISPLAY,
                0,
                new Vec3(0,0,0),
                0
        );
        ((CraftPlayer) player).getHandle().connection.send(packet);

    }




    @Override
    public void spawnItemEntity(Player player, int entityID, UUID uuid, BlockEntityLocation location) {
        ClientboundAddEntityPacket packet = new ClientboundAddEntityPacket(
                entityID,
                uuid,
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getPitch(),
                location.getRotation(),
                EntityType.ITEM_DISPLAY,
                0,
                new Vec3(0,0,0),
                0
        );
        ((CraftPlayer) player).getHandle().connection.send(packet);
    }

    @Override
    public void spawnBlockEntity(Player player, int entityID, UUID uuid, BlockEntityLocation location) {
        ClientboundAddEntityPacket packet = new ClientboundAddEntityPacket(
                entityID,
                uuid,
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getPitch(),
                location.getRotation(),
                EntityType.BLOCK_DISPLAY,
                0,
                new Vec3(0,0,0),
                0
        );
        ((CraftPlayer) player).getHandle().connection.send(packet);
    }

    @Override
    public void updateMetadata(Player player, int entityID, String text, int type, int opacity, int width, Color color,
                               boolean shadow, boolean seeThru, boolean useDefaultBackground, int alignment, int brightness,
                               float scale_x, float scale_y, float scale_z) {
        var list = new java.util.ArrayList<>(List.of(
                getMetaEntityText(text),
                getMetaEntityViewRange(25),
                getMetaEntityBillboard(type),
                getMetaTextOpacity((byte) opacity),
                getMetaTextWidth(width),
                getMetaEntityBackground(color),
                getMetaTextData(shadow, seeThru, useDefaultBackground, alignment),
                getMetaScale(scale_x, scale_y, scale_z)
        ));

        if(brightness != -1){
            list.add(getMetaBrightness(brightness));
        }


        sendEntityMetadata(player, entityID, list);

    }


    @Override
    public void updateItemMetadata(Player player, int entityID, boolean shadow, int alignment, int brightness,
                                   float scale_x, float scale_y, float scale_z, ItemSlot itemSlot, ItemStack itemStack) {
        var list = new java.util.ArrayList<SynchedEntityData.DataValue<?>>(List.of(
                getMetaEntityViewRange(25),
                getMetaTextData(shadow, false, true, alignment),
                getMetaScale(scale_x, scale_y, scale_z),
                getEntitySlotType((itemSlot.getId())),
                getEntityItem(itemStack)
        ));
        if(brightness != -1){
            list.add(getMetaBrightness(brightness));
        }


        sendEntityMetadata(player, entityID, list);

    }

    @Override
    public void destroyEntity(Player player, int entityId) {
        ClientboundRemoveEntitiesPacket packet = new ClientboundRemoveEntitiesPacket(entityId);
        ((CraftPlayer) player).getHandle().connection.send(packet);
    }

    public SynchedEntityData.DataValue<Component> getMetaEntityText(String text) {
        return new SynchedEntityData.DataValue<>(22,
                EntityDataSerializers.COMPONENT,
                PaperAdventure.asVanilla(MiniMessage.miniMessage().deserialize(text))
        );
    }

    public SynchedEntityData.DataValue<Byte> getMetaTextData(boolean shadow, boolean seeThru, boolean useDefault, int alignment) {
        byte bits = 0b00000000;
        bits |= shadow ? 0b00000001 : 0b00000000;
        bits |= seeThru ? 0b00000010 : 0b00000000;
        bits |= useDefault ? 0x00000100 : 0b00000000;
        bits |= alignment  == 0x00000000 ? 0b00000000 : alignment  == 1 ? 0b00010000 : 0b00011000;
        return new SynchedEntityData.DataValue<>(26, EntityDataSerializers.BYTE, bits);
    }

    public SynchedEntityData.DataValue<Byte> getMetaTextOpacity(byte value) {
        return new SynchedEntityData.DataValue<>(25, EntityDataSerializers.BYTE, value);
    }

    public SynchedEntityData.DataValue<Integer> getMetaTextWidth(int width) {
        return new SynchedEntityData.DataValue<>(23, EntityDataSerializers.INT, width);
    }


    public SynchedEntityData.DataValue<Vector3f> getMetaScale(float x, float y, float z) {
        return new SynchedEntityData.DataValue<>(11, EntityDataSerializers.VECTOR3, new Vector3f(x, y, z));
    }

    public SynchedEntityData.DataValue<Integer> getMetaBrightness(int brightness) {
        return new SynchedEntityData.DataValue<>(15, EntityDataSerializers.INT, brightness);
    }

    public SynchedEntityData.DataValue<Byte> getMetaEntityBillboard(int type) {
        return new SynchedEntityData.DataValue<>(14, EntityDataSerializers.BYTE, (byte) type);
    }

    public SynchedEntityData.DataValue<Float> getMetaEntityViewRange(float viewRange) {
        return new SynchedEntityData.DataValue<>(16, EntityDataSerializers.FLOAT, viewRange);
    }

    public SynchedEntityData.DataValue<Integer> getMetaEntityBackground(Color color) {
        return new SynchedEntityData.DataValue<>(24, EntityDataSerializers.INT, color.asARGB());
    }

    public SynchedEntityData.DataValue<Byte> getEntitySlotType(int slot) {
        return new SynchedEntityData.DataValue<>(23, EntityDataSerializers.BYTE, (byte) slot);
    }

    public SynchedEntityData.DataValue<net.minecraft.world.item.ItemStack> getEntityItem(ItemStack itemStack) {
        return new SynchedEntityData.DataValue<>(22,
                EntityDataSerializers.ITEM_STACK,
                CraftItemStack.asNMSCopy(itemStack)
        );
    }


    public void sendEntityMetadata(Player player, int entityId, List<SynchedEntityData.DataValue<?>> dataValue) {
        ClientboundSetEntityDataPacket packet = new ClientboundSetEntityDataPacket(
                entityId,
                dataValue
        );
        ((CraftPlayer) player).getHandle().connection.send(packet);
    }
}
