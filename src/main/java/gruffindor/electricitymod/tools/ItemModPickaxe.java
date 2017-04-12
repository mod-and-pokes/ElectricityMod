package gruffindor.electricitymod.tools;

import net.minecraft.item.ItemPickaxe;

public class ItemModPickaxe extends ItemPickaxe{

	public ItemModPickaxe(ToolMaterial material, String unlocalizedName) {
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
	}

}
