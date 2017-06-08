package pl.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        /*String[] langs = new String[4];
        langs[0] = "Java";
        langs[1] = "C#";
        langs[2] = "Python";
        langs[3] = "PHP";*/

        //String[] langs = {"Java", "C#", "Python", "PHP"};

        /*List<String> langs = new ArrayList<String>();
        langs.add("Java");
        langs.add("C#");
        langs.add("Python");
        langs.add("PHP");*/

        List<String> langs = Arrays.asList("Java", "C#", "Python", "PHP");

        /*for(int i = 0; i < langs.length; i++) {
            System.out.println("I want to learn " + langs[i]);
        }*/
        /*for(String l : langs) {
            System.out.println("I want to learn " + l);
        }*/

        for(int i = 0; i < langs.size(); i++) {
            System.out.println("I want to learn " + langs.get(i));
        }

    }
}
