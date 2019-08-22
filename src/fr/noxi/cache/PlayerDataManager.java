package fr.noxi.cache;

import org.bukkit.entity.Player;

import fr.noxi.Coins;

public class PlayerDataManager {

	private Coins pl;
	
	public PlayerDataManager(Coins pl) {
		this.pl = pl;
	}

	public void loadPlayerData(Player player){
		if(!pl.dataPlayers.containsKey(player)){
			PlayerData pData = pl.sql.createPlayerData(player);
			pl.dataPlayers.put(player, pData);
		}
		
	}
	
	public void savePlayerData(Player player){
		if(pl.dataPlayers.containsKey(player)){
			pl.sql.updatePlayerData(player);
			
		}
	}
	
}
