package com.hinamlist.hinam_list.service.json_scraper;

import com.hinamlist.hinam_list.service.json_scraper.exception.APIResponseException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Component
public abstract class AbstractJsonScraper implements IJsonScraper{
    protected HttpClient client;

    public AbstractJsonScraper() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        client = HttpClient.newBuilder().cookieHandler(cookieManager).build();
    }

    private HttpRequest.Builder createHttpBuilder(String uriString) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();
        try {
            requestBuilder.uri(new URI(uriString));
        } catch (URISyntaxException uriExp) { // uri string is incorrect
            throw (new RuntimeException("Target URI is not in the correct format", uriExp));
        }
        return requestBuilder;
    }

    protected HttpRequest createHttpGetRequest(String uriString) {
        var builder = createHttpBuilder(uriString);
        builder.GET();
        return builder.build();
    }

    protected HttpRequest createHttpPostRequest(String uriString, Map<String,String> propertyMap) {
        var builder = createHttpBuilder(uriString);
        builder.header("Content-Type", "application/json; charset=utf-8");
        JSONObject obj = new JSONObject(propertyMap);
        builder.POST(HttpRequest.BodyPublishers.ofString(obj.toString()));
        return builder.build();
    }

    protected String getResponse(HttpRequest request) throws IOException, InterruptedException, APIResponseException {

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int responseCode = response.statusCode();
        if (responseCode != 200) {
            throw (new APIResponseException(responseCode));
        }
        return response.body();
    }
}
