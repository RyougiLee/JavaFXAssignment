package entity;
import jakarta.persistence.*;

@Entity
@Table(name="currency")
public class Currency {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="abbr_name")
    private String abbr_name;

    @Column(name = "currency_name")
    private String name;

    @Column(name="rate_to_usd")
    private double rate_to_usd;

    public Currency(String name,String abbr_name, double rate_to_usd){
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
