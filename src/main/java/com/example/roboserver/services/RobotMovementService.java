package com.example.roboserver.services;

import com.example.roboserver.utils.exceptions.InavlidLocationException;
import com.example.roboserver.utils.algebra.MatrixOperations;
import com.example.roboserver.utils.algebra.OrientationVector;
import com.example.roboserver.utils.algebra.Vector;
import com.example.roboserver.models.Grid;
import com.example.roboserver.models.RobotModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RobotMovementService {

    final Grid grid;

    public void rotateClockWise(RobotModel robotModel) {
        Vector newVector = MatrixOperations.rotateClockwise(robotModel.getOrientationVector());
        robotModel.setOrientationVector(new OrientationVector(newVector));
    }

    public void rotate180(RobotModel robotModel) {
        Vector newVector = MatrixOperations.rotate180(robotModel.getOrientationVector());
        robotModel.setOrientationVector(new OrientationVector(newVector));
    }

    public void rotateCounterClockWise(RobotModel robotModel) {
        Vector newVector = MatrixOperations.rotateCounterClockwise(robotModel.getOrientationVector());
        robotModel.setOrientationVector(new OrientationVector(newVector));
    }

    public void moveRobot(RobotModel robotModel, int distance) throws InavlidLocationException {
        try {
            Vector vector = robotModel.getLocationVector().findNewCoordinates(distance, robotModel.getOrientationVector());
            if (isValidPosition(vector)) {
                moveToCoordinates(vector, robotModel);
            } else {
                throw new InavlidLocationException();
            }
        }catch (NullPointerException e){
            throw new InavlidLocationException("Robot position not started");
        }
    }

    private boolean isValidPosition(Vector vector) {
        return grid.isValidCoordinate(vector);
    }

    private void moveToCoordinates(Vector vector, RobotModel robotModel) {
        robotModel.setLocationVector(vector);
    }

    public void putRobotInPosition(Vector vector, RobotModel robotModel, OrientationVector orientation) throws InavlidLocationException {
        if (isValidPosition(vector)) {
            moveToCoordinates(vector, robotModel);
            robotModel.setOrientationVector(orientation);
        } else {
            throw new InavlidLocationException();
        }
    }
}