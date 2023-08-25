package eu.virtusdevelops.simpledisplays.api;

import eu.virtusdevelops.simpledisplays.api.hologram.Hologram;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface SimpleDisplaysAPI {

    static int getVersion(){
        return 1;
    }

    /**
     *
     * @param plugin
     * @return
     * @since 1
     */
    static @NotNull SimpleDisplaysAPI get(@NotNull Plugin plugin) {
        return null;
    }

    /**
     * Creates the hologram
     *
     * @param location the initial location of the hologram.
     * @return the created hologram
     * @since 1
     */
    @NotNull Hologram createHologram(@NotNull Location location);

    /**
     * Returns all the holograms,
     * this does not include deleted holograms.
     *
     * @return immutable copy of all the holograms
     * @since 1
     */
    @NotNull Collection<Hologram> getHolograms();

    /**
     *  Deletes all holograms created.
     *  Same as invoking {@link Hologram#delete()} on each hologram.
     *
     * @since 1
     */
    void deleteHolograms();

}
