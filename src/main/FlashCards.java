package main;

import java.io.*;
import java.util.*;

public class FlashCards {

    private static Map<String, String> map = new HashMap<>();
    private static List<String> lines = new ArrayList<>();
    private static Map<String, Integer> errorsCards = new HashMap<>();

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equals("-import")) {
            importFromFile(args[1]);
        } else if (args.length > 2 && args[2].equals("-import")) {
            importFromFile(args[3]);
        }

        try (Scanner scanner = new Scanner(System.in)) {
            String command = "";
            while (!command.equalsIgnoreCase("exit")) {
                String menu = "Input the action " +
                        "(add, remove, import, export, ask, log, hardest card, reset stats, exit):";
                System.out.println(menu);
                lines.add(menu);
                command = scanner.nextLine();
                lines.add(command);
                switch (command) {
                    case "add":
                        add();
                        break;
                    case "ask":
                        ask();
                        break;
                    case "remove":
                        remove();
                        break;
                    case "import":
                        importFromFile();
                        break;
                    case "export":
                        exportToFile();
                        break;
                    case "log":
                        log();
                        break;
                    case "hardest card":
                        hardestCard();
                        break;
                    case "exit":
                        System.out.println("Bye bye!");
                        if (args.length > 0 && args[0].equals("-export")) {
                            exportToFile(args[1]);
                        } else if (args.length > 2 && args[2].equals("-export")) {
                            exportToFile(args[3]);
                        }
                        System.exit(0);
                        break;
                    case "reset stats":
                        resetStats();
                        break;
                    default:
                        String unknownCommand = "Unknown command. Try again " +
                                "(add, remove, import, export, ask, log, hardest card, reset stats, exit):";
                        System.out.println(unknownCommand);
                        lines.add(unknownCommand);
                }
            }
        }
    }

    /**
     * saves the application log to the given file.
     * Save all lines that have been input in or output to the console to the file.
     * You can use a list to store the lines.
     */
    private static void log() {
        String fileName = "File name:";
        System.out.println(fileName);
        lines.add(fileName);

        Scanner scanner = new Scanner(System.in);
        String file = (scanner.nextLine());
        lines.add(file);

        try (FileWriter writer = new FileWriter(file)) {
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }
            String logSaved = "The log has been saved.";
            System.out.println(logSaved);
            lines.add(logSaved);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        lines.add("\n");
    }


    /**
     * prints the term of the card that has the most mistakes.
     * You can store the mistake count in a map. If there are no cards with mistakes,
     * you should print There are no cards with errors.
     * And for multiple hardest cards, you should list them all
     */
    private static void hardestCard() {
        StringBuilder hardestCard = new StringBuilder();
        if (errorsCards.isEmpty()) {
            String noErrors = "There are no cards with errors.";
            System.out.println(noErrors);
            lines.add(noErrors);

        } else {
            int maxErrors = 0;

            for (String card : errorsCards.keySet()) {
                if (errorsCards.get(card) > maxErrors) {
                    maxErrors = errorsCards.get(card);
                }
            }

            List<String> hardestCardsList = new ArrayList<>();
            for (String card : errorsCards.keySet()) {
                if (errorsCards.get(card) == maxErrors) {
                    hardestCardsList.add(card);
                }
            }

            if (hardestCardsList.size() > 1 && maxErrors > 0) {
                hardestCard.append("The hardest cards are ");
                for (int i = 0; i < hardestCardsList.size() - 1; i++) {
                    hardestCard.append("\"").append(hardestCardsList.get(i)).append("\", ");
                }
                hardestCard.append("\"").append(hardestCardsList.get(hardestCardsList.size() - 1))
                                        .append("\". You have ")
                                        .append(maxErrors)
                                        .append(" errors answering them.");
                System.out.println(hardestCard.toString());
                lines.add(hardestCard.toString());

            } else if (maxErrors > 0) {
                for (String key : errorsCards.keySet()) {
                    hardestCard.append("The hardest card is \"")
                            .append(key)
                            .append("\". You have ")
                            .append(errorsCards.get(key))
                            .append(" errors answering it.");
                }
                System.out.println(hardestCard.toString());
                lines.add(hardestCard.toString());
            } else {
                String noErrors = "There are no cards with errors.";
                System.out.println(noErrors);
                lines.add(noErrors);
            }

            System.out.println();
            lines.add("\n");
        }
    }


    /**
     * erases the mistake counts for all cards
     */
    private static void resetStats() {
        errorsCards.clear();
        String reset = "Card statistics has been reset.";
        System.out.println(reset);
        lines.add(reset);

        System.out.println();
        lines.add("\n");
    }


    private static void exportToFile() {
        String fileName = "File name:";
        System.out.println(fileName);
        lines.add(fileName);

        Scanner scanner = new Scanner(System.in);
        String file = (scanner.nextLine());
        lines.add(file);

        try (FileWriter writer = new FileWriter(file)) {
            Set termsToSave = map.keySet();
            for (Object term : termsToSave) {
                writer.write(term.toString() + System.lineSeparator());
                writer.write(map.get(term) + System.lineSeparator());
                if (errorsCards.containsKey(term)) {
                    writer.write(errorsCards.get(term) + System.lineSeparator());
                } else {
                    writer.write("0" + System.lineSeparator());
                }
            }
            int saved = 0;
            if (!termsToSave.isEmpty()) {
                saved = termsToSave.size();
            }
            String cardsSaved = saved + " cards have been saved.";
            System.out.println(cardsSaved);
            lines.add(cardsSaved);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        lines.add("\n");
    }

    /**
     * @param file from command line
     */
    private static void exportToFile(String file) {
        try (FileWriter writer = new FileWriter(file)) {
            Set termsToSave = map.keySet();
            for (Object term : termsToSave) {
                writer.write(term.toString() + System.lineSeparator());
                writer.write(map.get(term) + System.lineSeparator());
                if (errorsCards.containsKey(term)) {
                    writer.write(errorsCards.get(term) + System.lineSeparator());
                } else {
                    writer.write("0" + System.lineSeparator());
                }
            }
            int saved = 0;
            if (!termsToSave.isEmpty()) {
                saved = termsToSave.size();
            }
            String cardsSaved = saved + " cards have been saved.";
            System.out.println(cardsSaved);
            lines.add(cardsSaved);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        lines.add("\n");
    }


    private static void importFromFile() {
        String fileName = "File name:";
        System.out.println(fileName);
        lines.add(fileName);

        Scanner scanner = new Scanner(System.in);
        File file = new File(scanner.nextLine());
        lines.add(file.toString());

        try (Scanner reader = new Scanner(file)) {
            int loaded = 0;
            while (reader.hasNext()) {
                String term = reader.nextLine();
                String def = reader.nextLine();
                String errors = reader.nextLine();
                map.put(term, def);
                errorsCards.put(term, Integer.parseInt(errors));

                loaded++;
            }
            String loadedCards = loaded + " cards have been loaded.";
            System.out.println(loadedCards);
            lines.add(loadedCards);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println();
        lines.add("\n");
    }

    /**
     * @param fileName from command line
     */
    private static void importFromFile(String fileName) {
        File file = new File(fileName);
        lines.add(file.toString());

        try (Scanner reader = new Scanner(file)) {
            int loaded = 0;
            while (reader.hasNext()) {
                String term = reader.nextLine();
                String def = reader.nextLine();
                String errors = reader.nextLine();
                map.put(term, def);
                errorsCards.put(term, Integer.parseInt(errors));

                loaded++;
            }
            String loadedCards = loaded + " cards have been loaded.";
            System.out.println(loadedCards);
            lines.add(loadedCards);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println();
        lines.add("\n");
    }

    /**
     * Ask the user a card to remove.
     * If the card is there it will be removed
     * else a message appears.
     */
    private static void remove() {
        String theCard = "The card:";
        System.out.println(theCard);
        lines.add(theCard);

        Scanner scanner = new Scanner(System.in);
        String card = scanner.nextLine();
        lines.add(card);

        if (map.remove(card, map.get(card))) {
            String removed = "The card has been removed.";
            System.out.println(removed);
            lines.add(removed);
            errorsCards.remove(card);

        } else {
            String cantRemove = "Can't remove \"" + card + "\": there is no such card.";
            System.out.println(cantRemove);
            lines.add(cantRemove);
        }
        System.out.println();
        lines.add("\n");
    }

    /**
     * Add a new card to the map:
     * if a term or a definition is already there, it ask the user
     * to text it again.
     */
    private static void add() {
        System.out.println("The card:");
        Scanner scanner = new Scanner(System.in);
        String term;
        term = scanner.nextLine();
        lines.add(term);

        if (map.containsKey(term)) {
            String exists = "The card \"" + term + "\" already exists.";
            System.out.println(exists);
            lines.add(exists);
            return;
        }
        String definition = "The definition of the card:";
        System.out.println(definition);
        lines.add(definition);

        String def = scanner.nextLine();
        lines.add(def);
        if (map.containsValue(def)) {
            String exists = "The definition \"" + def + "\" already exists.";
            System.out.println(exists);
            lines.add(exists);

        } else {
            map.put(term, def);
            errorsCards.put(term, 0);
            String pair = "The pair (\"" + term + "\":\"" + def + "\") has been added.";
            System.out.println(pair);
            lines.add(pair);

            System.out.println();
            lines.add("\n");
        }

    }

    /**
     * Ask the user how many times does he want to be asked
     * and ask the number of random cards.
     * If the answer is wrong it says the correct answer
     * and if the definition is right for another term it says the right term too.
     */
    private static void ask() {
        System.out.println("How many times to ask?");
        Scanner scanner = new Scanner(System.in);
        String timesToAskString = scanner.nextLine();
        int timesToAsk = Integer.parseInt(timesToAskString);
        lines.add(timesToAskString);

        Random random = new Random();
        Object[] terms = map.keySet().toArray();
        int len = terms.length;

        while (timesToAsk > 0) {
            Object term;
            if (len > 1) {
                term = terms[random.nextInt(len - 1)];
            } else {
                term = terms[random.nextInt(len)];
            }

            String question = "Print the definition of \"" + term.toString() + "\":";
            System.out.println(question);
            lines.add(question);

            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase(map.get(term))) {
                String correct = "Correct answer.";
                System.out.println(correct);
                lines.add(correct);

            } else if (map.containsValue(answer)) {
                Object rightTerm = "";
                for (Object t : terms) {
                    if (answer.equalsIgnoreCase(map.get(t))) {
                        rightTerm = t;
                    }
                }
                String wrong = "Wrong answer. The correct one is \""
                        + map.get(term) + "\", you've just written the definition of \""
                        + rightTerm.toString() + "\".";
                System.out.println(wrong);
                lines.add(wrong);

                if (errorsCards.containsKey(term)) {
                    errorsCards.put(term.toString(), errorsCards.get(term) + 1);
                } else {
                    errorsCards.put(term.toString(), 1);
                }

            } else {
                String wrong = "Wrong answer. The correct one is \"" + map.get(term) + "\".";
                System.out.println(wrong);
                lines.add(wrong);

                if (errorsCards.containsKey(term)) {
                    errorsCards.put(term.toString(), errorsCards.get(term) + 1);
                } else {
                    errorsCards.put(term.toString(), 1);
                }
            }
            timesToAsk--;
        }
        System.out.println();
        lines.add("\n");
    }
}
