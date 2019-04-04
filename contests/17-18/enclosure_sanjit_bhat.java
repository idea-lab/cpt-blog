/*
Sanjit Bhat
ACSL Enclosure
Contest #2 2017-18
Acton Boxborough Regional High School
Senior Division
*/

import java.io.*;
import java.util.*;

public class enclosure_sanjit_bhat {
    static String your_name = "Sanjit Bhat";
    static String prob_name = "enclosure";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File(prob_name + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(prob_name + ".out"), true);
        out.println(your_name);

        // Note: watch out when testing this problem. ACSL stupidly uses different minus sign chars in different test cases
        for (int test_case = 0; test_case < 10; test_case++) {
            try {
                // read in input
                String raw_input = sc.nextLine();
                raw_input = "+" + raw_input + "+";  // for one indexing and end curly brace case

                // identify missing enclosure
                char[] operators = {'+', 8211, 8722, '*', '/'};  // see note in line 21. The numbers are ASCII values for two different minus sign chars
                char[] enclosures = {'{', '[', '(', ')', ']', '}'};
                boolean[] enclosures_exist = new boolean[6];
                for (int i = 0; i < enclosures.length; i++) {
                    if (raw_input.indexOf(enclosures[i]) != -1) enclosures_exist[i] = true;
                }
                int missing = 0;
                for (int i = 0; i < 6; i++) {
                    if (enclosures_exist[i] && !enclosures_exist[6-i-1]) {
                        missing = 6-i-1;
                    }
                }
                char conj_missing = enclosures[6-missing-1];

                // reverse string if missing starting enclosure to simplify for loop stack simulation
                raw_input = (missing < 3) ? (new StringBuilder(raw_input)).reverse().toString() : raw_input;

                ArrayList<Integer> output_locs = new ArrayList<>();
                Stack<Character> st_enclosure = new Stack<>();  // this is the KEY data structure for enclosure matching
                boolean only_nums = true;

                for (int i = 0; i < raw_input.length(); i++) {
                    char curr_char = raw_input.charAt(i);

                    boolean is_enclosure = false;
                    char conj_enclosure = 'x';
                    for (int j = 0; j < enclosures.length; j++) {
                        if (curr_char == enclosures[j]) {
                            is_enclosure = true;
                            conj_enclosure = enclosures[6-j-1];
                        }
                    }

                    boolean is_operator = false;
                    for (char c : operators) {
                        if (curr_char == c) is_operator = true;
                    }

                    // if start enclosure (conjugate not in stack), push first
                    if (is_enclosure && st_enclosure.search(conj_enclosure) == -1) st_enclosure.push(curr_char);

                    // checks for 1) more than single int enclosed, 2) enclosures matched, 3) non-number
                    if (!only_nums && !st_enclosure.isEmpty() && st_enclosure.peek() == conj_missing && (is_operator || is_enclosure)) output_locs.add(i);

                    // if end enclosure (conjugate in stack), 2 cases
                    if (is_enclosure && st_enclosure.search(conj_enclosure) != -1) {
                        if (st_enclosure.peek() == conj_enclosure) {
                            st_enclosure.pop();
                        } else st_enclosure.push(curr_char);
                    }

                    // 1) conjugate of missing enclosure there and 2) operator there -> we have more than single int enclosed
                    if (st_enclosure.search(conj_missing) != -1 && is_operator) only_nums = false;
                }

                // output answers
                for (int i = 0; i < output_locs.size(); i++) {
                    if (missing < 3) {
                        // deal with reversed string indexing
                        out.print(raw_input.length() - output_locs.get(output_locs.size()-i-1));
                    } else out.print(output_locs.get(i));
                    if (i != output_locs.size()-1) out.print(", ");
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