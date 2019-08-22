package fr.noxi.grades;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;

public enum Rank {
	CITOYEN(0, "§7", ChatColor.GRAY),
	VIP(10,"§7[§bV§cI§bP§7]", ChatColor.BLUE),
	EPICXONIS(100, "§dEpic§d§lXonis§5 >", ChatColor.LIGHT_PURPLE),
	MODO(200, "§7[§6Modo§7]", ChatColor.YELLOW),
	RESPMODO(400, "§7[§6Resp§7.§eModo§7]", ChatColor.YELLOW),
	ADMIN(900, "§c§lADMIN§4 >", ChatColor.RED);
	
	private int power;
	private String displayName;
	private ChatColor colorTag;
	public static Map<Integer, Rank> grade = new HashMap<>();
	
	Rank(int power,String displayName, ChatColor tag){
		this.power = power;
		this.displayName = displayName;
		this.colorTag = tag;
	}
	
	static{
		for(Rank r : Rank.values()){
			grade.put(r.getPower(), r);
		}
	}
	
	public int getPower(){
		return power;
	}
	public String getName(){
		return displayName;
	}
	public ChatColor getTag(){
		return colorTag;
	}
	public static Rank powerToRank(int power){
		return grade.get(power);
	}
}
