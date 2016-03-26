package lib.securebit.chat;

import java.util.Collection;

import org.bukkit.entity.Player;

public interface ChatManager {
	
	public abstract void add(ChatRoom channel);
	
	public abstract void remove(String name);
	
	public abstract void setDefault(Player p, ChatRoom channel);
			
	public abstract ChatRoom get(String name);
	
	public abstract ChatRoom getDefault(Player p);
	
	public abstract Collection<ChatRoom> getChannels();
	
	public abstract Collection<ChatRoom> getPlayerChannels(Player p);
}
