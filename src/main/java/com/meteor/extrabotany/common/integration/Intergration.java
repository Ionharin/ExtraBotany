package com.meteor.extrabotany.common.integration;

import minetweaker.MineTweakerAPI;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.integration.hugetools.ItemRender;
import com.meteor.extrabotany.common.integration.minetweaker.MinetweakerCompact;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class Intergration {
	public static void preInitClient(FMLPreInitializationEvent event) {
		
	}
	
	public static void initClient(FMLInitializationEvent event) {
		ItemRender.initHugeItemRender();
		if(ExtraBotany.minetweakerLoaded)
			MinetweakerCompact.init();
	}
	
	public static void postInitClient(FMLPostInitializationEvent event) {
		
	}
	
	public static void preInit(FMLPreInitializationEvent event) {
		
	}
	
	public static void init(FMLInitializationEvent event) {
		
	}
	
	public static void postInit(FMLPostInitializationEvent event) {
		
	}
	
    public static void serverStarting(FMLServerStartingEvent event)
    {

    }
}
