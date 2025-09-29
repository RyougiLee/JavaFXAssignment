package application;

import dao.ConverterDao;
import entity.Currency;
import model.ConverterDB;
import view.ConverterGUIDB;

import java.text.DecimalFormat;

import java.util.List;

public class ConverterApp {

    private ConverterGUIDB gui;
    private ConverterDB converter = new ConverterDB();
    private static final DecimalFormat df = new DecimalFormat(".00");

    public ConverterApp(ConverterGUIDB gui){

        ConverterDao cvtdao = new ConverterDao();
        this.gui = gui;

        List<Currency> currencies = cvtdao.getAllCurrencies();
        for (Currency crc : currencies){
            System.out.println(crc.getName()+ " " + crc.getRate());
            converter.add(crc.getName(), crc.getRate());
            gui.addChoiceBox(crc.getName());
        }
    }

    public void Calculate(){

        double rate = converter.getRate(this.gui.getCurrencyFrom(),this.gui.getCurrencyTo());

        double currencyValueFrom = this.gui.getCurrencyValueFrom();
        double result = this.gui.getCurrencyValueFrom()*rate;
        this.gui.setCurrencyValueTo(Double.valueOf(df.format(result)));
    }
}
