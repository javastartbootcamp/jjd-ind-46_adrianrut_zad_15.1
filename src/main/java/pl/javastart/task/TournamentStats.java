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
        // tutaj dodaj swoje rozwiązanie
        // użyj przekazanego scannera do wczytywania wartości
        List<Player> playerList = new ArrayList<>();
        do {
            if (addPlayerToList(scanner, playerList)) {
                break;
            }
        } while (!Objects.equals(scanner.nextLine(), STOP));
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
                + SORT_ASCENDING + " - rosnąco,"
                + SORT_DESCENDING + " - malejąco)");
    }

    private static void printSortParameters() {
        System.out.println("Po jakim parametrze posortować? " +
                "(" + NAME + " - imię, "
                + LAST_NAME + " - nazwisko, "
                + RESULT + " - wynik)");
    }

    private static void sort(List<Player> playerList, int sortParameter, int sortOrder) {
        if (sortParameter == NAME) {
            if (sortOrder == SORT_ASCENDING) {
                playerList.sort(new Player.PlayerNameComparator());
            } else {
                playerList.sort(new Player.PlayerNameComparator().reversed());
            }
        } else if (sortParameter == LAST_NAME) {
            if (sortOrder == SORT_ASCENDING) {
                playerList.sort(new Player.PlayerLastNameComparator());
            } else {
                playerList.sort(new Player.PlayerLastNameComparator().reversed());
            }
        } else {
            if (sortOrder == SORT_ASCENDING) {
                playerList.sort(new Player.PlayerResultComparator());
            } else {
                playerList.sort(new Player.PlayerResultComparator().reversed());
            }
        }
    }

    private static boolean addPlayerToList(Scanner scanner, List<Player> playerList) {
        System.out.println("Podaj imię zawodnika (lub " + STOP + ")");
        String name = scanner.nextLine();
        if (Objects.equals(name, STOP)) {
            return true;
        }
        System.out.println("Podaj nazwisko zawodnika");
        String lastName = scanner.nextLine();
        System.out.println("Podaj wynik");
        int result = scanner.nextInt();
        playerList.add(new Player(name, lastName, result));
        return false;
    }

}
