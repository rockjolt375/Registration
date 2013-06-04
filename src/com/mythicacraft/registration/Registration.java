package com.mythicacraft.registration;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.mythicacraft.registration.commands.Commands;

public class Registration extends JavaPlugin {
	
	public final Logger log = Logger.getLogger("Minecraft");
	
	public final HashMap<Player, String> emailHash = new HashMap<Player, String>();
	public final HashMap<Player, String> passHash = new HashMap<Player, String>();
	public final HashMap<Player, String> taskIDHash = new HashMap<Player, String>();
	
	public void onDisable() {
		log.info("[Registration] Disabled!");
	}
	
	public void onEnable(){
		getCommand("register").setExecutor(new Commands(this));
		getCommand("reg").setExecutor(new Commands(this));
		getCommand("confirm").setExecutor(new Commands(this));
		getCommand("cancel").setExecutor(new Commands(this));
		log.info("[Registration] Enabled!");
	}

}
