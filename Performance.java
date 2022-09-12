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

    private static void medirTempos() {
        List<String> strings = new ArrayList<>();

        startTime();
        for (int i = 0; i < 100000; i++) {
            strings.add("aaaaaaaaaaaaa");
        }
        stopTime(".......iniciacao do vetor");

        startTime();
        List<String> concats = new ArrayList<>();
        for (String s: strings) {
            concats.add((s + "ssssssssss").replace("a", "b"));
        }
        stopTime("......................for");

        startTime();
        strings.stream().map(s -> (s + "ssssssssss").replace("a", "b")).collect(Collectors.toList());
        stopTime("...................stream");

        startTime();
        strings.parallelStream().map(s -> (s + "ssssssssss").replace("a", "b")).collect(Collectors.toList());
        stopTime("...........parallelStream");

        startTime();
        strings.stream().parallel().map(s -> (s + "ssssssssss").replace("a", "b")).collect(Collectors.toList());
        stopTime("......stream().parallel()");

        startTime();
        strings.stream().map(s -> (s + "ssssssssss").replace("a", "b")).parallel().collect(Collectors.toList());
        stopTime("stream().mpa().parallel()");

        strings.
    }

    public static void main(String[] args) {
        for(int i = 1; i < 11; i++) {
            System.out.println("------ ITERACAO " + i + " -------------------------------------------");
            medirTempos();
        }

    }
}
