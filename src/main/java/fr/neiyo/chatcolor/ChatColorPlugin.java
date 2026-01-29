package fr.neiyo.chatcolor;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;

public class ChatColorPlugin extends JavaPlugin {

    public ChatColorPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        new ChatColorEvent(this);
    }
}
