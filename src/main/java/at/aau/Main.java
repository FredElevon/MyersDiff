package at.aau;


import difflib.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List<String> original = fileToLines("originalFile.txt");
        List<String> revised  = fileToLines("revisedFile.xt");

        // Compute diff. Get the Patch object. Patch is the container for computed deltas.
        Patch patch = DiffUtils.diff(original, revised);

        for (Delta delta: patch.getDeltas()) {
            System.out.println(delta);
        }
    }
}
