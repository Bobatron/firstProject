package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class TextProcessorUtil {

    static int[] syllableCounts;
    static String [] extractedWords;
    static String inputText;

    static final char[] VOWELS = new char[]{'a','e','i','o','u'};

    public static int[] getSyllableCounts(String pathToTextFile) throws IOException {
        inputText = new String (Files.readAllBytes(Paths.get(pathToTextFile))).replaceAll("\r\n", " ").replaceAll("\n", " ");
        extractedWords = inputText.split(" ");
        System.out.println(Arrays.toString(extractedWords));
        syllableCounts = new int[extractedWords.length];

        for (int i = 0; i < extractedWords.length; i++) {
            syllableCounts[i] = syllableCounter(extractedWords[i]);
        }
        return syllableCounts;
    }

    public static int syllableCounter(String word){
        int count = 0;
        for(int wordPos = 0; wordPos < word.length(); wordPos++){
            if(isVowel(word.charAt(wordPos))){
                if(wordPos > 0 && isVowel(word.charAt(wordPos-1))){
                   continue;
                }
                count++;
            }
        }
        return count > 0 ? count : 1;
    }

    public static boolean isVowel(char letter){
        for (char vowel : VOWELS) {
            if(vowel == letter)
                return true;
        }
        return false;
    }
}
