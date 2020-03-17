public class ArrayExample2 {
    public static void main(String[] args) {
        int[] scores;
        scores = new int[] {34,36,37,86};
        int sum1 = 0;
        for(int i = 0; i < scores.length; i++){
            sum1 += scores[i];
        }
        System.out.println(sum1);
//        int sum2 = add(scores);
        int sum2 = add(new int[] {34,36,37,86});
        System.out.println(sum2);
    }
    public static int add(int[] arrays){
        int sum = 0;
        for(int i = 0; i < arrays.length; i++){
            sum += arrays[i];
        }
        return sum;
    }
}
