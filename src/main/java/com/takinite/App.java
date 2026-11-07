package com.takinite;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
        InfluxAPI InfluxApi = new InfluxAPI();
        DefesaCivilAPI DefesaCivilApi = new DefesaCivilAPI();
        while(true){
            Map<Integer,NivelRioDTO> NiveisDTOs = DefesaCivilApi.getNiveisAsDTOs();
            NiveisDTOs.forEach((id, NivelDto) -> {
                InfluxApi.doEnviarInfo(NivelDto);
                System.out.println("Enviado Dados "+NivelDto.getRio()+": "+NivelDto.getNivel());
            });
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
