package com.shnupbups.epaddon.pieces;

import com.shnupbups.epaddon.blocks.ButtonPieceBlock;
import com.shnupbups.epaddon.blocks.ButtonPieceBlockItem;
import com.shnupbups.extrapieces.ExtraPieces;
import com.shnupbups.extrapieces.blocks.PieceBlock;
import com.shnupbups.extrapieces.blocks.PieceBlockItem;
import com.shnupbups.extrapieces.core.PieceSet;
import com.shnupbups.extrapieces.core.PieceType;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

public class ButtonPiece extends PieceType {
	public ButtonPiece() {
		super(new Identifier("epaddon", "button"));
	}

	@Override
	public PieceBlock getNew(PieceSet set) {
		return new ButtonPieceBlock(set);
	}

	@Override
	public Identifier getTagId() {
		return new Identifier("buttons");
	}

	@Override
	public void addBlockModels(ArtificeResourcePack.ClientResourcePackBuilder pack, PieceBlock pb) {
		super.addBlockModels(pack, pb);
		addBlockModel(pack, pb, "pressed");
		addBlockModel(pack, pb, "inventory");
	}

	@Override
	public void addItemModel(ArtificeResourcePack.ClientResourcePackBuilder pack, PieceBlock pb) {
		pack.addItemModel(Registry.BLOCK.getId(pb.getBlock()), model -> {
			model.parent(ExtraPieces.prependToPath(ExtraPieces.appendToPath(Registry.BLOCK.getId(pb.getBlock()), "_inventory"), "block/"));
		});
	}

	@Override
	public void addBlockstate(ArtificeResourcePack.ClientResourcePackBuilder pack, PieceBlock pb) {
		pack.addBlockState(Registry.BLOCK.getId(pb.getBlock()), (state) -> {
			for (Direction facing : Direction.values()) {
				if (facing != Direction.UP && facing != Direction.DOWN) {
					for (WallMountLocation face : WallMountLocation.values()) {
						state.variant("face=" + face.asString() + ",facing=" + facing.asString() + ",powered=false", var -> {
							var.model(ExtraPieces.prependToPath(Registry.BLOCK.getId(pb.getBlock()), "block/"));
							var.uvlock(true);
							if (face.equals(WallMountLocation.CEILING)) {
								switch (facing) {
									case EAST:
										var.rotationY(270);
										break;
									case WEST:
										var.rotationY(90);
										break;
									case SOUTH:
										var.rotationY(0);
										break;
									case NORTH:
										var.rotationY(180);
										break;
								}
								var.rotationX(180);
							} else {
								switch (facing) {
									case EAST:
										var.rotationY(90);
										break;
									case WEST:
										var.rotationY(270);
										break;
									case SOUTH:
										var.rotationY(180);
										break;
									case NORTH:
										var.rotationY(0);
										break;
								}
								if (face.equals(WallMountLocation.WALL)) var.rotationX(90);
							}
						});
						state.variant("face=" + face.asString() + ",facing=" + facing.asString() + ",powered=true", var -> {
							var.model(ExtraPieces.prependToPath(ExtraPieces.appendToPath(Registry.BLOCK.getId(pb.getBlock()), "_pressed"), "block/"));
							var.uvlock(true);
							if (face.equals(WallMountLocation.CEILING)) {
								switch (facing) {
									case EAST:
										var.rotationY(270);
										break;
									case WEST:
										var.rotationY(90);
										break;
									case SOUTH:
										var.rotationY(0);
										break;
									case NORTH:
										var.rotationY(180);
										break;
								}
								var.rotationX(180);
							} else {
								switch (facing) {
									case EAST:
										var.rotationY(90);
										break;
									case WEST:
										var.rotationY(270);
										break;
									case SOUTH:
										var.rotationY(180);
										break;
									case NORTH:
										var.rotationY(0);
										break;
								}
								if (face.equals(WallMountLocation.WALL)) var.rotationX(90);
							}
						});
					}
				}
			}
		});
	}

	@Override
	public PieceBlockItem getBlockItem(PieceBlock pb) {
		return new ButtonPieceBlockItem(pb, new Item.Settings());
	}
}
