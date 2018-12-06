/*
Sanjit Bhat
ACSL Ninety-Nine
Contest #1 2017-18
Acton Boxborough Regional High School
Senior Division
*/

import java.io.*;
import java.util.*;

public class ninetynine_sanjit_bhat {
    static String your_name = "Sanjit Bhat";
    static String prob_name = "ninetynine";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File(prob_name + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(prob_name + ".out"), true);
        out.println(your_name);

        String first_line = sc.nextLine();
        String[] starting_cards = first_line.split(", ");

        for (int l = 0; l < 5; l++) {
            try {
                // initialize player decks
                ArrayList<Integer> p1_cards = new ArrayList<>();
                ArrayList<Integer> p2_cards = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    add_to_list(p1_cards, convert_val(starting_cards[i]));
                }
                for (int i = 5; i < 10; i++) {
                    add_to_list(p2_cards, convert_val(starting_cards[i]));
                }

                // read in cards on top of pile
                String[] input = sc.nextLine().split(", ");
                int pts = Integer.parseInt(input[0]);
                int[] next_cards = new int[10];
                for (int i = 1; i < 11; i++) {
                    next_cards[i-1] = convert_val(input[i]);
                }

                // simulate game
                int card_idx = 0;
                while (pts <= 99) {
                    int card_played = 0;
                    if (card_idx % 2 == 0) {  // player 1's turn
                        card_played = play_card(p1_cards, next_cards, card_idx);
                    } else {  // player 2's turn
                        card_played = play_card(p2_cards, next_cards, card_idx);
                    }

                    int pt_delta = card_played;
                    if (card_played == 9) {
                        pt_delta = 0;
                    } else if (card_played == 10) {
                        pt_delta = -10;
                    } else if (card_played == 7 && pts + 7 > 99) {
                        pt_delta = 1;
                    }

                    // check for boundaries
                    int[][] boundaries = {{33, 34}, {55, 56}, {77, 78}};
                    for (int i = 0; i < boundaries.length; i++) {
                        if (pt_delta > 0 && pts+pt_delta >= boundaries[i][1] && pts <= boundaries[i][0]) pt_delta += 5;
                        else if (pt_delta < 0 && pts+pt_delta <= boundaries[i][0] && pts >= boundaries[i][1]) pt_delta += 5;
                    }

                    pts += pt_delta;
                    card_idx++;
                }

                out.println(pts + ", Player #" + (((card_idx) % 2) + 1));

            } catch (Exception e) {
                System.out.println("Something's wrong here");
                e.printStackTrace();
            }
        }

        out.close();
    }

    static int play_card(ArrayList<Integer> pn_cards, int[] next_cards, int card_idx) {
        int card_played = pn_cards.get(2);
        pn_cards.remove(2);
        if (card_idx < next_cards.length) add_to_list(pn_cards, next_cards[card_idx]);
        return card_played;
    }

    static void add_to_list(ArrayList<Integer> arr, int val) {
        if (arr.isEmpty()) {
            arr.add(val);
        } else {
            for (int i = 0; i < arr.size(); i++) {
                if (val < arr.get(i)) {
                    arr.add(i, val);
                    return;
                }
            }
            arr.add(val);
        }
    }

    static int convert_val(String str) {
        char c = str.charAt(0);
        if (c <= '9' && c >= '0') {
            return c - '0';
        } else if (c == 'T') {
            return 10;
        } else if (c == 'J') {
            return 11;
        } else if (c == 'Q') {
            return 12;
        } else if (c == 'K') {
            return 13;
        } else return 14;
    }
}