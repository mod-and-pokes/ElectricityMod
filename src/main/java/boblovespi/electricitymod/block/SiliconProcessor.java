package boblovespi.electricitymod.block;

import boblovespi.electricitymod.tileentity.IRunnableMachine;
import boblovespi.electricitymod.tileentity.TileEntitySiliconProcessor;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by Willi on 4/11/2017.
 */
public class SiliconProcessor extends Block implements ITileEntityProvider
{
	public SiliconProcessor()
	{
		super(Material.ANVIL);
		setRegistryName(REGISTERY_NAME());
		setUnlocalizedName(UNLOCALIZED_NAME());
	}

	@Override public String UNLOCALIZED_NAME()
	{
		return "machine_silicon";
	}

	@Override public String REGISTERY_NAME()
	{
		return UNLOCALIZED_NAME();
	}

	@Override public TileEntity createNewTileEntity(World world, int i)
	{
		return new TileEntitySiliconProcessor();
	}

	@Override public boolean onBlockActivated(World world, BlockPos pos,
			IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack items, EnumFacing dir, float x, float y,
			float z)
	{
		if (world.isRemote)
			return true;
		TileEntity entity = world.getTileEntity(pos);
		if (entity instanceof TileEntitySiliconProcessor)
		{
			IRunnableMachine machine = ((IRunnableMachine) entity);
			if (items != null && items.getItem() == ItemBlock
					.getItemFromBlock(Blocks.SAND))
			{
				if (machine.AttemptRun())
				{
					--items.stackSize;
					return true;
				}
			}
		}

		return true;
	}

	@Override @SideOnly(Side.CLIENT) public void randomDisplayTick(
			IBlockState state, World world, BlockPos pos, Random rand)
	{
		TileEntity entity = world.getTileEntity(pos);
		if (entity instanceof TileEntitySiliconProcessor)
		{
			if (((TileEntitySiliconProcessor) entity).IsRunning())
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
						pos.getX() + rand.nextDouble(),
						pos.getY() + rand.nextDouble(),
						pos.getZ() + rand.nextDouble(), 0, 0, 0);
		}
	}
}
