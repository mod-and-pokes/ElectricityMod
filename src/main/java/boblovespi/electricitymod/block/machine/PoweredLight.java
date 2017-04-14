package boblovespi.electricitymod.block.machine;

import boblovespi.electricitymod.block.EMBlock;
import boblovespi.electricitymod.tileentity.TileEntityPoweredLight;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Willi on 4/12/2017.
 */
public class PoweredLight extends Block implements ITileEntityProvider, EMBlock
{
	private static final PropertyBool IsActivated = PropertyBool
			.create("running");

	public PoweredLight()
	{
		super(Material.REDSTONE_LIGHT);
		setRegistryName(REGISTERY_NAME());
		setUnlocalizedName(UNLOCALIZED_NAME());
		setDefaultState(blockState.getBaseState()
				.withProperty(IsActivated, Boolean.FALSE));
	}

	@Override public String UNLOCALIZED_NAME()
	{
		return "machine_light";
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
		return new TileEntityPoweredLight(meta == 0);
	}

	private void LightOn()
	{
		getDefaultState().withProperty(IsActivated, true);
		setLightLevel(1);
	}

	private void LightOff()
	{
		getDefaultState().withProperty(IsActivated, false);
		setLightLevel(0);
	}

	@Override public void updateTick(World worldIn, BlockPos pos,
			IBlockState state, Random rand)
	{
		if (worldIn.isRemote)
			return;
		if (worldIn.getTileEntity(pos) != null && worldIn
				.getTileEntity(pos) instanceof TileEntityPoweredLight)
		{
			if (((TileEntityPoweredLight) worldIn.getTileEntity(pos)).isOn)
				LightOn();
			else
				LightOff();

		}
	}

	@Override public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(IsActivated, meta == 1);
	}

	@Override public int getMetaFromState(IBlockState state)
	{
		return state.getValue(IsActivated) ? 1 : 0;
	}

	@Override public BlockStateContainer getBlockState()
	{
		return super.getBlockState();
	}

	@Override protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, IsActivated);
	}

}
