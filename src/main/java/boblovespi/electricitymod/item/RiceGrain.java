package boblovespi.electricitymod.item;

import boblovespi.electricitymod.initialization.BlockInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.List;

/**
 * Created by Willi on 4/12/2017.
 */
public class RiceGrain extends Item implements IPlantable, EMItem
{
	public RiceGrain()
	{
		super();
		setUnlocalizedName(UNLOCALIZED_NAME());
		setRegistryName(REGISTERY_NAME());
	}

	@Override public String UNLOCALIZED_NAME()
	{
		return "rice";
	}

	@Override public String REGISTERY_NAME()
	{
		return UNLOCALIZED_NAME();
	}

	@Override public String getMetaFilePath(int meta)
	{
		return UNLOCALIZED_NAME();
	}

	@Override public Item toItem()
	{
		return this;
	}

	@Override public void getSubItems(Item itemIn, CreativeTabs tab,
			List<ItemStack> subItems)
	{
		subItems.add(new ItemStack(itemIn, 1, 0));
	}

	@Override public EnumPlantType getPlantType(IBlockAccess iBlockAccess,
			BlockPos blockPos)
	{
		return EnumPlantType.Water;
		// return EnumPlantType.Crop;
	}

	@Override public IBlockState getPlant(IBlockAccess iBlockAccess,
			BlockPos blockPos)
	{
		return BlockInit.riceCrop.toBlock().getDefaultState();
	}

	@Override public EnumActionResult onItemUse(ItemStack items,
			EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float dir, float x, float y)
	{
		IBlockState state = world.getBlockState(pos.up());

		if (facing == EnumFacing.UP && player
				.canPlayerEdit(pos.offset(facing), facing, items) && state
				.getBlock()
				.canSustainPlant(state, world, pos.up(), EnumFacing.UP, this)
				&& world.isAirBlock(pos.up(2)))
		{
			world.setBlockState(pos.up(2),
					BlockInit.riceCrop.toBlock().getDefaultState());
			--items.stackSize;

			return EnumActionResult.SUCCESS;
		} else
			return EnumActionResult.FAIL;
	}
}
