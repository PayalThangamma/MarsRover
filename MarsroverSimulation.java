import java.util.*;

enum Direction {
    N("North"), E("East"), S("South"), W("West");

    private String fullName;

    Direction(String fullName) {
        this.fullName = fullName;
    }

    public Direction turnLeft() {
        return values()[(ordinal() + 3) % 4];
    }

    public Direction turnRight() {
        return values()[(ordinal() + 1) % 4];
    }

    public String getFullName() {
        return fullName;
    }
}

// Position Class
class Position {
    int x, y;
    Direction direction;

    public Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}

interface Command {
    void execute(Rover rover);
}

class MoveCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.move();
    }
}

class TurnLeftCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.turnLeft();
    }
}

class TurnRightCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.turnRight();
    }
}

abstract class GridComponent {
    public abstract boolean isOccupied(int x, int y);
}

class Grid extends GridComponent {
    private int maxX, maxY;
    private Set<String> obstacles = new HashSet<>();

    public Grid(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void addObstacle(int x, int y) {
        obstacles.add(x + ":" + y);
    }

    @Override
    public boolean isOccupied(int x, int y) {
        return obstacles.contains(x + ":" + y);
    }

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x <= maxX && y >= 0 && y <= maxY;
    }
}

class Rover {
    private Position position;
    private Grid grid;
    private boolean obstacleDetected = false;

    public Rover(int x, int y, Direction direction, Grid grid) {
        this.position = new Position(x, y, direction);
        this.grid = grid;
    }

    public void move() {
        int nextX = position.x;
        int nextY = position.y;

        switch (position.direction) {
            case N -> nextY++;
            case E -> nextX++;
            case S -> nextY--;
            case W -> nextX--;
        }

        if (!grid.isWithinBounds(nextX, nextY)) {
            System.out.println("Boundary reached at (" + nextX + ", " + nextY + "). Stopping movement.");
        } else if (grid.isOccupied(nextX, nextY)) {
            obstacleDetected = true;
            System.out.println("Obstacle detected at (" + nextX + ", " + nextY + "). Stopping movement.");
        } else {
            position.x = nextX;
            position.y = nextY;
            obstacleDetected = false; // Reset obstacle detected flag on successful move
        }
    }

    public void turnLeft() {
        position.direction = position.direction.turnLeft();
    }

    public void turnRight() {
        position.direction = position.direction.turnRight();
    }

    public String getStatus() {
        String status = "Rover is at (" + position.x + ", " + position.y + ") facing " + position.direction.getFullName() + ".";
        if (obstacleDetected) {
            status += " Obstacle detected.";
        } else {
            status += " No obstacles detected.";
        }
        return status;
    }
}

// Main Class
public class MarsRoverSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Grid grid = null;
        try {
            System.out.print("Enter grid size (maxX maxY): ");
            int maxX = scanner.nextInt();
            int maxY = scanner.nextInt();
            grid = new Grid(maxX, maxY);

            System.out.print("Enter number of obstacles: ");
            int numObstacles = scanner.nextInt();
            for (int i = 0; i < numObstacles; i++) {
                System.out.print("Enter obstacle coordinates (x y): ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                grid.addObstacle(x, y);
            }

            System.out.print("Enter starting position and direction (x y direction): ");
            int startX = scanner.nextInt();
            int startY = scanner.nextInt();
            Direction startDirection = Direction.valueOf(scanner.next().toUpperCase());
            Rover rover = new Rover(startX, startY, startDirection, grid);

            System.out.print("Enter commands (e.g., MMLMR): ");
            String commands = scanner.next().toUpperCase();

            Map<Character, Command> commandMap = new HashMap<>();
            commandMap.put('M', new MoveCommand());
            commandMap.put('L', new TurnLeftCommand());
            commandMap.put('R', new TurnRightCommand());

            for (char commandChar : commands.toCharArray()) {
                Command command = commandMap.get(commandChar);
                if (command != null) {
                    command.execute(rover);
                } else {
                    System.out.println("Invalid command: " + commandChar);
                }
            }

            System.out.println(rover.getStatus());

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numbers for grid size and coordinates.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid direction. Please enter N, E, S, or W.");
        } finally {
            scanner.close();
        }
    }
}
