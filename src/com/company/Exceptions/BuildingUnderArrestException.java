package com.company.Exceptions;

import java.io.Serializable;

/**
 * Created by Opsymonroe on 04.11.2017.
 */
public class BuildingUnderArrestException extends Exception implements Serializable{
    public BuildingUnderArrestException(String s){
        super(s);
    }
}
