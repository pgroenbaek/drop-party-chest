package io.grnbk.droppartychestvalue;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DropPartyChestValuePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DropPartyChestValuePlugin.class);
		RuneLite.main(args);
	}
}