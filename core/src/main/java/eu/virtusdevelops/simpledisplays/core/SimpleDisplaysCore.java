package eu.virtusdevelops.simpledisplays.core;

import eu.virtusdevelops.simpledisplays.api.internal.SimpleDisplaysApiProvider;
import eu.virtusdevelops.simpledisplays.core.api.SimpleDisplaysAPIv1;
import eu.virtusdevelops.simpledisplays.core.models.EnableException;
import eu.virtusdevelops.simpledisplays.nms.Packets;
import eu.virtusdevelops.simpledisplays.nms.Packets_1_19_R1;
import eu.virtusdevelops.simpledisplays.nms.Packets_1_20_R1;
import eu.virtusdevelops.virtuscore.compatibility.ServerVersion;
import org.bukkit.plugin.Plugin;

public class SimpleDisplaysCore {

    private Packets packets;



    public void enable(Plugin plugin) throws EnableException {
        packets = switch (ServerVersion.getServerVersion()){
            case V1_19 -> new Packets_1_19_R1();
            case V1_20 -> new Packets_1_20_R1();
            default -> null;
        };

        if(packets == null)
            throw new EnableException("Invalid server version: " + ServerVersion.getServerVersionString() + " SimpleDisplays only supports versions above and including 1.19");


        SimpleDisplaysApiProvider.setImplementation(new SimpleDisplaysAPIv1(
                // TODO
        ));




    }



}
