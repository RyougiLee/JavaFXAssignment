package controller;
import model.Dictionary;
import view.DictionaryView;

public class DictionaryController {

    private Dictionary dict = new Dictionary();
    private DictionaryView view;
    private String result;
    private String searchContent;

    public DictionaryController(DictionaryView view){
        this.view = view;
        dict.add("test1","result1");
        dict.add("test2","result2");
        dict.add("test3","result3");
    }

    public void search(){
        searchContent = view.getSearchContent();
        result = dict.retrieve(searchContent);
        view.setResult(result);
    }

}
