---
layout:     post
title:      Coding Problem Style Guide
date:       2018-12-05 05:42:29
summary:    TODO's for the ACSL coding problem to ensure proper grading
author:     Sanjit Bhat
categories: info
---

Speed of grading the coding problems comes at the sacrifice
of flexibility. As such, we need everyone to conform to the below
rules for their coding problems. Otherwise, the grader
will simply grade their outputs as incorrect.

1. *The filename must be `{prob_name}_{first_name}_{last_name}.java`.*
 As an example, my solution for the agram problem would be
`agram_sanjit_bhat.java`. For Adam's ninetynine solution, it would
be `ninetynine_adam_jelinsky.java`. This rule is for file-keeping
purposes and to know who's code is whose. The grader can't
actually access individual variables during a program's execution,
so if something goes wrong it uses the filename to identify
the respective student. We will decide on a "prob_name" right
after we receive the problem on Friday. This variable
to be consistent among everyone's code.

2. *Print your name as the first line of the .out
file.* As mentioned earlier, the grader can't actually access
the your_name variable. It can, however, parse the .out
file. For every student's code, it expects the first line
of the output file to be his/her name, using the filename
as a backup. Note the disastrous consequences if it expects
the first line to be a name when your code prints a test
case output.

3. *The input is separated by commas and spaces.
All test cases are fed in at once, one line per test case.*
Looking at the sample input provided, the only difference
would be that we take out the 1., 2., 3. numbering in our .in
files. Thus, a sample input would look like this:
    ```
    A, K, K, 4, 9, K, A, Q, 9, T
    70, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7
    84, A, 7, 9, T, K, 7, Q, 9, 5, 6
    55, T, A, A, K, K, Q, Q, J, J, T
    51, 7, 7, 7, 7, 6, 6, 4, 4, 4, 4
    30, T, Q, 7, 7, A, Q, J, K, Q, A
    ```
   Note that here (problem ninetynine), the first line is metadata for
all subsequent test cases.

4. *The output in the .out file needs to match character for character
with the actual ACSL output.* This means that unnecessary or
omitted commas will cause the grader to mark your answers as wrong.
In addition, extra lines between outputs will cause similar effects.
For sample output, look at the test cases provided with each problem.
As mentioned earlier, the only difference is that we don't
include the 1., 2., 3. numbering in our outputs.

   Note that some people only outputed test cases
using `System.out.println()`.
Standard output, as it's called, is discarded by the grader
and is not considered for correctness.
You must print to `{prob_name}.out` and read in
from `{prob_name}.in`.

5. *All your code should be in one class due to compiling issues
with the grader.* If you must, you can create more classes,
but they should be static and should be included in the same file.

6. *The grader will give each program 3 seconds to chug through
all 5 test cases.* Bad things happen if you exceed this limit,
so please try to write code that doesn't take "wicked long",
as McClung would say.

7. *Use a try-catch statement around the processing of each
test case.* This is due to robustness issues. If something
happens like an array out of bounds exception that you didn't
expect, your code would otherwise completely stop processing
the subsequent inputs. This gives you zero points for
subsequent inputs when your code might have actually worked
on them.

8. *Add a header at the top to let people know your name,
the problem name, your school, etc.* If you get a 10/10 perfect
score on this contest, it is likely your code will be
put on the ACSL website as a reference.

All together, this is template code that you can copy-paste for each
of your ACSL problems and use. Here, the student's name is "Johnny
Postulate", the problem name is "theorem", and the file name
is `theorem_johnny_postulate.java`.
```java
/*
Johnny Postulate
ACSL theorem
Contest #1 2017-18
Acton Boxborough Regional High School
Senior Division
*/

import java.io.*;
import java.util.*;

public class theorem_johnny_postulate {
    static String your_name = "Johnny Postulate";
    static String prob_name = "theorem";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File(prob_name + ".in"));
        PrintWriter out = new PrintWriter(new FileWriter(prob_name + ".out"), true);
        out.println(your_name);

        for (int l = 0; l < 5; l++) {
            try {
                // read in input
                String raw_input = sc.nextLine();
                String[] actual_values = raw_input.split(", ");

                // do calculations

                // output answer to test case
                out.println("bill noeth");
            } catch (Exception e) {
                System.out.println("Something's wrong with this test case");
                e.printStackTrace();
            }
        }
        sc.close();
        out.close();
    }
}
```

