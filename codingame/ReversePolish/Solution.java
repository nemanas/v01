import java.util.*;
import java.io.*;
import java.math.*;
/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {
    
    // Creating an empty Stack
    static Stack<String> STACK = new Stack<String>();
    static Stack<String> out = new Stack<String>();
    static String s = new String();
    static int n1, n2, tmp = 0, errKnt=0;
    static Object cur;
    static int N=0;
    
    static void printStack() {
        // Displaying the Stack
        System.err.println("Print Initial Stack: " + STACK);
        //final String values = Arrays.toString(STACK.toArray());
        //System.err.println("STACK values: <" + values + ">");
        final Object[] vals = STACK.toArray();
        for (Object obj : vals) {
                System.err.println(obj);
        }
    }
    public static int evaluate(String[] tokens) throws ArithmeticException, NumberFormatException {
        Deque<Integer> stk = new ArrayDeque<>();
        Deque<Integer> stack = new ArrayDeque<>();
                
        int ret=0, ret1=0;
        for (String token : tokens) {
            System.err.println("Found next token <" + token + ">");
            switch (token) {
              case "POP":
                System.err.println("Got POP");
                stk.pop();
                stk.iterator().forEachRemaining(System.err::println);
                break;
              case "DUP":
                System.err.println("Got DUP");
                ret = stk.pop();
                stk.push(ret);
                stk.push(ret);
                stk.iterator().forEachRemaining(System.err::println);
                break;
              case "SWP":
                System.err.println("Got SWP");
                ret = stk.pop();
                ret1 = stk.pop();
                System.err.println("Swapping ret1=" + ret1 + " with ret=" + ret);
                stk.push(ret);
                stk.push(ret1);
                stk.iterator().forEachRemaining(System.err::println);
                break;
              case "ROL":
                System.err.println("Got ROL");
                ret = stk.pop();
                ret = stk.size() - ret;
                stk.iterator().forEachRemaining(System.err::println);
                
                int pk = stk.peek();
                int rm = 0; //rm = stk.remove(ret);
                System.err.println("Rolling out position at " + ret + " peeking at " + pk + " removing " + rm + " done");
                
                //Stack<Integer> stack = stk.clone(); //new Stack<Integer>(stk); 
                //Stack<Integer> stack = new Stack<Integer>();
                //Deque<Integer> stack = new ArrayDeque<>();
                Iterator<Integer> it = stk.descendingIterator();
                //Iterator<Integer> it = stk.iterator();
                int k=0;
                while (it.hasNext()) {
                    pk = it.next();
                    System.err.println("Copying next element at " + k + " as pk=" + pk);
                    if(k++ == ret) {
                        rm = pk;
                        System.err.println("Saving removed element as rm="+rm+" at ret=" + ret + " with k=" + k);
                    } else {
                        stack.push(pk);
                    }
                }
                stack.push(rm);
                System.err.println("Done ROL");

                stk = stack; 
                stk.iterator().forEachRemaining(System.err::println);
                break;
              case "ADD":
                System.err.println("Got ADD");
                //stk.push(stk.pop() + stk.pop());
                if(stk.size() >= 2) {
                    stk.push(stk.pop() + stk.pop());
                } else {
                    ret = stk.pop();
                    System.out.println("ERROR");
                }
                stk.iterator().forEachRemaining(System.err::println);
                break;
              case "SUB":
                stk.push(-stk.pop() + stk.pop());
                break;
              case "MUL":
                System.err.println("Got MUL");
                stk.push(stk.pop() * stk.pop());
                break;
              case "DIV":  // NOTE: Integer division truncates towards 0
                System.err.println("Got DIV");
                int divisor = stk.pop();
                System.err.println("Found divisor="+divisor);
                if(divisor == 0) {
                    errKnt++;
                    //System.out.println("ERROR");
                    //stk.push(1);
                    //return ret;
                    stk.pop();
                } else {
                    stk.push(stk.pop() / divisor);
                }
                stk.iterator().forEachRemaining(System.err::println);
                break;
              default:
                System.err.println("Pushing default token <" + token + "> as int <" + Integer.parseInt(token) + ">");
                stk.push(Integer.parseInt(token));
                stk.iterator().forEachRemaining(System.err::println);
            }
        }
        //ret = stk.pop();

        System.err.println("FINAL RESULTS AS FOLLOWS");
        //stk.iterator().forEachRemaining(System.out::println);
        //Iterator<Integer> it = stk.iterator();
        Iterator<Integer> it = stk.descendingIterator();
        //Iterator<Integer> it = stack.descendingIterator();
        int k=0;
        System.err.println("errKnt="+errKnt);
        while (it.hasNext()) {
            if(k++ > 0) System.out.print(" ");
            System.out.print(it.next());
        }
        if (errKnt > 0) {
            if (stk.size() > 0 )
                System.out.print(" ");
            System.out.println("ERROR");
        }
    
        //return stk.pop();

        return ret;
        //stk.iterator().forEachRemaining(System.out::println);
    }

  // ============ Start of Part-0 works for test1 ok ============== 
    public static void main(String args[]) {
        final Scanner in = new Scanner(System.in);
        N = in.nextInt();
        String[] newArray = new String[N];  

        for (int i = 0; i < N; i++) {
            final String instruction = in.next();
            STACK.push(instruction);
            newArray[i] = instruction;  
        }
        for (int i = 0; i < N; i++) {
            System.err.println("Next string["+i+"] is <" + newArray[i] + ">");  
        }
        evaluate(newArray);
    }
}

