package boblovespi.electricitymod.energy;

/**
 * Created by Willi on 4/12/2017.
 */
public interface IUsesEnergy
{
	EnergyNetwork getNetwork();

	IUsesEnergy setNetwork(EnergyNetwork network);

	boolean isActive();
}
