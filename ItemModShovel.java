package gruffindor.electricitymod.tools;

import boblovespi.electricitymod.ElectricityMod;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSpade;
import net.minecraft.util.ResourceLocation;

public class ItemModShovel extends ItemSpade{

	public ItemModShovel(ToolMaterial material, String unlocalizedName) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(ElectricityMod.MOD_ID, unlocalizedName));

	}

}
