package org.web.page;

import org.web.page.analyzer.Analyzer;
import org.web.page.parser.Parser;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Main class of the program
 */
public class Application {
    public static void main(String[] args)
    {
        String fileName = "downloaded.html";

        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> countedWords;
        Iterator<Map.Entry<String, Integer>> iterator;
        Analyzer analyzer;
        Parser parser;
        String url;
        String content ="";

        //Getting an URL and downloading a page
        parser = new Parser();

        url = args.length > 0?  args[0]: null;
        while (true)  {

            if(url==null){
                System.out.println("\nEnter a page's URL");
                url = scanner.nextLine();
            }else System.out.println("Given URL: " + url);

            try {
                parser.DownloadPage(url,fileName);
                break;
            } catch (IOException e) {
                System.out.print("There is something wrong with your link. \n Type 'q' to exit \n Press enter... ");
                if (scanner.nextLine().equals("q")) return;
                url=null;
            }
        }
        System.out.println("Downloaded");

        //Parsing the page
        System.out.println("Parsing the page...");
        while (true) {
            try {
                content = parser.Parse("Downloaded.html");
                break;
            } catch (IOException e) {
                System.out.print("There is something wrong with your downloaded file. \n Type 'q' to exit \n Press enter... ");
                if (scanner.nextLine().equals("q")) return;
            }
            System.out.println(content);
            System.out.println("Counting...");
        }

        //Counting words
        analyzer = new Analyzer();
        countedWords = analyzer.CountWords(content);
        iterator = countedWords.entrySet().iterator();

        System.out.println("\nWords | Count");
        while (iterator.hasNext()){
            Map.Entry<String, Integer> element = iterator.next();
            System.out.println(element.getKey() +"-"+element.getValue());
        }

        System.out.print("Press enter...");
        scanner.nextLine();
    }
}
