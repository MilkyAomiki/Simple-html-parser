package org.web.page.parser;

import java.io.*;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Handle web page downloading and parsing
 */
public class Parser {

    public void DownloadPage(String url, String filePath) throws IOException {
        URL webPage = new URL(url);

        BufferedReader reader = new BufferedReader(new InputStreamReader(webPage.openStream()));
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        String content;

        while ((content = reader.readLine()) != null) {
            writer.write(content);
        }

        reader.close();
        writer.close();
    }

    public String Parse(String filePath) throws IOException {
        File storedPage = new File(filePath);
        Document page = Jsoup.parse(storedPage, "UTF-8", "");
        return page.text();
    }



}
