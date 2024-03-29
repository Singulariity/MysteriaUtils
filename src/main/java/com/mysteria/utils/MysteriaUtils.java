package com.mysteria.utils;

import com.mysteria.customapi.CustomAPIPlugin;
import com.mysteria.customapi.itemmanager.ItemInfo;
import com.mysteria.utils.enums.DefaultFontInfo;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class MysteriaUtils {

	private static final @Nonnull
	Component CHAT_PREFIX = Component.text()
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
		return PlainTextComponentSerializer.plainText().serialize(component);
	}

	@Nonnull
	public static Component translateToComponent(@Nonnull String string) {
		return LegacyComponentSerializer.legacy('&').deserialize(string);
	}

	public static void broadcastMessage(@Nonnull Component s) {
		Bukkit.broadcast(s, Server.BROADCAST_CHANNEL_USERS);
		Bukkit.getConsoleSender().sendMessage(CHAT_PREFIX.append(s));
	}

	@Nonnull
	public static String legacyColoredText(@Nonnull String string){
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	@Nonnull
	public static Component showItemComponent(@Nonnull ItemStack itemStack) {
		ItemStack item = itemStack.clone();

		CustomAPIPlugin.getItemManager().setItemPacketMeta(item);
		Component displayName;
		if (item.getItemMeta().displayName() == null && item.getI18NDisplayName() != null) {
			displayName = Component.text(item.getI18NDisplayName());
		} else {
			displayName = item.getItemMeta().displayName();
			if (displayName == null) {
				displayName = Component.text("Item");
			}
		}

		return Component.text("[")
				.append(displayName.decoration(TextDecoration.ITALIC, false))
				.append(Component.text("]"))
				.hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_ITEM, item.asHoverEvent().value()))
				.color(ItemInfo.get(item).getRarity().getColor());

	}

	@Nonnull
	public static org.bukkit.Color toBukkitColor(@Nonnull TextColor textColor) {
		return org.bukkit.Color.fromRGB(textColor.red(), textColor.green(), textColor.blue());
	}

	/**
	 *
	 * @param seconds cooldown timer
	 * @return cooldown finish long
	 */
	public static long createCooldown(int seconds) {
		return System.currentTimeMillis() + (1000L * seconds);
	}

	/**
	 *
	 * @param cooldown cooldown finish long
	 * @return true if cooldown finished
	 */
	public static boolean checkCooldown(long cooldown) {
		return System.currentTimeMillis() >= cooldown;
	}

	/**
	 *
	 * @param cooldown cooldown finish long
	 * @return the string
	 */
	@Nonnull
	public static String cooldownString(long cooldown) {
		long now = System.currentTimeMillis();

		if (now < cooldown) {
			String finalString = "";
			long seconds = TimeUnit.MILLISECONDS.toSeconds(cooldown - now);
			int day = (int) TimeUnit.SECONDS.toDays(seconds);
			long hour = TimeUnit.SECONDS.toHours(seconds) - (day * 24L);
			long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
			long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
			if (day > 0) finalString += day + " day(s), ";
			if (hour > 0) finalString += hour + " hour(s), ";
			if (minute > 0) finalString += minute + " minute(s) and ";
			finalString += second + " second(s)";
			return finalString;
		}
		return "???";
	}

	public static Component centeredComponent(@Nonnull Component message) {

		String plainText = PlainTextComponentSerializer.plainText().serialize(message);

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

	public static void giveItem(@Nonnull Player player, ItemStack... itemStacks) {
		giveItem(player, true, itemStacks);
	}

	public static void giveItem(@Nonnull Player player, boolean message, ItemStack... itemStacks) {
		HashMap<Integer, ItemStack> left = player.getInventory().addItem(itemStacks);
		if (!left.isEmpty()) {
			for (ItemStack itemStack : left.values()) {
				Item item = player.getWorld().dropItem(player.getLocation(), itemStack);
				item.setVelocity(new Vector());
				item.setOwner(player.getUniqueId());
				item.setInvulnerable(true);
				item.setCanMobPickup(false);
				if (message) {
					Component itemComponent = showItemComponent(itemStack);
					sendMessage(player, Component.translatable("mystery.inventory.item_dropped",
							NamedColor.CARMINE_PINK, itemComponent));
				}
			}
		}
	}

	@Nonnull
	public static Component getChatPREFIX() {
		return CHAT_PREFIX;
	}

	private MysteriaUtils(){
		throw new UnsupportedOperationException();
	}

}
