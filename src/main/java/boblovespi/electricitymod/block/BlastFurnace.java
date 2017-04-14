package boblovespi.electricitymod.block;

import boblovespi.electricitymod.ElectricityMod;
import boblovespi.electricitymod.client.gui.GuiHandler;
import boblovespi.electricitymod.tileentity.TileEntityBlastFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Willi on 4/13/2017.
 */
public class BlastFurnace extends Block implements ITileEntityProvider, EMBlock
{
	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(1 / 32,
			0, 1 / 32, 1 - 1 / 32, 35 / 16, 1 - 1 / 32);

	public BlastFurnace()
	{
		super(Material.DRAGON_EGG);
		setRegistryName(REGISTERY_NAME());
		setUnlocalizedName(UNLOCALIZED_NAME());
	}

	@Override public String UNLOCALIZED_NAME()
	{
		return "blast_furnace";
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

	@Override public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityBlastFurnace();
	}

	@Override public boolean onBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX,
			float hitY, float hitZ)
	{
		if (!worldIn.isRemote)
		{
			playerIn.openGui(ElectricityMod.main, GuiHandler.BLAST_FURNACE,
					worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

	@Override public boolean isFullCube(IBlockState p_isFullCube_1_)
	{
		return false;
	}

	@Override public boolean isVisuallyOpaque()
	{
		return false;
	}

	@Override public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}

	@Override public AxisAlignedBB getBoundingBox(IBlockState state,
			IBlockAccess source, BlockPos pos)
	{
		return boundingBox;
	}

	@Override public void addCollisionBoxToList(IBlockState state, World world,
			BlockPos pos, AxisAlignedBB bb, List<AxisAlignedBB> aabb,
			@Nullable Entity ent)
	{
		addCollisionBoxToList(pos, bb, aabb, boundingBox);
	}

	@Override public boolean isSideSolid(IBlockState base_state,
			IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		switch (side)
		{
		case DOWN:
			return true;
		case UP:
			return true;
		default:
			return false;
		}
	}

	@Override public void breakBlock(World worldIn, BlockPos pos,
			IBlockState state)
	{
		TileEntityBlastFurnace te = (TileEntityBlastFurnace) worldIn
				.getTileEntity(pos);
		IItemHandler handler = te
				.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
						null);

		for (int i = 0; i < handler.getSlots(); i++)
		{
			ItemStack stack = handler.getStackInSlot(i);
			InventoryHelper
					.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(),
							stack);
		}

		super.breakBlock(worldIn, pos, state);
	}
}
