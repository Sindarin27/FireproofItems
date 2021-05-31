package com.sindarin.fireproofitems;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FireProofItemsMod.MOD_ID)
public class FireProofItemsMod {
    public static final String MOD_ID = "fireproofitems";

    public static final ResourceLocation itemTag = new ResourceLocation(MOD_ID, "fire_proof_items");

    public FireProofItemsMod() {
        MinecraftForge.EVENT_BUS.addListener(this::onEntitySpawn);
    }

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof ItemEntity) {
            if (ItemTags.getAllTags().getTagOrEmpty(itemTag).contains(((ItemEntity) event.getEntity()).getItem().getItem())) {
                event.getEntity().setInvulnerable(true);
            }
        }
    }
}
