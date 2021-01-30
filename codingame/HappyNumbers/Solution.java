import java.util.*;
import java.util.stream.Stream;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        for (int i = 0; i < N; i++) {
            String x = in.nextLine();

            System.out.print(x);
            Set <String> calculations = new HashSet<>();
            calculations.add("1");

            do {
                calculations.add(x);
                x = "" + Stream.of(x.split(""))
                        .map(Integer::parseInt)
                        .mapToInt(anything -> anything * anything)
                        .sum();
            } while (!calculations.contains(x));

            System.out.println((x.equals("1")?" :)": " :("));  
      }
    }
}

