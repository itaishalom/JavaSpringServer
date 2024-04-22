package com.example.roboserver.commands;

import com.example.roboserver.utils.exceptions.InavlidLocationException;
import com.example.roboserver.utils.algebra.OrientationVector;
import com.example.roboserver.utils.algebra.Vector;
import com.example.roboserver.models.RobotModel;
import com.example.roboserver.services.RobotMovementService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PositionCommand implements Command {
    private Vector vector;
    private OrientationVector orientationVector;
    private RobotMovementService robotMovementService;


    @Override
    public void apply(RobotModel robotModel) throws InavlidLocationException {
        robotMovementService.putRobotInPosition(vector, robotModel, orientationVector);
    }
}
