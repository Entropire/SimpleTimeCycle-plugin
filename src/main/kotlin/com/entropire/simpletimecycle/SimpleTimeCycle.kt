package com.entropire.simpletimecycle

import com.entropire.simpletimecycle.listeners.ServerLoadListener
import org.bukkit.plugin.java.JavaPlugin

class SimpleTimeCycle : JavaPlugin() {

    override fun onEnable() {
        saveDefaultConfig()
        server.pluginManager.registerEvents(ServerLoadListener(this), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
