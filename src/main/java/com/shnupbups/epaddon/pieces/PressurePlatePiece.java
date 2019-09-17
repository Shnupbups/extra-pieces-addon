package com.shnupbups.epaddon.pieces;

import com.shnupbups.epaddon.blocks.PressurePlatePieceBlock;
import com.shnupbups.epaddon.blocks.PressurePlatePieceBlockItem;
import com.shnupbups.extrapieces.ExtraPieces;
import com.shnupbups.extrapieces.blocks.PieceBlock;
import com.shnupbups.extrapieces.blocks.PieceBlockItem;
import com.shnupbups.extrapieces.core.PieceSet;
import com.shnupbups.extrapieces.core.PieceType;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PressurePlatePiece extends PieceType {
	public PressurePlatePiece() {
		super(new Identifier("epaddon", "pressure_plate"));
	}

	@Override
	public PieceBlock getNew(PieceSet set) {
		return new PressurePlatePieceBlock(set);
	}

	@Override
	public void addBlockModels(ArtificeResourcePack.ClientResourcePackBuilder pack, PieceBlock pb) {
		super.addBlockModels(pack, pb);
		addBlockModel(pack, pb, "down");
	}

	@Override
	public void addBlockstate(ArtificeResourcePack.ClientResourcePackBuilder pack, PieceBlock pb) {
		pack.addBlockState(Registry.BLOCK.getId(pb.getBlock()), (state) -> {
			state.variant("powered=false", var -> {
				var.model(ExtraPieces.prependToPath(Registry.BLOCK.getId(pb.getBlock()), "block/"));
			});
			state.variant("powered=true", var -> {
				var.model(ExtraPieces.prependToPath(ExtraPieces.appendToPath(Registry.BLOCK.getId(pb.getBlock()), "_down"), "block/"));
			});
		});
	}

	@Override
	public PieceBlockItem getBlockItem(PieceBlock pb) {
		return new PressurePlatePieceBlockItem(pb, new Item.Settings());
	}
	
	@Override
	public int getStonecuttingCount() {
		return 8;
	}
}
