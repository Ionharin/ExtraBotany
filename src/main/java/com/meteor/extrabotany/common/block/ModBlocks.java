package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.decor.BlockElvenQuartzSlab;
import com.meteor.extrabotany.common.block.decor.BlockElvenQuartzStairs;
import com.meteor.extrabotany.common.block.decor.BlockGaiaQuartzSlab;
import com.meteor.extrabotany.common.block.decor.BlockGaiaQuartzStairs;
import com.meteor.extrabotany.common.block.subtile.SubTileJudasvow;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileArtifaconia;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileIcebirdium;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileNecrofluer;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileNumeronDandelife;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileNumeronbalsam;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileVolatilily;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileWoodienia;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileBlueenchantress;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileCandyflower;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileGeminiorchid;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileMoonlightlily;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileOmniviolet;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileSunshinelily;
import com.meteor.extrabotany.common.block.tile.TileRelicPlate;
import com.meteor.extrabotany.common.lib.LibBlockName;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.registry.GameRegistry;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.block.decor.slabs.BlockModSlab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;

public class ModBlocks {
	
	public static Block specialFlower;
	//Gaia Quartz
	public static Block gaiaquartz;
	public static Block gaiaquartzslabfull;
	public static Block gaiaquartzslab;
	public static Block gaiaquartzstairs;
	//Elven Quartz
	public static Block elvenquartz;
	public static Block elvenquartzslabfull;
	public static Block elvenquartzslab;
	public static Block elvenquartzstairs;
	
	public static Block relicplate;
	
	public static void init() {
		relicplate = new BlockRelicPlate();
		specialFlower = new BlockSpecial();
		//Gaia Quartz
		gaiaquartz = new BlockMods(Material.iron, LibBlockName.GAIAQUARTZ);
		gaiaquartzslab = new BlockGaiaQuartzSlab(false);
		gaiaquartzslabfull = new BlockGaiaQuartzSlab(true);
		gaiaquartzstairs = new BlockGaiaQuartzStairs();
		//Elven Quartz
		elvenquartz = new BlockMods(Material.iron, LibBlockName.ELVENQUARTZ);
		elvenquartzslab = new BlockElvenQuartzSlab(false);
		elvenquartzslabfull = new BlockElvenQuartzSlab(true);
		elvenquartzstairs = new BlockElvenQuartzStairs();
		
		
		((BlockModSlab) gaiaquartzslab).register();
		((BlockModSlab) gaiaquartzslabfull).register();
		((BlockModSlab) elvenquartzslab).register();
		((BlockModSlab) elvenquartzslabfull).register();
		
		registerTile(TileRelicPlate.class, LibBlockName.RELICPLATE);
		initTileEntities();
	}
	
	private static void initTileEntities() {
		BotaniaAPI.registerSubTile(LibBlockName.ARTIFACONIA, SubTileArtifaconia.class);
		BotaniaAPI.registerSubTile(LibBlockName.JUDASVOW, SubTileJudasvow.class);
		BotaniaAPI.registerSubTile(LibBlockName.ICEBIRDIUM, SubTileIcebirdium.class);
		BotaniaAPI.registerSubTile(LibBlockName.NUMERON_BALSAM, SubTileNumeronbalsam.class);
		BotaniaAPI.registerSubTile(LibBlockName.VOLATILILY, SubTileVolatilily.class);
		BotaniaAPI.registerSubTile(LibBlockName.OMNIVIOLET, SubTileOmniviolet.class);
		BotaniaAPI.registerSubTile(LibBlockName.WOODIENIA, SubTileWoodienia.class);
		BotaniaAPI.registerSubTile(LibBlockName.NECRO_FLUER, SubTileNecrofluer.class);
		BotaniaAPI.registerSubTile(LibBlockName.NUMERON_DANDELIFE, SubTileNumeronDandelife.class);
		BotaniaAPI.registerSubTile(LibBlockName.GEMINIORCHID, SubTileGeminiorchid.class);
		BotaniaAPI.registerSubTile(LibBlockName.CANDY_FLOWER, SubTileCandyflower.class);
		BotaniaAPI.registerSubTile(LibBlockName.SUNSHINE_LILY, SubTileSunshinelily.class);
		BotaniaAPI.registerSubTile(LibBlockName.MOONLIGHT_LILY, SubTileMoonlightlily.class);
		if(ExtraBotany.arsmagicaLoaded = true){
		BotaniaAPI.registerSubTile(LibBlockName.BLUE_ENCHANTRESS, SubTileBlueenchantress.class);
		}		
		
	}
	
	private static void registerTile(Class<? extends TileEntity> clazz, String key) {
		GameRegistry.registerTileEntity(clazz, LibReference.PREFIX_MOD + key);
	}
}
