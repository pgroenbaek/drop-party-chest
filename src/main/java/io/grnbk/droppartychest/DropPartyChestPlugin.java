package io.grnbk.droppartychest;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.widgets.Widget;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.QuantityFormatter;

@Slf4j
@PluginDescriptor(
	name = "Drop Party Chest"
)
public class DropPartyChestPlugin extends Plugin
{
	private static final int CLANHALL_CHEST_ITEMS_CONTAINER_ID = 33684;
	private static final int CLANHALL_CHEST_TITLE_WIDGET_ID = 51314689;
	private static final String CLANHALL_CHEST_TITLE = "Clan Party Drop Chest";
	private static final int PARTYROOM_CHEST_ITEMS_CONTAINER_ID = 91;
	private static final int PARTYROOM_CHEST_TITLE_WIDGET_ID = 17367041;
	private static final String PARTYROOM_CHEST_TITLE = "Party Drop Chest";

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private ItemManager itemManager;

	@Inject
	private DropPartyChestConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Drop Party Chest started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Drop Party Chest stopped!");
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		int containerId = event.getContainerId();

		if (containerId == PARTYROOM_CHEST_ITEMS_CONTAINER_ID)
		{
			updateChestItemsValue(containerId, PARTYROOM_CHEST_TITLE_WIDGET_ID, PARTYROOM_CHEST_TITLE);
		}
		else if (containerId == CLANHALL_CHEST_ITEMS_CONTAINER_ID)
		{
			updateChestItemsValue(containerId, CLANHALL_CHEST_TITLE_WIDGET_ID, CLANHALL_CHEST_TITLE);
		}
	}

	private void updateChestItemsValue(int containerId, int widgetId, String title)
	{
		final Widget titleContainer = client.getWidget(widgetId);
		if (titleContainer == null)
		{
			return;
		}

		final Widget titleWidget = titleContainer.getChild(1);
		if (titleWidget == null)
		{
			return;
		}

		final long totalChestItemsValue = getTotalGrandExchangeValue(containerId);
		if (totalChestItemsValue > 0) {
			final String totalChestValueText = createValueText(totalChestItemsValue);
			titleWidget.setText(title + totalChestValueText);
		}
		else {
			titleWidget.setText(title);
		}
	}

	private long getTotalGrandExchangeValue(int containerId)
	{
		long grandExchangeValue = 0;

		ItemContainer itemContainer = client.getItemContainer(containerId);
		if (itemContainer != null) {
			for (int i = 0; i < itemContainer.size(); ++i)
			{
				Item item = itemContainer.getItem(i);
				if (item != null) {
					grandExchangeValue += (long) itemManager.getItemPrice(item.getId()) * item.getQuantity();
				}
			}
		}

		return grandExchangeValue;
	}

	private String createValueText(long grandExchangeValue)
	{
		StringBuilder stringBuilder = new StringBuilder();
		if (grandExchangeValue != 0)
		{
			stringBuilder.append(" (");

			if (config.showExact())
			{
				stringBuilder.append(QuantityFormatter.formatNumber(grandExchangeValue));
			}
			else
			{
				stringBuilder.append(QuantityFormatter.quantityToStackSize(grandExchangeValue));
			}
			stringBuilder.append(')');
		}

		return stringBuilder.toString();
	}

	@Provides
	DropPartyChestConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(DropPartyChestConfig.class);
	}
}
