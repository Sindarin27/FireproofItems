package sindarin.fireproofitems;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(FireProofItemsMod.MOD_ID)
public class FireProofItemsMod {
    public static final String MOD_ID = "fireproofitems";

    public static final ResourceLocation itemTagLocation = new ResourceLocation(MOD_ID, "fire_proof_items");
    public static final TagKey<Item> itemTag = ItemTags.create(itemTagLocation);

    public FireProofItemsMod() {
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.SERVER, ModConfig.serverSpec);
        MinecraftForge.EVENT_BUS.addListener(this::onEntitySpawn);
    }

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ItemEntity) {
            ItemStack stack = ((ItemEntity) event.getEntity()).getItem();
            Item item = stack.getItem();
            item.isFireResistant = stack.is(itemTag) == ModConfig.SERVER.isWhitelist.get();
        }
    }
}
