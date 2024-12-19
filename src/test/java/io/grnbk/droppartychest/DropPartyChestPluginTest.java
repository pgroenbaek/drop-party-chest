package io.grnbk.droppartychest;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DropPartyChestPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DropPartyChestPlugin.class);
		RuneLite.main(args);
	}
}