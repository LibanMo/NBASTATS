package com.example.dockerizespring.service;

import com.example.dockerizespring.models.Player;
import com.example.dockerizespring.models.PlayerStat;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Service
public class ScraperService {

@Autowired
PlayerService playerService;

    public String executeScript(String playerName) {
        System.out.println("hello scraperservice");
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("node", "src/main/java/com/example/dockerizespring/pupeteer/postAllstar.js", playerName);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            System.out.println("Exited with error code : " + exitCode);
            System.out.println(output.toString());
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<PlayerStat> getStats(String name){
        try{


            List<PlayerStat> result = new ArrayList<PlayerStat>();
            WebClient webClient = new WebClient();
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setThrowExceptionOnScriptError(false);


            HtmlPage page = webClient.getPage("https://www.basketball-reference.com/");
            HtmlInput input = page.getElementByName("search");
            input.setValueAttribute(name);
            HtmlSubmitInput button = page.querySelector("#header > div.search > form > input[type=submit]:nth-child(2)");
            HtmlPage resultPage = button.click();
            HtmlAnchor a = resultPage.querySelector("#players > div.search-item > div.search-item-name > strong > a");
            HtmlPage newPage = a.click();

            HtmlElement a2 = newPage.querySelector("#per_game\\.2024 > th > a");
            HtmlPage SeasonStats = a2.click();
            System.out.println(SeasonStats);


            // Select the table by its ID
            HtmlTable table = SeasonStats.getFirstByXPath("//*[@id=\"pgl_basic\"]");


            for (HtmlTableRow row : table.getRows().subList(1, table.getRows().size())) {

                String gs = row.getCell(8).asText();
                if(gs.equals("1") || gs.equals("0")){
                    String date = row.getCell(2).asText().trim();
                    String team = row.getCell(4).asText();
                    Integer reb = Integer.valueOf(row.getCell(row.getCells().size() - 10).asText());
                    Integer ast = Integer.valueOf(row.getCell(row.getCells().size() - 8).asText());
                    Integer stl = Integer.valueOf(row.getCell(23).asText());
                    Integer blk = Integer.valueOf(row.getCell(24).asText());
                    Integer pts = Integer.valueOf(row.getCell(row.getCells().size() - 3).asText());
                    PlayerStat playerStat = new PlayerStat(date, pts, reb, ast, stl, blk );
                    result.add(playerStat);

                }
            }
            webClient.close();
            System.out.println(result.size());
            playerService.SavePlayerStats(name, result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
