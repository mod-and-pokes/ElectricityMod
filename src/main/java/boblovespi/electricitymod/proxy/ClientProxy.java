package boblovespi.electricitymod.proxy;

import boblovespi.electricitymod.initialization.BlockInit;
import boblovespi.electricitymod.initialization.ItemInit;

/**
 * Created by Willi on 4/10/2017.
 */
public class ClientProxy implements CommonProxy
{
	@Override public void PreInit()
	{
		ItemInit.RegisterVariations();
	}

	@Override public void Init()
	{
		ItemInit.RegisterRenders();
		BlockInit.RegisterRenders();
	}
}
