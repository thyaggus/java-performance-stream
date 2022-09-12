package funcional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Performance {

    private static long start;

    private static void stopTime(String msg) {
        System.out.println("$>" + msg + " executado em: " + (System.currentTimeMillis() - start) + "ms");
    }

    private static void startTime() {
        start = System.currentTimeMillis();
    }

    private static String stress(String s) {
        String a = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        a += s.toUpperCase() + a;
        s.replace("A", "b");
        a = a.toUpperCase();
        a = a.toLowerCase();
        return a;
    }

    private static void medirTempos() {
        List<String> strings = new ArrayList<>();

        startTime();
        for (int i = 0; i < 100000; i++) {
            strings.add("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        }
        stopTime(".......iniciacao do vetor");

        startTime();
        List<String> concats = new ArrayList<>();
        for (String s: strings) {
            concats.add(stress(s));
        }
        stopTime("......................for");

        startTime();
        strings.stream().map(s -> stress(s)).collect(Collectors.toList());
        stopTime("...................stream");

        startTime();
        strings.parallelStream().map(s -> stress(s)).collect(Collectors.toList());
        stopTime("...........parallelStream");

        startTime();
        strings.stream().parallel().map(s -> stress(s)).collect(Collectors.toList());
        stopTime("......stream().parallel()");

        startTime();
        strings.stream().map(s -> stress(s)).parallel().collect(Collectors.toList());
        stopTime("stream().mpa().parallel()");

        strings = null;
    }

    public static void main(String[] args) {
        for(int i = 1; i < 11; i++) {
            System.out.println("------ ITERACAO " + i + " -------------------------------------------");
            medirTempos();
        }

    }
}
