package com.takinite;

import java.util.Date;

public class NivelRioDTO {

    private String rio;
    private float nivel;
    private Date data;
    private int idRio;

    public int getIdRio() {
        return idRio;
    }
    public void setIdRio(int idRio) {
        this.idRio = idRio;
    }

    public NivelRioDTO(){}

    public NivelRioDTO(String rio, float nivel, Date data, int idRio) {
        this.rio = rio;
        this.nivel = nivel;
        this.data = data;
        this.idRio = idRio;
    }
    public String getRio() {
        return rio;
    }
    public void setRio(String rio) {
        this.rio = rio;
    }
    public float getNivel() {
        return nivel;
    }
    public void setNivel(float nivel) {
        this.nivel = nivel;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    
}
