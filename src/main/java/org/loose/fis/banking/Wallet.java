package org.loose.fis.banking;

public class Wallet {
    private String username;
    private int funds;

    public Wallet ( String username){
        this.username = username;
        this.funds = 0;
    }

    public String getUsername (){
        return this.username;
    }
    public int getfunds (){
        return this.funds;
    }
    public void setfunds (int newfund){
        this.funds= newfund;
    }
}

