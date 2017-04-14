package boblovespi.electricitymod.block.machine;

import boblovespi.electricitymod.block.EMBlock;
import boblovespi.electricitymod.tileentity.TileEntityNetworkMonitor;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Willi on 4/13/2017.
 */
public class EnergyNetworkMonitor extends Block
		implements EMBlock, ITileEntityProvider
{
	public EnergyNetworkMonitor()
	{
		super(Material.REDSTONE_LIGHT);
	}

	@Override public String UNLOCALIZED_NAME()
	{
		return "network_monitior";
	}

	@Override public String REGISTERY_NAME()
	{
		return UNLOCALIZED_NAME();
	}

	@Override public String getMetaFilePath(int meta)
	{
		return UNLOCALIZED_NAME();
	}

	@Override public Block toBlock()
	{
		return this;
	}

	@Override public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityNetworkMonitor();
	}

	@Override public boolean onBlockActivated(World world, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX,
			float hitY, float hitZ)
	{
		if (world.isRemote)
			return true;
		if (world.getTileEntity(pos) instanceof TileEntityNetworkMonitor)
		{

		}

		return true;
	}
}
