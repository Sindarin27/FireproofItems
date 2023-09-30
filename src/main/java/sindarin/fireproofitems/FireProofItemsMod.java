package sindarin.fireproofitems;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(FireProofItemsMod.MOD_ID)
public class FireProofItemsMod {
    public static final String MOD_ID = "fireproofitems";

    public static final ResourceLocation itemTag = new ResourceLocation(MOD_ID, "fire_proof_items");

    public FireProofItemsMod() {
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.SERVER, ModConfig.serverSpec);
        MinecraftForge.EVENT_BUS.addListener(this::onEntitySpawn);
    }

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof ItemEntity) {
            Item item = ((ItemEntity) event.getEntity()).getItem().getItem();
            item.isFireResistant = ItemTags.getAllTags().getTagOrEmpty(itemTag).contains(item) == ModConfig.SERVER.isWhitelist.get();
        }
    }
}
