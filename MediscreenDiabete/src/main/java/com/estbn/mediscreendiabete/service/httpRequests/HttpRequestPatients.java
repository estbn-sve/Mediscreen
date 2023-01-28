package com.estbn.mediscreendiabete.service.httpRequests;

import com.estbn.mediscreendiabete.entity.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

@Service
public class HttpRequestPatients {
    String baseUrlPatients = "http://localhost:8080/patient/";

    @SneakyThrows
    public Patient httpById(int id) {
        ObjectMapper mapper = new ObjectMapper();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(baseUrlPatients + "byId/" + id);
        Patient response = client.execute(request, httpResponse ->
                mapper.readValue(httpResponse.getEntity().getContent(), Patient.class));
        return response;
    }

    @SneakyThrows
    public Patient httpByFamilyName(String familyName) {
        ObjectMapper mapper = new ObjectMapper();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(baseUrlPatients + "byFamilyName/" + familyName);
        Patient response = client.execute(request, httpResponse ->
                mapper.readValue(httpResponse.getEntity().getContent(), Patient.class));
        return response;
    }
}
