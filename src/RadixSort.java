import java.util.Arrays;

public class RadixSort {

    // Método principal de Radix Sort
    public static void radixSort(int[] arr) {
        // Encuentra el número máximo para saber el número de dígitos
        int max = getMax(arr); //el numero maximo

        // Aplica Counting Sort para cada dígito
        // En lugar de pasar el número de dígitos, pasamos el exponente (10^i
        // el exp controla el digito que estamos usando dentro de exp cambia a 1,10, 100
        for (int exp = 1; max / exp > 0; exp *= 10) {
            System.out.println("Ordenando por digito con exp = " + exp);
            countingSort(arr, exp);
            System.out.println("Estado del array despues de ordenar por digito con exp = " + exp + ": " + Arrays.toString(arr));
        }
    }

    // Función para realizar el Counting Sort basado en el dígito representado por exp
    public static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n]; // Matriz de salida
        int[] count = new int[10]; // Matriz de conteo (base 10)

        // Inicializa la matriz de conteo con ceros
        Arrays.fill(count, 0);

        // Almacena el conteo de las ocurrencias de los dígitos
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // Cambia count[i] para que contenga las posiciones actuales
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        /*

        System.out.println("Conteo de digitos para exp = " + exp + ": " + Arrays.toString(count));*/

        // Construye la matriz de salida
        for (int i = n - 1; i >= 0; i--) {
            int index = (arr[i] / exp) % 10;
            output[count[index] - 1] = arr[i];
            count[index]--;
        }

        System.out.println("Array de salida despues de ordenar por digito con exp = " + exp + ": " + Arrays.toString(output));

        // Copia la matriz de salida en arr[], de modo que arr[] ahora esté ordenado según el número actual
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // Función para obtener el número máximo en arr[]
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // Declaración e inicialización del arreglo
        int[] arr = {267, 33, 85, 99, 952, 14, 1, 80,532};

        // Mostrar el arreglo antes de ordenar
        System.out.println("Arreglo antes del ordenamiento: " + Arrays.toString(arr));

        // Aplicar Radix Sort
        radixSort(arr);

        // Mostrar el arreglo después de ordenar
        System.out.println("Arreglo despues del ordenamiento: " + Arrays.toString(arr));
    }
}
