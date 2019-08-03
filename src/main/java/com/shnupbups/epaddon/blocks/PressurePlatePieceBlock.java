package com.shnupbups.epaddon.blocks;

import com.shnupbups.epaddon.EPAddon;
import com.shnupbups.extrapieces.blocks.PieceBlock;
import com.shnupbups.extrapieces.core.PieceSet;
import com.shnupbups.extrapieces.core.PieceType;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class PressurePlatePieceBlock extends PressurePlateBlock implements PieceBlock {
	private final PieceSet set;

	public PressurePlatePieceBlock(PieceSet set) {
		super(
				set.getBase().getDefaultState().getMaterial() == Material.STONE ?
						ActivationRule.MOBS : ActivationRule.EVERYTHING,
				Settings.copy(set.getBase())
		);
		this.set = set;
	}

	@Override
	public Block getBlock() {
		return this;
	}

	@Override
	public PieceType getType() {
		return EPAddon.PRESSURE_PLATE;
	}

	@Override
	public PieceSet getSet() {
		return set;
	}

	@Override
	protected void playPressSound(IWorld iWorld_1, BlockPos blockPos_1) {
		if (this.material == Material.WOOD) {
			iWorld_1.playSound(null, blockPos_1, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.8F);
		} else if (this.material == Material.METAL) {
			iWorld_1.playSound(null, blockPos_1, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.90000004F);
		} else if (this.material != Material.WOOL) {
			iWorld_1.playSound(null, blockPos_1, SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
		}

	}

	@Override
	protected void playDepressSound(IWorld iWorld_1, BlockPos blockPos_1) {
		if (this.material == Material.WOOD) {
			iWorld_1.playSound(null, blockPos_1, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.7F);
		} else if (this.material == Material.METAL) {
			iWorld_1.playSound(null, blockPos_1, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.75F);
		} else if (this.material != Material.WOOL) {
			iWorld_1.playSound(null, blockPos_1, SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
		}
	}

	@Override
	protected int getRedstoneOutput(World world_1, BlockPos blockPos_1) {
		Box box_1 = BOX.offset(blockPos_1);
		List list_3;
		List l;
		int int_1;
		switch (getActivationType()) {
			case ALL_ENTITIES:
				list_3 = world_1.getEntities((Entity) null, box_1);
				break;
			case ONLY_LIVING:
				list_3 = world_1.getEntities(LivingEntity.class, box_1);
				break;
			case ONLY_ITEMS:
				list_3 = world_1.getEntities(ItemEntity.class, box_1);
				break;
			case ONLY_PLAYERS:
				list_3 = world_1.getEntities(PlayerEntity.class, box_1);
				break;
			case ONLY_PASSIVE:
				list_3 = world_1.getEntities(PassiveEntity.class, box_1);
				break;
			case ONLY_HOSTILE:
				list_3 = world_1.getEntities(HostileEntity.class, box_1);
				break;
			case ONLY_ANIMALS:
				list_3 = world_1.getEntities(AnimalEntity.class, box_1);
				break;
			case NOT_PLAYERS:
				l = world_1.getEntities((Entity) null, box_1);
				list_3 = l;
				for (Object o : l) {
					if (o instanceof PlayerEntity) list_3.remove(o);
				}
				break;
			case ONLY_ADULTS:
				l = world_1.getEntities((Entity) null, box_1);
				list_3 = l;
				for (Object o : l) {
					if (o instanceof PassiveEntity && ((PassiveEntity) o).isBaby()) list_3.remove(o);
					else if (o instanceof ZombieEntity && ((ZombieEntity) o).isBaby()) list_3.remove(o);
				}
				break;
			case HEAVY_WEIGHTED:
				int_1 = Math.min(world_1.getEntities(Entity.class, BOX.offset(blockPos_1)).size(), 150);
				if (int_1 > 0) {
					float float_1 = (float) Math.min(150, int_1) / (float) 150;
					return MathHelper.ceil(float_1 * 15.0F);
				} else {
					return 0;
				}
			case LIGHT_WEIGHTED:
				int_1 = Math.min(world_1.getEntities(Entity.class, BOX.offset(blockPos_1)).size(), 15);
				if (int_1 > 0) {
					float float_1 = (float) Math.min(15, int_1) / (float) 15;
					return MathHelper.ceil(float_1 * 15.0F);
				} else {
					return 0;
				}
			case MEDIUM_WEIGHTED:
				int_1 = Math.min(world_1.getEntities(Entity.class, BOX.offset(blockPos_1)).size(), 82);
				if (int_1 > 0) {
					float float_1 = (float) Math.min(82, int_1) / (float) 82;
					return MathHelper.ceil(float_1 * 15.0F);
				} else {
					return 0;
				}
			case VERY_LIGHT_WEIGHTED:
				int_1 = Math.min(world_1.getEntities(Entity.class, BOX.offset(blockPos_1)).size(), 7);
				if (int_1 > 0) {
					float float_1 = (float) Math.min(7, int_1) / (float) 7;
					return MathHelper.ceil(float_1 * 15.0F);
				} else {
					return 0;
				}
			case VERY_HEAVY_WEIGHTED:
				int_1 = Math.min(world_1.getEntities(Entity.class, BOX.offset(blockPos_1)).size(), 300);
				if (int_1 > 0) {
					float float_1 = (float) Math.min(300, int_1) / (float) 300;
					return MathHelper.ceil(float_1 * 15.0F);
				} else {
					return 0;
				}
			default:
				return 0;
		}

		if (!list_3.isEmpty()) {
			Iterator var5 = list_3.iterator();

			while (var5.hasNext()) {
				Entity entity_1 = (Entity) var5.next();
				if (!entity_1.canAvoidTraps()) {
					return 15;
				}
			}
		}
		return 0;
	}

	public ActivationType getActivationType() {
		if (this.material.equals(Material.STONE)) {
			if (this.hardness >= 30) return ActivationType.ONLY_PLAYERS;
			else if (this.hardness < 1.5) return ActivationType.ONLY_HOSTILE;
			else return ActivationType.ONLY_LIVING;
		} else if (this.material.equals(Material.WOOL)) return ActivationType.ONLY_ITEMS;
		else if (this.material.equals(Material.GLASS)) return ActivationType.ONLY_PASSIVE;
		else if (this.material.equals(Material.METAL)) {
			if (this.hardness >= 6) return ActivationType.VERY_HEAVY_WEIGHTED;
			else if (this.hardness >= 5) return ActivationType.HEAVY_WEIGHTED;
			else if (this.hardness >= 4) return ActivationType.MEDIUM_WEIGHTED;
			else if (this.hardness >= 3) return ActivationType.LIGHT_WEIGHTED;
			return ActivationType.VERY_LIGHT_WEIGHTED;
		} else if (this.material.equals(Material.EARTH)) return ActivationType.ONLY_ANIMALS;
		else if (this.material.equals(Material.PACKED_ICE) || this.material.equals(Material.CLAY)) return ActivationType.NOT_PLAYERS;
		else if (this.material.equals(Material.ORGANIC)) return ActivationType.ONLY_ADULTS;
		else return ActivationType.ALL_ENTITIES;
	}

	public enum ActivationType {
		ONLY_HOSTILE,
		ONLY_PLAYERS,
		ONLY_PASSIVE,
		ONLY_LIVING,
		ONLY_ITEMS,
		ONLY_ANIMALS,
		ONLY_ADULTS,
		HEAVY_WEIGHTED,
		MEDIUM_WEIGHTED,
		LIGHT_WEIGHTED,
		VERY_HEAVY_WEIGHTED,
		VERY_LIGHT_WEIGHTED,
		ALL_ENTITIES,
		NOT_PLAYERS;

		public String asString() {
			switch (this) {
				case HEAVY_WEIGHTED:
					return "heavy_weighted";
				case ALL_ENTITIES:
					return "all_entities";
				case ONLY_ITEMS:
					return "only_items";
				case ONLY_ADULTS:
					return "only_adults";
				case NOT_PLAYERS:
					return "not_players";
				case LIGHT_WEIGHTED:
					return "light_weighted";
				case ONLY_LIVING:
					return "only_living";
				case ONLY_ANIMALS:
					return "only_animals";
				case ONLY_HOSTILE:
					return "only_hostile";
				case ONLY_PASSIVE:
					return "only_passive";
				case ONLY_PLAYERS:
					return "only_players";
				case MEDIUM_WEIGHTED:
					return "medium_weighted";
				case VERY_HEAVY_WEIGHTED:
					return "very_heavy_weighted";
				case VERY_LIGHT_WEIGHTED:
					return "very_light_weighted";
			}
			return "";
		}
	}
}
