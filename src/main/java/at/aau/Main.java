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

   /*     LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);

        System.out.println(list.toString()); */

        for (Object o : patch.getDeltas()) {
            Delta delta = (Delta) o;
            System.out.println(delta.toString());

            if (delta.toString().substring(1, 8).contains("Change")) {
                diffInfo.setActionType("UPDATE");
            } else if (delta.toString().substring(1, 8).contains("Insert")) {
                diffInfo.setActionType("INSERT");
            } else if (delta.toString().substring(1,8).contains("Delete")) {
                diffInfo.setActionType("DELETE");
            } else {
                diffInfo.setActionType("UNKNOWN");
            }


            diffInfo.setSrcEndlineOffset(delta.getOriginal().toString().length());

            diffInfo.setSrcID(i);
            diffInfo.setSrcStartLine(delta.getOriginal().getPosition());
            diffInfo.setSrcEndline(delta.getOriginal().size() - 1 + delta.getOriginal().getPosition());

            diffInfo.setDstID(i);
            diffInfo.setDstStartLine(delta.getRevised().getPosition());
            diffInfo.setDstEndline(delta.getRevised().size() - 1 + delta.getRevised().getPosition());

            System.out.println("ActionType: " + diffInfo.getActionType());

            System.out.println("srcID: " + diffInfo.getSrcID());
            System.out.println("srcStartLine: " + diffInfo.getSrcStartLine());
            System.out.println("srcEndline: " + diffInfo.getSrcEndline());
            System.out.println("srcEndlineOffset: " + diffInfo.getSrcEndlineOffset());

            System.out.println("dstStartLine: " + diffInfo.getDstStartLine());
            System.out.println("dstEndline: " + diffInfo.getDstEndline());
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
        }
        return liste;
    }

}
