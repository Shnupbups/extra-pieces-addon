package com.shnupbups.epaddon;

import com.shnupbups.epaddon.pieces.ButtonPiece;
import com.shnupbups.epaddon.pieces.InfestedPiece;
import com.shnupbups.epaddon.pieces.PressurePlatePiece;
import com.shnupbups.extrapieces.api.EPInitializer;
import com.shnupbups.extrapieces.core.PieceType;
import com.shnupbups.extrapieces.core.PieceTypes;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;

public class EPAddon implements EPInitializer {
	public static final PieceType BUTTON = new ButtonPiece();
	public static final PieceType PRESSURE_PLATE = new PressurePlatePiece();
	public static final PieceType INFESTED = new InfestedPiece();

	public void addData(ArtificeResourcePack.ServerResourcePackBuilder data) {

	}

	public void onInitialize() {
		PieceTypes.register(BUTTON);
		PieceTypes.register(PRESSURE_PLATE);
		PieceTypes.register(INFESTED);
	}
}
