package p1.model.user_functions;

import java.io.Serializable;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class UserStorage implements Serializable {
    //use TreeSet ****


//    public static int countSyllables(String word) {
//        int num = 0;
//        var pattern = "[AEIOUYaeiouy]+";
//        var tokenSplitter = Pattern.compile(pattern);
//        var matcher = tokenSplitter.match(word);
//
//        var lastToken = "";
//        while (matcher.find()) {
//        num++;
//        lastToken = matcher.group();
//        System.out.println(lastToken);
//        }
//        if (lastToken.equals("e") && num > 1 && word.charAt(world.length()-1 == 'e')) {
//            num--;
//        }
//        return num;
//    }
//     */

    //"[^.?!]+" Pattern tokenSplitter = Pattern.compile("[^.?!]+");
    //Matcher m = tokenSplitter.match(str);
    //ask prof chen about how binary is implemented in tokenSplitter
    //while(m.find()) { num++; }

    private static TreeMap<String, User> userStorage = new TreeMap<>();

    private UserStorage() {}

    public static TreeMap<String, User> getUserStorage() {
        return userStorage;
    }
}
