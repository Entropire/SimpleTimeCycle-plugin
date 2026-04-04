package com.entropire.simpletimecycle

import org.bukkit.Bukkit
import org.bukkit.GameRule
import org.bukkit.World
import org.bukkit.scheduler.BukkitRunnable

class TimeController(
    private val handledWorlds: List<World>,
    private val dayCycleSpeedMinutes: Long,
    private val nightCycleSpeedMinutes: Long,
): BukkitRunnable() {
    private val timeBuffers = mutableMapOf<World, Double>()
    private val sleepStartTimes = mutableMapOf<World, Long>()

    init {
        handledWorlds.forEach {
            it.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false)
            timeBuffers[it] = 0.0
        }
    }

    override fun run() {
        for(world in handledWorlds) {
            val currentTime = world.time

            val dayDurationTicks = dayCycleSpeedMinutes * 60.0 * 20.0
            val nightDurationTicks = nightCycleSpeedMinutes * 60.0 * 20.0

            val increment = if (currentTime in 0..11999) {
                12000.0 / dayDurationTicks
            } else {
                12000.0 / nightDurationTicks
            }

            if (currentTime in 12000..23999) {
                val players = world.players
                val sleeping = players.count { it.isSleeping }

                if (players.isNotEmpty() && sleeping > 0) {

                    val now = System.currentTimeMillis()
                    val start = sleepStartTimes.getOrPut(world) { now }

                    val delayMs = 2000L

                    if (now - start >= delayMs) {
                        world.time = 0

                        sleepStartTimes.remove(world)
                    }

                    continue
                } else {
                    sleepStartTimes.remove(world)
                }
            }


            val buffer = timeBuffers[world] ?: 0.0
            val newBuffer = buffer + increment

            if (newBuffer >= 1.0) {
                val add = newBuffer.toLong()
                world.time = (currentTime + add) % 24000
                timeBuffers[world] = newBuffer - add
            } else {
                timeBuffers[world] = newBuffer
            }
        }
    }
}