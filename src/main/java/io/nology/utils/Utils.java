package io.nology.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Utils {
    public static void mineCoordinates(int rows, int columns, int mines) {
        // mines number validated before passing in
        Set<int[]> mineCoordinates = new HashSet<int[]>();

        while (mineCoordinates.size() < mines) {
            int rowRef = (int) Math.ceil(Math.random() * rows);
            int columnRef = (int) Math.ceil(Math.random() * columns);
            mineCoordinates.add(new int[] { rowRef, columnRef });
        }

        System.out.println(Arrays.deepToString(mineCoordinates.toArray()));

    }

}
