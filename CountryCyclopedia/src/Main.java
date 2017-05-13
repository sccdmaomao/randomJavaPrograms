// Main for Country Cyclopedia
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // Step 0. Declare variables
    static ArrayList<String> countries = new ArrayList();
    static ArrayList<String> orderCountries = new ArrayList();

    public static void main(String[] args){

        // Step 1. Read txt file
        if (args.length <= 0) System.out.println("Usage: java Main PATH\\country.txt");
        else {
            try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    countries.addAll(new ArrayList<>(
                                        Arrays.asList(sCurrentLine.split(","))));
                }
            } catch (IOException e) {
                System.out.print("Error: unable to read file, make sure you provided correct path");
                e.printStackTrace();
            }
        }

        //System.out.print("Input countries read from file: " + countries + "\n");

        // Step 2. Sort read file in Lexicographical Order
        for (String country : countries) {
            orderCountries.add(sortLexicographically(country));
        }
        //System.out.print("sorted countries: " + orderCountries + "\n");

        // Step 3. Ask for user input
        promoteQuestion();
    }

    private static void promoteQuestion() {
        // Step 3. Ask for user input
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a word of your wish >:D");
        String word = input.next();
        String sorted = sortLexicographically(word);

        // Step 4. Linear Search to generate output
        boolean found = false;
        String countryName = null;
        for (int i=0; i<orderCountries.size(); i++) {
            if (orderCountries.get(i).equals(sorted)) {
                countryName = countries.get(i);
                found = true;
            }
        }
        if (found) {
            countryName = countryName.substring(0,1).toUpperCase()
                            .concat(countryName.substring(1));
            System.out.println("Yes, it is a country. It is \"" + countryName + "\"!");
        } else {
            System.out.println("No such country exists");
        }

        // Step 5. Repeat or quit
        System.out.println("Would you like to check again or quit?");
        if (input.next().equals("again")) {
            promoteQuestion();
        }

    }

    private static String sortLexicographically(String original) {
        char[] charArray = original.toCharArray();
        Arrays.sort(charArray);
        String sorted = new String(charArray);
        return sorted;
    }
}
