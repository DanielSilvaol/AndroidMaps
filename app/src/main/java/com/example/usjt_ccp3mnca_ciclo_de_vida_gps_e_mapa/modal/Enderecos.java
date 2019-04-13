package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.modal;

import java.io.Serializable;

public class Enderecos implements Serializable {

    private int id;
    private Double longetude;
    private Double latetude;

    public Enderecos(double latitude, double longitude){
        this.latetude = longitude;
        this.longetude = longitude;
    }
    public Enderecos(Integer id,Double latetude, Double longetude) {
        setLongetude(longetude);
        setLatetude(latetude);
        setId(id);
    }

    public Double getLongetude() {
        return longetude;
    }

    public void setLongetude(Double longetude) {
        this.longetude = longetude;
    }

    public Double getLatetude() {
        return latetude;
    }

    public void setLatetude(Double latetude) {
        this.latetude = latetude;
    }

    @Override
    public String toString() {
        return "Lat" + longetude +
                ", Log=" + latetude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
