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

            // System.out.println(move + maze[posX - 1][posY] + maze[posX + 1][posY] +
            // maze[posX][posY + 1]
            // + maze[posX][posY - 1] + posX + posY);

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
                System.out.println(200 - lives);

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

    public String singlemove(int dir) {
        stack.push(dir);
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
                    y = y - 1;
                    return ("north");
                case 0:
                    y = y + 1;
                    return ("south");
                case 3:
                    x = x - 1;
                    return ("west");
                case 2:
                    x = x + 1;
                    return ("east");
                default:
                    return ("Error");
            }
        } else {
            if (north && !points[x][y - 1]) {
                return (singlemove(0));
            } else if (east && !points[x + 1][y]) {
                return (singlemove(3));
            } else if (west && !points[x - 1][y]) {
                return (singlemove(2));
            } else {
                return (singlemove(1));
            }
        }
    }
}