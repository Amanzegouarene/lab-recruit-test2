package fraj.lab.recruit.test2.atm;

/**
 * A component that implements the cash withdrawal process on an ATM.
 */
public class ATM {

	private final AmountSelector amountSelector;
	private final CashManager cashManager;
	private final PaymentProcessor paymentProcessor;

	/**
	 * Constructs an {@link ATM} with a set of associated components.
	 * 
	 * @param argAmountSelector   the {@link AmountSelector} used to have the user
	 *                            select the wanted amount
	 * @param argCashManager      the {@link CashManager} that deals with bank notes
	 * @param argPaymentProcessor the {@link PaymentProcessor} that deals with
	 *                            payment operations
	 */
	public ATM(AmountSelector argAmountSelector, CashManager argCashManager, PaymentProcessor argPaymentProcessor) {

		amountSelector = argAmountSelector;
		cashManager = argCashManager;
		paymentProcessor = argPaymentProcessor;

	}

	/**
	 * Runs a cash withdrawal session on the ATM.
	 */
	public ATMStatus runCashWithdrawal() throws ATMTechnicalException {
		int locAmount = amountSelector.selectAmount();
		if (locAmount <= 0) {
			throw new ATMTechnicalException();
		}
		if (! cashManager.canDeliver(locAmount)) {
			return ATMStatus.CASH_NOT_AVAILABLE;
		}
		PaymentStatus paymentStatus = paymentProcessor.pay(locAmount);
		if (PaymentStatus.FAILURE == paymentStatus) {
			return ATMStatus.PAYMENT_REJECTED;
		}
		cashManager.deliver(locAmount);
		return ATMStatus.DONE;
	}

}
