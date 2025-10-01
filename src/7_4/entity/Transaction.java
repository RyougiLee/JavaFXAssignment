package entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private double amount;

    @ManyToOne
    Currency source_currency;
    @ManyToOne
    Currency target_currency;

    public Transaction(Currency source_currency, Currency target_currency, Double amount){
        this.source_currency = source_currency;
        this.target_currency = target_currency;
        this.amount = amount;
    }

    public Transaction() {

    }

    public int getID(){
        return id;
    }

    public double getAmount(){
        return amount;
    }

    public Currency getSource_currency(){
        return source_currency;
    }

    public  Currency getTarget_currency(){
        return target_currency;
    }
}
