
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author David
 */
public class AssignmentFive{
    
    /**
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String args[]) throws FileNotFoundException{
        BinarySearchTree BT = new BinarySearchTree();
        BinarySearchTree[] dictionary = new BinarySearchTree[26];
        int totalWords = 0;
        double correct = 0;
        double incorrect = 0;
        long compNotFound = 0;
        long compFound = 0;
        int[] counter = new int[1];
        
        //loads the array with linked lists
        for (int i = 0; i < dictionary.length; i++) {
            dictionary[i] = new BinarySearchTree<String>();
            
        }//for
       
        
        loadDict(dictionary);
        
        try {

            FileInputStream inFile = new FileInputStream(new File("oliver.txt"));

            char letter;
            String str = ""; //word to be processed
            int n = 0;

            while ((n = inFile.read()) != -1) {

                letter = (char) n;

                if (Character.isLetter(letter)) {// if the character is a letter
                    str += Character.toLowerCase(letter);// add the letter to a string as a lower case
                }//if
                //System.out.println(str);//echo
                if ((Character.isWhitespace(letter) || letter == '-') && !str.isEmpty()) {

                    totalWords++;

                    if (dictionary[str.charAt(0) - 97].search(str, counter) ) {
                        correct++;
                        compFound += counter[0];
                        
                    } else {
                        incorrect++;
                        compNotFound += counter[0];

                    }

                    str = "";//reset str to an empty string

                }//if

            }//while

            inFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }//catch
        
        //prints the information needed.
       System.out.println("Correct words found: " + correct);
       System.out.println("Incorrect words found: " + incorrect);
       System.out.println("Total words found: " + totalWords);
        System.out.println("There average number of words not found was: " + compFound/correct);
        System.out.println("The average number of words found was: " + compNotFound/incorrect);
        
    }//main
    
    /**
     *
     * @param list - the array of lists that will be loaded
     * @throws FileNotFoundException
     * requires - an array of lists
     * ensures- the array will be loaded with as in the first position, bs in the second and so on.
     * 
     * method that will load an array made of lists with the words from a dictionary file.
     */
    public static void loadDict(BinarySearchTree[] dictionary) throws FileNotFoundException{
        
        java.io.File file = new File("random_dictionary.txt");
        Scanner input = new Scanner(file);

        while (input.hasNext()) {
            String word = input.nextLine().toLowerCase();            
            dictionary[(int)word.charAt(0) -97].insert(word);
                       
        }//while
   }//loadDict
   
}//class
