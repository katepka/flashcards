package main;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main{
    public static void main(String[] args) {
        File file = new File("dataset_91065.txt");
        int count = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                int next = Integer.parseInt(scanner.nextLine());
                if (next == 0) {
                    break;
                }
                if (next % 2 == 0) {
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println("Ooops... IOException");
        }
        System.out.println(count);

//        Scanner scanner = new Scanner(System.in);
//        // write your code here
//        int a =  scanner.nextInt();
//        int b = scanner.nextInt();
//        int n = scanner.nextInt();
//        int k = scanner.nextInt();

//        int minFromMaxRand = k - 1;
//        int resSeed = a;
//        int max = 0;
//        int maxRand = k - 1;
//
//        for (int seed = a; seed <= b; seed++) {
//            System.out.println("seed = " + seed);
//
//
//
//            maxRand = 0;
//            int count = 0;
//            Random random = new Random(seed);
//
//            while (count < n) {
//                int randNum = random.nextInt(k);
//                if (randNum > maxRand) {
//                    maxRand = randNum;
//
//                }
//                count++;
//            }
//
//
//            if (maxRand < minFromMaxRand) {
//                minFromMaxRand = maxRand;
//                resSeed = seed;
//                max = maxRand;
//            } else  if  (maxRand == minFromMaxRand) {
//                minFromMaxRand = maxRand;
//                max = maxRand;
//            }
//            System.out.println("maxRand = " + maxRand);
//            System.out.println("min = " + minFromMaxRand);
//
//        }
//        System.out.println(resSeed);
//        System.out.println(max);


//        Scanner scanner = new Scanner(System.in);
//        TreeMap<Integer, String> map = new TreeMap<>();
//        Arrays.stream(scanner.nextLine().split("\\s")).forEach(s -> {
//            String[] pair = s.split(":");
//            map.put(Integer.parseInt(pair[0]),pair[1]);
//        });
//        NavigableMap<Integer, String> res = MapUtils.getSubMap(map);
//        res.forEach((k, v) -> System.out.println(k + " : " + v));

//        Scanner scanner = new Scanner(System.in);
//        String numbers = scanner.nextLine();
//        Set<Integer> set = SetUtils.getSetFromString(numbers);
//        SetUtils.removeAllNumbersGreaterThan10(set);
//        set.forEach(e -> System.out.print(e + " "));

//        Scanner scanner = new Scanner(System.in);
//
//        String original = scanner.nextLine();
//        String result = scanner.nextLine();
//        String encoded = scanner.nextLine();
//        String decrypted = scanner.nextLine();
//
//        Map<Character, Character> chipher = new HashMap<>();
//
//        for (int i = 0; i < original.length(); i++) {
//            chipher.put(original.charAt(i), result.charAt(i));
//        }
//
//        for (int i = 0; i < encoded.length(); i++) {
//            System.out.print(chipher.get(encoded.charAt(i)));
//        }
//        System.out.println();
//
//        for (int i = 0; i < decrypted.length(); i++) {
//            for (Character key : chipher.keySet()) {
//                char value = chipher.get(key);
//                if (key != null) {
//                    if (decrypted.charAt(i) == value) {
//                        System.out.print(key);
//                    }
//                }
//
//            }
//        }


    }

    private static int convert(Double val) {
        if (val == null || val.isNaN()) {
            return 0;
        } else if (val.equals(Double.POSITIVE_INFINITY)) {
            return Integer.MAX_VALUE;
        } else if (val.equals(Double.NEGATIVE_INFINITY)) {
            return Integer.MIN_VALUE;
        } else {
            return val.intValue();
        }
    }
}

class SetUtils {

    public static Set<Integer> getSetFromString(String str) {
        // write your code here
        Set<String> setStr = new HashSet<>(Arrays.asList(str.split(" ")));
        Set<Integer> set = new HashSet<>();
        for (String s : setStr) {
            set.add(Integer.parseInt(s));
        }
        return set;
    }

    public static void removeAllNumbersGreaterThan10(Set<Integer> set) {
        // write your code here
        set.removeIf(integer -> integer > 10);
    }

}

class MapUtils {

    public static NavigableMap<Integer, String> getSubMap(NavigableMap<Integer, String> map){
        NavigableMap<Integer, String> result = new TreeMap<>();
        if (map.firstKey() % 2 != 0) {
            // return sub-map from First Key inclusive to FirstKey+4 inclusive in descending order
            result = map.subMap(map.firstKey(), true, map.firstKey() + 4, true);
            return result.descendingMap();
        } else {
            // return sub-map from LastKey-4 inclusive to the Last Key inclusive in descending order
            result = map.subMap(map.lastKey() - 4, true, map.lastKey(), true);
            return result.descendingMap();
        }
    }

}

