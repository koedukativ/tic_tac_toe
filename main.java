package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            char[][] field = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    field[i][j] = ' ';
                }
            }
            printField(field);
            boolean player = true; //true für X, false für O
            while (true) {
                int[] coordinates = new int[2];
                coordinates = nextMove();
                char[][] newField = checkCoordinates(coordinates, field, player);
                player = !player;
                printField(newField);
                String stateOfGame = analyseField(newField);
                if (stateOfGame == "X wins" || stateOfGame == "O wins" || stateOfGame == "Draw") {
                    System.out.println(stateOfGame);
                    break;
                } else {
                    field = newField;
                }
            }
    }

    public static void printField(char[][] field) {
        System.out.println("---------");
        System.out.println("| " + field[0][2] + " " + field[1][2] + " " + field[2][2] + " |");
        System.out.println("| " + field[0][1] + " " + field[1][1] + " " + field[2][1] + " |");
        System.out.println("| " + field[0][0] + " " + field[1][0] + " " + field[2][0] + " |");
        System.out.println("---------");
    }

    public static int[] nextMove() {
        System.out.println("Enter the coordinates: ");
        Scanner scanner = new Scanner(System.in);
        int[] coordinates = new int[2];
        while (true) {
            coordinates[0] = scanner.nextInt();
            coordinates[1] = scanner.nextInt();
            if (coordinates[0] > 3 || coordinates[1] > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else if (coordinates[0] == 0 || coordinates[1] == 0) {
                System.out.println("You should enter numbers!");
                continue;
            } else {
                return coordinates;
            }
        }

    }

    public static char[][] checkCoordinates (int[] coordinates, char[][]field, boolean player) {
        if (field[coordinates[0] - 1][coordinates[1] - 1] == 'X' || field[coordinates[0] - 1][coordinates[1] - 1] == 'O') {
            System.out.println("This cell is occupied! Choose another one!");
            checkCoordinates(nextMove(), field, player);
        } else if (player == true){
            field[coordinates[0] - 1][coordinates[1] - 1] = 'X';
        } else {
            field[coordinates[0] - 1][coordinates[1] - 1] = 'O';
        }
        return field;
    }

    public static String analyseField(char[][] field) {
        int counterX = 0;
        int counterO = 0;
        int counterEmpty = 0;
        String message = "Game not finished";
        // horizontal
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 3; l++) {
                if (field[k][l] == 'X') {
                    counterX += 1;
                } else if (field[k][l] == 'O') {
                    counterO += 1;
                } else {
                    counterEmpty += 1;
                }
            }
            if (counterX == 3) {
                message = "X wins";
                return message;
            } else if (counterO == 3) {
                message = "O wins";
                return message;
            }
            counterX = 0;
            counterO = 0;
        }
        // vertikal
        for (int m = 0; m < 3; m++) {
            for (int n = 0; n < 3; n++) {
                if (field[n][m] == 'X') {
                    counterX += 1;
                } else if (field[n][m] == 'O') {
                    counterO += 1;
                }
            }
            if (counterX == 3) {
                message = "X wins";
                return message;
            } else if (counterO == 3) {
                message = "O wins";
                return message;
            }
            counterX = 0;
            counterO = 0;
        }
        // diagonal
        if (field[1][1] == field[0][0] && field[1][1] == field[2][2] || field[1][1] == field[0][2] && field[1][1] == field[2][0]) {
            if (field[1][1] == 'X') {
                message = "X wins";
                return message;
            }
            else if (field[1][1] == 'O') {
                message = "O wins";
                return message;
            }
        }
        // checking conditions
        if (counterEmpty == 0) {
            message = "Draw";
        }
        return message;
    }
}
