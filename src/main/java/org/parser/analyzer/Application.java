package org.parser.analyzer;

import java.io.Console;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> countedWords;
        Iterator iterator;
        Analyzer analyzer;
        Parser parser;
        String url;
        String content ="";


        parser = new Parser();
        while (true)  {
            System.out.println("\n Enter the Page's URL");
            url = scanner.nextLine();
            try {
                parser.DownloadPage(url, "Downloaded.html");
                break;
            } catch (IOException e) {
                System.out.print("There is something wrong with your link. \n Type 'q' to exit \n Press any key... ");
                if (scanner.nextLine().equals("q")) return;
            }
        }
        System.out.println("Downloaded!");
        System.out.println("Parsing page...");
        while (true) {
            try {
                content = parser.Parse("Downloaded.html");
                break;
            } catch (IOException e) {
                System.out.print("There is something wrong with your downloaded file. \n Type 'q' to exit \n Press any key... ");
                if (scanner.nextLine().equals("q")) return;
            }
            System.out.println(content);
            System.out.println("Counting...");
        }
        analyzer = new Analyzer();
        countedWords = analyzer.CountWords(content);
        iterator = countedWords.entrySet().iterator();

        System.out.println("\n Words | Count");
        while (iterator.hasNext()){
            Map.Entry<String, Integer> element = (Map.Entry<String, Integer>)iterator.next();

            System.out.println(element.getKey() +"-"+element.getValue());
        }
    }
}
