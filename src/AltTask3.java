import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class AltTask3 {

    public static void main(String[] args) {
        char[][] maze = getMazeByFileName("Filename.txt");//Сначала столбец, затем строка [N столбца][N строки]
        char[][] mazeWithWay = getWayInMaze(maze);
    }

    static char[][] getWayInMaze(char[][] maze) {
        int length = maze.length;
        Point startPosition = getStartedPosition(maze);
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.add(startPosition);
        while(!queue.isEmpty()){
            Point p = queue.pop();

        }
        return null;
    }

    static void doResearch(char[][] maze, ArrayList<Point> list){

    }


    static Point getStartedPosition(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if(maze[j][i]=='s')
                    return new Point(j,i);
            }
        }
        return null;
    }

    static char[][] getMazeByFileName(String fileName) {
        char[] array = new char[1000];
        char[][] maze = new char[1][1];
        try (FileReader file2 = new FileReader(fileName);) {
            file2.read(array);
            int length = getLength(array);
            maze = getMaze(array, length);
            System.out.println("123");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maze;
    }

    static char[][] getMaze(char[] symbols, int length) {
        int i = 0;
        int x = 0;
        int y = 0;
        char[][] maze = new char[length][length];
        while (symbols[i] != '\u0000')
            if (symbols[i] == '\r') {
                x = 0;
                y++;
                i += 2;
            } else {
                maze[y][x] = symbols[i];
                i++;
                x++;
            }
        return maze;
    }

    static int getLength(char[] mazeFromFile) {
        int i = 0;
        while (mazeFromFile[i] != '\r')
            i++;
        return i;
    }

}
