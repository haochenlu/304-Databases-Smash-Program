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
        String out = "";
        int count = 0;
        for (String line: lines) {
            backend.load(line);
            //System.out.println(Integer.toString(count));
            count++;
        }
        out = backend.query("select sgID from shieldgroup");
        System.out.println(out);
        backend.close();
    }
}
