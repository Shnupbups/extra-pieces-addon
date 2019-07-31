package com.shnupbups.epaddon.blocks;

import com.shnupbups.epaddon.EPAddon;
import com.shnupbups.extrapieces.blocks.PieceBlock;
import com.shnupbups.extrapieces.core.PieceSet;
import com.shnupbups.extrapieces.core.PieceType;
import net.minecraft.block.Block;
import net.minecraft.block.InfestedBlock;
import net.minecraft.block.Material;

public class InfestedPieceBlock extends InfestedBlock implements PieceBlock {
	private final PieceSet set;

	public InfestedPieceBlock(PieceSet set) {
		super(set.getBase(),Settings.of(Material.CLAY).strength(0.0F, 0.75F));
		this.set=set;
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
		return EPAddon.INFESTED;
	}
}
