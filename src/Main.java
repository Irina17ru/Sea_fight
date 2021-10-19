import java.util.*;

public class Main {

    public static void main(String[] args) {
        int xcoordinate, ycoordinate, xsize, ysize; //координаты первой клетки корабля и его размеры
        boolean direction;  //направление размещения корабля - вертикально или горизонтально
        Random random = new Random();
        int [][] field = new int[10][10];   //инициализация игрового поля

        Integer [] size = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        List<Integer> ships = new ArrayList<Integer>(Arrays.asList(size));  //динамический список со всеми кораблями

        while(!ships.isEmpty()){    //пока в списке есть хотя бы один корабль, мы пытаемся его расположить
            direction = random.nextBoolean();
            xcoordinate = random.nextInt(10);
            ycoordinate = random.nextInt(10);

            if (direction) {
                ysize = ships.get(0);
                xsize = 1;
            }
            else {
                ysize = 1;
                xsize = ships.get(0);
            }
            if (xcoordinate + xsize <= 9 && ycoordinate + ysize <=9) {  //если все координаты корабля в пределах поля
                if (checker(field, xcoordinate, ycoordinate, xsize, ysize)) {   //проверка (см. ниже)
                    field = insert(field, xcoordinate, ycoordinate, xsize, ysize);
                    ships.remove(0);    //убираем из списка установленный на поле корабль
                }
            }
        }
        print(field);
    }

    /*проверяет, есть ли корабль на выбранных координатах (прим. для вертикального двухпалубника,
    где * - соседняя клетка проверки, х - клетка корабля

    * * *
    * х *
    * х *
    * * *

    координаты, выходящие за границы поля игнорируются
    */
    public static boolean checker(int[][] field, int xcoordinate, int ycoordinate, int xsize, int ysize){
        for (int i = ycoordinate - 1; i < ycoordinate + ysize + 1; i++){
            for (int j = xcoordinate - 1; j < xcoordinate + xsize + 1; j++){
                if (i >=0 && i <= 9 && j >= 0 && j <= 9){
                    if (field[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // если проверка пройдена, по текущим координатам вставляет корабль
    public static int[][] insert(int[][] field, int xcoordinate, int ycoordinate, int xsize, int ysize){
        for (int i = ycoordinate; i < ycoordinate + ysize; i++){
            for (int j = xcoordinate; j < xcoordinate + xsize; j++){
                if (i >=0 && i <= 9 && j >= 0 && j <=9){
                    field[i][j] = 1;
                }
            }
        }
        return field;
    }

    //функция вывода массива в консоль
    public static void print(int[][] field){
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field.length; j++){
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }
}
