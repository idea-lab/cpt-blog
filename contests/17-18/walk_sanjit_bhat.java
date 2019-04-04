/*
Sanjit Bhat
ACSL Walk
Contest #3 2017-18
Acton-Boxborough Regional High School
Senior Division
*/

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class walk_sanjit_bhat {
    static String your_name = "Sanjit Bhat";
    static String prob_name = "walk";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File(prob_name + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(prob_name + ".out"), true);
        out.println(your_name);

        // locations (row/col) of relative clockwise movements
        int[][] mvmts = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};

        // Hashmap of starting directions that correspond to the index of start in mvmt, relative to center
        HashMap<String, Integer> dir_indices = new HashMap<>();
        dir_indices.put("L", 0);
        dir_indices.put("R", 4);
        dir_indices.put("B", 6);
        dir_indices.put("A", 2);

        // convert given hex values into an int[][] matrix
        String[] lines = sc.nextLine().split(", ");
        int[][] vals = new int[8][8];
        for (int i = 0; i < 8; i++) {
            String oct_conv = hexToOct(lines[i]);
            for (int j = 0; j < 8; j++) {
                vals[i][j] = oct_conv.charAt(j) - '0';
            }
        }

        for (int test_case = 0; test_case < 5; test_case++) {
            try {
                // read in input
                String raw_input = sc.nextLine();
                String[] actual_values = raw_input.split(", ");

                int row = Integer.parseInt(actual_values[0])-1;  // zero-index everything
                int col = Integer.parseInt(actual_values[1])-1;
                int start_dir = dir_indices.get(actual_values[2]);
                int num_iter = Integer.parseInt(actual_values[3]);

                for (int i = 0; i < num_iter; i++) {
                    int delta_idx = (start_dir + vals[row][col]) % 8;  // calculate new relative loc after turn
                    // move. + 8 and % 8 correct for going off the grid in any direction
                    row = (row + mvmts[delta_idx][0] + 8) % 8;
                    col = (col + mvmts[delta_idx][1] + 8) % 8;
                    start_dir = (delta_idx + 4) % 8;  // new start is reverse of coordinates
                }

                // output answer to test case
                out.println((row + 1) + ", " + (col + 1));
            } catch (Exception e) {
                out.println("Something's wrong with this test case");
                e.printStackTrace();
            }
        }
        sc.close();
        out.close();
    }

    static String hexToOct(String s) {
        return new BigInteger(s, 16).toString(8);
    }
}