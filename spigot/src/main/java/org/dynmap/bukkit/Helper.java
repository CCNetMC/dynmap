package org.dynmap.bukkit;

import org.bukkit.Bukkit;
import org.dynmap.Log;
import org.dynmap.bukkit.helper.BukkitVersionHelper;
import org.dynmap.bukkit.helper.BukkitVersionHelperCB;
import org.dynmap.bukkit.helper.BukkitVersionHelperGlowstone;
import org.dynmap.bukkit.helper.v117.BukkitVersionHelperSpigot117;
import org.dynmap.bukkit.helper.v118.BukkitVersionHelperSpigot118;
import org.dynmap.bukkit.helper.v118_2.BukkitVersionHelperSpigot118_2;

public class Helper {

    public static final BukkitVersionHelper getHelper() {
        if (BukkitVersionHelper.helper == null) {
        	String v = Bukkit.getServer().getVersion();
            Log.info("version=" + v);
            if (v.contains("MCPC")) {
                Log.severe("*********************************************************************************");
                Log.severe("* MCPC-Plus is no longer supported via the Bukkit version of Dynmap.            *");
                Log.severe("* Install the appropriate Forge version of Dynmap.                              *");
                Log.severe("* Add the DynmapCBBridge plugin to enable support for Dynmap-compatible plugins *");
                Log.severe("*********************************************************************************");
            }
            else if(v.contains("BukkitForge")) {
                Log.severe("*********************************************************************************");
                Log.severe("* BukkitForge is not supported via the Bukkit version of Dynmap.                *");
                Log.severe("* Install the appropriate Forge version of Dynmap.                              *");
                Log.severe("* Add the DynmapCBBridge plugin to enable support for Dynmap-compatible plugins *");
                Log.severe("*********************************************************************************");
            }
            else if(Bukkit.getServer().getClass().getName().contains("GlowServer")) {
                Log.info("Loading Glowstone support");
                BukkitVersionHelper.helper = new BukkitVersionHelperGlowstone();
            }
            else if (v.contains("(MC: 1.18)") || (v.contains("(MC: 1.18.1)"))) {
            	BukkitVersionHelper.helper = new BukkitVersionHelperSpigot118();
            }
            else if (v.contains("(MC: 1.18")) {
            	BukkitVersionHelper.helper = new BukkitVersionHelperSpigot118_2();
            }
            else if (v.contains("(MC: 1.17")) {
                BukkitVersionHelper.helper = new BukkitVersionHelperSpigot117();
            }
            else {
            	BukkitVersionHelper.helper = new BukkitVersionHelperCB();
            }
        }
        return BukkitVersionHelper.helper;
    }

}
