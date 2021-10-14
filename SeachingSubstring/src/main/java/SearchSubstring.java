import java.io.*;
import java.nio.charset.StandardCharsets;
import java.lang.*;
import java.util.*;

public class SearchSubstring {

    /**
     * This function creates integer array which can help to find similar prefix and suffix
     * @param substr - substring
     * @return - integer array
     */
    public int [] pfl(char[] substr){
        int pfl[] = new int[substr.length];
        pfl[0] = 0;
        for (int i = 1;i < substr.length; ++i){
            int j = pfl[i - 1];
            while(substr[i] != substr[j] && j > 0)
                j = pfl[j - 1];
            if (substr[i] == substr[j])
                pfl[i] = j + 1;
            else pfl[i] = 0;
        }
        return pfl;
    }

    /**
     * This function find all indexes where substring begins in the buffer (pattern) and writes their in list.
     * @param pattern - substring which we should find in the taxt
     * @param text - string where we should find indexes which start substring
     * @param pfl - integer array which help define right moving
     * @param current_pos - it's number which show length all string where we find indexes
     * @param indexes - list where we write indexes
     * @param len_buffer - length buffer
     * @return integer array which contain indexes in text which we already show
     */
    public static ArrayList<Integer> indexesOf(char[] pattern, char[] text, int[] pfl,
                                               int current_pos, ArrayList<Integer> indexes, int len_buffer)
    {
        int k = 0;
        for (int i = 0; i < text.length; ++i)
        {
            while (pattern[k] != text[i] && k > 0)
                k = pfl[k - 1];
            if (pattern[k] == text[i])
            {
                k = k + 1;
                if (k == pattern.length)
                {
                    int index =  i + 1 - k + current_pos - len_buffer;
                    if (indexes.isEmpty() || index != indexes.get(indexes.size() - 1))
                        indexes.add(index);
                    k = pfl[k - 1];
                }
            }
            else k = 0;
        }
        return indexes;
    }


    /**
     * This function find all indexes where substring begins in the text
     * @param address - this string contains address file where we should take information
     * @param substring - it's a string. We use a substring that finds all occurrences of this string in the text
     * @return - indexes array
     * @throws Exception
     */
    public int []findIndexes(String address, String substring) throws FileNotFoundException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(address), StandardCharsets.UTF_8));
        if (substring.isEmpty())
            throw new FileNotFoundException("empty substring");
        int current_pos = 0;
        ArrayList<Integer> idx = new ArrayList<>();
        int[] pfl = pfl(substring.toCharArray());
        int len_buffer = substring.length();
        char[] buffer = new char[len_buffer];
        char[] last_buffer = new char[len_buffer];

        while (true) {
            try {
                if (!(reader.read(buffer) != -1))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String str = new String(last_buffer) + new String(buffer);
            idx = indexesOf(substring.toCharArray(), str.toCharArray(), pfl, current_pos, idx, len_buffer);
            current_pos += len_buffer;
            last_buffer = buffer.clone();
        }
        return idx.stream().mapToInt(i -> i).toArray();
    }
}

