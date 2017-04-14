package boblovespi.electricitymod.tileentity.energy;

/**
 * Created by Willi on 4/11/2017.
 */
@Deprecated public interface IRequiresEnergy extends IUsesEnergy

{
	IProducesEnergy getPowerSource();
	void CheckPowerSource();
}
