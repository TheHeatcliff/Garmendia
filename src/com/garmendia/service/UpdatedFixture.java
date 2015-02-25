package com.garmendia.service;

import java.util.ArrayList;
import java.util.Map;

import com.garmendia.domain.Match;

public class UpdatedFixture {

	private static UpdatedFixture mInstance = null;
 
    private Map<String,ArrayList<Match>> fixture;
 
    private UpdatedFixture(){}
 
    public static UpdatedFixture getInstance(){
        if(mInstance == null) {
            mInstance = new UpdatedFixture();
        }
        return mInstance;
    }
 
    public Map<String,ArrayList<Match>> getFixture(){
        return this.fixture;
    }
 
    public void setFixture(Map<String,ArrayList<Match>> fixture){
        this.fixture = fixture;
    }
}
