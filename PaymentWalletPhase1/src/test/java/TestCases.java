import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.cg.paymentwallet.beans.Customer;
import com.cg.paymentwallet.exception.PaymentWalletException;
import com.cg.paymentwallet.service.WalletService;
import com.cg.paymentwallet.service.WalletServiceImpl;

public class TestCases {
	WalletService service=new WalletServiceImpl();

	@Test
	public void testOne() throws PaymentWalletException {
	
		BigDecimal big=new BigDecimal("600.00");
		service.validate("sneha", "9790453294",big);
		
	}
	@Test
	public void testTwo() throws PaymentWalletException {
	
		BigDecimal big=new BigDecimal("700.00");
		service.validate("phani", "1234567890",big);
		
	}
	@Test(expected=PaymentWalletException.class)
	
	public void testThree() throws PaymentWalletException {
	
		BigDecimal big=new BigDecimal("600.00");
		service.validate("pullela","9790454",big);
		
	}
	
	
}
