package boblovespi.electricitymod.util;

import boblovespi.electricitymod.ElectricityMod;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Willi on 4/13/2017.
 */
public class ConfigLoader
{
	private static Configuration config;

	public static final String CATEGORY_NAME_BLAST_FURNACE = "blast_furnace";

	public static float BLAST_FURNACE_smeltSpeedScalar;
	public static float BLAST_FURNACE_burnSpeedScalar;
	public static int BLAST_FURNACE_smeltTime;

	public static void PreInit()
	{
		File configFile = new File(Loader.instance().getConfigDir(),
				"ElectricityMod.cfg");
		config = new Configuration(configFile);
		SyncFromFiles();
	}

	public static Configuration getConfig()
	{
		return config;
	}

	public static void ClientPreInit()
	{
		MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
	}

	public static void SyncFromFiles()
	{
		SyncConfig(true, true);
	}

	public static void SyncFromGui()
	{
		SyncConfig(false, true);
	}

	public static void SyncFromFields()
	{
		SyncConfig(false, false);
	}

	private static void SyncConfig(boolean loadFromConfigFile,
			boolean readFieldsFromConfig)
	{
		if (loadFromConfigFile)
			config.load();

		Property propertyCookSpeedScalar = config
				.get(CATEGORY_NAME_BLAST_FURNACE, "smelt_speed_scalar", 1f);
		propertyCookSpeedScalar.setLanguageKey(
				"gui.config.blast_furnace.smelt_speed_scalar.name");
		propertyCookSpeedScalar.setComment(I18n.format(
				"gui.config.blast_furnace.smelt_speed_scalar.comment"));

		propertyCookSpeedScalar.setMinValue(0.1f);
		propertyCookSpeedScalar.setMaxValue(10f);

		Property propertyBurnSpeedScalar = config
				.get(CATEGORY_NAME_BLAST_FURNACE, "burn_speed_scalar", 1f);
		propertyCookSpeedScalar.setLanguageKey(
				"gui.config.blast_furnace.burn_speed_scalar.name");
		propertyCookSpeedScalar.setComment(I18n.format(
				"gui.config.blast_furnace.burn_speed_scalar.comment"));

		propertyCookSpeedScalar.setMinValue(0.1f);
		propertyCookSpeedScalar.setMaxValue(10f);

		Property propertySteelSmeltTime = config
				.get(CATEGORY_NAME_BLAST_FURNACE, "smelt_time", 1000);
		propertySteelSmeltTime
				.setLanguageKey("gui.config.blast_furnace.smelt_time.name");
		propertyCookSpeedScalar.setComment(
				I18n.format("gui.config.blast_furnace.smelt_time.comment"));

		propertySteelSmeltTime.setMinValue(1);
		propertySteelSmeltTime.setMaxValue(100000);

		List<String> propertyOrderBlastFurnace = new ArrayList<String>();
		propertyOrderBlastFurnace.add(propertyCookSpeedScalar.getName());
		propertyOrderBlastFurnace.add(propertyBurnSpeedScalar.getName());
		config.setCategoryPropertyOrder(CATEGORY_NAME_BLAST_FURNACE,
				propertyOrderBlastFurnace);

		if (readFieldsFromConfig)
		{
			BLAST_FURNACE_burnSpeedScalar = (float) propertyBurnSpeedScalar
					.getDouble();
			BLAST_FURNACE_smeltSpeedScalar = (float) propertyCookSpeedScalar
					.getDouble();
			BLAST_FURNACE_smeltTime = propertySteelSmeltTime.getInt();
		}

		propertyBurnSpeedScalar.set(BLAST_FURNACE_burnSpeedScalar);
		propertyCookSpeedScalar.set(BLAST_FURNACE_smeltSpeedScalar);

		if (config.hasChanged())
			config.save();
	}

	public static class ConfigEventHandler
	{
		@SubscribeEvent(priority = EventPriority.LOWEST) public void onEvent(
				ConfigChangedEvent.OnConfigChangedEvent e)
		{
			if (e.getModID() == ElectricityMod.MOD_ID)
				SyncFromGui();
		}
	}

}
