package lib.securebit.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

public class BasicChatRoom implements ChatRoom {

	protected String name;
	protected String key;
	protected ChatPermission defaultPermission;
	protected Map<Player, ChatPermission> members;
	
	public BasicChatRoom(String name) {
		this(name, null);
	}
	
	public BasicChatRoom(String name, String key) {
		this(name, key, ChatPermission.BOTH);
	}
	
	public BasicChatRoom(String name, String key, ChatPermission defaultPermission) {
		this.name = name;
		this.key = key;
		this.defaultPermission = defaultPermission;
		this.members = new HashMap<>();
	}
	
	@Override
	public void addMember(Player p) {
		this.members.put(p, this.defaultPermission);
	}

	@Override
	public void removeMember(Player p) {
		this.members.remove(p);
	}

	@Override
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public void setDefaultPermission(ChatPermission level) {
		this.defaultPermission = level;
	}

	@Override
	public void broadcast(String message) {
		this.members.keySet().forEach(p -> {
			ChatPermission permission = this.members.get(p);
			if (permission == ChatPermission.READ || permission == ChatPermission.BOTH) {
				p.sendMessage(message);
			}
		});
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public ChatPermission getDefaultPermission() {
		return this.defaultPermission;
	}

	@Override
	public List<Player> getMembers() {
		return new ArrayList<Player>(this.members.keySet());
	}

	@Override
	public ChatPermission getPermission(Player p) {
		return this.members.get(p);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setPermission(Player p, ChatPermission level) {
		this.members.put(p, level);
	}

}