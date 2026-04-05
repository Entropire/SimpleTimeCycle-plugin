package com.entropire.simpletimecycle.listeners

import com.entropire.simpletimecycle.SimpleTimeCycle
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerBedEnterEvent
import org.bukkit.event.world.TimeSkipEvent
import kotlin.math.ceil


class PlayerSleepListener(
    private val plugin: SimpleTimeCycle
) : Listener {

    private val playerSleepPercentage: Int = plugin.config.getInt("player-sleep-percentage").coerceIn(0, 100)

    @EventHandler
    fun onPlayerSleep(event: PlayerBedEnterEvent) {
        val player = event.player
        val world = player.world

        if (!plugin.config.getBoolean("enable-player-sleep")) {
            val message = plugin.config.getString("player-sleep-disabled-message")
                ?.replace("{player}", player.name)
                ?.replace("{world}", world.name)
                ?: "Sleeping is disabled!"

            player.sendActionBar(Component.text(message))
            event.isCancelled = true
            return
        }

        if (world.time < 12541L) return

        val playersInWorld = world.players
        val sleepingPlayers = playersInWorld.count { it.isSleeping } + 1
        val sleepingPlayersNeeded = ceil(playersInWorld.size * playerSleepPercentage / 100.0).toInt()

        val rawMessage = if (canNightSkip(world, player)) {
            plugin.config.getString("player-sleep-success-message") ?: "Night skipped in {world}!"
        } else {
            plugin.config.getString("player-sleep-percentage-message") ?: "Not enough players sleeping in {world}!"
        }

        Bukkit.getScheduler().runTaskLater(plugin, Runnable {
            val parsedMessage = rawMessage
                .replace("{world}", world.name)
                .replace("{amount-needed}", sleepingPlayersNeeded.toString())
                .replace("{amount-sleeping}", sleepingPlayers.toString())

            val message = Component.text(parsedMessage)
            playersInWorld.forEach { it.sendActionBar(message) }
        }, 1L)
    }

    @EventHandler
    fun onTimeSkip(event: TimeSkipEvent) {
        if (event.skipReason != TimeSkipEvent.SkipReason.NIGHT_SKIP) return

        val world = event.world
        event.isCancelled = true

        if (canNightSkip(world)) {
            world.time = 0L
        }
    }

    fun canNightSkip(world: World, includePlayer: Player? = null): Boolean {
        val playersInWorld = world.players
        val sleepingPlayers = playersInWorld.count { it.isSleeping } + if (includePlayer != null) 1 else 0
        val sleepingPlayersNeeded = ceil(playersInWorld.size * playerSleepPercentage / 100.0).toInt()
        return sleepingPlayers >= sleepingPlayersNeeded
    }
}