package model;

import java.util.HashMap;

public class Dictionary {

    HashMap<String,String> dict = new HashMap<>();

    public void add(String key, String value){
        dict.put(key,value);
    }

    public String retrieve(String key){

        if (dict.containsKey(key)){
        return dict.get(key);} else if (key.equals("")) {
            return "Empty Key";
        }
        return "Word Not Found";
    }
}
