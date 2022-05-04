package task.lendapp.service;


import org.springframework.http.ResponseEntity;
import task.lendapp.entity.Wallet;
import task.lendapp.entity.request.CustomerRequest;

import java.util.Optional;

public interface CustomerService {
    ResponseEntity listCustomers();
    ResponseEntity registerCustomer(CustomerRequest customer);
    Optional<Wallet> getWalletByCustomer(Integer id);
    ResponseEntity listLoans();
    void updateWallet(Wallet wallet);
}
