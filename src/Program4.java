/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.*;
import java.util.LinkedList;

/**
 *
 * @author david young
 */
public class Program4 {
    MyLinkedList[] dictionary = new MyLinkedList[26];//create array that will store alphabet
    long wordsFound = 0;
    long wordsNotFound = 0;
    long comparisonsOfWordsFound = 0;
    long comparisonsOfWordsNotFound = 0;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Program4 ex = new Program4();
        ex.readTextFile();
        ex.readfile();
        ex.writeoutput();

    }

    /**
     *
     */
    public Program4() {
            for (int i = 0; i < dictionary.length; i++) {
                dictionary[i] = new MyLinkedList<>();
            }
    }

    /**
     *Read a text file into a linked list
     *  
     */
    public void readTextFile() {
        File f = new File("random_dictionary.txt");// create random dictionary file
        try {
            Scanner inf = new Scanner(f); 
            while (inf.hasNext()) {//read dictionary file
                String word = inf.nextLine().trim().toLowerCase();//assign new words to variable, trim and lowercase
                dictionary[word.charAt(0) - 97].add(word);//add new words to the linked alphabetically according to asci 
                
            }
            //dictionary['a' - 97].addFirst("a");
          //  dictionary['i' - 97].addFirst("i");
        //   dictionary['a' - 97].addLast("a");
          // dictionary['i' - 97].addLast("i");
            inf.close();
        } 
        
        
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     *Read a text file into a string parser
     */
    public void readfile() {
        int[] count = new int[1];//create accumulator 
       
        try (FileInputStream inf = new FileInputStream(new File("oliver.txt"))) {//create oliver file

            char let;

            String str = ""; //word to be processed

            int n = 0;

            while ((n = inf.read()) != -1) {//read oliver file

                let = (char) n;

                if (Character.isLetter(let)) {//if character is a letter make lovercase and add to string variable
                    str += Character.toLowerCase(let);
                }

                if ((Character.isWhitespace(let) || let == '-') && !str.isEmpty()) {//if character is a space or hyphen and empty send prior word to binary search
                    count[0]=0;
                    if (dictionary[(int) str.charAt(0) - 97].contains(str, count)){//compare the words in the oliver file to those in the dictionary
                        
                        wordsFound++;//increment words found
                        comparisonsOfWordsFound += count[0];//increase comparisons of words found
                        
                        
                    }else{
                        wordsNotFound++;//increment words not found
                        comparisonsOfWordsNotFound += count[0];//increase comparisons of words not found
                         
                    }
                     str = "";//clear string variable

                }
              
            }
            inf.close();
            
        } 
        catch (IOException e) {

            e.printStackTrace();

        }
        
    }

    /**
     *Print the program outputs
     */
    public void writeoutput()  {//print averages 

        System.out.printf("The average number of comparison words found " +
                 ": %4.4f \n " , (double)comparisonsOfWordsFound/wordsFound);
        
        System.out.printf("The average number of comparison words not found " +
                 ": %4.4f \n " , (double )comparisonsOfWordsNotFound/wordsNotFound);
    }
}
    


/*
Unaltered dictionary:
The average number of comparison words found : 3558.0711 
The average number of comparison words not found : 7381.3783 
 BUILD SUCCESSFUL (total time: 2 minutes 7 seconds)
*/

/*
Add first 'a' and 'i'
The average number of comparison words found : 3423.6923 
The average number of comparison words not found : 7245.5300 
 BUILD SUCCESSFUL (total time: 1 minute 19 seconds)

*/

/*
Add last 'a' and 'i'
The average number of comparison words found : 3423.6923 
The average number of comparison words not found : 7245.6055 
BUILD SUCCESSFUL (total time: 1 minute 21 seconds)

*/
