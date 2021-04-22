package com.mysteria.utils;

import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

public final class MysteriaUtilsPlugin extends JavaPlugin {

	private static MysteriaUtilsPlugin instance;

	public MysteriaUtilsPlugin() {
		if (instance != null) throw new IllegalStateException();
		instance = this;
	}

	@Override
	public void onEnable() {


	}

	@Nonnull
	public static MysteriaUtilsPlugin getInstance() {
		if (instance == null) throw new IllegalStateException();
		return instance;
	}

}
