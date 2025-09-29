package application;

import dao.ConverterDao;
import entity.Currency;

import java.util.List;

public class ConverterApp {

    public static void main(String[] args) {

        ConverterDao cvtdao = new ConverterDao();

        List<Currency> currencies = cvtdao.getAllCurrencies();
        for (Currency crc : currencies){
            System.out.println(crc.getName()+ " " + crc.getRate());
        }
    }
}
