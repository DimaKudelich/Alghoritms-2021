package com.kudelich.strings.run;

import com.kudelich.strings.boyer.BoyerMoore;

public class Main {
    public static void main(String[] args) {
        String pat = "pat";
        String txt = "find pat in str with pat and patpat";

        BoyerMoore boyerMoore = new BoyerMoore(pat);
//        boyerMoore.searchAll(txt);
//        System.out.println();
//        boyerMoore.printTrace(txt);

        String str1 = "12321";
        String str2 = "11232";

        System.out.println(boyerMoore.isCycle(str1,str2));
    }
}
