package pl.javastart.task;

import java.util.*;

public class TournamentStats {

    private static final String STOP = "stop";
    private static final int NAME = 1;
    private static final int LAST_NAME = 2;
    private static final int RESULT = 3;
    private static final int SORT_ASCENDING = 1;
    private static final int SORT_DESCENDING = 2;

    FileUtils fileUtils = new FileUtils();

    public static final String FILE_NAME = "stats.csv";

    void run(Scanner scanner) {
        List<Player> playerList = new ArrayList<>();
        boolean playerToList;
        do {
            playerToList = addPlayerToList(scanner, playerList);
        } while (!playerToList);
        printSortParameters();
        int sortParameter = scanner.nextInt();
        printSortOrder();
        int sortOrder = scanner.nextInt();
        sort(playerList, sortParameter, sortOrder);
        fileUtils.writeToFile(FILE_NAME, playerList);
        System.out.println("Dane posortowano i zapisano do pliku " + TournamentStats.FILE_NAME);
    }

    private static void printSortOrder() {
        System.out.println("Sortować rosnąco czy malejąco? ( "
                + SORT_ASCENDING + " - rosnąco, "
                + SORT_DESCENDING + " - malejąco)");
    }

    private static void printSortParameters() {
        System.out.println("Po jakim parametrze posortować? " +
                "(" + NAME + " - imię, "
                + LAST_NAME + " - nazwisko, "
                + RESULT + " - wynik)");
    }

    private static void sort(List<Player> playerList, int sortParameter, int sortOrder) {
        Comparator<Player> comparator;
        if (sortParameter == NAME) {
            comparator = new Player.PlayerNameComparator();
        } else if (sortParameter == LAST_NAME) {
            comparator = new Player.PlayerLastNameComparator();
        } else {
            comparator = new Player.PlayerResultComparator();
        }
        if (sortOrder == SORT_DESCENDING) {
            playerList.sort(comparator.reversed());
        } else {
            playerList.sort(comparator);
        }
    }

    private static boolean addPlayerToList(Scanner scanner, List<Player> playerList) {
        System.out.println("Podaj wynik kolejnego gracza (lub " + STOP + ")");
        String player = scanner.nextLine();
        if (player.equalsIgnoreCase(STOP)) {
            return true;
        } else {
            String[] split = player.split(" ");
            int result = Integer.parseInt(split[2]);
            playerList.add(new Player(split[0], split[1], result));
            return false;
        }

    }

}
