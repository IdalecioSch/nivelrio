package com.takinite;
import com.influxdb.client.*;
import com.influxdb.client.write.Point;

public class InfluxAPI {
    private final InfluxDBClient client;   
    private final WriteApiBlocking writeApi;

    public InfluxAPI() {
        this.client = InfluxDBClientFactory.create("http://10.1.0.4:8086", 
                                                    "xxxxxxxxxxxB_DwbdoxXUDxxxxxxxF_2_nUw==".toCharArray(), 
                                                    "Informality",
                                                    "CHEIAS");
        this.writeApi = client.getWriteApiBlocking();
    }

    public boolean doEnviarInfo(NivelRioDTO NivelDTO){
        writeApi.writePoint(toPoint(NivelDTO));
        return true;
    }

    private Point toPoint(NivelRioDTO NivelDTO){
        return Point.measurement("nivelRio")
                    .addTag("IDRio", String.valueOf(NivelDTO.getIdRio()))
                    .addTag("rio", NivelDTO.getRio())
                    .addField("nivel", NivelDTO.getNivel());
    }

    public void close(){
        client.close();
    }
}
