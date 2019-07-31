package com.shnupbups.epaddon.blocks;

import com.shnupbups.epaddon.EPAddon;
import com.shnupbups.extrapieces.blocks.PieceBlock;
import com.shnupbups.extrapieces.core.PieceSet;
import com.shnupbups.extrapieces.core.PieceType;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.ViewableWorld;

public class ButtonPieceBlock extends AbstractButtonBlock implements PieceBlock {
	private final PieceSet set;

	public ButtonPieceBlock(PieceSet set) {
		super(set.getBase().getDefaultState().getMaterial() == Material.WOOD, Settings.copy(set.getBase()));
		this.set = set;
	}

	public int getTickRate(ViewableWorld viewableWorld_1) {
		if (material.equals(Material.WOOD)) return 30;
		else if (material.equals(Material.METAL)) return 40;
		else if (material.equals(Material.WOOL)) return 10;
		else if (material.equals(Material.ORGANIC)) return 35;
		else if (material.equals(Material.EARTH)) return 25;
		else if (material.equals(Material.GLASS)) return 15;
		else if (material.equals(Material.PACKED_ICE)) return 45;
		else return 20;
	}

	@Override
	public Block getBlock() {
		return this;
	}

	@Override
	public PieceSet getSet() {
		return set;
	}

	@Override
	public PieceType getType() {
		return EPAddon.BUTTON;
	}

	protected SoundEvent getClickSound(boolean on) {
		if (material.equals(Material.WOOD))
			return on ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF;
		return on ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
	}
}
