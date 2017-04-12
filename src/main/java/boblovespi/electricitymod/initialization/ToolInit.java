package boblovespi.electricitymod.initialization;

import boblovespi.electricitymod.ElectricityMod;
import gruffindor.electricitymod.tools.ItemModAxe;
import gruffindor.electricitymod.tools.ItemModHoe;
import gruffindor.electricitymod.tools.ItemModPickaxe;
import gruffindor.electricitymod.tools.ItemModShovel;
import gruffindor.electricitymod.tools.ItemModSword;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ToolInit {
	
	public static final ToolMaterial bronzeMaterial = EnumHelper.addToolMaterial(ElectricityMod.MOD_ID + ":bronze", 2, 251, 5.0F, 2.0F, 12);
	
	public static ItemPickaxe bronzePickaxe;
	public static ItemModAxe bronzeAxe;
	public static ItemHoe bronzeHoe;
	public static ItemSpade bronzeShovel;
	public static ItemSword bronzeSword;
	
	public static void Init() {
		bronzePickaxe = new ItemModPickaxe(bronzeMaterial, "bronze_pickaxe");
		bronzeAxe = new ItemModAxe(bronzeMaterial, "bronze_axe");
		bronzeHoe = new ItemModHoe(bronzeMaterial, "bronze_hoe");
		bronzeShovel = new ItemModShovel(bronzeMaterial, "bronze_shovel");
		bronzeSword = new ItemModSword(bronzeMaterial, "bronze_sword");
	}

	public static void Register() {
		GameRegistry.register(bronzePickaxe);
		GameRegistry.register(bronzeAxe);
		GameRegistry.register(bronzeHoe);
		GameRegistry.register(bronzeShovel);
		GameRegistry.register(bronzeSword);
	}

	public static void RegisterRenders() {
		registerRender(bronzePickaxe);
		registerRender(bronzeAxe);
		registerRender(bronzeHoe);
		registerRender(bronzeShovel);
		registerRender(bronzeSword);
	}

	public static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(
				new ResourceLocation(ElectricityMod.MOD_ID, item.getUnlocalizedName().substring(5)), "inventory"));
		
	}
}
