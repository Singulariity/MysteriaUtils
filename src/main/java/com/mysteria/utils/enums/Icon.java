package com.mysteria.utils.enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("unused")
public enum Icon {

	SPEED('\uE000'),
	SLOW('\uE001'),
	FAST_DIGGING('\uE002'),
	SLOW_DIGGING('\uE003'),
	INCREASE_DAMAGE('\uE004'),
	HEAL('\uE005'),
	HARM('\uE006'),
	JUMP('\uE007'),
	CONFUSION('\uE008'),
	REGENERATION('\uE009'),
	DAMAGE_RESISTANCE('\uE00A'),
	FIRE_RESISTANCE('\uE00B'),
	WATER_BREATHING('\uE00C'),
	INVISIBILITY('\uE00D'),
	BLINDNESS('\uE00E'),
	NIGHT_VISION('\uE00F'),
	HUNGER('\uE010'),
	WEAKNESS('\uE011'),
	POISON('\uE012'),
	WITHER('\uE013'),
	HEALTH_BOOST('\uE014'),
	ABSORPTION('\uE015'),
	SATURATION('\uE016'),
	GLOWING('\uE017'),
	LEVITATION('\uE018'),
	LUCK('\uE019'),
	UNLUCK('\uE01A'),
	SLOW_FALLING('\uE01B'),
	CONDUIT_POWER('\uE01C'),
	DOLPHINS_GRACE('\uE01D'),
	BAD_OMEN('\uE01E'),
	HERO_OF_THE_VILLAGE('\uE01F'),
	DOOM('\uE020'),
	FUSS('\uE021'),
	BUNNY('\uE022'),
	INSANITY('\uE023'),
	LUCIDNESS('\uE024'),
	BLEED('\uE025'),
	BROKEN_LEG('\uE026'),
	FEATHERFALL('\uE027'),
	CAMOUFLAGE('\uE028'),
	ARCHERY('\uE029'),
	SICKNESS('\uE02A'),
	CREATIVE_SHOCK('\uE02B'),
	CURSE('\uE02C'),
	PURGE('\uE02D'),
	REWIND('\uE02E'),
	FROSTBURN('\uE02F'),
	CALAMITY('\uE030'),
	SILENCE('\uE031'),
	;

	private final char character;

	Icon(char character) {
		this.character = character;
	}

	@Override
	public String toString() {
		return String.valueOf(character);
	}

	@Nullable
	public static Icon getIcon(@Nonnull String name) {
		try {
			return Icon.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException ignored) {
			return null;
		}
	}

}
