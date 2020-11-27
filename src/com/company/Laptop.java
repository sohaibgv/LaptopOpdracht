package com.company;

public class Laptop {
    String merk;
    String type;
    int prijs;
    public Laptop( ){

    }
    public Laptop(String merkPassed,String typePassed, int prijsPassed ){
        merk = merkPassed;
        type = typePassed;
        prijs = prijsPassed;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrijs() {
        return prijs;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    @Override
    public String toString() {
        return  (merk + ' ' +
                 type + " $"+
                prijs + ' ' );
    }
}
