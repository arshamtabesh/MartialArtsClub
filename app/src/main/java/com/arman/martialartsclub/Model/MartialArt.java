package com.arman.martialartsclub.Model;

public class MartialArt {
    private int martialArtID;
    private String martialArtName;
    private double martialArtPrice;
    private String martialArtColor;

    public MartialArt(int martialArtID, String martialArtName, double martialArtPrice, String martialArtColor) {
        this.martialArtID = martialArtID;
        this.martialArtName = martialArtName;
        this.martialArtPrice = martialArtPrice;
        this.martialArtColor = martialArtColor;
    }

    public MartialArt(String martialArtName, double martialArtPrice, String martialArtColor) {
        this.martialArtID = 0;
        this.martialArtName = martialArtName;
        this.martialArtPrice = martialArtPrice;
        this.martialArtColor = martialArtColor;
    }

    public int getMartialArtID() {
        return martialArtID;
    }

    public void setMartialArtID(int martialArtID) {
        this.martialArtID = martialArtID;
    }

    public String getMartialArtName() {
        return martialArtName;
    }

    public void setMartialArtName(String martialArtName) {
        this.martialArtName = martialArtName;
    }

    public double getMartialArtPrice() {
        return martialArtPrice;
    }

    public void setMartialArtPrice(int martialArtPrice) {
        this.martialArtPrice = martialArtPrice;
    }

    public String getMartialArtColor() {
        return martialArtColor;
    }

    public void setMartialArtColor(String martialArtColor) {
        this.martialArtColor = martialArtColor;
    }

    @Override
    public String toString() {
        return  getMartialArtID()+" " +
                getMartialArtName() +" " +
                getMartialArtPrice() + " "+
                getMartialArtColor() + " ";
    }
}
