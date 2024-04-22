package com.example.roboserver.commands;

import com.example.roboserver.utils.exceptions.InavlidLocationException;
import com.example.roboserver.models.RobotModel;


public interface Command {
    void apply(RobotModel robotModel) throws InavlidLocationException;
}


