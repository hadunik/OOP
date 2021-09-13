public class HeapSort {

    /**
     * Sorting array of variables.
      * @param arr array to sort
     */
    static public void sort(int[] arr)
    {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            sifting(arr, n, i);

        for (int i = n - 1; i >= 0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            sifting(arr, i, 0);
        }
    }

    /**
     * changed position elements in array by the rule
     * @param arr array with elems
     * @param n count elems in array
     * @param i elem for start
     */
    static void sifting(int[] arr, int n, int i)
    {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            sifting(arr, n, largest);
        }
    }

}
