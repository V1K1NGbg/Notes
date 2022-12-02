import java.util.*;

public class l10 {
    // X,X,X, ,X,X,X,X,X,X,X, ,X,X,X,X,X,X, ,X
    // X,X,X, ,X,X,X,X,X,X,X, ,X,X,X,X,X,X, ,X
    // X,X,X, , , , , , ,X,X, ,X,X,X,X, , , ,X
    // X,X,X,X,X,X,X, ,X,X,X,X,X,X,X,X,X,X,X,X
    // X,X,X,X,X,X,X, ,X,X, , , , , , ,X,X,X,X
    // X,X,X, , ,X,X,X,X,X, ,X,X,X,X, ,X,X,X,X
    // X,X, , ,X, ,X,X,X,X, , , ,X,X, ,X,X,X,X
    // X,X,X, ,X,X,X,X,X,X,X,X, ,X,X, ,X,X,X,X
    // X,X, , ,X, , ,X,X,X,X,X, ,X,X, ,X,X,X,X
    // X,X,X,X,X,X, , , , , , , ,X,X, ,X,X,X,X
    // X, ,X,X,X,X, ,X,X, ,O,X,X,X,X, ,X,X,X,X
    // , , , , ,X,X,X,X, , ,X,X,X,X, ,X,X,X,X
    // X,X,X,X,X,X,X,X,X,X,X,X,X,X,X, ,X,X,X,X
    // X,X,X,X,X,X, , ,X,X,X,X, , , , ,X,X,X,X
    // X,X, ,X,X, ,X,X,X,X,X,X, ,X,X, ,X,X,X,X
    // X, , ,X,X, ,X,X,X,X,X,X, ,X,X, ,X,X,X,X
    // X,X, ,X,X, ,X, , ,X, , , ,X,X, ,X,X, ,
    // X, , ,X,X,X,X,X,X,X, ,X,X,X,X, ,X,X, ,X
    // X,X, ,X,X,X,X,X,X,X, ,X,X,X,X,X,X,X, ,X
    // X,X, ,X,X,X,X,X,X,X, ,X,X,X,X,X,X,X, ,X
    public static void main(String[] args) {

        int lives = 200;

        Scanner in = new Scanner(System.in);

        String[] input = new String[20];

        String mazein = "XXX XXXXXXX XXXXXX X\nXXX XXXXXXX XXXXXX X\nXXX      XX XXXX   X\nXXXXXXX XXXXXXXXXXXX\nXXXXXXX XX      XXXX\nXXX  XXXXX XXXX XXXX\nXX  X XXXX   XX XXXX\nXXX XXXXXXXX XX XXXX\nXX  X  XXXXX XX XXXX\nXXXXXX       XX XXXX\nX XXXX XX  XXXX XXXX\n     XXXX  XXXX XXXX\nXXXXXXXXXXXXXXX XXXX\nXXXXXX  XXXX    XXXX\nXX XX XXXXXX XX XXXX\nX  XX XXXXXX XX XXXX\nXX XX X  X   XX XX  \nX  XXXXXXX XXXX XX X\nXX XXXXXXX XXXXXXX X\nXX XXXXXXX XXXXXXX X";

        for (int i = 0; i < 20; i++) {
            input[i] = mazein.split("\n")[i];
            // input[i]=in.nextLine();

        }

        // int posX=Integer.parseInt(in.nextLine());

        // int posY=Integer.parseInt(in.nextLine());

        int posX = 10;
        int posY = 10;

        boolean[][] maze = new boolean[20][20];

        for (int i = 0; i < 20; i++) {

            for (int j = 0; j < 20; j++) {

                if (input[i].charAt(j) == 'X') {

                    maze[i][j] = false;

                } else {

                    maze[i][j] = true;

                }

            }

        }

        Brain myBrain = new Brain();

        while (lives > 0) {

            String move = myBrain.getMove(maze[posX - 1][posY], maze[posX + 1][posY], maze[posX][posY + 1],
                    maze[posX][posY - 1]);

            // System.out.println(move + maze[posX - 1][posY] + maze[posX + 1][posY] + maze[posX][posY + 1]
            //         + maze[posX][posY - 1] + posX + posY);

            if (move.equals("north") && maze[posX - 1][posY]) {

                posX--;

            } else if (move.equals("south") && maze[posX + 1][posY]) {

                posX++;

            } else if (move.equals("east") && maze[posX][posY + 1]) {

                posY++;

            } else if (move.equals("west") && maze[posX][posY - 1]) {

                posY--;

            }

            lives--;

            if (posY % 19 == 0 || posX % 19 == 0) {

                System.out.println(posX + "," + posY);
                System.out.println(200-lives);

                System.exit(0);

            }

        }

        System.out.println("You died in the maze!");

        in.close();
    }

}

class Brain {

    public Stack<Integer> stack = new Stack<>(); // n0, s1, w2, e3
    public boolean[][] points = new boolean[40][40];
    public int x = 19, y = 19;
    public int last = 0;

    public String singlemove(int dir) {
        stack.push(dir);
        last = dir;
        switch (dir) {
            case 0:
                y = y - 1;
                return ("north");
            case 1:
                y = y + 1;
                return ("south");
            case 2:
                x = x - 1;
                return ("west");
            case 3:
                x = x + 1;
                return ("east");
            default:
                return ("Error");
        }

    }

    public String direction(boolean primary, boolean secondary, boolean tertiary, int primaryint,
            int secondaryint, int tertiaryint, int elseint, boolean checkprimary, boolean checksecondary,
            boolean checktertiary) {
        if (primary && !checkprimary) {
            return (singlemove(primaryint));
        } else if (secondary && !checksecondary) {
            return (singlemove(secondaryint));
        } else if (tertiary && !checktertiary) {
            return (singlemove(tertiaryint));
        } else {
            return (singlemove(elseint));
        }
    }

    public String getMove(boolean north, boolean south, boolean east, boolean west) {
        
        int count = 0;
        
        if (north)
            count = count + 1;
        if (south)
            count = count + 1;
        if (west)
            count = count + 1;
        if (east)
            count = count + 1;
        if (points[x + 1][y])
            count = count - 1;
        if (points[x - 1][y])
            count = count - 1;
        if (points[x][y + 1])
            count = count - 1;
        if (points[x][y - 1])
            count = count - 1;

        points[x][y] = true;
        if (count == 0) {
            switch (stack.pop()) {
                case 1:
                    last = 0;
                    y = y - 1;
                    return ("north");
                case 0:
                    last = 1;
                    y = y + 1;
                    return ("south");
                case 3:
                    last = 2;
                    x = x - 1;
                    return ("west");
                case 2:
                    last = 3;
                    x = x + 1;
                    return ("east");
                default:
                    return ("Error");
            }
        } else {
            switch (last) {
                case 0:
                    return (direction(north, east, west, 0, 3, 2, 1, points[x][y - 1], points[x + 1][y], points[x - 1][y]));
                case 1:
                    return (direction(south, west, east, 1, 2, 3, 0, points[x][y + 1], points[x - 1][y], points[x + 1][y]));
                case 2:
                    return (direction(west, north, south, 2, 0, 1, 3, points[x - 1][y], points[x][y - 1], points[x][y + 1]));
                case 3:
                    return (direction(east, south, north, 3, 1, 0, 2, points[x + 1][y], points[x][y + 1], points[x][y - 1]));
                default:
                    return ("Error");
            }
        }
    }
}