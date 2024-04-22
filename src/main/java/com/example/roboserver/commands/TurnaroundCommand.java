package com.example.roboserver.commands;

import com.example.roboserver.models.RobotModel;
import com.example.roboserver.services.RobotMovementService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TurnaroundCommand implements Command {
    private RobotMovementService robotMovementService;

    @Override
    public void apply(RobotModel robotModel) {
        robotMovementService.rotate180(robotModel);
    }
}
