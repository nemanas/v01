import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        String culprit = "";
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        Map<String, Set<String>> locations = new HashMap<>();
        String regex = "(\\w+): I was in the (\\w+)";
        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i < N; i++) {
            String suspect, location, others;
            String line = in.nextLine();
            System.err.println("Original statement: " + line + "\n");
            Matcher m = pattern.matcher(line);
            m.find();
            
            others = line.substring(m.group(0).length(), line.length() - 1);
            suspect = m.group(1);
            location = m.group(2);
            System.err.println("Suspect being questiond: " + suspect);
            System.err.println("Location: " + location);
            System.err.println("Others statement: " + others);
            
            Set<String> alibis = new HashSet<>();

            if (!locations.containsKey(location)){
                if (!others.contains("alone")){
                    for (String person : others.split("(?: with | and )")){
                        alibis.add(person);
                    }
                }
                alibis.add(suspect);
                System.err.println("Alibies claimed: " + alibis.toString());
                locations.put(location, alibis);
            } else {
                if (others.contains("alone")){
                    culprit = locations.get(location).iterator().next();
                } else if (!locations.get(location).contains(suspect)){
                    culprit = suspect;
                }
                System.err.println("Culprit: " + culprit);
            }
        System.err.println("Locations Set: " + locations.toString() + "\n");
        }

      if (culprit.length() == 0){
          System.out.println("It was me!");
      } else {
          System.out.println(culprit + " did it!");
      }
    }
}
