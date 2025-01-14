package org.dynmap.bukkit;

import java.lang.reflect.Constructor;

import org.bukkit.Bukkit;
import org.dynmap.Log;
import org.dynmap.bukkit.helper.BukkitVersionHelper;
import org.dynmap.bukkit.helper.BukkitVersionHelperCB;
import org.dynmap.bukkit.helper.BukkitVersionHelperGlowstone;
import org.dynmap.bukkit.helper.v119.BukkitVersionHelperSpigot119;
import org.dynmap.bukkit.helper.v119_3.BukkitVersionHelperSpigot119_3;
import org.dynmap.bukkit.helper.v119_4.BukkitVersionHelperSpigot119_4;
import org.dynmap.bukkit.helper.v120.BukkitVersionHelperSpigot120;

public class Helper {

	private static BukkitVersionHelper loadVersionHelper(String classname) {
		try {
			Class<?> c = Class.forName(classname);
			Constructor<?> cons = c.getConstructor();
			return (BukkitVersionHelper) cons.newInstance();
		} catch (Exception x) {
			Log.severe("Error loading " + classname, x);
			return null;
		}
	}
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
                BukkitVersionHelper.helper = loadVersionHelper("org.dynmap.bukkit.helper.BukkitVersionHelperGlowstone");
            }
            else if (v.contains("(MC: 1.20)") || v.contains("(MC: 1.20.1)")) {
            	BukkitVersionHelper.helper = loadVersionHelper("org.dynmap.bukkit.helper.v120.BukkitVersionHelperSpigot120");
            }
            else if (v.contains("(MC: 1.20")) {
            	BukkitVersionHelper.helper = loadVersionHelper("org.dynmap.bukkit.helper.v120_2.BukkitVersionHelperSpigot120_2");
            }
            else if (v.contains("(MC: 1.19)") || v.contains("(MC: 1.19.1)") || v.contains("(MC: 1.19.2)")) {
            	BukkitVersionHelper.helper = loadVersionHelper("org.dynmap.bukkit.helper.v119.BukkitVersionHelperSpigot119");
            }
            else if (v.contains("(MC: 1.19.3)")) {
            	BukkitVersionHelper.helper = loadVersionHelper("org.dynmap.bukkit.helper.v119_3.BukkitVersionHelperSpigot119_3");
            }
            else if (v.contains("(MC: 1.19.")) {
            	BukkitVersionHelper.helper = loadVersionHelper("org.dynmap.bukkit.helper.v119_4.BukkitVersionHelperSpigot119_4");
            }
            else {
            	BukkitVersionHelper.helper = loadVersionHelper("org.dynmap.bukkit.helper.BukkitVersionHelperCB");
            }
        }
        return BukkitVersionHelper.helper;
    }

}
