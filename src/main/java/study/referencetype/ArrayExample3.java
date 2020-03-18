package main.java.study.referencetype;

public class ArrayExample3 {
    public static void main(String[] args) {
        int[] array1 = {1,2,3};
        int[] array2 = new int[5];
        int[] array3 = new int[5];

        for(int i = 0; i < array1.length; i++){
            array2[i] = array1[i];
        }

        for(int i = 0; i < array2.length; i++){
            System.out.print(array2[i]);
        }

        System.arraycopy(array1, 0, array3, 0, array1.length);
        System.out.println();
        for(int i = 0; i < array3.length; i++){
            System.out.print(array3[i]);
        }
    }
}
