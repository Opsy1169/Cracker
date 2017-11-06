package com.company.Exceptions;

/**
 * Created by Opsymonroe on 30.09.2017.
 */
public class InvalidSpaceAreaException extends IllegalArgumentException {
    public InvalidSpaceAreaException(String s){
        super(s);
    }
}
