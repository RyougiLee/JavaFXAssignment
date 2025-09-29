package controller;

import model.ConverterDB;
import view.ConverterGUIDB;

import java.text.DecimalFormat;

public class ConverterControllerDB {

    private ConverterGUIDB gui;
    private ConverterDB converter = new ConverterDB();
    private static final DecimalFormat df = new DecimalFormat(".00");

    public ConverterControllerDB(ConverterGUIDB gui){

        this.gui = gui;

        converter.add("CNY",0.14);
        converter.add("GBP",1.35);
        converter.add("EUR",1.18);
        converter.add("JPY",0.0068);
        converter.add("USD",1.0);
    }

    public void Calculate(){

        double rate = converter.getRate(this.gui.getCurrencyFrom(),this.gui.getCurrencyTo());

        double currencyValueFrom = this.gui.getCurrencyValueFrom();
        double result = this.gui.getCurrencyValueFrom()*rate;
        this.gui.setCurrencyValueTo(Double.valueOf(df.format(result)));
    }
}
