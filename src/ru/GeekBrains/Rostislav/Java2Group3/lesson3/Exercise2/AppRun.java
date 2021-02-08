package ru.GeekBrains.Rostislav.Java2Group3.lesson3.Exercise2;

public class AppRun {

    public static void main(String[] args) {

        Phonebook phonebook = new Phonebook();

        phonebook.add("Smith", "26565325");
        phonebook.add("Angel", "77777777");
        phonebook.add("Devil", "666");
        phonebook.add("Goose", "322231245");
        phonebook.add("Goose", "568793445"); //добавление телефона в ту же фамилию

        System.out.println("My Phonebook:");
        System.out.println();
        System.out.println("Number of Smith: " + phonebook.get("Smith"));
        System.out.println("Number of Angel: " + phonebook.get("Angel"));
        System.out.println("Number of Devil: " + phonebook.get("Devil"));
        System.out.println("Number of Goose: " + phonebook.get("Goose"));
        System.out.println("Number of Trump: " + phonebook.get("Trump")); // вывод отсутствующей записи
    }
}
