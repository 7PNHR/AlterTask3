import java.io.*;

public class AltTask3 {

    public static void main(String[] args) {
        char[][] maze = findWayInMaze("Filename.txt");
        print(maze);
        maze = findWayInMaze("BigMaze.txt");
        print(maze);
        maze = new char[][]{
                {'s', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', 'f'}
        };
        maze = findWayInMaze(maze);
        print(maze);
    }

    public static char[][] findWayInMaze(String fileName) {
        char[][] maze = getMazeByFileName(fileName);//Сначала столбец, затем строка [N столбца][N строки]
        return getSolvedMaze(maze);
    }

    public static char[][] findWayInMaze(char[][] maze) {
        return getSolvedMaze(maze);
    }

    static char[][] getSolvedMaze(char[][] maze) {
        Point startPosition = findStartedPosition(maze);
        char[][] result = findWayInMaze(maze, startPosition);
        if (result != null)
            result[startPosition.Y][startPosition.X] = 's';
        return result;
    }

    static char[][] findWayInMaze(char[][] maze, Point p) {
        maze = getClone(maze);
        if (maze[p.Y][p.X] == 'f')
            return maze;
        maze[p.Y][p.X] = '*';
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (!((Math.abs(dx + dy) != 1)
                        || (p.X + dx < 0 || p.X + dx >= maze.length || p.Y + dy < 0 || p.Y + dy >= maze.length)
                        || (maze[p.Y + dy][p.X + dx] != '.' && maze[p.Y + dy][p.X + dx] != 'f'))) {
                    char[][] researchResult = findWayInMaze(maze.clone(), new Point(p.X + dx, p.Y + dy));
                    if (researchResult != null) return researchResult;
                }
            }
        }
        return null;
    }

    static Point findStartedPosition(char[][] maze) {
        Point result = new Point(0, 0);
        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze.length; j++)
                if (maze[j][i] == 's')
                    result = new Point(i, j);
        return result;
    }
//region Получение массива чаров из файла
    static char[][] getMazeByFileName(String fileName) {
        char[] array = new char[1000];
        char[][] maze = new char[1][1];
        try (FileReader file2 = new FileReader(fileName);) {
            int value = file2.read(array);
            int length = getLength(array);
            maze = getMaze(array, length);
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
//endregion
//region Вспомогательные методы (клонирование массива, вывод массива чаров)
    static char[][] getClone(char[][] array) {
        char[][] newArray = new char[array.length][array.length];
        for (int i = 0; i < array.length; i++)
            System.arraycopy(array[i], 0, newArray[i], 0, array.length);
        return newArray;
    }

    static void print(char[][] maze) {
        if (maze == null) {
            System.out.println("Налл");
            return;
        }
        for (char[] chars : maze) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(chars[j]);
            }
            System.out.println();
        }
        System.out.println();
    }
//endregion
}
