package application;

import dao.ConverterDao;
import dao.TransactionDAO;
import entity.Currency;
import entity.Transaction;
import model.ConverterDB;
import view.ConverterGUIDB;

import java.text.DecimalFormat;

import java.util.List;

public class ConverterApp {

    private ConverterGUIDB gui;
    private ConverterDB converter = new ConverterDB();
    private static final DecimalFormat df = new DecimalFormat(".00");
    ConverterDao cvtdao = new ConverterDao();
    TransactionDAO transdao = new TransactionDAO();

    public ConverterApp(ConverterGUIDB gui){
        this.gui = gui;

        List<Currency> currencies = cvtdao.getAllCurrencies();
        for (Currency crc : currencies){
            System.out.println(crc.getName()+ " " + crc.getRate());
            converter.add(crc.getName(), crc.getRate());
            gui.addChoiceBox(crc.getName());
        }
    }

    public void update(){

        List<Currency> currencies = cvtdao.getAllCurrencies();
        converter.reset();
        gui.resetChoiceBox();
        for (Currency crc : currencies){
            System.out.println(crc.getName()+ " " + crc.getRate());
            converter.add(crc.getName(), crc.getRate());
            gui.addChoiceBox(crc.getName());
        }
    }

    public void Calculate(){

        String source_currency_name = this.gui.getCurrencyFrom();
        String target_currency_name = this.gui.getCurrencyTo();
        double rate = converter.getRate(source_currency_name,target_currency_name);
        double currencyValueFrom = this.gui.getCurrencyValueFrom();
        double result = this.gui.getCurrencyValueFrom()*rate;
        this.gui.setCurrencyValueTo(Double.valueOf(df.format(result)));

        Currency source_currency = cvtdao.find(source_currency_name);
        Currency target_currency = cvtdao.find(target_currency_name);
        Transaction transaction = new Transaction(source_currency,target_currency,currencyValueFrom);
        transdao.persist(transaction);
    }

    public void addNewCurrency(String name, String abbr_name, double rate_to_usd){

        cvtdao.persist(new Currency(name,abbr_name,rate_to_usd));
    }

    public static void main(String[] args) {

        ConverterDao cvtdao = new ConverterDao();
        TransactionDAO transdao = new TransactionDAO();

        Currency JPY = new Currency("JPY","Japanese Yen",0.0067);
        Currency EUR = new Currency("EUR","Euro",1.17);
        cvtdao.persist(JPY);
        cvtdao.persist(EUR);

        transdao.persist(new Transaction(JPY,EUR,1.0));
    }
}
