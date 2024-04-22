package com.example.roboserver.utils.algebra;

public class MatrixOperations {
    private static final Matrix clockwiseMatrix = new Matrix(new Vector(0, -1), new Vector(1, 0));
    private static final Matrix counterClockwiseMatrix = new Matrix(new Vector(0, 1), new Vector(-1, 0));
    private static final Matrix rotate180Matrix = new Matrix(new Vector(-1, 0), new Vector(-1, 0));


    //// NOTE !! Top left is defined 0,0 so the matrices switched - clockwise - counterclockwise
    public static Vector rotateClockwise( Vector vector) {
        return counterClockwiseMatrix.multiply(vector);
    }

    //// NOTE !! Top left is defined 0,0 so the matrices switched - clockwise - counterclockwise
    public static Vector rotateCounterClockwise(  Vector vector) {
        return clockwiseMatrix.multiply(vector);
    }

    public static Vector rotate180(Vector vector) {
        return rotate180Matrix.multiply(vector);
    }

}
