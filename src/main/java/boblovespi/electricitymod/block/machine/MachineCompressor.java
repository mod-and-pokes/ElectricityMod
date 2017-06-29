package boblovespi.electricitymod.block.machine;

import boblovespi.electricitymod.ElectricityMod;
import boblovespi.electricitymod.block.EMBlock;
import boblovespi.electricitymod.client.gui.GuiHandler;
import boblovespi.electricitymod.creativetabs.MachineTab;
import boblovespi.electricitymod.tileentity.TileEntityBlastFurnace;
import boblovespi.electricitymod.tileentity.TileEntityMachineCompressor;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

/**
 * Created by Willi on 4/22/2017.
 */
public class MachineCompressor extends Block implements EMBlock,
		ITileEntityProvider
{
	public MachineCompressor()
	{
		super(Material.ANVIL);
		setUnlocalizedName(UNLOCALIZED_NAME());
		setRegistryName(REGISTERY_NAME());
		setCreativeTab(MachineTab.MACHINE_TAB);
	}

	@Override public String UNLOCALIZED_NAME()
	{
		return "machine_compressor";
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
		return new TileEntityMachineCompressor();
	}

	@Override public boolean onBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX,
			float hitY, float hitZ)
	{
		if (!worldIn.isRemote)
		{
			playerIn.openGui(ElectricityMod.main, GuiHandler.MACHINE_COMPRESSOR,
					worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
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
