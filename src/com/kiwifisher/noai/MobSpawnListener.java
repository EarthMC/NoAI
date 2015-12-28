package com.kiwifisher.noai;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import java.util.List;

/**
 * This class listens for mobs spawning, and checks if they are meant to have their AI disabled, and if they are it disables it. Quite simple really.
 */
public class MobSpawnListener implements Listener {

    private NoAI plugin;

    public MobSpawnListener(NoAI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {

        final LivingEntity entity = event.getEntity();
        final CreatureSpawnEvent.SpawnReason spawnReason = event.getSpawnReason();

        /*
        If the mob is meant to be disabled, and so is it's spawn method, the disabled it.
         */
        if (mobIsDisabled(entity) && spawnMethodIsDisabled(entity, spawnReason)) {

            disableAI(entity);

        }

    }


    /**
     * Disables a mobs AI
     * @param bukkitEntity The mob to disble
     */
    public void disableAI(org.bukkit.entity.Entity bukkitEntity) {
        net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) bukkitEntity).getHandle();
        NBTTagCompound tag = nmsEntity.getNBTTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        nmsEntity.c(tag);
        tag.setInt("NoAI", 1);
        nmsEntity.f(tag);
    }

    /**
     * Checks if a mob is meant to have it's AI disabled.
     * @param entity The entity to check
     * @return Return true if it is meant to be disabled.
     */
    public boolean mobIsDisabled(LivingEntity entity) {

        return plugin.getConfig().contains("disabled-mobs." + entity.getType().name() + ".disabled") && plugin.getConfig().getBoolean("disabled-mobs." + entity.getType().name() + ".disabled");

    }

    /**
     * Checks if a mobs spawn method is disabled
     * @param entity The entity to check
     * @param spawnReason The reason it spawned
     * @return Return true if the mob should be disabled.
     */
    public boolean spawnMethodIsDisabled(LivingEntity entity, CreatureSpawnEvent.SpawnReason spawnReason) {

        List<String> blockedSpawnMethods = plugin.getConfig().getStringList("disabled-mobs." + entity.getType().name() + ".disable-for-spawn-methods");

        return blockedSpawnMethods.contains(spawnReason.name());

    }

}
