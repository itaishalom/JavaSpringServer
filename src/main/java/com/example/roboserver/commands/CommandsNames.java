package com.example.roboserver.commands;

public enum CommandsNames {
    POSITION,
    FORWARD,
    WAIT,
    TURNAROUND,
    LEFT,
    RIGHT;

    public static CommandsNames fromString(String text) {
        for (CommandsNames command : CommandsNames.values()) {
            if (command.name().equalsIgnoreCase(text)) {
                return command;
            }
        }
        return null;
    }
}