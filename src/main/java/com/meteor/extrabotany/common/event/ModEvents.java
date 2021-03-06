package com.meteor.extrabotany.common.event;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;

public class ModEvents {
	public static void init() {
		MinecraftForge.EVENT_BUS.register(new EventKnowledgeTypeUnlock());
		FMLCommonHandler.instance().bus().register(new EventKnowledgeTypeUnlock());
	    MinecraftForge.EVENT_BUS.register(new EventPunish());
	    FMLCommonHandler.instance().bus().register(new EventPunish());
		MinecraftForge.EVENT_BUS.register(new EventHighDamageResistance());
		FMLCommonHandler.instance().bus().register(new EventHighDamageResistance());
		MinecraftForge.EVENT_BUS.register(new EventShield());
		FMLCommonHandler.instance().bus().register(new EventShield());
		MinecraftForge.EVENT_BUS.register(new EventUnbreakable());
		FMLCommonHandler.instance().bus().register(new EventUnbreakable());
	    MinecraftForge.EVENT_BUS.register(new EventGaiaIII());
	    FMLCommonHandler.instance().bus().register(new EventGaiaIII());
	    MinecraftForge.EVENT_BUS.register(new EventElven());
	    FMLCommonHandler.instance().bus().register(new EventElven());
	    MinecraftForge.EVENT_BUS.register(new EventAchievement());
	    FMLCommonHandler.instance().bus().register(new EventAchievement());
	    MinecraftForge.EVENT_BUS.register(new EventMobDrop());
	    FMLCommonHandler.instance().bus().register(new EventMobDrop());
	}
}
