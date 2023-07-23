package eu.virtusdevelops.simpledisplays.plugin;

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
    private Packets packets;
    private FileManager fileManager;

    @Override
    public void onEnable(){
        packets = switch (ServerVersion.getServerVersion()){
            case V1_19 -> new Packets_1_19_R1();
            case v1_20 -> new Packets_1_20_R1();
            default -> null;
        };
        if(packets == null){
            getLogger().severe("Could not find proper packets NMS, please use support version of minecraft.");
            VirtusCore.plugins().disablePlugin(this);
            return;
        }

        fileManager = new FileManager(this, Set.of(
                FileLocation.of("holograms.yml", true)
        ));
        fileManager.loadFiles();






        PluginManager pm = VirtusCore.plugins();
        pm.registerEvents(new PlayerListener(), this);


        getLogger().info("Seems like it works :O?");
    }


    public Packets getPackets(){
        return packets;
    }

    public void reload(){

    }

    @Override
    public void onDisable(){

    }
}
