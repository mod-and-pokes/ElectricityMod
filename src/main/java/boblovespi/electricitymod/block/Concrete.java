package boblovespi.electricitymod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Willi on 4/11/2017.
 */
public class Concrete extends Block implements EMBlock
{
	public Concrete()
	{
		super(Material.ROCK);
		setUnlocalizedName(UNLOCALIZED_NAME());
		setRegistryName(REGISTERY_NAME());
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setHardness(10);
		setResistance(10000);
	}

	@Override public String UNLOCALIZED_NAME()
	{
		return "concrete";
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

	@Override public void onEntityWalk(World world, BlockPos pos, Entity entity)
	{
		if (entity instanceof EntityPlayer)
		{
			((EntityPlayer) entity)
					.addPotionEffect(new PotionEffect(MobEffects.SPEED, 0, 1));
		}
	}
}
