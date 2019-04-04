/*
Sanjit Bhat
ACSL Lights Out
Contest #3 2016-17
Acton-Boxborough Regional High School
Senior Division
*/

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class lights_sanjit_bhat {
    static String your_name = "Sanjit Bhat";
    static String prob_name = "lights";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File(prob_name + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(prob_name + ".out"), true);
        out.println(your_name);

        // read in all the boards, condense each into one string, and convert it to binary
        String[] boards_bin = new String[6];
        for (int i = 0; i < 6; i++) {
            StringBuilder board_hex = new StringBuilder();
            for (int j = 0; j < 4; j++) {
                board_hex.append(sc.next());
            }

            // convert board to binary
            String postfix = hexToBin(board_hex.toString());

            // pad each binary string to make it fill entire board
            StringBuilder zero_prefix = new StringBuilder();
            for (int j = 0; j < 64 - postfix.length(); j++) {
                zero_prefix.append("0");
            }
            boards_bin[i] = zero_prefix.toString() + postfix;
        }

        for (int test_case = 0; test_case < 5; test_case++) {
            try {
                String prev_board = boards_bin[test_case];
                String future_board = boards_bin[test_case+1];

                // calculate array of differences between strings
                // this shows whether it was in change "radius"
                boolean[] diffs = new boolean[64];
                for (int char_pos = 0; char_pos < 64; char_pos++) {
                    if (prev_board.charAt(char_pos) != future_board.charAt(char_pos)) {
                        diffs[char_pos] = true;
                    }
                }

                int press_loc = -1;
                // find a diff that is the only one in its row - corresponds to top or bottom of star
                // there might only be one (x in bottom-most or top-most part of screen) or two (change in middle of screen)
                for (int i = 0; i < 8; i++) {
                    int num_diffs = 0;
                    int diff_loc = -1;
                    for (int j = i*8; j < (i+1)*8; j++) {
                        if (diffs[j]) {
                            num_diffs++;
                            diff_loc = j;
                        }
                    }

                    // look at locations 2 rows above and 2 rows below for center of press
                    if (num_diffs == 1) {
                        if (diff_loc-16 >= 0 && diffs[diff_loc-16]) press_loc = diff_loc-16;
                        if (diff_loc+16 < 64 && diffs[diff_loc+16]) press_loc = diff_loc+16;
                    }
                }

                // convert from index notation to row, col notation
                int row = (press_loc / 8) + 1;
                int col = (press_loc % 8) + 1;
                out.println(row + "" + col);
            } catch (Exception e) {
                out.println("Something's wrong with this test case");
                e.printStackTrace();
            }
        }
        sc.close();
        out.close();
    }

    static String hexToBin(String s) {
        return new BigInteger(s, 16).toString(2);
    }
}