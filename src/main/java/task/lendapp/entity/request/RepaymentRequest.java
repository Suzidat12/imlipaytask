package task.lendapp.entity.request;

import lombok.Data;

@Data
public class RepaymentRequest {
    private Integer customerId;
    private Integer loanId;
    private Double amount;
}
