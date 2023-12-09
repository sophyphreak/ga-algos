import java.util.ArrayList;
import java.util.Arrays;

class IncreasingSubspaces {
    public static int increasingSubsequence(ArrayList<Integer> a, int combos) {
        if (a.size() == 0) {
            return combos;
        }
        for (int i = 0; i < a.size(); i++) {
            ArrayList<Integer> sublist = new ArrayList<Integer>(a.subList(0, i));
            sublist.addAll(a.subList(i + 1, a.size()));
            combos += increasingSubsequence(sublist, 0);
        }
        return combos + 1;
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(1, 7, 3, 5, 2, 8,
                10, 24, -1, -5, 4));
        // ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(1, 2));
        System.out.println(increasingSubsequence(a, 0) + 1);
    }
}