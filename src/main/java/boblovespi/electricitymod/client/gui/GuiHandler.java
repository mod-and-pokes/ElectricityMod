package boblovespi.electricitymod.client.gui;

import boblovespi.electricitymod.container.ContainerBlastFurnace;
import boblovespi.electricitymod.container.ContainerMachineCompressor;
import boblovespi.electricitymod.tileentity.TileEntityBlastFurnace;
import boblovespi.electricitymod.tileentity.TileEntityMachineCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by Willi on 4/13/2017.
 */
public class GuiHandler implements IGuiHandler
{
	public static final int BLAST_FURNACE = 0;
	public static final int MACHINE_COMPRESSOR = 1;

	@Override public Object getServerGuiElement(int ID, EntityPlayer player,
			World world, int x, int y, int z)
	{
		if (ID == BLAST_FURNACE)
		{
			return new ContainerBlastFurnace(player.inventory,
					(TileEntityBlastFurnace) world
							.getTileEntity(new BlockPos(x, y, z)));
		}
		if (ID == MACHINE_COMPRESSOR)
			return new ContainerMachineCompressor(player.inventory,
					(TileEntityMachineCompressor) world
							.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

	@Override public Object getClientGuiElement(int ID, EntityPlayer player,
			World world, int x, int y, int z)
	{
		if (ID == BLAST_FURNACE)
		{
			return new GuiBlastFurnace(player.inventory,
					(TileEntityBlastFurnace) world
							.getTileEntity(new BlockPos(x, y, z)));
		}
		if (ID == MACHINE_COMPRESSOR)
			return new GuiCompressor(player.inventory,
					(TileEntityMachineCompressor) world
							.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}
}
