package task.lendapp.serviceImpl;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import task.lendapp.entity.*;
import task.lendapp.entity.request.CustomerRequest;
import task.lendapp.entity.request.RepaymentRequest;
import task.lendapp.service.CustomerService;
import task.lendapp.service.LoanService;
import task.lendapp.service.ProductService;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanServiceImpl implements LoanService {

    @NonNull
    private ProductService productService;
    @NonNull
    private CustomerService customerService;
   HashMap<Integer,LoanStore> loanStoreHashMap = new HashMap();
    ArrayList<Customer> customerArrayList = new ArrayList<Customer>();
    ArrayList<Product> productArrayList = new ArrayList<Product>();

    @Override
    public ResponseEntity listLoanProducts(){
        return ResponseEntity.ok(new LinkedList<>(productService.listProducts().values()));
    }

    @Override
    public ResponseEntity listCustomers() {
        return customerService.listCustomers();
    }



    @Override
    public ResponseEntity registerCustomer(CustomerRequest customer) {
        return customerService.registerCustomer(customer);
    }

    @Override
    public ResponseEntity requestLoan(Integer customerId, Integer productId) {
        Optional<Wallet> walletOptional = customerService.getWalletByCustomer(customerId);
        Optional<Product> productOptional = productService.findProductById(productId);
        return null;
    }

    @Override
    public ResponseEntity retrieveBalanceByCustomer(Integer customerId) {
        Optional<Wallet> walletOptional = customerService.getWalletByCustomer(customerId);
        if(walletOptional.isEmpty())
            return new ResponseEntity("Record not found", HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(walletOptional.get());
    }
private LoanStore findSingleLoan(List<LoanStore> loanStoreList, Integer loanId){
     for(LoanStore record:loanStoreList){
         if(record.getId()==loanId){
             return record;
         }
     }
     return null;
}
    @Override
    public ResponseEntity addLoanRepayment(RepaymentRequest repaymentRequest) {
        Optional<Wallet> walletOptional = customerService.getWalletByCustomer(repaymentRequest.getCustomerId());
        if(walletOptional.isEmpty())
            return new ResponseEntity("Record not found", HttpStatus.NOT_FOUND);
        Wallet wallet = walletOptional.get();
        if(wallet.getLoanStoreList()==null || wallet.getLoanStoreList().isEmpty())
            return new ResponseEntity("Loan history not available", HttpStatus.NOT_FOUND);
        List<LoanStore> loanStoreListToRemove= new LinkedList<>(wallet.getLoanStoreList());
        List<LoanStore> loanStoreList = wallet.getLoanStoreList();
        LoanStore loanStore = findSingleLoan(loanStoreList, repaymentRequest.getLoanId());
        loanStoreListToRemove.remove(loanStore);
        if(loanStore==null)
            return new ResponseEntity("Loan not available for the specified Id",
                    HttpStatus.NOT_FOUND);
        List<RepaymentHistory> repaymentHistoryList = loanStore.getRepaymentHistoryList()==null?new ArrayList<>():loanStore.getRepaymentHistoryList();
        repaymentHistoryList.add(new RepaymentHistory(repaymentRequest.getAmount(), new Date()));
        wallet.setAmountDue(wallet.getAmountDue()-repaymentRequest.getAmount());
        wallet.setEligibleBalance(wallet.getMaxLoanAmount()-wallet.getAmountDue());
        loanStore.setRepaymentHistoryList(repaymentHistoryList);
        loanStoreListToRemove.add(loanStore);
        customerService.updateWallet(wallet);
        return ResponseEntity.ok("Repayment successful");
    }

    @Override
    public ResponseEntity listLoanRepaymentByCustomer(Integer customerId) {

        return ResponseEntity.ok(loanStoreHashMap.get(customerId));
    }

    @Override
    public ResponseEntity checkLoanEligibilityStatus(Integer customerId) {
        Customer customer = customerArrayList.get(0);
        Product product = productArrayList.get(0);
        if(customer.getEligibleAmount() <= product.getAmount() ){
           log.info("Customer Eligible for loan");
        }else{
           log.info("Customer not eligible for the loan");
        }

        return null;
    }





    }
