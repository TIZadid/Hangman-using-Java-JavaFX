package sample;

import java.io.*;
import java.util.ArrayList;
/**
 * An object of type WordList reads a list of words from a resource file
 * that is part of the same program and makes the words available.
 */
public class WordList {

    private ArrayList<String> words;  // the list of words.

    /**
     * Create a list of words by reading from a resource file.
     * @throws IllegalArgumentException if words can't be read from the resource file.
     */
    public WordList() {
        words = new ArrayList<String>();
        String txtfile;
        txtfile="wordlist.txt";
        String path = txtfile;
        File file = new File(path);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            line = bufferedReader.readLine();

            while(!line.equals("0")){
                if (line.length() > 0)
                    words.add(line);
                line = bufferedReader.readLine();
                System.out.println(line);

            }
            if (words.size() == 0)
                throw new IllegalArgumentException("Nothing was found in the file " + txtfile);
            System.out.println(words.size() + " words read from " + txtfile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }



    /**
     * Returns the number of words in the list.
     */
    public int getWordCount() {
        return words.size();
    }

    /**
     * Returns a specified word from the list.
     * @param index the position of the word in the list, with positions numbered from 0 to getWordCount() - 1.
     * @throws IndexOutOfBoundsException if index is not in the range 0 to getWordCount() - 1.
     */
    public String getWord(int index) {
        return words.get(index);
    }

    /**
     * Returns a specified word from the list, and removes that word from the list so it can't
     * be fetched again.  The size of the list decreases by one.
     * @param index the position of the word in the list, with positions numbered from 0 to getWordCount() - 1.
     * @throws IndexOutOfBoundsException if index is not in the range 0 to getWordCount() - 1.
     */
    public String removeWord(int index) {
        return words.remove(index);
    }

    /**
     * Returns a randomly selected word from the list of words, and removes it from 
     * the list so that it will not be returned again.
     */
    public String removeRandomWord() {
        int index = (int)(getWordCount()*Math.random());
        return removeWord(index);
    }

}