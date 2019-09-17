package com.shnupbups.epaddon.blocks;

import com.shnupbups.extrapieces.blocks.PieceBlock;
import com.shnupbups.extrapieces.blocks.PieceBlockItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Language;

public class InfestedPieceBlockItem extends PieceBlockItem {
	public InfestedPieceBlockItem(PieceBlock block_1, Settings item$Settings_1) {
		super(block_1, item$Settings_1);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public Text getName(ItemStack stack) {
		return Language.getInstance().hasTranslation(this.getTranslationKey(stack)) ? super.getName(stack) : new TranslatableText(this.getPieceBlock().getType().getTranslationKey(), new TranslatableText(this.getPieceBlock().getSet().getBase().getTranslationKey()));
	}
}
