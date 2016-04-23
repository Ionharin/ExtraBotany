package com.meteor.extrabotany.common.blocks.subtile.generating;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import am2.AMCore;

import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;

import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.subtile.generating.SubTileHydroangeas;
import vazkii.botania.common.block.subtile.generating.SubTilePassiveGenerating;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.lib.LibMisc;

public class SubTileBlueenchantress extends SubTilePassiveGenerating{
	private static final String TAG_BURN_TIME = "burnTime";
	private static final String TAG_COOLDOWN = "cooldown";

	private static final int[][] OFFSETS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, 1 }, { -1, -1 }, { 1, 1 }, { 1, -1 } };

	int burnTime, cooldown;

	@Override
	public void onUpdate() {
		super.onUpdate();

		if(cooldown > 0) {
			cooldown--;
			for(int i = 0; i < 3; i++)
				Botania.proxy.wispFX(supertile.getWorldObj(), supertile.xCoord + 0.5 + Math.random() * 0.2 - 0.1, supertile.yCoord + 0.5 + Math.random() * 0.2 - 0.1, supertile.zCoord + 0.5 + Math.random() * 0.2 - 0.1, 0.1F, 0.1F, 0.1F, (float) Math.random() / 6, (float) -Math.random() / 30);
		}

		if(burnTime == 0) {
			if(mana < getMaxMana() && !supertile.getWorldObj().isRemote) {
				List<int[]> offsets = Arrays.asList(OFFSETS);
				Collections.shuffle(offsets);

				for(int[] offsetArray : offsets) {
					int[] positions = {
							supertile.xCoord + offsetArray[0],
							supertile.zCoord + offsetArray[1]
					};

					Block search = getMaterialToSearchFor();
					if(supertile.getWorldObj().getBlock(positions[0], supertile.yCoord, positions[1]) == search && (getBlockToSearchBelow() == null || supertile.getWorldObj().getBlock(positions[0], supertile.yCoord - 1, positions[1]) == getBlockToSearchBelow()) && supertile.getWorldObj().getBlockMetadata(positions[0], supertile.yCoord, positions[1]) == 0) {
						if(search != AMCore.proxy.blocks.liquidEssence)
							supertile.getWorldObj().setBlockToAir(positions[0], supertile.yCoord, positions[1]);
						else {
							int waterAround = 0;
							for(ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS)
								if(supertile.getWorldObj().getBlock(positions[0] + dir.offsetX, supertile.yCoord, positions[1] + dir.offsetZ) == search)
									waterAround++;

							if(waterAround < 2)
								supertile.getWorldObj().setBlockToAir(positions[0], supertile.yCoord, positions[1]);
						}

						if(cooldown == 0)
							burnTime += getBurnTime();
						else cooldown = getCooldown();

						sync();
						playSound();
						break;
					}
				}
			}
		} else {
			if(supertile.getWorldObj().rand.nextInt(8) == 0)
				doBurnParticles();
			burnTime--;
			if(burnTime == 0) {
				cooldown = getCooldown();
				sync();
			}
		}
	}
	
	public Block getBlockToSearchBelow() {
		return null;
	}
	
	@Override
	public LexiconEntry getEntry() {
		return LexiconModData.blueenchantress;
	}

	public void doBurnParticles() {
		Botania.proxy.wispFX(supertile.getWorldObj(), supertile.xCoord + 0.55 + Math.random() * 0.2 - 0.1, supertile.yCoord + 0.9 + Math.random() * 0.2 - 0.1, supertile.zCoord + 0.5, 0.7F, 0.05F, 0.05F, (float) Math.random() / 6, (float) -Math.random() / 60);
	}

	@Override
	public boolean isPassiveFlower() {
		return false;
	}

	public Block getMaterialToSearchFor() {
		return AMCore.proxy.blocks.liquidEssence;
	}

	public void playSound() {
		supertile.getWorldObj().playSoundEffect(supertile.xCoord, supertile.yCoord, supertile.zCoord, "botania:thermalily", 0.2F, 1F);
	}

	@Override
	public int getDelayBetweenPassiveGeneration() {
		return 1;
	}

	public int getBurnTime() {
		return 400;
	}

	@Override
	public int getValueForPassiveGeneration() {
		return ConfigHandler.efficiencyBlueenchantress;
	}

	@Override
	public int getMaxMana() {
		return 1000;
	}
	
	@Override
	public void writeToPacketNBT(NBTTagCompound cmp) {
		super.writeToPacketNBT(cmp);

		cmp.setInteger(TAG_BURN_TIME, burnTime);
		cmp.setInteger(TAG_COOLDOWN, cooldown);
	}

	@Override
	public void readFromPacketNBT(NBTTagCompound cmp) {
		super.readFromPacketNBT(cmp);

		burnTime = cmp.getInteger(TAG_BURN_TIME);
		cooldown = cmp.getInteger(TAG_COOLDOWN);
	}

	@Override
	public void populateDropStackNBTs(List<ItemStack> drops) {
		super.populateDropStackNBTs(drops);
		int cooldown = this.cooldown;
		if(burnTime > 0)
			cooldown = getCooldown();

		if(cooldown > 0)
			ItemNBTHelper.setInt(drops.get(0), TAG_COOLDOWN, getCooldown());
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		cooldown = ItemNBTHelper.getInt(stack, TAG_COOLDOWN, 0);
	}

	@Override
	public boolean canGeneratePassively() {
		return burnTime > 0;
	}

	public int getCooldown() {
		return 0;
	}
	
	@Override
	public int getColor() {
		return 0x0479CC;
	}
}
