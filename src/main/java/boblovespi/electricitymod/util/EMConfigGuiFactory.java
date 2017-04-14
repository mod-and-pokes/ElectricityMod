package boblovespi.electricitymod.util;

import boblovespi.electricitymod.ElectricityMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;

/**
 * Created by Willi on 4/13/2017.
 */
public class EMConfigGuiFactory implements IModGuiFactory
{
	@Override public void initialize(Minecraft minecraftInstance)
	{

	}

	@Override public Class<? extends GuiScreen> mainConfigGuiClass()
	{
		return EMConfigGui.class;
	}

	@Override public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
	{
		return null;
	}

	@Override public RuntimeOptionGuiHandler getHandlerFor(
			RuntimeOptionCategoryElement element)
	{
		return null;
	}

	public static class EMConfigGui extends GuiConfig
	{
		public EMConfigGui(GuiScreen parentScreen,
				List<IConfigElement> configElements, String modID,
				String configID, boolean allRequireWorldRestart,
				boolean allRequireMcRestart, String title)
		{
			super(parentScreen, getConfigElements(), ElectricityMod.MOD_ID,
					false, false, I18n.format("gui.config.main_title"));
		}

		private static List<IConfigElement> getConfigElements()
		{
			List<IConfigElement> list = new ArrayList<IConfigElement>();
			list.add(new DummyCategoryElement(
					I18n.format("gui.config.category.blast_furnace"),
					"gui.config.category.blast_furnace",
					CategoryEntryBlastFurnace.class));

			return list;
		}

		private static class CategoryEntryBlastFurnace
				extends GuiConfigEntries.CategoryEntry
		{
			public CategoryEntryBlastFurnace(GuiConfig owningScreen,
					GuiConfigEntries owningEntryList,
					IConfigElement configElement)
			{
				super(owningScreen, owningEntryList, configElement);
			}

			@Override protected GuiScreen buildChildScreen()
			{
				Configuration config = ConfigLoader.getConfig();

				ConfigElement categoryBlastFurnace = new ConfigElement(
						config.getCategory(
								ConfigLoader.CATEGORY_NAME_BLAST_FURNACE));
				List<IConfigElement> properties = categoryBlastFurnace
						.getChildElements();
				String windowTitle = I18n
						.format("gui.config.category.blast_furnace");
				return new GuiConfig(owningScreen, properties,
						owningScreen.modID, configElement.requiresWorldRestart()
						|| owningScreen.allRequireWorldRestart,
						configElement.requiresMcRestart()
								|| owningScreen.allRequireMcRestart,
						windowTitle);
			}
		}
	}
}
