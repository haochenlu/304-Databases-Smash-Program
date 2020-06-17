package main;

public class Launcher {

    public static void main(String[] args) {
        Backend backend = new Backend();
        backend.login("ora_mkalina", "a92772482");
        backend.query("");
        backend.close();
    }
}
