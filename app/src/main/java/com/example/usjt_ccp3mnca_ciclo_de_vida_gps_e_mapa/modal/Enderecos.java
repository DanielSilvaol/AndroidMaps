package com.example.usjt_ccp3mnca_ciclo_de_vida_gps_e_mapa.modal;

import java.io.Serializable;

public class Enderecos implements Serializable {

    private Double longetude;
    private Double latetude;

    public Enderecos(Double longetude, Double latetude) {
        setLongetude(longetude);
        setLatetude(latetude);
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
}
