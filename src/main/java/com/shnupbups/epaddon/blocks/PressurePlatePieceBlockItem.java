package com.shnupbups.epaddon.blocks;

import com.shnupbups.extrapieces.blocks.PieceBlock;
import com.shnupbups.extrapieces.blocks.PieceBlockItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class PressurePlatePieceBlockItem extends PieceBlockItem {

	public PressurePlatePieceBlockItem(PieceBlock block_1, Settings item$Settings_1) {
		super(block_1, item$Settings_1);
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void appendTooltip(ItemStack itemStack_1, World world_1, List<Text> list_1, TooltipContext tooltipContext_1) {
		super.appendTooltip(itemStack_1, world_1, list_1, tooltipContext_1);
		if (tooltipContext_1.isAdvanced())
			list_1.add(new TranslatableText("tooltip.activationType", new TranslatableText("activationType." + ((PressurePlatePieceBlock) this.getPieceBlock()).getActivationType().asString())).setStyle(new Style().setColor(Formatting.YELLOW)));
	}
}
