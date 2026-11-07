package com.takinite;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
        InfluxAPI influxApi = new InfluxAPI();
        while(true){
            Map<Integer,NivelRioDTO> NiveisDTOs = (new DefesaCivilAPI()).getNiveisAsDTOs();
            NiveisDTOs.forEach((id, NivelDto) -> {
                influxApi.doEnviarInfo(NivelDto);
                System.out.println("Enviado Dados "+NivelDto.getRio()+": "+NivelDto.getNivel());
            });
            NiveisDTOs.clear();
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
