package com.estbn.mediscreendiabete.service.httpRequests;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HttpRequestNotes {
    String baseUrlNotes = "http://localhost:9090/note/patient";

    @SneakyThrows
    public List<String> run(String familyName) {
        ObjectMapper mapper = new ObjectMapper();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(baseUrlNotes + "/miniNotes/" + familyName);
        List<String> response = client.execute(request, httpResponse ->
                mapper.readValue(httpResponse.getEntity().getContent(), List.class));
        return response;
    }
}
