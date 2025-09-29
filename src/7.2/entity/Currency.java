package entity;

public class Currency {

    private String abbr_name;
    private double rate_to_usd;

    public Currency(String abbr_name, double rate_to_usd){
        this.abbr_name = abbr_name;
        this.rate_to_usd = rate_to_usd;
    }

    public String getName(){
        return abbr_name;
    }

    public double getRate(){
        return rate_to_usd;
    }
}
