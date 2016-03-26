package lib.securebit.chat;

import lib.securebit.InfoLayout;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class ChatHandler implements Listener {

	private InfoLayout layout;
	private ChatManager chatManager;
	
	public ChatHandler(ChatManager manager, InfoLayout layout) {
		this.layout = layout;
		this.chatManager = manager;
	}
	
	@EventHandler
	public void onAsyncChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String message = e.getMessage();
		ChatRoom defaultChannel = this.chatManager.getDefault(p);
		
		for (ChatRoom chatroom : this.chatManager.getPlayerChannels(p)) {
			if (chatroom.getPermission(p) != ChatPermission.READ) { // -> WRITE or BOTH
				if (message.startsWith(chatroom.getKey())) {
					this.handleMessage(e, chatroom);
					return;
				}
			}
		}
		
		if (defaultChannel != null && defaultChannel.getPermission(p) != ChatPermission.READ) {
			this.handleMessage(e, defaultChannel);
			return;
		}
		
		this.layout.message(p, "-Fehler: Deine Nachricht konnte nicht zugestellt werden.-");
	}
	
	private void handleMessage(AsyncPlayerChatEvent e, ChatRoom channel) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (!this.chatManager.getPlayerChannels(p).contains(channel)) {
				e.getRecipients().remove(p);
			}
		}
		
		channel.getMembers().forEach(p -> {
			if (channel.getPermission(p) == ChatPermission.WRITE) { // Write = No read
				e.getRecipients().remove(p);
			}
		});
	}
	
	public void init(Plugin plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
}
