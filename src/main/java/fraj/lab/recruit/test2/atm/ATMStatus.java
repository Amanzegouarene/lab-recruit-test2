package fraj.lab.recruit.test2.atm;

/**
 * The possible statuses for cash withdrawal sessions on the ATM.
 */
public enum ATMStatus {
	
	/**
	 * The session was completed and the user got their cash.
	 */
	DONE,
	/**
	 * Not enough cash is available for completing the session.
	 */
	CASH_NOT_AVAILABLE,
	/**
	 * The payment operation could not be completed.
	 */
	PAYMENT_REJECTED

}
