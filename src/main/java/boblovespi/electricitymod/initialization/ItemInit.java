package boblovespi.electricitymod.initialization;

import boblovespi.electricitymod.ElectricityMod;
import boblovespi.electricitymod.item.*;
import boblovespi.electricitymod.util.Debug;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import scala.actors.threadpool.Arrays;

/**
 * Created by Willi on 4/10/2017.
 */
public class ItemInit
{
	public static EMItem ingot;
	public static EMItem computerChip;
	public static EMItem siliconPlate;
	public static EMItem flatbread;
	public static EMItem riceGrain;
	public static EMItem wireSpool;

	public static EMItem chineseFood;
	public static EMItem coalCoke;
	public static EMItem slag;
	public static EMItem crosbowBolt;
	public static EMItem crosbow;

	public static void Init()
	{
		SetNames();
		ingot = new Ingot();
		computerChip = new ComputerChip();
		siliconPlate = new SiliconPlate();
		flatbread = new Flatbread();
		riceGrain = new RiceGrain();
		wireSpool = new WireSpool();
		coalCoke = new EMBaseItem("coal_coke", CreativeTabs.MATERIALS);
		slag = new EMBaseItem("slag", CreativeTabs.MATERIALS);

		crosbowBolt = new EMBaseItem("crossbow_bolt", CreativeTabs.COMBAT);
		crosbow = new Crossbow();

		chineseFood = new EMFood("chinese_food", 12, 12, 72, false, false,
				Arrays.asList(new PotionEffect[] {
						new PotionEffect(Potion.getPotionById(9), 600, 0),
						new PotionEffect(Potion.getPotionById(10), 600, 1) }),
				Arrays.asList(new Float[] { 1f, 1f }));
	}

	private static void SetNames()
	{
		// Ingot.Init("ingot", "Ingot");
		// ComputerChip.Init("computer_chip", "Computer_Chip");
	}

	public static void Register()
	{
		RegisterItem(ingot);

		RegisterItem(computerChip);
		RegisterItem(siliconPlate);
		RegisterItem(flatbread);
		RegisterItem(riceGrain);
		RegisterItem(chineseFood);
		RegisterItem(wireSpool);
		RegisterItem(coalCoke);
		RegisterItem(slag);
		RegisterItem(crosbow);
		RegisterItem(crosbowBolt);
	}

	private static void RegisterItem(EMItem i)
	{
		Debug.DebugLog().info("Registering item: ", i.REGISTERY_NAME());
		GameRegistry.register(i.toItem());
	}

	public static void RegisterRenders()
	{
		RegisterRender(ingot, 0, ingot.getMetaFilePath(0));
		RegisterRender(ingot, 1, ingot.getMetaFilePath(1));
		RegisterRender(ingot, 2, ingot.getMetaFilePath(2));
		RegisterRender(ingot, 3, ingot.getMetaFilePath(3));

		RegisterRender(computerChip, 0);
		RegisterRender(siliconPlate, 0);
		RegisterRender(flatbread, 0);

		RegisterRender(riceGrain, 0);
		RegisterRender(chineseFood, 0);

		RegisterRender(wireSpool, 0, wireSpool.getMetaFilePath(0));
		RegisterRender(wireSpool, 1, wireSpool.getMetaFilePath(1));

		RegisterRender(coalCoke, 0);
		RegisterRender(slag, 0);

		RegisterRender(crosbow, 0, crosbow.getMetaFilePath(0));
		RegisterRender(crosbow, 0, crosbow.getMetaFilePath(1));
		RegisterRender(crosbowBolt, 0);
	}

	private static void RegisterRender(EMItem item, int meta)
	{
		System.out.println(
				"The other file path: " + ElectricityMod.MOD_ID + ":" + item
						.getMetaFilePath(meta));

		ModelResourceLocation loc = new ModelResourceLocation(
				new ResourceLocation(ElectricityMod.MOD_ID,
						item.getMetaFilePath(0)), "inventory");

		System.out.println(
				"The other model resource location: " + loc.toString());

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(item.toItem(), meta, loc);
	}

	private static void RegisterVanillaItemRender(net.minecraft.item.Item item,
			int meta)
	{
		ModelResourceLocation loc = new ModelResourceLocation(
						/*ElectricityMod.MOD_ID + ":" +
				item.getMetaFilePath(meta)*/
				item.getRegistryName(), "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(item, meta, loc);
	}

	private static void RegisterRender(EMItem item, int meta, String loca)
	{
		ModelResourceLocation loc = new ModelResourceLocation(
						/*ElectricityMod.MOD_ID + ":" +
				item.getMetaFilePath(meta)*/
				item.toItem().getRegistryName(), "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(item.toItem(), meta, new ModelResourceLocation(
						new ResourceLocation(ElectricityMod.MOD_ID, loca),
						"inventory"));
	}

	public static void RegisterVariations()
	{
		ModelBakery.registerItemVariants(ingot.toItem(),
				new ResourceLocation(ElectricityMod.MOD_ID,
						ingot.getMetaFilePath(0)),
				new ResourceLocation(ElectricityMod.MOD_ID,
						ingot.getMetaFilePath(1)),
				new ResourceLocation(ElectricityMod.MOD_ID,
						ingot.getMetaFilePath(2)),
				new ResourceLocation(ElectricityMod.MOD_ID,
						ingot.getMetaFilePath(3)));

		ModelBakery.registerItemVariants(wireSpool.toItem(),
				new ResourceLocation(ElectricityMod.MOD_ID,
						wireSpool.getMetaFilePath(0)),
				new ResourceLocation(ElectricityMod.MOD_ID,
						wireSpool.getMetaFilePath(1)));

		ModelBakery.registerItemVariants(crosbow.toItem(),
				new ResourceLocation(ElectricityMod.MOD_ID,
						crosbow.getMetaFilePath(0)),
				new ResourceLocation(ElectricityMod.MOD_ID,
						crosbow.getMetaFilePath(1)));
	}

	/* TODO: private static void RegisterVariation()
	{

		for (int i = 0; i < itemtype.; i++)
		{

		}
	}*/
}
