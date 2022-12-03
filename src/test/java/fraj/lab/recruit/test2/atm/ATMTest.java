package fraj.lab.recruit.test2.atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class ATMTest {

    private ATM atm;

    private AmountSelector amountSelectorMock;

    private CashManager cashManagerMock;

    private PaymentProcessor paymentProcessorMock;

    @BeforeEach
    public void beforeAll() {
        amountSelectorMock = mock(AmountSelector.class);
        cashManagerMock = mock(CashManager.class);
        paymentProcessorMock = mock(PaymentProcessor.class);

        atm = new ATM(amountSelectorMock, cashManagerMock, paymentProcessorMock);
    }

    @Disabled("To test Pitest mutation testing")
    @Test
    void givenNegativeSelectedAmount_whenRunCashWithdrawal_shouldThrownATMTechnicalExceptionAndStopProcessing() {
        // Given
        when(amountSelectorMock.selectAmount()).thenReturn(-1);

        assertThatThrownBy(
                // When
                () -> atm.runCashWithdrawal())
                // Then
                .isInstanceOf(ATMTechnicalException.class);
        verifyNoInteractions(paymentProcessorMock);
        verifyNoInteractions(cashManagerMock);

    }

    @Test
    void givenZeroSelectedAmount_whenRunCashWithdrawal_shouldThrownATMTechnicalExceptionAndStopProcessing() {
        // Given
        when(amountSelectorMock.selectAmount()).thenReturn(0);

        assertThatThrownBy(
                // When
                () -> atm.runCashWithdrawal())
                // Then
                .isInstanceOf(ATMTechnicalException.class);
    }

    @Test
    void givenNotAvailableSelectedAmount_whenRunCashWithdrawal_shouldReturnCashNotAvailableAndDontProcessPayment() throws ATMTechnicalException {
        // Given
        when(amountSelectorMock.selectAmount()).thenReturn(100);
        when(cashManagerMock.canDeliver(100)).thenReturn(false);

        // When
        ATMStatus atmStatus = atm.runCashWithdrawal();

        // Then
//        assertThat(atmStatus).isEqualByComparingTo(ATMStatus.CASH_NOT_AVAILABLE);
        verifyNoInteractions(paymentProcessorMock);
        verify(cashManagerMock, times(0)).deliver(anyInt());
    }

    @Test
    void givenAvailableSelectedAmountButRejectedPayment_whenRunCashWithdrawal_shouldReturnPaymentRejectedAndDontDeliverCash() throws ATMTechnicalException {
        // Given
        when(amountSelectorMock.selectAmount()).thenReturn(100);
        when(cashManagerMock.canDeliver(100)).thenReturn(true);
        when(paymentProcessorMock.pay(100)).thenReturn(PaymentStatus.FAILURE);

        // When
        ATMStatus atmStatus = atm.runCashWithdrawal();

        // Then
        assertThat(atmStatus).isEqualByComparingTo(ATMStatus.PAYMENT_REJECTED);
        verify(cashManagerMock, times(0)).deliver(anyInt());
    }

    @Test
    void givenAvailableSelectedAmountAndSuccessPayment_whenRunCashWithdrawal_shouldReturnDoneAndDeliverCash() throws ATMTechnicalException {
        // Given
        when(amountSelectorMock.selectAmount()).thenReturn(100);
        when(cashManagerMock.canDeliver(100)).thenReturn(true);
        when(paymentProcessorMock.pay(100)).thenReturn(PaymentStatus.SUCCESS);

        // When
        ATMStatus atmStatus = atm.runCashWithdrawal();

        // Then
        assertThat(atmStatus).isEqualByComparingTo(ATMStatus.DONE);
        verify(cashManagerMock, times(1)).deliver(100);
    }
}
