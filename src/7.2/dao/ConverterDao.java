package dao;

import entity.Currency;
import model.ConverterDB;
import java.sql.*;
import datasource.MariaDbConnection;
import java.util.*;

public class ConverterDao {

    public List<Currency> getAllCurrencies(){
        Connection conn = MariaDbConnection.getConnection();
        String sql = "SELECT abbr_name, rate_to_usd FROM CURRENCY";
        List<Currency> currencies = new ArrayList<Currency>();

        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()){
                String abbr_name = rs.getString(1);
                Double rate_to_usd = rs.getDouble(2);

                Currency currency = new Currency(abbr_name,rate_to_usd);
                currencies.add(currency);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return currencies;
    }

}
