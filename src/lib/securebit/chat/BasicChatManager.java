package lib.securebit.chat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.entity.Player;

public class BasicChatManager implements ChatManager {

	protected Map<Player, ChatRoom> defaults;
	protected Map<String, ChatRoom> channels;
	
	public BasicChatManager() {
		this.defaults = new HashMap<>();
		this.channels = new HashMap<>();
	}
	
	@Override
	public void add(ChatRoom channel) {
		this.channels.put(channel.getName(), channel);
	}

	@Override
	public void remove(String name) {
		this.channels.remove(name);
	}

	@Override
	public void setDefault(Player p, ChatRoom channel) {
		this.defaults.put(p, channel);
	}

	@Override
	public ChatRoom get(String name) {
		return this.channels.get(name);
	}

	@Override
	public ChatRoom getDefault(Player p) {
		return this.defaults.get(p);
	}

	@Override
	public Collection<ChatRoom> getChannels() {
		return this.channels.values();
	}

	@Override
	public Collection<ChatRoom> getPlayerChannels(Player p) {
		return this.channels.values().stream().filter(chatroom -> {
			return chatroom.getMembers().contains(p);
		}).collect(Collectors.toList());
	}

}
