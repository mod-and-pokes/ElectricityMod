package boblovespi.electricitymod.block.machine;

import boblovespi.electricitymod.block.EMBlock;
import boblovespi.electricitymod.tileentity.TileEntityPoweredLight;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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

	public void ToggleLight()
	{
		getDefaultState().withProperty(IsActivated,
				!getBlockState().getBaseState().getValue(IsActivated));
	}

	@Override public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(IsActivated, meta == 1);
	}

	@Override public int getMetaFromState(IBlockState state)
	{
		return state.getValue(IsActivated) ? 1 : 0;
	}
}
