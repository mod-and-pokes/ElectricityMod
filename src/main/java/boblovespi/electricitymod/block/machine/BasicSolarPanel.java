package boblovespi.electricitymod.block.machine;

import boblovespi.electricitymod.block.EMBlock;
import boblovespi.electricitymod.creativetabs.MachineTab;
import boblovespi.electricitymod.tileentity.IRunnableMachine;
import boblovespi.electricitymod.tileentity.TileEntitySolarPanel;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Willi on 4/11/2017.
 */
public class BasicSolarPanel extends Block
		implements ITileEntityProvider, EMBlock
{
	// 3.5 / 16 =

	private static final AxisAlignedBB boundingBox = new AxisAlignedBB(0, 0, 0,
			1, 0.21875, 1);

	public BasicSolarPanel()
	{
		super(Material.IRON);
		setRegistryName(REGISTERY_NAME());
		setUnlocalizedName(UNLOCALIZED_NAME());
		setCreativeTab(MachineTab.MACHINE_TAB);
	}

	@Override public String UNLOCALIZED_NAME()
	{
		return "machine_solar_panel";
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

	@Override public TileEntity createNewTileEntity(World world, int i)
	{
		return new TileEntitySolarPanel();
	}

	@Override public boolean onBlockActivated(World world, BlockPos pos,
			IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack items, EnumFacing side, float x, float y,
			float z)
	{
		if (!world.isRemote)
		{
			TileEntity e = world.getTileEntity(pos);
			if (e instanceof IRunnableMachine)
			{
				if (((IRunnableMachine) e).AttemptRun())
				{

				}
			}
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
}
