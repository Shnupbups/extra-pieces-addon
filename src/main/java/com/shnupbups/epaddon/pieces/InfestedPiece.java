package com.shnupbups.epaddon.pieces;

import com.shnupbups.epaddon.blocks.InfestedPieceBlock;
import com.shnupbups.epaddon.blocks.InfestedPieceBlockItem;
import com.shnupbups.epaddon.blocks.PressurePlatePieceBlockItem;
import com.shnupbups.extrapieces.ExtraPieces;
import com.shnupbups.extrapieces.blocks.PieceBlock;
import com.shnupbups.extrapieces.blocks.PieceBlockItem;
import com.shnupbups.extrapieces.core.PieceSet;
import com.shnupbups.extrapieces.core.PieceType;
import com.shnupbups.extrapieces.recipe.StonecuttingPieceRecipe;
import com.shnupbups.extrapieces.recipe.WoodmillingPieceRecipe;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class InfestedPiece extends PieceType {
	public InfestedPiece() {
		super(new Identifier("epaddon","infested"));
	}

	@Override
	public PieceBlock getNew(PieceSet set) {
		return new InfestedPieceBlock(set);
	}

	@Override
	public Identifier getTagId() {
		return new Identifier("epaddon","infested_blocks");
	}

	@Override
	public StonecuttingPieceRecipe getStonecuttingRecipe() {
		return null;
	}

	@Override
	public WoodmillingPieceRecipe getWoodmillingRecipe() {
		return null;
	}

	@Override
	public void addLootTable(ArtificeResourcePack.ServerResourcePackBuilder data, PieceBlock pb) {
		data.addLootTable(ExtraPieces.prependToPath(Registry.BLOCK.getId(pb.getBlock()), "blocks/"), (loot) -> {
			loot.type(new Identifier("block"));
			loot.pool((pool) -> {
				pool.rolls(1);
				pool.entry((entry) -> {
					entry.type(new Identifier("item"));
					entry.name(Registry.BLOCK.getId(pb.getSet().getBase()));
				});
				pool.condition(new Identifier("match_tool"), (cond) -> {
					cond.addObject("predicate", pred -> {
						pred.addArray("enchantments", ench -> {
							ench.addObject(obj -> {
								obj.add("enchantment","minecraft:silk_touch");
								obj.addObject("levels", lvl -> {
									lvl.add("min",1);
								});
							});
						});
					});
				});
			});
		});
	}

	@Override
	public PieceBlockItem getBlockItem(PieceBlock pb) {
		return new InfestedPieceBlockItem(pb, new Item.Settings());
	}
}
