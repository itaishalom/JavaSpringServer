package com.example.roboserver.utils.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InavlidLocationException extends Exception{
    public InavlidLocationException(String message){
        super(message);
    }
}
