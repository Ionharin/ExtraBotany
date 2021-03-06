package com.meteor.extrabotany.common.world;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import com.meteor.extrabotany.common.item.ModItems;

public class ModChestGen {
	public static void init(){
		WeightedRandomChestContent key = new WeightedRandomChestContent(new ItemStack(ModItems.key), 1, 4, 1);
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, key);
		addAll(new ItemStack(ModItems.boxs), 1, 1, 1, false);
		addAll(new ItemStack(ModItems.dungeonbox), 1, 2, 1, false);
	}
	
	public static void addAll(ItemStack itemstack, int min, int max, int stacksize, boolean lib){
		WeightedRandomChestContent rand = new WeightedRandomChestContent(itemstack, min, max, stacksize);
		ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, rand);
		ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, rand);
		ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR, rand);
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST, rand);
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, rand);
		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER, rand);
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, rand);
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR, rand);
		ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING, rand);
		ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH, rand);
		if(lib)
			ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY, rand);
	}
}
