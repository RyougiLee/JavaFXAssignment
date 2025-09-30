package model;

import java.util.HashMap;

public class ConverterDB {

    HashMap<String, Double> database = new HashMap<>();

    public void add(String currency, Double rate){
        database.put(currency, rate);
    }

    public void reset(){database.clear();}

    public Double getRate(String currencyFrom, String currencyTo ){
        Double rateFrom = database.get(currencyFrom);
        Double rateTo = 1/database.get(currencyTo);
        if(currencyFrom.equals("USD")){
            return rateTo;
        } else if (currencyTo.equals("USD")) {
            return rateFrom;
        }
        else {
            return rateTo*rateFrom;
        }
    }
}
