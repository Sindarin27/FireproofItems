package com.grillo78.fireproofitems;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.Logger;

import com.grillo78.fireproofitems.init.CommonEvents;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = FireproofItemsMod.MODID, name = FireproofItemsMod.NAME, version = FireproofItemsMod.VERSION)
public class FireproofItemsMod
{
	
	public static final ArrayList<Item> ITEMS = new ArrayList<>();
	
    public static final String MODID = "fireproofitems";
    public static final String NAME = "Fireproof Items Mod";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        MinecraftForge.EVENT_BUS.register(new CommonEvents());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	try {
    		File configFile = new File("config/FireproofMod.txt");
        	if(!configFile.exists()) {
        		configFile.createNewFile();
        	}
        	BufferedReader bufReader = new BufferedReader(new FileReader(configFile));
        	String row;
        	while((row = bufReader.readLine()) != null) {
        		if(ForgeRegistries.ITEMS.containsKey(new ResourceLocation(row))) {
        			ITEMS.add(ForgeRegistries.ITEMS.getValue(new ResourceLocation(row)));
        		}
        	}
    	} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
