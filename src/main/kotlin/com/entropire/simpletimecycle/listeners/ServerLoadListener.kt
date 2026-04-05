package com.entropire.simpletimecycle.listeners

import com.entropire.simpletimecycle.SimpleTimeCycle
import com.entropire.simpletimecycle.TimeController
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerLoadEvent

class ServerLoadListener(private val plugin: SimpleTimeCycle) : Listener {

    @EventHandler
    fun onServerLoad(event: ServerLoadEvent) {
        val config = plugin.config
        var dayCycleSpeedMinutes = config.getLong("day-cycle-length")
        dayCycleSpeedMinutes = if (dayCycleSpeedMinutes < 1) 1L else dayCycleSpeedMinutes
        var nightCycleSpeedMinutes = config.getLong("night-cycle-length")
        nightCycleSpeedMinutes = if (nightCycleSpeedMinutes < 1) 1L else nightCycleSpeedMinutes
        val handledWorlds = plugin.server.worlds.filter { it.name in config.getStringList("affected-worlds") }
        TimeController(handledWorlds, dayCycleSpeedMinutes, nightCycleSpeedMinutes).runTaskTimer(plugin, 0, 1)
    }
}