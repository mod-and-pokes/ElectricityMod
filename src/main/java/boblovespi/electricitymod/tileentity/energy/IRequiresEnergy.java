package boblovespi.electricitymod.tileentity.energy;

/**
 * Created by Willi on 4/11/2017.
 */
public interface IRequiresEnergy extends IUsesEnergy

{
	public IProducesEnergy getPowerSource();
	public void CheckPowerSource();
}
