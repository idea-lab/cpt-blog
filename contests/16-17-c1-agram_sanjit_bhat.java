/*
Sanjit Bhat
ACSL agram
Contest #1 2016-17
Acton Boxborough Regional High School
Senior Division
*/

import java.io.*;
import java.util.*;

public class agram_sanjit_bhat {
    static String your_name = "Sanjit Bhat";
    static String prob_name = "agram";  // this needs to be standardized across everyone's code
    // file names should be "{prob_name}_{first name}_{last name}.java". This is for file-keeping sake as we can't have two files with the same name.

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File(prob_name + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(prob_name + ".out"), true);  // no buffering in case program craps out. Prev test cases will be outputted
        out.println(your_name);  // MUST include this

        for (int l = 0; l < 5; l++) {
            try {  // use try catch in case code craps out on one test case
                String input = sc.nextLine();
                String[] cards = input.split(", ");

                char[] suits = {'S', 'H', 'D', 'C'};
                HashMap<Character, Integer> suit_vals = new HashMap<>();
                for (int i = 0; i < suits.length; i++) {
                    suit_vals.put(suits[i], i);
                }

                char[] ranks = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};
                HashMap<Character, Integer> rank_vals = new HashMap<>();
                for (int i = 0; i < ranks.length; i++) {
                    rank_vals.put(ranks[i], i);
                }

                char opp_rank = cards[0].charAt(0);
                char opp_suit = cards[0].charAt(1);
                int opp_rank_val = rank_vals.get(opp_rank);

                char higher_in_suit = 'N';  // lowest card in same suit that's higher than opponent card
                char lowest_in_suit = 'N';  // lowest card in same suit
                char lowest_overall_rank = 'N';  // rank of lowest card overall
                char lowest_overall_suit = 'N'; // suit of lowest card overall

                for (int i = 1; i < cards.length; i++) {
                    char curr_rank = cards[i].charAt(0);
                    char curr_suit = cards[i].charAt(1);
                    int curr_rank_val = rank_vals.get(curr_rank);
                    if (curr_suit == opp_suit && curr_rank_val > opp_rank_val &&
                            (higher_in_suit == 'N' || curr_rank_val < rank_vals.get(higher_in_suit))) {
                        higher_in_suit = curr_rank;
                    }
                    if (curr_suit == opp_suit && (lowest_in_suit == 'N' || curr_rank_val < rank_vals.get(lowest_in_suit))) {
                        lowest_in_suit = curr_rank;
                    }
                    if (lowest_overall_rank == 'N' || curr_rank_val < rank_vals.get(lowest_overall_rank) ||
                            (curr_rank_val == rank_vals.get(lowest_overall_rank) &&
                                    suit_vals.get(curr_suit) > suit_vals.get(lowest_overall_suit))) {
                        lowest_overall_rank = curr_rank;
                        lowest_overall_suit = curr_suit;
                    }
                }

                if (higher_in_suit != 'N') {
                    out.println("" + higher_in_suit + opp_suit);  // remember to print to .out file for all test cases
                } else if (lowest_in_suit != 'N') {
                    out.println("" + lowest_in_suit + opp_suit);
                } else {
                    out.println("" + lowest_overall_rank + lowest_overall_suit);
                }
            } catch (Exception e) {
                out.println("Something went wrong with this test case");  // even if code craps out on one test case, your code will move on and grader will be fine
                e.printStackTrace();
            }
        }
        out.close();
    }
}