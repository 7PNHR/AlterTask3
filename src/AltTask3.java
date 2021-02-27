import java.io.*;

public class AltTask3 {

    public static void main(String[] args) {
        char[][] maze = getMazeByFileName("Filename.txt");//Сначала столбец, затем строка [N столбца][N строки]
        getWayInMaze(maze);
    }

    static char[][] getWayInMaze(char[][] maze) {
        int[] startPosition = getStartedPosition(maze);
        int[] finishPosition = getFinishedPosition(maze);
        return null;
    }

    static int[] getStartedPosition(char[][] maze){
        return findSymbolPosition(maze,'s');
    }

    static int[] getFinishedPosition(char[][] maze){
        return findSymbolPosition(maze,'f');
    }

    /**
     * Находим заданную точку
     * возвращает массив из 2 точек, где 1 точка номер столбца
     * а вторая номер строки
     */
    static int[] findSymbolPosition(char[][] maze, char symbol) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if(maze[j][i]==symbol)
                    return new int[]{j,i};
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
