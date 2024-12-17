package io.grnbk.chestvalue;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class PartyRoomChestValuePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PartyRoomChestValuePlugin.class);
		RuneLite.main(args);
	}
}