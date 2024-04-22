package com.example.roboserver.commands;

import com.example.roboserver.utils.algebra.OrientationVector;
import com.example.roboserver.utils.algebra.Vector;
import com.example.roboserver.services.RobotMovementService;


public class CommandFactory {
    public static Command factory(CommandsNames command, String[] parts, RobotMovementService robotMovementService) {
        switch (command) {
            case POSITION:
                Vector vector = new Vector(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                OrientationVector orientation = OrientationVector.getOrientationVector(parts[3]);
                if (orientation == null) {
                    throw new IllegalArgumentException("Unknown orientation");
                }
                return (new PositionCommand(vector, orientation, robotMovementService));
            case FORWARD:
                int distance = Integer.parseInt(parts[1]);
                return (new ForwardCommand(distance, robotMovementService));
            case WAIT:
                return (new WaitCommand());
            case TURNAROUND:
                return (new TurnaroundCommand(robotMovementService));
            case LEFT:
                return (new LeftCommand(robotMovementService));
            case RIGHT:
                return new RightCommand(robotMovementService);
            default:
                return null; // never happens
        }
    }
}
