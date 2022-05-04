package task.lendapp.service;


import org.springframework.http.ResponseEntity;
import task.lendapp.entity.request.CustomerRequest;
import task.lendapp.entity.request.RepaymentRequest;

public interface LoanService {
    ResponseEntity listCustomers();
    ResponseEntity registerCustomer(CustomerRequest customer);
    ResponseEntity requestLoan(Integer customerId, Integer productId);
    ResponseEntity retrieveBalanceByCustomer(Integer customerId);
    ResponseEntity addLoanRepayment(RepaymentRequest repaymentRequest);
    ResponseEntity listLoanRepaymentByCustomer(Integer customerId);
    ResponseEntity checkLoanEligibilityStatus(Integer customerId);

    ResponseEntity listLoanProducts();

}
