package com.entropire.simpletimecycle.listeners

import com.entropire.simpletimecycle.SimpleTimeCycle
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent
import net.kyori.adventure.text.Component

class PlayerSleepListener(
    private val plugin: SimpleTimeCycle
) : Listener {
    @EventHandler
    fun onPlayerSleep(event: PlayerBedEnterEvent) {
        val player = event.player
        if(!plugin.config.getBoolean("enable-player-sleep")){
            val rawMessage = plugin.config.getString("player-sleep-disabled-message") ?: "Sleeping is disabled!"
            val parsedMessage = rawMessage
                .replace("{player}", player.name)
                .replace("{world}", player.world.name)
            val message = Component.text(parsedMessage)
            player.sendActionBar(message)
            event.isCancelled = true
        }

    }
}