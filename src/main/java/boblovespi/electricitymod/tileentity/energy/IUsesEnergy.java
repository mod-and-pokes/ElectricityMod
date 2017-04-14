package boblovespi.electricitymod.tileentity.energy;

/**
 * Created by Willi on 4/11/2017.
 */
@Deprecated public interface IUsesEnergy
{
	boolean hasEnoughEnergy();

	boolean hasExcessEnergy();

	void UpdateConnections();

	boolean Ping(IUsesEnergy machine);

	void AddConnection(IUsesEnergy machine);

	boolean RemoveConnection(IUsesEnergy machine);

	boolean ReplyPing(IUsesEnergy machine);

	float getEnergyUsed();

	void AcceptEnergy();

	void DeclineEnergy();
}
