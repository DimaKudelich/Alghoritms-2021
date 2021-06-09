package com.kudelich.strings.boyer;

import java.util.ArrayList;

public class BoyerMoore {
    private int[] right;
    private String pattern;

    private ArrayList<Integer> allOverlaps;
    private int count;

    public BoyerMoore(String pattern) {
        this.pattern = pattern;

        int M = pattern.length();
        int R = 256;
        right = new int[R];

        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }

        for (int j = 0; j < M; j++) {
            right[pattern.charAt(j)] = j;
        }
    }

    public int searchText(String txt) {
        int N = txt.length();
        int M = pattern.length();
        int skip;

        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (pattern.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            }
            if (skip == 0) return i;
        }
        return N;
    }

    public void searchAll(String txt) {
        int N = txt.length();
        int M = pattern.length();
        int skip;
        count = 0;
        allOverlaps = new ArrayList<>();

        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (pattern.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            }
            if (skip == 0) {
                count++;
                allOverlaps.add(i);
                skip = 1;
            }
        }

        if (count == 0) {
            System.out.println("Не было найдено совпадений");
        } else {
            System.out.print("Было найдено " + count + " совпадений(я) в позициях: ");
            for (int i = 0; i < allOverlaps.size(); i++) {
                System.out.print(allOverlaps.get(i) + "; ");
            }
        }
    }

    public void printTrace(String txt){
        System.out.printf("%2c\t",'_');
        for (int i = 0; i < txt.length(); i++) {
            System.out.printf("%d\t",i);
        }
        System.out.println();

        System.out.printf("%2c\t",'_');
        for (int i = 0; i < txt.length(); i++) {
            System.out.printf("%1c\t",txt.charAt(i));
        }
        System.out.println();

        for (int i = 0; i < allOverlaps.size(); i++) {
            System.out.printf("%2d\t",allOverlaps.get(i));

            for (int j = 0; j < txt.length(); j++) {
                if (j == allOverlaps.get(i)) {
                    for (int k = 0; k < pattern.length(); k++) {
                        System.out.printf("%c\t",pattern.charAt(k));
                    }
                    j = txt.length();
                } else {
                    System.out.printf("%c\t", ' ');
                }
            }

            System.out.println();
        }
    }

    public boolean isCycle(String string1, String string2) {
        if (string1.length() != string2.length() || string1.length() == 0) {
            return false;
        }
        int startIndex;

        for (int i = 0; i < string1.length(); i++) {
            if (string1.charAt(0) == string2.charAt(i)) {
                startIndex = i;
                boolean flag = true;

                int index2 = startIndex;
                for (int j = 1; j < string1.length(); j++) {
                    int index1 = j;
                    index2 = (index2 + 1) % (string1.length());
                    if (string1.charAt(index1) != string2.charAt(index2)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    return true;
                }
            }
        }

        return false;
    }
}
