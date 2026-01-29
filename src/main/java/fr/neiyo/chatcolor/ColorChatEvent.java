package fr.neiyo.chatcolor;

import com.hypixel.hytale.event.EventRegistry;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.permissions.PermissionsModule;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import pt.supercrafting.hytale.adventure.util.HytaleDirectConverter;

import javax.annotation.Nonnull;

public final class ColorChatEvent {

    private final PlayerChatEvent.Formatter miniMessageFormatter = (playerRef, msg) -> {
        return Message.join(Message
                        .translation("server.chat.playerMessage")
                        .param("username", playerRef.getUsername())
                        .param("message", ""),
                HytaleDirectConverter.fromMiniMessage(msg)); // placeholder is broken for message options ¯\_(ツ)_/¯
    };

    public ColorChatEvent(JavaPlugin plugin) {
        EventRegistry eventRegistry = plugin.getEventRegistry();
        eventRegistry.registerGlobal(PlayerChatEvent.class, this::onPlayerChat);
    }

    private void onPlayerChat(PlayerChatEvent event) {
        PlayerRef sender = event.getSender();
        if (!hasPermission(sender)) return;
        event.setFormatter(miniMessageFormatter);
    }

    private boolean hasPermission(@Nonnull PlayerRef playerRef) {
        return PermissionsModule.get().hasPermission(playerRef.getUuid(), "chatcolor.use"); //TODO: configurable permission ?
    }
}