package com.takinite;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DefesaCivilAPI {
    
    private HttpClient client;
    private HttpRequest requestRios;
    private HttpRequest requestNivel;
    private ObjectMapper mapper;

    public DefesaCivilAPI(){
        this.mapper = new ObjectMapper();
        this.client = HttpClient.newHttpClient();
        this.requestRios = HttpRequest.newBuilder()
                                      .uri(URI.create("https://public.asthon.com.br/public/stations/list?city_id=4214805&_v=2"))
                                      .GET()
                                      .header("Accept", "application/json")
                                      .build();
        this.requestNivel = HttpRequest.newBuilder()
                              .uri(URI.create("https://public.asthon.com.br/public/stations/live?city_id=4214805&_v=2"))
                              .GET()
                              .header("Accept", "application/json")
                              .build();
    }

public Map<Integer, NivelRioDTO> getNiveisAsDTOs() {
    Map<Integer, NivelRioDTO> niveis = new HashMap<>();
    try {
        String responseRios = client.send(
            this.requestRios,
            HttpResponse.BodyHandlers.ofString()
        ).body();

        JsonNode jsonRios = mapper.readTree(responseRios);

        for (JsonNode rio : jsonRios) {
            String uuid = rio.get("station_id").asText();
            int id = UUID.fromString(uuid).hashCode();
            String nome = rio.get("name").asText();

            NivelRioDTO dto = new NivelRioDTO();
            dto.setRio(nome);
            dto.setIdRio(id);
            niveis.put(id, dto);
        }

        String responseNiveis = client.send(
            this.requestNivel,
            HttpResponse.BodyHandlers.ofString()
        ).body();

        JsonNode jsonNiveis = mapper.readTree(responseNiveis);
        for (JsonNode rio : jsonNiveis) {
            String uuid = rio.path("station_id").asText();
            int id = UUID.fromString(uuid).hashCode();
            Float nivel = Float.parseFloat(rio.path("level_m").asText("0.0"));

            NivelRioDTO dto = niveis.get(id);
            if(dto == null){
                continue;
            }
            dto.setNivel(nivel);
            niveis.put(id, dto);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException("Erro ao consultar estações", e);
    }

    return niveis;
}
}
