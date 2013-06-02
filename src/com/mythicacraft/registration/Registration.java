package com.mythicacraft.registration;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.mythicacraft.registration.commands.Commands;

public class Registration extends JavaPlugin {
	
	public final Logger log = Logger.getLogger("Minecraft");
	
	public void onDisable() {
		log.info("[Registration] Disabled!");
	}
	
	public void onEnable(){
		getCommand("register").setExecutor(new Commands());
		getCommand("reg").setExecutor(new Commands());
		
		log.info("[Registration] Enabled!");
	}

}
