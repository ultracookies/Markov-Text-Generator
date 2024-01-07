package p1.model.texteditor_functions.markovtextgenerator;

import java.util.LinkedList;
import java.util.TreeMap;

public class Create {

    private String newTxt = "";
    private TreeMap<String, LinkedList<String>> parents;

    public Create(TreeMap<String, LinkedList<String>> parents) {
        this.parents = parents;
    }

    public void create(String keyWord, int numOfWords) {
        var word = keyWord;
        newTxt = keyWord;
        for (int i = 0; i < numOfWords; i++) {
            var currentList = parents.get(word);
            int b = rn(currentList);
            word = currentList.get(b);
            newTxt = newTxt.concat(" " + word);
        }
    }

    private int rn(LinkedList currentList) {
        return (int) (Math.random() * (currentList.size()));
    }

    public String getNewTxt() {
        return newTxt;
    }
}
