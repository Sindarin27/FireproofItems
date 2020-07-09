package com.grillo78.fireproofitems.init;

import com.grillo78.fireproofitems.FireproofItemsMod;

import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonEvents {

	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityItem) {
			if (FireproofItemsMod.ITEMS.contains((((EntityItem) event.getEntity()).getItem().getItem())))
				event.getEntity().setEntityInvulnerable(true);
		}
	}

}
