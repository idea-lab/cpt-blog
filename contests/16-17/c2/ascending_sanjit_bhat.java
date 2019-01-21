/*
Sanjit Bhat
ACSL Ascending Strings
Contest #2 2016-17
Acton Boxborough Regional High School
Senior Division
*/

import java.io.*;
import java.util.*;

public class ascending_sanjit_bhat {
    static String your_name = "Sanjit Bhat";
    static String prob_name = "ascending";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File(prob_name + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(prob_name + ".out"), true);
        out.println(your_name);

        for (int test_case = 0; test_case < 5; test_case++) {
            try {
                String raw_string = sc.nextLine();

                // first number will always be leftmost digit
                int curr_highest = Integer.parseInt(raw_string.substring(0, 1));
                raw_string = raw_string.substring(1);
                out.print(curr_highest + " ");

                int curr_num = 0;
                boolean is_left = false;  // whether we pull new digits from left or right side
                while (curr_num <= curr_highest && raw_string.length() > 0) {
                    int string_length = raw_string.length();
                    if (is_left) {
                        // pull digit from left side
                        curr_num = curr_num * 10 + Integer.parseInt(raw_string.substring(0, 1));
                        raw_string = raw_string.substring(1);
                    } else {
                        // pull digit from right side
                        curr_num = curr_num * 10 + Integer.parseInt(raw_string.substring(string_length-1, string_length));
                        raw_string = raw_string.substring(0, string_length-1);
                    }
                    if (curr_num > curr_highest) {
                        // reset vars and print out if total number pulled is larger than previous
                        out.print(curr_num + " ");
                        curr_highest = curr_num;
                        curr_num = 0;
                        is_left = !is_left;
                    }
                }
                out.println();
            } catch (Exception e) {
                out.println("Something's wrong with this test case");
                e.printStackTrace();
            }
        }
        sc.close();
        out.close();
    }
}