package io.grnbk.droppartychestvalue;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("drop-party-chest-value")
public interface DropPartyChestValueConfig extends Config
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
