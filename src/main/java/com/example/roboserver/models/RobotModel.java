package com.example.roboserver.models;


import com.example.roboserver.utils.algebra.OrientationVector;
import com.example.roboserver.utils.algebra.Vector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RobotModel {

    private Vector locationVector = null;
    @Getter
    private OrientationVector orientationVector = null;

}