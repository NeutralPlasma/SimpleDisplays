package eu.virtusdevelops.simpledisplays.plugin.commands;

import eu.virtusdevelops.simpledisplays.plugin.SimpleDisplays;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private SimpleDisplays simpleDisplays;

    public ReloadCommand(SimpleDisplays simpleDisplays){
        this.simpleDisplays = simpleDisplays;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        var time = System.currentTimeMillis();
        simpleDisplays.reload();
        time = System.currentTimeMillis() - time;

        sender.sendMessage(MiniMessage.miniMessage().deserialize(
                "<color:#00aa02><hover:show_text:'<yellow>Took:<red> " + time + "<yellow>ms'>Successfully reloaded plugin!</color>"
        ));
        return true;
    }
}
