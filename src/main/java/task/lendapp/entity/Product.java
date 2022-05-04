package task.lendapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    private Integer id;
    private Double amount;
    private Double interest;
    private Integer tenure;
    private String tenuretype;
    private Double interestrate;
    private String currency;

    public Product(Double amount, Integer id, Double interest, Integer tenure, String tenuretype, Double interestrate, String currency) {
        this.amount = amount;
        this.interest = interest;
        this.tenure = tenure;
        this.tenuretype = tenuretype;
        this.interestrate = interestrate;
        this.currency = currency;
        this.id = id;
    }

    public Product() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Integer getTenure() {
        return tenure;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

    public String getTenuretype() {
        return tenuretype;
    }

    public void setTenuretype(String tenuretype) {
        this.tenuretype = tenuretype;
    }

    public Double getInterestrate() {
        return interestrate;
    }

    public void setInterestrate(Double interestrate) {
        this.interestrate = interestrate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
