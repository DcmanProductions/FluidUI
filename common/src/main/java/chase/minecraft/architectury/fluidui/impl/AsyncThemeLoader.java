package chase.minecraft.architectury.fluidui.impl;

import chase.minecraft.architectury.fluidui.FluidTheme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AsyncThemeLoader
{
	private static AsyncThemeLoader instance = new AsyncThemeLoader();
	private final List<FluidTheme> themes;
	
	private AsyncThemeLoader()
	{
		instance = this;
		themes = new ArrayList<>();
	}
	
	public void put(FluidTheme theme)
	{
		themes.add(theme);
	}
	public void clear(){
		themes.clear();
	}
	
	
	public void loadAll()
	{
		for (FluidTheme theme : themes)
		{
			theme.load();
		}
		clear();
	}
	
	
	public static AsyncThemeLoader getInstance()
	{
		return instance;
	}
	
}
