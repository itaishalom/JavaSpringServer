package com.example.roboserver.utils.algebra;

import com.example.roboserver.models.OrientationNames;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class OrientationVector extends Vector {
    String name;

    @Override
    public String toString() {
        return name;
    }

    public OrientationVector(Vector vector) {
        super(vector.getX(), vector.getY());
        name = getName(vector);
    }

    public OrientationVector(OrientationVector vector) {
        super(vector.getX(), vector.getY());
        this.name = vector.name;
    }

    private OrientationVector(int x, int y, String name) {
        super(x, y);
        this.name = name;
    }

    public static OrientationVector getOrientationVector(String name) {
        return switch (name) {
            case OrientationNames.NORTH -> new OrientationVector(vectorNorth);
            case OrientationNames.SOUTH -> new OrientationVector(vectorSouth);
            case OrientationNames.EAST -> new OrientationVector(vectorEast);
            case OrientationNames.WEST -> new OrientationVector(vectorWest);
            default -> null; // should not happen
        };
    }

    private String getName(Vector vector) {
        String name = "Unknown orientation";
        if (vector.equals(vectorWest)) {
            name = OrientationNames.WEST;
        } else if (vector.equals(vectorEast)) {
            name = OrientationNames.EAST;
        } else if (vector.equals(vectorNorth)) {
            name = OrientationNames.NORTH;
        } else if (vector.equals(vectorSouth)) {
            name = OrientationNames.SOUTH;
        }
        return name;
    }

    private final static OrientationVector vectorNorth = new OrientationVector(0, -1, OrientationNames.NORTH);
    private final static OrientationVector vectorSouth = new OrientationVector(0, 1, OrientationNames.SOUTH);
    private final static OrientationVector vectorEast = new OrientationVector(1, 0, OrientationNames.EAST);
    private final static OrientationVector vectorWest = new OrientationVector(-1, 0, OrientationNames.WEST);

}
