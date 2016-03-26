package lib.securebit.chat;

import java.util.List;

import org.bukkit.entity.Player;

public interface ChatRoom {

	public abstract void addMember(Player p);
	
	public abstract void removeMember(Player p);
	
	public abstract void setKey(String key);
	
	public abstract void setDefaultPermission(ChatPermission level);
	
	public abstract void setPermission(Player p, ChatPermission level);
	
	public abstract void broadcast(String message);
	
	public abstract String getKey();
	
	public abstract String getName();
	
	public abstract ChatPermission getDefaultPermission();
	
	public abstract ChatPermission getPermission(Player p);
	
	public abstract List<Player> getMembers();
}
