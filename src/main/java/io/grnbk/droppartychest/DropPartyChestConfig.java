package io.grnbk.droppartychest;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("drop-party-chest")
public interface DropPartyChestConfig extends Config
{
	@ConfigItem(
		keyName = "showExact",
		name = "Show exact chest value",
		description = "Show exact chest value."
	)
	default boolean showExact()
	{
		return false;
	}
}
