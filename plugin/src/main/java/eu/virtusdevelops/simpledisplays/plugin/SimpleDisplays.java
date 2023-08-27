package eu.virtusdevelops.simpledisplays.plugin;

import eu.virtusdevelops.simpledisplays.api.Location;
import eu.virtusdevelops.simpledisplays.api.SimpleDisplaysAPI;
import eu.virtusdevelops.simpledisplays.core.SimpleDisplaysCore;
import eu.virtusdevelops.simpledisplays.core.models.EnableException;
import eu.virtusdevelops.simpledisplays.nms.Packets;
import eu.virtusdevelops.simpledisplays.nms.Packets_1_19_R1;
import eu.virtusdevelops.simpledisplays.nms.Packets_1_20_R1;
import eu.virtusdevelops.simpledisplays.plugin.listeners.PlayerListener;
import eu.virtusdevelops.virtuscore.VirtusCore;
import eu.virtusdevelops.virtuscore.compatibility.ServerVersion;
import eu.virtusdevelops.virtuscore.managers.FileManager;
import eu.virtusdevelops.virtuscore.utils.FileLocation;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public class SimpleDisplays extends JavaPlugin {
    private SimpleDisplaysAPI api;
    private SimpleDisplaysCore core;

    @Override
    public void onEnable(){

        core = new SimpleDisplaysCore();
        try {
            core.enable(this);
        } catch (EnableException e) {

            getLogger().severe(
                    e.getMessage()
            );

            VirtusCore.plugins().disablePlugin(this);
            return;
        }


        // api load
        api = SimpleDisplaysAPI.get(this);

        // load dependency injection (PAPI)


        FileManager fileManager = new FileManager(this, Set.of(
                FileLocation.of("holograms.yml", true)
        ));
        fileManager.loadFiles();






        PluginManager pm = VirtusCore.plugins();
        pm.registerEvents(new PlayerListener(), this);


        getLogger().info("""
        \n
         ___  ____
        / __)(  _ \\
        \\__ \\ )(_) )
        (___/(____/
        SimpleDisplays,
        Proudly coded by VirtusDevelops \u001B[35m<3\u001B[0m
        Server version: \u001B[35m%s\u001B[0m
        Core version: \u001B[35m%s\u001B[0m  / \u001B[35m%s\u001B[0m
        """
                .formatted(
                        ServerVersion.getServerVersionString(),
                        VirtusCore.getCoreVersion(),
                        this.getDescription().getVersion())
        );

    }

    public void reload(){

    }

    @Override
    public void onDisable(){

    }
}
