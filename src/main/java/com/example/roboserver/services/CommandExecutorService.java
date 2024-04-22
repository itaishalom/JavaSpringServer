package com.example.roboserver.services;

import com.example.roboserver.utils.exceptions.InavlidLocationException;
import com.example.roboserver.models.RobotModel;
import com.example.roboserver.commands.Command;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandExecutorService {

    public void executeCommands(List<Command> commands, RobotModel robotModel) throws InavlidLocationException {
        for (Command command : commands) {
            executeCommand(command, robotModel);
        }
    }

    public void executeCommand(Command command, RobotModel robotModel) throws InavlidLocationException {
        command.apply(robotModel);
    }
}