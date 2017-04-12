package boblovespi.electricitymod.tileentity.energy;

/**
 * Created by Willi on 4/11/2017.
 */
public interface IUsesEnergy
{
	public boolean hasEnoughEnergy();

	public boolean hasExcessEnergy();

	public void UpdateConnections();

	public boolean Ping(IUsesEnergy machine);

	public float getEnergyAmount();

	public void AddConnection(IUsesEnergy machine);

	public boolean RemoveConnection(IUsesEnergy machine);
}
