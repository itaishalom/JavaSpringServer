package com.example.roboserver.utils.algebra;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Matrix {
    Vector colOne;
    Vector colTwo;

    public Vector multiply(Vector vector) {

        int x = (this.colOne.getX() * vector.getX() + this.colTwo.getX() * vector.getY());
        int y = (this.colOne.getY() * vector.getX() + this.colTwo.getY() * vector.getY());
        return new Vector(x, y);
    }
}


