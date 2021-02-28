import java.io.*;

public class AltTask3 {

    static char[][] result;

    public static void main(String[] args) {
        char[][] maze = getMazeByFileName("Filename.txt");//Сначала столбец, затем строка [N столбца][N строки]
        char[][] result = getWayInMaze(maze);
        print(result);
    }

    static char[][] getWayInMaze(char[][] maze) {
        int length = maze.length;
        Point startPosition = getStartedPosition(maze);
        doResearch(maze, startPosition);
        result[startPosition.Y][startPosition.X]='s';
        return result;
    }

    static void doResearch(char[][] maze, Point p) {
        maze = getClone(maze);
        if (maze[p.Y][p.X] == 'f') {
            result=maze;
            return;
        }
        maze[p.Y][p.X] = '*';
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if ((Math.abs(dx + dy) != 1)
                        || (p.X + dx < 0 || p.X + dx >= maze.length || p.Y + dy < 0 || p.Y + dy >= maze.length)
                        || (maze[p.Y + dy][p.X + dx] != '.' && maze[p.Y + dy][p.X + dx] != 'f')) ;
                else doResearch(maze.clone(), new Point(p.X + dx, p.Y + dy));
            }
        }
    }

    static char[][] getClone(char[][] array) {
        char[][] newArray = new char[array.length][array.length];
        for (int i = 0; i < array.length; i++)
            System.arraycopy(array[i], 0, newArray[i], 0, array.length);
        return newArray;
    }

    static void print(char[][] maze) {
        for (char[] chars : maze) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(chars[j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static Point getStartedPosition(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (maze[j][i] == 's')
                    return new Point(i, j);
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
