package fraj.lab.recruit.test2.atm;

/**
 * The component in charge of managing payment operations with the user's card.
 */
public interface PaymentProcessor {
	
	/**
	 * Operates a payment of the specified amount.
	 * 
	 * @param argAmount the amount to pay
	 * @return the status of the payment operation as a {@link PaymentStatus} 
	 */
	PaymentStatus pay(int argAmount);

}
