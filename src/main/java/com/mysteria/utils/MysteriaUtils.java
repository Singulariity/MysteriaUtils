package com.mysteria.utils;

import com.mysteria.utils.enums.DefaultFontInfo;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Random;

@SuppressWarnings("unused")
public class MysteriaUtils {

	private static final @Nonnull Component CHAT_PREFIX = Component.text()
			.append(Component.text("[", NamedColor.WIZARD_GREY))
			.append(Component.text("MU", NamedColor.PROTOSS_PYLON))
			.append(Component.text("]", NamedColor.WIZARD_GREY))
			.append(Component.space())
			.build();

	public static void sendMessage(@Nonnull Identity identity, @Nonnull Audience receiver, @Nonnull Component message) {
		receiver.sendMessage(identity, CHAT_PREFIX.append(message));
	}

	public static void sendMessage(@Nonnull Audience receiver, @Nonnull Component message) {
		receiver.sendMessage(CHAT_PREFIX.append(message));
	}

	public static void sendMessageGreen(@Nonnull Audience receiver, @Nonnull String message) {
		receiver.sendMessage(CHAT_PREFIX.append(Component.text(message, NamedColor.SKIRRET_GREEN)));
	}

	public static void sendMessageGreen(@Nonnull Audience receiver, @Nonnull Component message) {
		receiver.sendMessage(CHAT_PREFIX.append(message.colorIfAbsent(NamedColor.SKIRRET_GREEN)));
	}

	public static void sendMessageRed(@Nonnull Audience receiver, @Nonnull String message) {
		receiver.sendMessage(CHAT_PREFIX.append(Component.text(message, NamedColor.CARMINE_PINK)));
	}

	public static void sendMessageRed(@Nonnull Audience receiver, @Nonnull Component message) {
		receiver.sendMessage(CHAT_PREFIX.append(message.colorIfAbsent(NamedColor.CARMINE_PINK)));
	}

	public static void sendMessageDarkRed(@Nonnull Audience p, @Nonnull String message) {
		p.sendMessage(CHAT_PREFIX.append(Component.text(message, NamedColor.HARLEY_DAVIDSON_ORANGE)));
	}

	public static void sendMessageDarkRed(@Nonnull Audience p, @Nonnull Component message) {
		p.sendMessage(CHAT_PREFIX.append(message.colorIfAbsent(NamedColor.HARLEY_DAVIDSON_ORANGE)));
	}

	public static void sendMessageYellow(@Nonnull Audience p, @Nonnull String message) {
		p.sendMessage(CHAT_PREFIX.append(Component.text(message, NamedColor.TURBO)));
	}

	public static void sendMessageYellow(@Nonnull Audience p, @Nonnull Component message) {
		p.sendMessage(CHAT_PREFIX.append(message.colorIfAbsent(NamedColor.TURBO)));
	}

	public static void sendMessageGray(@Nonnull Audience p, @Nonnull String message) {
		p.sendMessage(CHAT_PREFIX.append(Component.text(message, NamedColor.SOARING_EAGLE)));
	}

	public static void sendMessageGray(@Nonnull Audience p, @Nonnull Component message) {
		p.sendMessage(CHAT_PREFIX.append(message.colorIfAbsent(NamedColor.SOARING_EAGLE)));
	}

	@Nonnull
	public static String translateToString(@Nonnull Component component) {
		return PlainComponentSerializer.plain().serialize(component);
	}

	@Nonnull
	public static Component translateToComponent(@Nonnull String string) {
		return LegacyComponentSerializer.legacy('&').deserialize(string);
	}

	public static void broadcastMessage(@Nonnull Component s) {
		Bukkit.broadcast(CHAT_PREFIX.append(s), "");
		Bukkit.getConsoleSender().sendMessage(CHAT_PREFIX.append(s));
	}

	@Nonnull
	public static String legacyColoredText(@Nonnull String string){
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	@Nonnull
	public static Component showItemComponent(@Nonnull ItemStack itemStack) {
		return Component.text("");

		// TODO
		/*
		ItemStack item = itemStack.clone();
		CustomItemsPlugin.getInstance().getItemManager().setItemPacketMeta(item);
		Component displayName;
		if (item.getItemMeta().displayName() == null && item.getI18NDisplayName() != null) {
			displayName = nonItalic(Component.text(item.getI18NDisplayName()));
		} else {
			displayName = item.getItemMeta().displayName();
			if (displayName == null) {
				displayName = nonItalic(Component.text("Item"));
			}
		}

		return Component.text("[")
				.append(displayName)
				.append(Component.text("]"))
				.hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_ITEM, item.asHoverEvent().value()))
				.color(ItemInfo.get(item).getRarity().getColor());
		 */

	}

	@Nonnull
	public static org.bukkit.Color toBukkitColor(@Nonnull TextColor textColor) {
		return org.bukkit.Color.fromRGB(textColor.red(), textColor.green(), textColor.blue());
	}

	public static Component centeredComponent(@Nonnull Component message) {

		String plainText = PlainComponentSerializer.plain().serialize(message);

		int messagePxSize = 0;
		boolean isBold = message.hasDecoration(TextDecoration.BOLD);

		for (char c : plainText.toCharArray()) {
			DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
			messagePxSize = isBold ? messagePxSize + dFI.getBoldLength() : messagePxSize + dFI.getLength();
			messagePxSize++;
		}
		int toCompensate = 154 - messagePxSize / 2;
		int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
		int compensated = 0;
		StringBuilder sb = new StringBuilder();
		while (compensated < toCompensate) {
			sb.append(" ");
			compensated += spaceLength;
		}

		return Component.text()
				.append(Component.text(sb.toString()))
				.append(message)
				.build();
	}

	public static boolean chance(int percentage) {
		return getRandom(0, 100) < percentage;
	}

	public static boolean chance(double percentage) {
		return getRandom(0D, 100D) < percentage;
	}

	/**
	 * Gets a random double between two values.
	 *
	 * @param min The minimum.
	 * @param max The maximum.
	 * @return The found value.
	 */
	public static double getRandom(double min, double max) {
		return (min + (max - min) * new Random().nextDouble());
	}

	/**
	 * Gets a random int between two values.
	 *
	 * @param min The minimum.
	 * @param max The maximum.
	 * @return The found value.
	 */
	public static int getRandom(int min, int max) {
		return new Random().nextInt((max - min) + 1) + min;
	}

	@Nonnull
	public static Component getChatPREFIX() {
		return CHAT_PREFIX;
	}

	private MysteriaUtils(){
		throw new UnsupportedOperationException();
	}

}
