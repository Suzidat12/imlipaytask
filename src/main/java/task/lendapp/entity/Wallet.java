package task.lendapp.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Wallet {
    private Integer id;
    private Double maxLoanAmount;
    private Double eligibleBalance;
    private Double walletBalance;
    private Double amountDue;
    private List<LoanStore> loanStoreList;
    private Customer customer;
    private Date dateCreated;

}
