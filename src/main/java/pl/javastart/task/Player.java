package pl.javastart.task;

import java.io.Serializable;
import java.util.Comparator;

public class Player implements Serializable {
    private String name;
    private String lastName;
    private int result;

    public Player(String name, String lastName, int result) {
        this.name = name;
        this.lastName = lastName;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return name + " " + lastName + ";" + result;
    }

    public static class PlayerNameComparator implements Comparator<Player> {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getName().compareTo(p2.getName());
        }
    }

    public static class PlayerLastNameComparator implements Comparator<Player> {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getLastName().compareTo(p2.getLastName());
        }
    }

    public static class PlayerResultComparator implements Comparator<Player> {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getResult() - p2.getResult();
        }
    }
}
