import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int WIN_COUNT=4;
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '•';

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;

    public static void main(String[] args) {
        while (true){
            initialize();
            printField();
            while (true){
                humanTurn();
                printField();
                if (gameCheck( DOT_HUMAN,"Вы победили!"))
                    break;
                aiTurn();
                printField();
                if (gameCheck(DOT_AI,"Компьютер победил!"))
                    break;
                }
            System.out.println("Желаете сыграть еще раз? Y-да");
            if (!SCANNER.next().equalsIgnoreCase("Y"))
                break;
        }
    }

    private static void initialize() {
        fieldSizeX = 5;
        fieldSizeY = 5;
        field = new char[fieldSizeX][fieldSizeY];
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    private static void printField() {
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print((i % 2 == 0) ? "-" : i / 2 + 1);
        }
        System.out.println();

        for (int i = 0; i < fieldSizeX; i++) {
            System.out.print(i + 1 + "|");

            for (int j = 0; j < fieldSizeY; j++)
                System.out.print(field[i][j] + "|");

            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void humanTurn(){
        int x,y;
        do {
            System.out.println("ведите коррдинаты хода х и у от 1 до 3 через пробел >>");
            x=SCANNER.nextInt()-1;
            y=SCANNER.nextInt()-1;

        }while (!isCellValid(x,y)|| !isCellEmpty(x,y));
        field[x][y]=DOT_HUMAN;
    }

    private static boolean isCellEmpty(int x,int y){
        return field[x][y]==DOT_EMPTY;
    }

    private static boolean isCellValid(int x,int y){
        return x>=0 && x<fieldSizeX && y >=0 && y< fieldSizeY;
    }

    private static void aiTurn(){
        int x,y;
        do {
            x=random.nextInt(fieldSizeX);
            y=random.nextInt(fieldSizeY);
        }while (!isCellEmpty(x,y));
        field[x][y]=DOT_AI;
    }

   static boolean checkWin(char dot) {
        return checkWinnerHorizontal(dot) ||
                checkWinnerVertical(dot) ||
                checkWinnerDiagonals();
    }

    private static boolean checkWinnerHorizontal(char c) {
        for (int i = 0; i < fieldSizeX; i++) {
            boolean res = true;
            for (int j = 1; j < fieldSizeY && res; j++)
                res = (field[i][j] == field[i][0])&&(field[i][0]==c);
            if (res)
                return true;
        }
        return false;
    }

    private static boolean checkWinnerVertical(char c) {
        for (int i = 0; i < fieldSizeX; i++) {
            boolean res = true;
            for (int j = 1; j < fieldSizeY && res; j++)
                res = (field[j][i] == field[0][i])&& (field[0][i]==c);
            if (res)
                return true;
        }
        return false;
    }

    private static boolean checkWinnerDiagonals() {
        boolean res = true;
        for (int i = 1; i < fieldSizeX && res; i++)
            res = field[i][i] == field[0][0];
        if (res)
            return true;
        res = true;
        for (int j = 1; j < fieldSizeY && res; j++)
            res = field[fieldSizeY - j - 1][j] == field[fieldSizeY - 1][0];
        return res;
    }
    static boolean checkDraw(){
        for (int x=0;x<fieldSizeX;x++){
            for (int y=0;y<fieldSizeY;y++){
                if (isCellEmpty(x,y)) return false;
            }
        }
        return true;
    }

    static boolean gameCheck(char c, String str){
        if (checkWin(c)) {
            System.out.println(str);
            return true;
        }
        if (checkDraw()){
            System.out.println("Ничья!");
            return true;
        }
        return false; //игра продолжается
    }
}