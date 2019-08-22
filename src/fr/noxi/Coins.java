package fr.noxi;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fr.noxi.cache.PlayerData;
import fr.noxi.cache.PlayerDataManager;
import fr.noxi.grades.Rank;

public class Coins extends JavaPlugin implements Listener{
	public static Coins instance;
	public static Coins getInstance(){
		return instance;
	}
	public SqlConnection sql;
	public PlayerDataManager dataManager = new PlayerDataManager(this);
	public Map<Player, PlayerData> dataPlayers = new HashMap<>();
	
	public void onEnable(){
		sql = new SqlConnection(this, "jdbc:mysql://","localhost","servermc", "root", "root");
		getServer().getPluginManager().registerEvents(this, this);
		sql.connection();
		instance = this;
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}
	public void onDisable(){
		sql.disconnect();
	}
	@EventHandler
	public void join(PlayerJoinEvent e){
		Player p = e.getPlayer();
		sql.createAccount(p);
		dataManager.loadPlayerData(p);
		p.setDisplayName(sql.getRank(p).toString());
	}
	
	@EventHandler
	public void chat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		Rank rank = sql.getRank(p);
		e.setFormat(rank.getName() + " " + p.getName() + " : " + rank.getTag() + e.getMessage());
	}
	@EventHandler
	public void quit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		dataManager.savePlayerData(p);
	}
	public void TeleportBungee(Player player, String serv){
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		
		try{
			System.out.println("Tentage de tp");
			out.writeUTF("Connect");
			out.writeUTF(serv);
			player.sendPluginMessage(this, "BungeeCord", b.toByteArray());
		}catch(IOException e){
			System.out.println("Tentage foiré");
			player.sendPluginMessage(this, "BungeeCord", b.toByteArray());
		}
	}
}
