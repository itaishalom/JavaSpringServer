package com.example.roboserver.services;


import com.example.roboserver.commands.*;
import com.example.roboserver.utils.exceptions.InavlidLocationException;
import com.example.roboserver.utils.algebra.OrientationVector;
import com.example.roboserver.utils.algebra.Vector;
import com.example.roboserver.models.Grid;
import com.example.roboserver.models.RobotModel;
import com.example.roboserver.models.OrientationNames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RobotMovementServiceTest {

    private RobotMovementService robotMovementService;

    @BeforeEach
    void setUp() {
        Grid grid = new Grid(10, 10);
        robotMovementService = new RobotMovementService(grid);
    }

    @Test
    void testMoveRobot_validMove() throws InavlidLocationException {
        RobotModel robotModel = new RobotModel(new Vector(5, 5), OrientationVector.getOrientationVector(OrientationNames.NORTH));
        int distance = 3;
        robotMovementService.moveRobot(robotModel, distance);
        assertEquals(5, robotModel.getLocationVector().getX());
        assertEquals(2, robotModel.getLocationVector().getY());
    }


    @Test
    void testMoveRobot_invalidMove() {
        RobotModel robotModel = new RobotModel(new Vector(9, 9), OrientationVector.getOrientationVector(OrientationNames.EAST));
        int distance = 2;
        assertThrows(InavlidLocationException.class, () -> robotMovementService.moveRobot(robotModel, distance));
    }

    @Test
    void testPutRobotInPosition_validPosition() throws InavlidLocationException {
        RobotModel robotModel = new RobotModel();
        Vector newVector = new Vector(3, 3);
        OrientationVector orientation = OrientationVector.getOrientationVector(OrientationNames.SOUTH);

        robotMovementService.putRobotInPosition(newVector, robotModel, orientation);

        assertEquals(3, robotModel.getLocationVector().getX());
        assertEquals(3, robotModel.getLocationVector().getY());
        assertEquals(orientation, robotModel.getOrientationVector());
    }

    @Test
    void testPutRobotInPosition_invalidPosition() {
        RobotModel robotModel = new RobotModel();
        Vector newVector = new Vector(11, 11);
        OrientationVector orientation = OrientationVector.getOrientationVector(OrientationNames.SOUTH);

        assertThrows(InavlidLocationException.class, () -> robotMovementService.putRobotInPosition(newVector, robotModel, orientation));
    }

    @Test
    void testMoveRobot_validMove_withInstructions() throws InavlidLocationException {
        RobotModel robotModel = new RobotModel();
        Vector newVector = new Vector(3, 3);
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(new PositionCommand(newVector, OrientationVector.getOrientationVector(OrientationNames.SOUTH), robotMovementService));
        commands.add(new ForwardCommand(3, robotMovementService));
        commands.add(new LeftCommand(robotMovementService));
        commands.add(new ForwardCommand(1, robotMovementService));
        commands.add(new TurnaroundCommand(robotMovementService));
        commands.add(new ForwardCommand(2, robotMovementService));
        commands.add(new RightCommand(robotMovementService));
        commands.add(new ForwardCommand(3, robotMovementService));
        CommandExecutorService commandExecutorService = new CommandExecutorService();
        commandExecutorService.executeCommands(commands, robotModel);

         assertEquals(new Vector(2,3), robotModel.getLocationVector());
        assertEquals(OrientationVector.getOrientationVector(OrientationNames.NORTH), robotModel.getOrientationVector());
    }


}
