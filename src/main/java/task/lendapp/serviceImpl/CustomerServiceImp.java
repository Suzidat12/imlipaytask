package task.lendapp.serviceImpl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import task.lendapp.entity.Customer;
import task.lendapp.entity.Wallet;
import task.lendapp.entity.request.CustomerRequest;
import task.lendapp.service.CustomerService;

import java.util.*;

@Service
@ApplicationScope
@Slf4j
public class CustomerServiceImp implements CustomerService {
    HashMap<Integer, Wallet> customerStore =new HashMap<>();

    @Override
    public ResponseEntity listCustomers() {

        List<Customer> customerList= new ArrayList<>();
        for(Wallet wallet: customerStore.values()){
            customerList.add(wallet.getCustomer());
        }
        log.info("Customer retrieved");
        return ResponseEntity.ok(customerList);
    }

    @Override
   public ResponseEntity registerCustomer(CustomerRequest payload){

      Customer customer = new Customer();
      customer.setId(customerStore.size()+1);
      customer.setCurrency("$");
      customer.setPhone(payload.getPhone());
      customer.setName(payload.getName());
      customer.setEligibleAmount(payload.getEligibleAmount());
      customer.setWalletBalance(payload.getWalletBalance());
      log.info("Finished setting all customer properties");
      Wallet wallet = new Wallet();
      wallet.setCustomer(customer);
      wallet.setAmountDue(0.0);
      wallet.setWalletBalance(payload.getWalletBalance());
      wallet.setMaxLoanAmount(customer.getEligibleAmount());
      wallet.setId(customer.getId());
      wallet.setDateCreated(new Date());
      customerStore.put(wallet.getId(),wallet);
        log.info("New customer added successfully");
       return ResponseEntity.ok("New customer added successfully");
   }

    @Override
    public Optional<Wallet> getWalletByCustomer(Integer customerId) {
        if(customerStore.containsKey(customerId)){
          return  Optional.of(customerStore.get(customerId));
        }
        return Optional.empty();
    }

    @Override
    public ResponseEntity listLoans() {
        return ResponseEntity.ok(new LinkedList<>(customerStore.values()));
    }

    @Override
    public void updateWallet(Wallet wallet) {
        customerStore.put(wallet.getId(), wallet);
    }
}
