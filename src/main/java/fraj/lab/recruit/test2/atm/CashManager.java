package fraj.lab.recruit.test2.atm;


/**
 * The component in charge of dealing with bank notes.
 */
public interface CashManager {

	/**
	 * Checks whether the specified amount is available.
	 * 
	 * @param argAmount the wanted amount
	 * @return <code>true</code> if the amount is available, otherwise <code>false</code>
	 */
	boolean canDeliver(int argAmount);
	
	/**
	 * Has the specified amount eventually delivered to the user.
	 * 
	 * @param argAmount the wanted amount
	 */
	void deliver(int argAmount);

}
