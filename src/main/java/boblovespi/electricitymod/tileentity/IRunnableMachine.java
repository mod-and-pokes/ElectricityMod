package boblovespi.electricitymod.tileentity;

import net.minecraft.util.ITickable;

/**
 * Created by Willi on 4/11/2017.
 */
public interface IRunnableMachine extends ITickable
{
	public boolean AttemptRun();
	public boolean IsRunning();
}
