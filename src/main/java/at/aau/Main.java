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
        List<DiffInfo> myList = diffFiles("PromotingEmployees.txt", "PromotingEmployees1.txt");
        System.out.println();
//        System.out.println(myList.get(1).getActionType());
        System.out.println("done.");
    }

    public static List<DiffInfo> diffFiles(String srcFilename, String dstFilename) {

        List<String> original = fileToLines(srcFilename);
        List<String> revised  = fileToLines(dstFilename);

        // Compute diff. Get the Patch object. Patch is the container for computed deltas.
        Patch patch = DiffUtils.diff(original, revised);


        ArrayList<DiffInfo> diffList = new ArrayList<>();

        int i = 0;


        for (Object o : patch.getDeltas()) {
            DiffInfo diffInfo = new DiffInfo();
            Delta delta = (Delta) o;
            System.out.println(delta.toString());



            /*int u = delta.getOriginal().toString().substring(2).indexOf("[") + 3; // start quo. ori.
            int v = delta.getOriginal().toString().length() - 2; // end quo. ori.
            diffInfo.setSrcEndLineOffset(delta.getOriginal().toString().substring(u,v).length());*/
            diffInfo.setSrcEndLineOffset((original.get(original.size()-1).length()));

            /*int s = delta.getRevised().toString().substring(2).indexOf("[") + 3; //start quotation revised content
            int t = delta.getRevised().toString().length() - 2; //end quotation revised content
            diffInfo.setDstEndLineOffset(delta.getRevised().toString().substring(s,t).length()); */
            diffInfo.setDstEndLineOffset((revised.get(revised.size()-1).length()));


            if (delta.getType().equals(Delta.TYPE.CHANGE)) {
                diffInfo.setActionType("UPDATE");
            } else if (delta.getType().equals(Delta.TYPE.INSERT)) {
                diffInfo.setActionType("INSERT");
            } else if (delta.getType().equals(Delta.TYPE.DELETE)) {
                diffInfo.setActionType("DELETE");
            } else {
                diffInfo.setActionType("UNKNOWN");
            }


            diffInfo.setSrcID(i);
            diffInfo.setSrcStartLine(delta.getOriginal().getPosition());
            diffInfo.setSrcEndLine(delta.getOriginal().size() - 1 + delta.getOriginal().getPosition());


            diffInfo.setDstID(i);
            diffInfo.setDstStartLine(delta.getRevised().getPosition());

            if (diffInfo.getActionType().equals("DELETE")) {
                diffInfo.setDstEndLine(delta.getRevised().size() + delta.getRevised().getPosition());
            } else {
                diffInfo.setDstEndLine(delta.getRevised().size() - 1 + delta.getRevised().getPosition());
            }



            diffList.add(diffInfo);

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
        return diffList;
    }

    public static List<String> fileToLines(String fileName) {
        ArrayList<String> liste = new ArrayList<>();

        Path path = Paths.get(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(liste::add);
        }  catch (IOException ex) {
            System.out.println("Something doesn't work!");
        }
        return liste;
    }

}
