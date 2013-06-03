package com.mythicacraft.registration.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mythicacraft.registration.Registration;

/* To do:
 *  - Database check that scans for duplicates of email/sendername
 */

public class Commands implements CommandExecutor {

	Boolean confirmed = false;
	
	public Registration plugin;

	 public Commands(Registration plugin) {
	  this.plugin = plugin;
	 }
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		
		if(commandLabel.equalsIgnoreCase("register") || commandLabel.equalsIgnoreCase("reg")){
			if(!sender.hasPermission("register.registered")){
				

				
				if(args.length != 2){ //If player did not enter /register [email] [password] command format
					sender.sendMessage(ChatColor.RED + "Please enter a valid email and password by typing '/register [email] [password]'");
					return true;
				}
				
				if(!validate(args[0])){ //Checks validity of email address
					sender.sendMessage(ChatColor.RED + "The email you've entered appears to be invalid. If you believe this is an error, please use '/helpme' to contact a mod or you may manually register at the website: www.mythicacraft.com");
					return true;
				}
				
				String [] regInfo = {args[0], args[1]};

				confirmInfo(regInfo, sender);
				
			} //End perm check hasPermission
			else{
				sender.sendMessage(ChatColor.GREEN + "You are already registered!");
			}
		} //End commandLabel = reg or register
		
		if(commandLabel.equalsIgnoreCase("confirm")){ //If player confirms information, execute to PHP script
			if(plugin.playerHash.containsKey(player)){
			//Run php script shit
			plugin.playerHash.remove(player);
			confirmed = true;
			return true;
			}
			else{
				sender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");
			}
		}
		
		if(commandLabel.equalsIgnoreCase("cancel")){ //If player cancels process, restart.
			if(plugin.playerHash.containsKey(player)){
			sender.sendMessage(ChatColor.GREEN + "You have cancelled your registration, please try again.");
			confirmed = true;
			plugin.playerHash.remove(player);
			return true;
			}
			else{
				sender.sendMessage(ChatColor.RED + "You do not have permissions to use this command");
			}
		}
		
		return true;
	}//End onCommand
	
	 public static boolean validate(String emailAdress) { //Does basic check for email structure
		  Pattern checkRegex = Pattern.compile("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})");
		  Matcher regexMatcher = checkRegex.matcher(emailAdress);
		  if(regexMatcher.find()) return true;
		  return false;
		 }
	 
	 public boolean confirmInfo(String[] info, final CommandSender sender){ //Sends confirmation message and initiates timeout
		 sender.sendMessage(ChatColor.GREEN + "You have entered " + ChatColor.GOLD + info[0] + ChatColor.GREEN + " and " + ChatColor.GOLD + info[1] + ChatColor.GREEN + ". Is this correct? Type '" + ChatColor.BLUE + "/register confirm" + ChatColor.GREEN +"' to confirm or '" + ChatColor.BLUE + "/register cancel" + ChatColor.GREEN + "' to try again. This will time out in 20 seconds.");
		Player player = (Player) sender;
		 plugin.playerHash.put(player, "confirm");
		 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			    public void run() {
			     if(confirmed = false){
			    	 sender.sendMessage(ChatColor.RED + "You have not confirmed your registration. Please retart to try again.");
			    	 return;
			     }
			    } //end void run()
			   } //end bukkit start scheduler
			   , 400L); 
		 
			   return true;
	 }
	 
}//End Class
