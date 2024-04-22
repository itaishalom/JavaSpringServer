package com.example.roboserver.utils.algebra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the position of a robot on the field
 */
@Setter
@Getter
@AllArgsConstructor
public class Vector {
    // Getters and Setters
    private final int x;
    private final int y;


    public Vector findNewCoordinates(int mul, OrientationVector orientationVector) {
        Vector result = Vector.dotProduct(orientationVector, mul);
        result = Vector.add(this, result);
        return result;
    }

    private static Vector dotProduct(Vector vector, int mul) {
        return new Vector(vector.x * mul, vector.getY() * mul);
    }

    private static Vector add(Vector vector1, Vector vector2) {
        return new Vector(vector1.getX() + vector2.getX(), vector1.getY() + vector2.getY());
    }


    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }


    @Override
    public boolean equals(Object other) {
        if (other instanceof Vector otherVector) {
            return this.x == otherVector.getX() && this.y == otherVector.getY();
        }
        return false;
    }
}

