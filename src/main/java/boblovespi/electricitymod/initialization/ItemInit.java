package boblovespi.electricitymod.initialization;

import boblovespi.electricitymod.ElectricityMod;
import boblovespi.electricitymod.item.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Willi on 4/10/2017.
 */
public class ItemInit
{
	public static Item ingot;
	public static Item computerChip;
	public static Item siliconPlate;
	public static ItemFood flatbread;

	public static void Init()
	{
		SetNames();
		ingot = new Ingot();
		computerChip = new ComputerChip();
		siliconPlate = new SiliconPlate();
		flatbread = new Flatbread();
	}

	private static void SetNames()
	{
		// Ingot.Init("ingot", "Ingot");
		// ComputerChip.Init("computer_chip", "Computer_Chip");
	}

	public static void Register()
	{
		GameRegistry.register(ingot);

		GameRegistry.register(computerChip);
		GameRegistry.register(siliconPlate);
		GameRegistry.register(flatbread);
	}

	public static void RegisterRenders()
	{
		RegisterRender(ingot, 0, ingot.getMetaFilePath(0));
		RegisterRender(ingot, 1, ingot.getMetaFilePath(1));
		RegisterRender(ingot, 2, ingot.getMetaFilePath(2));
		RegisterRender(ingot, 3, ingot.getMetaFilePath(3));

		RegisterRender(computerChip, 0);
		RegisterRender(siliconPlate, 0);
		RegisterVanillaItemRender(flatbread, 0);
	}

	private static void RegisterRender(Item item, int meta)
	{
		System.out.println(
				"The other file path: " + ElectricityMod.MOD_ID + ":" + item
						.getMetaFilePath(meta));

		ModelResourceLocation loc = new ModelResourceLocation(
						/*ElectricityMod.MOD_ID + ":" +
				item.getMetaFilePath(meta)*/
				item.getRegistryName(), "inventory");

		System.out.println(
				"The other model resource location: " + loc.toString());

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(item, meta, loc);
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

	private static void RegisterRender(Item item, int meta, String loca)
	{
		ModelResourceLocation loc = new ModelResourceLocation(
						/*ElectricityMod.MOD_ID + ":" +
				item.getMetaFilePath(meta)*/
				item.getRegistryName(), "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(item, meta, new ModelResourceLocation(
						new ResourceLocation(ElectricityMod.MOD_ID, loca),
						"inventory"));
	}

	public static void RegisterVariations()
	{
		ModelBakery.registerItemVariants(ingot,
				new ResourceLocation(ElectricityMod.MOD_ID,
						ingot.getMetaFilePath(0)),
				new ResourceLocation(ElectricityMod.MOD_ID,
						ingot.getMetaFilePath(1)),
				new ResourceLocation(ElectricityMod.MOD_ID,
						ingot.getMetaFilePath(2)),
				new ResourceLocation(ElectricityMod.MOD_ID,
						ingot.getMetaFilePath(3)));
	}

	/* TODO: private static void RegisterVariation()
	{

		for (int i = 0; i < itemtype.; i++)
		{

		}
	}*/
}
