package com.example.roboserver.services;

import com.example.roboserver.commands.Command;
import com.example.roboserver.commands.CommandsNames;
import com.example.roboserver.commands.CommandFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class CommandParser {

    private final RobotMovementService robotMovementService;
    // For testing and future uses.
    public ArrayList<Command> parseCommands(String input ) {
        String[] lines = input.split("\n");
        ArrayList<Command> commands = new ArrayList<>();
        for (String line : lines) {
            commands.add(parseCommand(line));
        }
        return commands;
    }

    public Command parseCommand(String line) {
        String[] parts = line.split(" ");
        CommandsNames command = CommandsNames.fromString(parts[0]);
        try {
            if (command != null) {
                return (CommandFactory.factory(command, parts, robotMovementService));
            } else {
                throw new IllegalArgumentException("Invalid command: " + parts[0]);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid command: " + parts[0] + " should be an integer");
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid command: " + parts[0]);

        }
    }

}