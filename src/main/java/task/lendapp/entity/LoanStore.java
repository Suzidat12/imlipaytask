package task.lendapp.entity;



import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LoanStore {
    private Integer id;
    private List<RepaymentHistory> repaymentHistoryList;
    private Product product;
    private Date transdate;
    private Boolean status;



}
