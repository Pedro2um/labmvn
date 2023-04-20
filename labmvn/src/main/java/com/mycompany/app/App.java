package com.mycompany.app;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.github.lalyos.jfiglet.FigletFont;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParseException, IOException
    {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=-20.27&longitude=-40.31&current_weather=true&forecast_days=1&timezone=America%2FSao_Paulo&quot;;";
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String respostaJSON = EntityUtils.toString(entity);
            System.out.println(respostaJSON);

            //String respostaJSON = "";
            JsonObject infoMeteo = JsonParser.parseString(respostaJSON).getAsJsonObject();
            JsonObject currentWeather = infoMeteo.getAsJsonObject("current_weather");
            System.out.println(currentWeather.get("temperature").getAsDouble());
            System.out.println(currentWeather.get("weathercode").getAsInt());

            String asciiArt1 = FigletFont.convertOneLine(
            currentWeather.get("temperature").getAsString()+"°C");
            System.out.println(asciiArt1);


            
        }

            

    }
}
