package com.example.roboserver.commands;

import com.example.roboserver.models.RobotModel;
import com.example.roboserver.services.RobotMovementService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LeftCommand implements Command {
    private RobotMovementService robotMovementService;

    @Override
    public void apply(RobotModel robotModel) {
        robotMovementService.rotateCounterClockWise(robotModel);
    }
}
