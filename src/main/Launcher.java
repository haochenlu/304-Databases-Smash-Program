package main;

import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Launcher {
    private static String file = "./melee_final.sql";

    public static void main(String[] args) {
        Backend backend = new Backend();
        backend.login("ora_mkalina", "a92772482");
        List<String> lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(Paths.get(file));   //taken from FileReaderWriter in Deliverable 4
        } catch (Exception badFile) {
            System.out.println("File not found");
        }
        ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();;
        int count = 0;
        for (String line: lines) {
            backend.load(line);
            count++;
        }
        out = backend.query("select hsSize\n" + "from shieldgroup\n", new String[]{"hsSize"}, new String[]{"float"});
        System.out.println(out.toString());
        backend.load("update grabgroup set gdFrames = 10 where ggID = '26_gg'");
        out = backend.query("select gdFrames\n" + "from grabgroup\n" + "where ggID = '26_gg'\n", new String[]{"gdFrames"}, new String[]{"int"});
        System.out.println(out.toString());
        backend.close();
    }
}
