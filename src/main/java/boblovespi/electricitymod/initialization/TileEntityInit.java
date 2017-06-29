package boblovespi.electricitymod.initialization;

import boblovespi.electricitymod.ElectricityMod;
import boblovespi.electricitymod.tileentity.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Willi on 4/13/2017.
 */
public class TileEntityInit
{
	public static void Init()
	{
		initTileEntity(TileEntitySiliconProcessor.class);
		initTileEntity(TileEntitySolarPanel.class);
		initTileEntity(TileEntityPoweredLight.class);
		initTileEntity(TileEntityNetworkMonitor.class);
		initTileEntity(TileEntityBlastFurnace.class);

		// Machines
		initTileEntity(TileEntityMachineCompressor.class);
	}

	private static void initTileEntity(Class<? extends TileEntity> entity)
	{
		GameRegistry.registerTileEntity(entity,
				ElectricityMod.MOD_ID + ":" + entity.getName());
	}
}
