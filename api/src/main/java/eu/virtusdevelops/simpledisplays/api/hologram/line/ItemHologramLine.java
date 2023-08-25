/**
 *
 * Copyright (C) NeutralPlasma
 *
 */
package eu.virtusdevelops.simpledisplays.api.hologram.line;

import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public interface ItemHologramLine extends HologramLine{

    /**
     * Returns the currently displayed item.
     *
     * @return the current item
     * @since 1
     */
    @Nullable
    ItemStack getItemStack();

    /**
     * Sets the displayed item.
     *
     * @param itemStack the new item
     * @since 1
     */
    void setItemStack(@Nullable ItemStack itemStack);

}
