package com.example.roboserver.controllers;

import com.example.roboserver.utils.exceptions.InavlidLocationException;
import com.example.roboserver.models.RobotModel;
import com.example.roboserver.commands.Command;
import com.example.roboserver.services.CommandParser;
import com.example.roboserver.services.CommandExecutorService;
import com.example.roboserver.services.RobotMovementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api/robot")
@AllArgsConstructor
public class RobotController {

    private final CommandParser commandParser;
    private final CommandExecutorService commandExecutorService;


    @PostMapping("/execute")
    public ResponseEntity<?> executeCommands(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            RobotModel robotModel = getRobotModel(reader);
            return ResponseEntity.ok(robotModel);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (InavlidLocationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The robot location is invalid " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error processing file");
        }
    }

    private RobotModel getRobotModel(BufferedReader reader) throws IOException, InavlidLocationException {
        RobotModel robotModel = new RobotModel();
        String line;
        while ((line = reader.readLine()) != null) {
            Command command = commandParser.parseCommand(line);
            commandExecutorService.executeCommand(command, robotModel);
        }
        return robotModel;
    }
}
