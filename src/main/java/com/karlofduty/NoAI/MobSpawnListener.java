package com.karlofduty.NoAI;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class MobSpawnListener implements Listener
{
    private NoAI plugin;
    public MobSpawnListener(NoAI plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event)
    {
        final LivingEntity entity = event.getEntity();
        final SpawnReason spawnReason = event.getSpawnReason();
        if (spawnMethodIsDisabled(entity, spawnReason))
        {
            entity.setAI(false);
        }
    }

    public boolean spawnMethodIsDisabled(LivingEntity entity, SpawnReason spawnReason)
    {
        return plugin.getConfig().getStringList("disabled-mobs." + entity.getType().name() + ".disable-for-spawn-methods").contains(spawnReason.name());
    }
}
