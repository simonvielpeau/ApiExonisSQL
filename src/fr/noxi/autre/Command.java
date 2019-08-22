package fr.noxi.autre;



import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.noxi.SqlConnection;




public class Command implements CommandExecutor{
	
	public SqlConnection sql;
	public static Command instance;
	public static Command getInstance(){
		return instance;
	}


	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String msg, String[] args) {
		if(sender instanceof Player){
			instance = this;
		
		
		}
		return false;
	}
}
