package ua.opnu;

import java.util.*;
import java.util.stream.Collectors;

public class EasyTasks {

    public static void main(String[] args) {
        // Тут можна протестувати роботу методів
        System.out.println("1. doubling: " + doubling(Arrays.asList(1, 2, 3)));
        System.out.println("2. square: " + square(Arrays.asList(1, 2, 3)));
        System.out.println("3. moreY: " + moreY(Arrays.asList("a", "b", "c")));
        System.out.println("4. noNeg: " + noNeg(Arrays.asList(1, -2, 3, -5)));
        System.out.println("5. no9: " + no9(Arrays.asList(1, 2, 19, 29, 3)));
        System.out.println("6. noZ: " + noZ(Arrays.asList("aaa", "aza", "bzbb")));
        System.out.println("7. refinedStrings: " + refinedStrings(Arrays.asList("aa", "c", "aa", "bbb")));
        System.out.println("8. flatten: " + flatten(Arrays.asList("John Wick", "John Snow")));
    }

    // Завдання 1: Помножити на 2
    public static List<Integer> doubling(List<Integer> nums) {
        return nums.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());
    }

    // Завдання 2: Піднести до квадрату
    public static List<Integer> square(List<Integer> nums) {
        return nums.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
    }

    // Завдання 3: Додати 'y' на початку і в кінці
    public static List<String> moreY(List<String> strings) {
        return strings.stream()
                .map(s -> "y" + s + "y")
                .collect(Collectors.toList());
    }

    // Завдання 4: Видалити негативні числа
    public static List<Integer> noNeg(List<Integer> nums) {
        return nums.stream()
                .filter(n -> n >= 0)
                .collect(Collectors.toList());
    }

    // Завдання 5: Видалити числа, що закінчуються на 9
    public static List<Integer> no9(List<Integer> nums) {
        return nums.stream()
                .filter(n -> Math.abs(n) % 10 != 9)
                .collect(Collectors.toList());
    }

    // Завдання 6: Видалити рядки з літерою 'z'
    public static List<String> noZ(List<String> strings) {
        return strings.stream()
                .filter(s -> !s.contains("z"))
                .collect(Collectors.toList());
    }

    // Завдання 7: Унікальні рядки, сортування за довжиною (спадання)
    public static List<String> refinedStrings(List<String> strings) {
        return strings.stream()
                .distinct() // Прибираємо дублікати
                .sorted(Comparator.comparingInt(String::length).reversed()) // Сортуємо за довжиною (спадання)
                .collect(Collectors.toList());
    }

    // Завдання 8: Розбити "Ім'я Прізвище" на окремі елементи
    public static List<String> flatten(List<String> strings) {
        return strings.stream()
                .flatMap(s -> Arrays.stream(s.split(" "))) // Перетворюємо рядок на потік слів і "сплющуємо"
                .collect(Collectors.toList());
    }

}