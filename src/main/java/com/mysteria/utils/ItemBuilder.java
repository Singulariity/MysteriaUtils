package com.mysteria.utils;

import com.mysteria.customapi.itemmanager.containers.ItemTagContainer;
import com.mysteria.customapi.itemtags.ItemTag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class ItemBuilder {

	private final ItemStack itemStack;
	private ItemMeta itemMeta;

	private ItemBuilder(@Nonnull Material material) {
		this(new ItemStack(material));
	}

	private ItemBuilder(@Nonnull ItemStack itemStack) {
		this.itemStack = itemStack.clone();
		this.itemMeta = itemStack.getItemMeta();
	}

	public static ItemBuilder builder(@Nonnull Material material) {
		return new ItemBuilder(material);
	}

	public static ItemBuilder builder(@Nonnull ItemStack itemStack) {
		return new ItemBuilder(itemStack);
	}





	public ItemBuilder name(@Nullable Component name) {
		itemMeta.displayName(name);
		return this;
	}

	public ItemBuilder lore(@Nullable Component lore) {
		if (lore == null) {
			itemMeta.lore(null);
		} else {
			itemMeta.lore(new ArrayList<>(Collections.singletonList(lore)));
		}
		return this;
	}

	public ItemBuilder lore(@Nullable List<Component> lore) {
		if (lore == null) {
			itemMeta.lore(null);
		} else {
			itemMeta.lore(new ArrayList<>(lore));
		}
		return this;
	}

	public ItemBuilder lore(@Nonnull Component... lore) {
		itemMeta.lore(new ArrayList<>(Arrays.asList(lore)));
		return this;
	}

	public ItemBuilder amount(int i) {
		itemStack.setAmount(Math.max(i, 1));
		return this;
	}

	public ItemBuilder glow() {
		itemMeta.addEnchant(Enchantment.LURE, 5, true);
		itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		return this;
	}

	public ItemBuilder addTags(@Nonnull ItemTag... tags) {
		ItemTagContainer itemTagContainer = ItemTagContainer.get(itemStack);
		itemTagContainer.addTags(tags);
		itemMeta = itemTagContainer.getUpdatedItemMeta();
		return this;
	}

	public ItemBuilder adaptGUI() {
		itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
		Component name = itemMeta.displayName();
		if (name != null) {
			itemMeta.displayName(name.decoration(TextDecoration.ITALIC, false));
		}
		List<Component> lore = itemMeta.lore();
		if (lore != null) {
			List<Component> finalLore = new ArrayList<>();
			for (Component line : lore) {
				finalLore.add(line.decoration(TextDecoration.ITALIC, false));
			}
			itemMeta.lore(finalLore);
		}
		return this;
	}



	public ItemStack build() {
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

}
