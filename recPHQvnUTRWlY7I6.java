public class SeaBattle {

    private static int verificationShot(char[][] battleField, char[][] field, int x, int y) {
        if (!isValidShot(battleField, field, x, y)) {
            return -1;
        }

        if (hasAlreadyShot(field, x, y)) {
            System.out.println("По этим координатам уже стреляли. Пожалуйста, выберите другие координаты.");
            return -1;
        }

        if (isHit(battleField, x, y)) {
            System.out.println("Попадание!");
            field[x][y] = 'X';
            return 1; 
        } else {
            System.out.println("Мимо!");
            field[x][y] = '*';
            return 0; 
        }
    }

    private static boolean isPlayerAlive(char[][] field) {
        for (char[] row : field) {
            for (char cell : row) {
                if (cell == 'X') {
                    return true; 
                }
            }
        }
        return false; 
    }

    private static boolean isValidShot(char[][] battleField, char[][] field, int x, int y) {
        if (x < 0 || x >= battleField.length || y < 0 || y >= battleField[0].length) {
            System.out.println("Выстрел вне поля. Пожалуйста, введите корректные координаты.");
            return false;
        }
        return true;
    }

    private static boolean hasAlreadyShot(char[][] field, int x, int y) {
        return field[x][y] != ' ' && field[x][y] != '*';
    }

    private static boolean isHit(char[][] battleField, int x, int y) {
        return battleField[x][y] == 'X';
    }

  
}
