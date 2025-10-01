package entity;
import jakarta.persistence.*;

@Entity
public class Currency {

    @Id
    private String abbr_name;
    private String name;
    private double rate_to_usd;

    public Currency(String abbr_name, String name, double rate_to_usd){
        this.abbr_name = abbr_name;
        this.rate_to_usd = rate_to_usd;
        this.name = name;
    }

    public Currency(){
    }

    public String getName(){
        return abbr_name;
    }

    public double getRate(){
        return rate_to_usd;
    }
}
