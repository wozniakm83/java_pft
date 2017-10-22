package pl.pft.sandbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringComparator {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter string A: ");
        String stringA = br.readLine();
        System.out.println("Enter string B: ");
        String stringB = br.readLine();
        br.close();

        int lengthA = stringA.length();
        int lengthB = stringB.length();
        if (lengthA >= lengthB) {
            String subStringA = new String();
            int count = 0;
            for(int i = 0; i < (lengthA - subStringA.length()); i++) {
                subStringA = stringA.substring(i, i+lengthB);
                if (subStringA.contains(stringB)) {
                    count++;
                }
            }
            System.out.println(String.format("String B is present within string A %s times", count));
        } else {
            System.out.println("String B doesn't appear within string A");
        }
    }
}


