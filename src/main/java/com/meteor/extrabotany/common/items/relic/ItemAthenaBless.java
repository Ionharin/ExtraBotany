package com.meteor.extrabotany.common.items.relic;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.api.IShieldHandler;
import com.meteor.extrabotany.common.events.EventKnowledgeTypeUnlock;
import com.meteor.extrabotany.common.events.EventShield;
import com.meteor.extrabotany.common.handler.ConfigHandler;
import com.meteor.extrabotany.common.handler.ShieldHandler;
import com.meteor.extrabotany.common.items.ModItems;
import com.meteor.extrabotany.common.lib.LibItemName;
import com.meteor.extrabotany.common.lib.LibReference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.item.IBaubleRender.RenderType;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.item.relic.ItemRelic;
import vazkii.botania.common.item.relic.ItemRelicBauble;

public class ItemAthenaBless extends ItemRelicBauble implements IShieldHandler{
	public static List<String> damageNegations = new ArrayList();

	public ItemAthenaBless(){
		super(LibItemName.ATHENABLESS);
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
		damageNegations.add(ItemRelic.damageSource().getDamageType());
	}
	
	@SubscribeEvent
	public void onPlayerAttacked(LivingAttackEvent event) {
		if(event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			if(getAthenaBless(player) != null)
				if(ItemRelic.isRightPlayer(player, getAthenaBless(player)) && damageNegations.contains(event.source.damageType))
				event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onEntityAttacked(LivingAttackEvent event) {
		if(event.source.getSourceOfDamage() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
			if(getAthenaBless(player) != null)
				if(ItemRelic.isRightPlayer(player, getAthenaBless(player)))
					addShieldAmount(event.ammount/5, player);
				}
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.RING;
	}

	public static ItemStack getAthenaBless(EntityPlayer player) {
		InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
		ItemStack stack1 = baubles.getStackInSlot(1);
		ItemStack stack2 = baubles.getStackInSlot(2);
		return isAthenaBless(stack1) ? stack1 : isAthenaBless(stack2) ? stack2 : null;
	}

	private static boolean isAthenaBless(ItemStack stack) {
		return stack != null && (stack.getItem() == ModItems.athenabless);
	}

	@Override
	public float setShieldAmount(float shield, EntityPlayer player) {
		if(shield <= getMaxShieldAmount(player))
			ShieldHandler.currentShield = shield;
		else if(shield > getMaxShieldAmount(player))
			ShieldHandler.currentShield = getMaxShieldAmount(player);
		return shield;
	}

	@Override
	public float getShieldAmount(EntityPlayer player) {
		return ShieldHandler.currentShield;
	}
	
	@Override
	public float addShieldAmount(float shield, EntityPlayer player) {
		if(getShieldAmount(player) + shield <= getMaxShieldAmount(player))
			ShieldHandler.currentShield = getShieldAmount(player) + shield;
		else if(getShieldAmount(player) + shield > getMaxShieldAmount(player))
			ShieldHandler.currentShield = getMaxShieldAmount(player);
		return shield;
	}

	@Override
	public float getMaxShieldAmount(EntityPlayer player) {
		return player.getMaxHealth() + ConfigHandler.extraShieldAmount;
	}
}
