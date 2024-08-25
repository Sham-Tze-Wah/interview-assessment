package org.sham.twoFiveAugust2024;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReadingAPI {
    public static void readFile(String filePath){
        Map<String, Integer> allKeywords = new HashMap<>();

        if (!filePath.endsWith(".txt")) {
            System.out.println("The file type is not supported. Please provide a .txt file.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] splittedTexts = line.split(" ");
                for (String splittedText : splittedTexts) {
                    if (splittedText != null) {
                        if (!allKeywords.containsKey(splittedText.toLowerCase())) {
                            allKeywords.put(splittedText.toLowerCase(), 1);
                        } else {
                            allKeywords.put(splittedText.toLowerCase(), allKeywords.get(splittedText.toLowerCase()) + 1);
                        }
                    }
                }
            }
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
            System.out.println(ioEx.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        // Convert the entries of the map to a list
        List<Map.Entry<String, Integer>> topTenKeywordList = new ArrayList<>(allKeywords.entrySet());

        // Sort the list based on values in descending order
        topTenKeywordList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

//		List<Map.Entry<String, Integer>> topTenKeywords = topTenKeywordList.subList(0, Math.min(10, topTenKeywordList.size()));

        // Print the top 10 entries
        int i = 0;
        for (Map.Entry<String, Integer> entry : topTenKeywordList) {
            if(i < 10){
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
            ++i;
        }
    }
}
