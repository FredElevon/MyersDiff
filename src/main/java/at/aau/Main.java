package at.aau;


import difflib.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List<String> original = fileToLines("originalFile.txt");
        List<String> revised  = fileToLines("revisedFile.txt");

        // Compute diff. Get the Patch object. Patch is the container for computed deltas.
        Patch patch = DiffUtils.diff(original, revised);


        DiffInfo diffInfo = new DiffInfo();

        int i = 0;


        for (Object o : patch.getDeltas()) {
            Delta delta = (Delta) o;
            System.out.println(delta.toString());

            int u = delta.getOriginal().toString().substring(2).indexOf("[")+3; // start quo. ori.
            int v = delta.getOriginal().toString().substring(u).indexOf("]") + u; // end quo. ori.

            int s = delta.getRevised().toString().substring(2).indexOf("[")+3; //start quotation revised content
            int t = delta.getRevised().toString().substring(s).indexOf("]") + s; //end quotation revised content


            if (delta.toString().substring(1, 8).contains("Change")) {
                diffInfo.setActionType("UPDATE");
            } else if (delta.toString().substring(1, 8).contains("Insert")) {
                diffInfo.setActionType("INSERT");
            } else if (delta.toString().substring(1,8).contains("Delete")) {
                diffInfo.setActionType("DELETE");
            } else {
                diffInfo.setActionType("UNKNOWN");
            }


            diffInfo.setSrcID(i);
            diffInfo.setSrcStartLine(delta.getOriginal().getPosition());
            diffInfo.setSrcEndLine(delta.getOriginal().size() - 1 + delta.getOriginal().getPosition());
            diffInfo.setSrcEndLineOffset(delta.getOriginal().toString().substring(u,v).length());

            diffInfo.setDstID(i);
            diffInfo.setDstStartLine(delta.getRevised().getPosition());
            diffInfo.setDstEndLine(delta.getRevised().size() - 1 + delta.getRevised().getPosition());
            diffInfo.setDstEndLineOffset(delta.getRevised().toString().substring(s,t).length());


            System.out.println("ActionType: " + diffInfo.getActionType());

            System.out.println("srcID: " + diffInfo.getSrcID());
            System.out.println("srcStartLine: " + diffInfo.getSrcStartLine());
            System.out.println("srcEndline: " + diffInfo.getSrcEndLine());
            System.out.println("srcEndlineOffset: " + diffInfo.getSrcEndLineOffset());

            System.out.println("dstStartLine: " + diffInfo.getDstStartLine());
            System.out.println("dstEndline: " + diffInfo.getDstEndLine());
            System.out.println("dstEndlineOffset: " + diffInfo.getDstEndLineOffset());
            System.out.println("dstID: " + diffInfo.getDstID());


            i++;
        }


    }

    public static List<String> fileToLines(String fileName) {
        ArrayList<String> liste = new ArrayList<>();

        Path path = Paths.get(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> liste.add(s));
        }  catch (IOException ex) {
            System.out.println("Something doesn't work!");
        }
        return liste;
    }

}
