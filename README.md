# Robot Command Executor

The Robot Command Executor is a Spring Boot application that simulates the movement of a robot on a grid based on a series of commands.

[Check the frontend app](https://github.com/itaishalom/RobotAppFlutterFronend)

## Overview

The application provides an API endpoint to execute robot commands from a file. It parses the commands, moves the robot accordingly, and returns the new vector of the robot after executing all commands.

## Supported Commands

The application supports the following commands:

- `POSITION x y DIRECTION`: Sets the initial position for the robot. `x` and `y` represent the vector on the grid, and `DIRECTION` specifies the initial direction (e.g., NORTH, SOUTH, EAST, WEST).
- `FORWARD n`: Moves the robot `n` steps forward in its current direction.
- `WAIT`: Allows the robot to do nothing.
- `TURNAROUND`: Allows the robot to turn 180 degrees.
- `LEFT`: Allows the robot to turn left.
- `RIGHT`: Allows the robot to turn right.

## Architecture

The application follows a layered architecture with the following components:

1. **Controller**: Handles incoming HTTP requests and delegates the execution of commands to service components.
2. **Service**: Contains a service to execute commands, a service to manage robot movement, and the parser that parses the commands from a file and converts them into command objects.
3. **Model**: Represents the data structures used in the application, such as the `RobotModel` class to track the robot's position and orientation.
4. **Command**: Defines command classes for each command, such as `CommandInstruction`, `CommandInstruction`, etc.
5. **Utils**: I created an exception class and a Matrix/vector manipulation class to calculate robot position over 2d Cartesian space.
   
### Prerequisites

- Java JDK 8 or higher
- Maven

### Installation

1. Clone the repository:

    ```
    git clone <repository-url>
    ```

2. Navigate to the project directory:

    ```
    cd robot-command-executor
    ```

3. Build the project:

    ```
    mvn clean install
    ```

4. Run the application:

    ```
    mvn spring-boot:run
    ```

## Usage

### Endpoint

POST localhost:8080/api/robot/execute

bash

### Request Parameters

- `file`: The file containing robot commands.

#### Example Request

```http
POST /api/robot/execute
Content-Type: multipart/form-data

[file content]
```

Response
The response contains the new vector of the robot after executing all commands.

Example input:

```
POSITION 1 3 EAST //sets the initial position for the robot
FORWARD 3 //lets the robot do 3 steps forward
WAIT //lets the robot do nothing
TURNAROUND //lets the robot turn around
FORWARD 1 //lets the robot do 1 step forward
RIGHT //lets the robot turn right
FORWARD 2 //lets the robot do 2 steps forward
```

Example Response
```
{
    "locationVector": {
        "x": 3,
        "y": 1
    },
    "orientationVector": {
        "x": 0,
        "y": -1,
        "name": "NORTH"
    }
}
```
#### Ideas for next steps

1. **Error Handling**: Implement global exception handling for consistent error responses.
2. **Security**: Integrate Spring Security for authentication and authorization.
3. **Logging and Monitoring**: Implement logging and monitoring for application health.
4. **Database Transactions**: Manage transactions declaratively for database operations, store the robot moves and final locations.
5. **Documentation**: Generate API documentation for clear endpoint documentation.
6. **Testing**: Expand test coverage with unit, integration, and end-to-end tests.
7. **CI/CD**: Set up CI/CD pipelines for automated building and deployment.
8. **Performance Optimization**: Identify and optimize performance bottlenecks.
9. **Add a better library for matrix and vector manipulations**: Support multiple dimensions vectors.
Also, better consider angles for rotations (90, 45, 180...) so the rotation matrices could be better calculated.
