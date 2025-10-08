package io.grnbk.droppartychest;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Notification;

@ConfigGroup("drop-party-chest")
public interface DropPartyChestConfig extends Config
{
	@ConfigItem(
		keyName = "showExact",
		name = "Show exact chest value",
		description = "Show exact chest value.",
        position = 0
	)
	default boolean showExact()
	{
		return false;
	}

    @ConfigItem(
            keyName = "chestValueNotification",
            name = "Chest value notification",
            description = "Configures if chest value notifications are enabled.",
            position = 1
    )
    default Notification getChestValueNotification()
    {
        return Notification.OFF;
    }

    @ConfigItem(
            keyName = "chestValueThreshold",
            name = "Chest value threshold",
            description = "The total value of the chest must exceed this amount to send a notification.",
            position = 2
    )
    default int getChestValueThreshold()
    {
        return 100000;
    }
}
