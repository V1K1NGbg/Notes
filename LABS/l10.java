import java.util.*;

public class l10 {

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

                System.exit(0);

            }

        }

        System.out.println("You died in the maze!");

        in.close();
    }

}

class Brain {

    public boolean[][] points = new boolean[20][20];
    public int x = 9, y = 9;
    public int last = 0;

    
    public String getMove(boolean north, boolean south, boolean east, boolean west) {
        switch (last) {
            case 0:
                if (north) {
                    if (!west && !east) {
                        return "north";
                    } else {
                        points[]
                    }
                }
                break;
            case 1:
                if (south) {
                    
                }
                break;
            case 2:
                if (east) {
                    
                }
                break;
            case 3:
                if (west) {
                    
                }
                break;
        }

    }
}