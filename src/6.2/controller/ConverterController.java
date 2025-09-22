package controller;

import model.Converter;
import view.ConverterGUI;
import java.text.DecimalFormat;

public class ConverterController {

    private ConverterGUI gui;
    private Converter converter = new Converter();
    private static final DecimalFormat df = new DecimalFormat(".00");

    public ConverterController(ConverterGUI gui){

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
