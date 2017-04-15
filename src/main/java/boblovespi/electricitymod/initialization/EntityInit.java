package boblovespi.electricitymod.initialization;

import boblovespi.electricitymod.ElectricityMod;
import boblovespi.electricitymod.entity.CrossbowBolt;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 * Created by Willi on 4/14/2017.
 */
public class EntityInit
{
	private static int id = 0;

	//private static CrossbowBolt crossbowBolt;

	public static void Init()
	{
		//crossbowBolt = new CrossbowBolt();

		//GameRegistry.register(crossbowBolt);

		Reg(CrossbowBolt.class, "ElectricityMod_CrossbowBolt", 64, 20, true);

	}

	private static void Reg(Class<? extends Entity> c, String name, int tr,
			int uf, boolean sends)
	{
		EntityRegistry
				.registerModEntity(c, name, ++id, ElectricityMod.main, tr, uf,
						sends);
	}

}
