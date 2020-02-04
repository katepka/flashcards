package main;

import java.util.*;

public class FlashCards {
    public static void main(String[] args) {

        System.out.println("Input the number of cards:");
        Scanner scanner = new Scanner(System.in);
        int cardsNumber = scanner.nextInt();
        scanner.nextLine();
        
        Map<String, String> map = new TreeMap<>();

        // Fit the map of cards:
        int count = 1;
        while (count <= cardsNumber) {

            System.out.println("The card #" + count + ":");
            String term = "";
            boolean ready = false;

            while (!ready) {
                term = scanner.nextLine();
                if (map.containsKey(term)) {
                    System.out.println("The card \"" + term + "\" already exists. Try again:");
                    continue;
                }
                ready = true;
            }

            System.out.println("The definition of the card #" + count + ":");
            ready = false;
            while (!ready) {
                String def = scanner.nextLine();
                if (map.containsValue(def)) {
                    System.out.println("The definition \"" + def + "\" already exists. Try again:");
                    continue;
                } else {
                    map.put(term, def);
                    ready = true;
                    count++;
                }
            }

        }

        // Ask definitions:
        Set<String> terms = map.keySet();
        count = 1;
        for (String term : terms) {
            System.out.println("Print the definition of \"" + term + "\":");
            count++;
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase(map.get(term))) {
                System.out.println("Correct answer.");
            } else if(map.containsValue(answer)) {
                String rightTerm = "";
                for (String t : terms) {
                    if (answer.equalsIgnoreCase(map.get(t))) {
                        rightTerm = t;
                    }
                }
                System.out.println("Wrong answer. The correct one is \""
                        + map.get(term) + "\", you've just written the definition of \""
                        + rightTerm + "\".");
            } else {
                System.out.println("Wrong answer. The correct one is \"" + map.get(term) + "\".");
            }
        }

    }
}
