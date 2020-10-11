
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Determinante {

    public static void main(String args[]) {
        int[][] matriz;

        try {
            BufferedReader br = new BufferedReader(new FileReader("a.txt"));
            //Primera linea nos dice longitud de la matriz
            String linea = br.readLine();
            int longitud = Integer.parseInt(linea);
            //System.out.println(linea);
            matriz = new int[longitud][longitud];
            //Las siguientes lineas son filas de la matriz
            System.out.println(longitud);
            linea = br.readLine();
            int fila = 0; //Para recorrer las filas de la matriz
            while (linea != null) {
                /*
				 * Tenemos todos los enteros JUNTOS en el String linea.
				 * Con split() los SEPARAMOS en un array donde cada entero
				 * es un String individual. Con un bucle, los parseamos a Integer
				 * para guardarlos en la matriz
                 */
                String[] enteros = linea.split(" ");
                for (int i = 0; i < enteros.length; i++) {
                    matriz[fila][i] = Integer.parseInt(enteros[i]);
                }

                fila++; //Incrementamos fila para la próxima línea de enteros
                linea = br.readLine(); //Leemos siguiente línea
            }
            br.close(); //Cerramos el lector de ficheros

            
            
            ImprimirMatriz(matriz);

            System.out.println(Determinante(matriz));
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra archivo");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("No se pudo convertir a entero");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error accediendo al archivo.");
            e.printStackTrace();
        }

    }

    public static void ImprimirMatriz(int matriz[][]) {        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(String.valueOf(matriz[i][j]) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] SubMatriz(int matriz[][], int x, int y) {
        int submatriz[][] = new int[matriz.length - 1][matriz.length - 1];
        int cur_x = 0;
        int cur_y = 0;
        for (int i = 0; i < matriz.length; i++) {
            if (i != x) {
                cur_y = 0;
                for (int j = 0; j < matriz.length; j++) {
                    if (j != y) {
                        submatriz[cur_x][cur_y] = matriz[i][j];
                        cur_y++;
                    }
                }
                cur_x++;
            }
        }
        return submatriz;
    }

    public static int Determinante(int matriz[][]) {
        int deter = 0;
        int i;
        int mult = 1;
        if (matriz.length > 2) {
            for (i = 0; i < matriz.length; i++) {
                deter += mult * matriz[i][0] * Determinante(SubMatriz(matriz, i, 0));
                mult *= -1;
            }
            return deter;
        } else {
            return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        }
    }

}
