package eu.virtusdevelops.simpledisplays.api.internal;


import eu.virtusdevelops.simpledisplays.api.SimpleDisplaysAPI;
import org.bukkit.plugin.Plugin;

public abstract class SimpleDisplaysApiProvider {

    private static SimpleDisplaysApiProvider apiProvider;


    public static void setImplementation(SimpleDisplaysApiProvider implementation){
        SimpleDisplaysApiProvider.apiProvider = implementation;
    }

    public static SimpleDisplaysApiProvider getImplementation(){
        if(apiProvider == null)
            throw new IllegalStateException("BLABLA");
        return apiProvider;
    }


    public abstract SimpleDisplaysAPI getSimpleDisplaysAPI(Plugin plugin);

}
