package com.example.roboserver.commands;

import com.example.roboserver.utils.exceptions.InavlidLocationException;
import com.example.roboserver.models.RobotModel;
import com.example.roboserver.services.RobotMovementService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ForwardCommand implements Command {
    private final int distance;

    private final RobotMovementService robotMovementService;

    @Override
    public void apply(RobotModel robotModel) throws InavlidLocationException {
        robotMovementService.moveRobot(robotModel, distance);
    }
}
