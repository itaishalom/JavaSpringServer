package com.example.roboserver.models;

import com.example.roboserver.utils.algebra.Vector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@Data
@Component
@AllArgsConstructor
@ConfigurationProperties(prefix = "grid")
public class Grid {
    private int gridWidth;
    private int gridHeight;

    public boolean isValidCoordinate(Vector vector) {
        return !(vector.getX() < 0 || vector.getX() > gridWidth || vector.getY() < 0 || vector.getY() > gridHeight);
    }
}

