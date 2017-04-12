package gruffindor.electricitymod.tools;

import boblovespi.electricitymod.ElectricityMod;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

public class ItemModSword extends ItemSword{

	public ItemModSword(ToolMaterial material, String unlocalizedName) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(new ResourceLocation(ElectricityMod.MOD_ID, unlocalizedName));

	}

}
