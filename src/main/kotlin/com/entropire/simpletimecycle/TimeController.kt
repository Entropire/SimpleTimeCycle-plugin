package com.entropire.simpletimecycle

import org.bukkit.GameRule
import org.bukkit.World
import org.bukkit.scheduler.BukkitRunnable

class TimeController(
    private val handledWorlds: List<World>,
    private val dayCycleSpeedMinutes: Long,
    private val nightCycleSpeedMinutes: Long,
) : BukkitRunnable() {

    private val timeBuffers = mutableMapOf<World, Double>()

    override fun run() {
        for (world in handledWorlds) {
            if (world.getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE) == false) continue

            val currentTime = world.time

            val desiredPerTick = if (currentTime in 0..11999) {
                12000.0 / (dayCycleSpeedMinutes * 60.0 * 20.0)
            } else {
                12000.0 / (nightCycleSpeedMinutes * 60.0 * 20.0)
            }

            val extra = desiredPerTick - 1.0

            val buffer = timeBuffers.getOrPut(world) { 0.0 }
            val newBuffer = buffer + extra

            val wholeTicks = newBuffer.toLong()

            if (wholeTicks != 0L) {
                world.time = (((currentTime + wholeTicks) % 24000) + 24000) % 24000

                if(world.time < 12541L){
                    SimpleTimeCycle.canSkipNight[world] = true
                }
            }

            timeBuffers[world] = newBuffer - wholeTicks
        }
    }
}
