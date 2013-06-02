package com.mythicacraft.registration.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class Commands implements CommandExecutor {

	Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Registration");
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(commandLabel.equalsIgnoreCase("register") || commandLabel.equalsIgnoreCase("reg")){
			if(!sender.hasPermission("register.registered")){
				if(args.length != 2){
					sender.sendMessage(ChatColor.RED + "Please enter a valid email and password by typing '/register [email] [password]'");
					return true;
				}
				
				/* To do:
				 *  - Accept args[0] and args[1], obtain sendername and put into an array
				 *  - Regex that checks email
				 *  - Database check that scans for duplicates of email/sendername
				 *  - Timeout and /register confirm setup or /register cancel using "runnable"
				 *  
				 */
				
				
				
				
			}
			else{
				sender.sendMessage(ChatColor.GREEN + "You are already registered!");
			}
		}
		
		return true;
	}//End onCommand
	
}//End Class
