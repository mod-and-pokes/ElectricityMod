package gruffindor.electricitymod.block;

import boblovespi.electricitymod.block.EMBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class Ore extends Block implements EMBlock{

	private String oreType;
	//private static final PropertyEnum <OreType>;
	
	public Ore(String oreType)
	{
		
		super(Material.ROCK);
		this.oreType = oreType;
		setUnlocalizedName(UNLOCALIZED_NAME());
		setRegistryName(REGISTERY_NAME());
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}

	@Override public String UNLOCALIZED_NAME()
	{
		return "ore_" + oreType;
	}

	@Override public String REGISTERY_NAME()
	{
		return UNLOCALIZED_NAME();
	}

	@Override public String getMetaFilePath(int meta)
	{
		return REGISTERY_NAME();
	}

	@Override public Block toBlock()
	{
		return this;
	}

}
