package gruffindor.electricitymod.tools;

import boblovespi.electricitymod.ElectricityMod;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.ResourceLocation;

public class ItemModHoe extends ItemHoe{

	public ItemModHoe(ToolMaterial material, String unlocalizedName) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(ElectricityMod.MOD_ID, unlocalizedName));

	}

}