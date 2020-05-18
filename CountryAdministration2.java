package com.company;

import java.util.Scanner;
import java.util.Random;

/**
 * Програма за работа с думи и числа изискана от държавна администрация
 * @author Ivan Petkov
 */
public class CountryAdministration2 {
    public static Scanner scan = new Scanner(System.in);

    /**
     * Ще се радвам да ми отговорите дали има проблем , че всичко е събрано във методи и main метода не върши нищо
     */
    public static void main(String[] args) {
        mainMenuSwitchCase();
    }


    public static void printMainMenu() {
        System.out.print("Главно меню с опции:\n" +
                "1. Работа с числа\n" +
                "2. Работа с думи\n" +
                "3. Изход от програмата\n\n" +
                "Изберете опция : ");
    }

    public static int takeMainMenuInput() {
        int x = scan.nextInt();
        if (x > 0 && x < 4) return x;
        System.err.print("Invalid option, try again: ");
        return takeMainMenuInput();
    }

    /**
     * Функциалмостта и логиката на цялото  main menu е разпределено тук
     */
    public static void mainMenuSwitchCase() {
        printMainMenu();
        switch (takeMainMenuInput()) {
            case 1:
                workWithNumbers();
                break;
              case 2: workWithWords();
                break;
            case 3:
                System.out.print("Благодарим ви, че използвахте програмата ни!");
                break;

        }
    }

    /**
     * Скелет и функционалност за всичката работа с числа
     */
    public static void workWithNumbers() {
        System.out.print("\nВъведете броят на числата с които ще работите: ");
        int arrLen = numberArrayLength();
        int[] arrayOfNumbers = new int[arrLen];
        System.out.print("Въведете " + arrLen + " числа: \n");
        fillArrayOfNumbers(arrayOfNumbers);
        printArray(arrayOfNumbers);

        printNumbersMenu();

        switch (takeNumbersMenuInput()) {
            case 1:
                sortArrayByPrimeNumbers(arrayOfNumbers);
                workWithNumbers();
                break;
            case 2:
                System.out.print("Най-често срещано число: " + mostCommonElement(arrayOfNumbers));
                break;
            case 3:
                printArray(findSpecificRowInArray(arrayOfNumbers, "Ascending"));
                workWithNumbers();
                break;
            case 4:
                printArray(findSpecificRowInArray(arrayOfNumbers, "Descending"));
                workWithNumbers();
                break;
            case 5:
                printArray(findSpecificRowInArray(arrayOfNumbers, "Equal"));
                workWithNumbers();
                break;
            case 6:
                printArray(findRowThatEqualsToRandom(arrayOfNumbers));
                workWithNumbers();
                break;
            case 7:
                mainMenuSwitchCase();
                System.out.print("\n");
                break;


        }

    }


    public static void printNumbersMenu() {
        System.out.print("\nМеню с опции:\n" +
                "1. Извеждане само на простите числа от масива\n" +
                "2. Извеждане на най-често срещан елемент в масива\n" +
                "3. Извеждане на максимална редица от нарастващи елементи в масива\n" +
                "4. Извеждане на максимална редица от намаляващи елементи в масива\n" +
                "5. Извеждане на максимална редица от еднакви елементи в масива\n" +
                "6. Извеждане на последователност от числа от масива, които имат сума\n" +
                "равна на число, генерирано на случаен принцип\n" +
                "7. Връщане назад към основното меню\n" +
                "Изберете опция : ");
    }

    public static int takeNumbersMenuInput() {
        int x = scan.nextInt();
        if (x > 0 && x < 8) return x;
        System.err.print("Invalid option, try again: ");
        return takeNumbersMenuInput();
    }

    public static int numberArrayLength() {
        return scan.nextInt();
    }

    public static void fillArrayOfNumbers(int[] array) {

        int i = 0;
        do {
            System.out.print(i + 1 + ": ");
            array[i] = scan.nextInt();

            if (array[i] >= 0 && array[i] <= 10000) i++;
        } while (i < array.length);
    }

    public static void printArray(int[] array) {
        System.out.print("{  ");
        for (int element : array) {

            System.out.print(element + "  ");
        }
        System.out.print("}");
    }


    public static void sortArrayByPrimeNumbers(int[] array) {
        System.out.print("Прости числа: \n");
        for (int element : array) {
            if (element > 1) {
                if (element == 2 || element == 3 || element == 5) System.out.print(element + " " + " \n");
                else if (element % 2 != 0 && element % 3 != 0 && element % 5 != 0)
                    System.out.print(element + " " + " \n");

            }
        }
    }

    /**
     * Метод за намиране на най-срещан елемент в масива
     */

    public static int mostCommonElement(int[] array) {
        sortArrayInAscendingOrder(array);
        int max = 1;
        int mostCommon = array[0];
        int current = 1;

        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i - 1]) current++;
            else {
                if (current > max) {
                    max = current;
                    mostCommon = array[i - 1];
                }
                current = 1;
            }
        }
        if (current > max) mostCommon = array[array.length - 1];
        return mostCommon;
    }


    public static void sortArrayInAscendingOrder(int[] array) {
        {

            for (int i = 0; i < array.length - 1; i++)
                for (int j = 0; j < array.length - i - 1; j++)
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
        }
    }

    /**
     * Метод изпълняваш 3 фунции, намиращ най-голям нарастващ/намаляващ/равен row
     */
    public static int[] findSpecificRowInArray(int[] array, String key) {
        int[] tempArr = new int[array.length];
        switch (key) {
            case "Ascending":
                for (int i = 1; i < array.length; i++) {
                    if (array[i] == array[i - 1] + 1) {
                        tempArr[i] = array[i - 1];
                        tempArr[i + 1] = array[i];

                    }
                }

            case "Descending":
                for (int i = 1; i < array.length; i++) {
                    if (array[i] == array[i - 1] - 1) {
                        tempArr[i] = array[i - 1];
                        tempArr[i + 1] = array[i];

                    }
                }

            case "Equal":
                for (int i = 1; i < array.length; i++) {
                    if (array[i] == array[i - 1]) {
                        tempArr[i] = array[i - 1];
                        tempArr[i + 1] = array[i];

                    }
                }


        }
        return tempArr;
    }

    /**
     * Метод намиращ редица на елементи в масива равна на случайно генерирано число
     * -- Забележка при тестване , случайно генерираното число е препоръчително да бъде малко
     */
    public static int[] findRowThatEqualsToRandom(int[] array) {
        Random rand = new Random();
        int x = rand.nextInt(10000);
        System.out.print("Случайно число: " + x + "\n");
        int[] tempArr = new int[array.length];
        int[] error = {x};
        for (int i = 0; i < array.length; i++) {
            int sum = 0;
            for (int j = 0; j < array.length; j++) {
                tempArr[j] = array[j];
                sum += tempArr[j];
                if (sum == x) return tempArr;
            }
        }
        System.out.print("Няма намерена редица равна на случайното число ");
        return error;
    }


// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Скелет и функционалност на работата с думи
     */

    public static void workWithWords() {
        System.out.print("\nВъведете броят на думите с които ще работите : ");
        int Words = scan.nextInt();
        String[] WordsArray = new String[Words];
        fillArrayOfWords(WordsArray);
        printArrayString(WordsArray);
        printWordsMenu();

        switch (takeWordsMenuInput()) {
            case 1: reverseWordsInArray(WordsArray);
                workWithWords();
                break;
            case 2: System.out.print("soonTM");
                workWithWords();
                break;
            case 3: countSymbolsInArray(WordsArray);
                workWithWords();
                break;
            case 4: countRepeatingWords(WordsArray);
                workWithWords();
                break;
            case 5:
                mainMenuSwitchCase();
                System.out.print("\n");
                break;

        }
    }

    public static void printWordsMenu() {
        System.out.print("Меню с опции:\n" +
                "1. Обърнете буквите на думите от масива наобратно и ги\n" +
                "визуализирайте в конзолата\n" +
                "2. Изведете броя на повтарящите се символи за всяка една от думите в\n" +
                "масива\n" +
                "3. Изведете броя на символите за всяка една от думите в масива\n" +
                "4. Изведете броя на повтарящите се думи от масива\n" +
                "5. Връщане назад към основното меню\n" +
                "Изберете опция: ");
    }

    public static int takeWordsMenuInput() {
        int x = scan.nextInt();
        if (x > 0 && x < 6) return x;
        System.err.print("Invalid option, try again: ");
        return takeMainMenuInput();
    }

    public static void fillArrayOfWords(String[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + 1 + ": ");
            array[i] = scan.next();
        }
    }
        public static void printArrayString(String[] array) {
            System.out.print("{  ");
            for (String element : array) {

                System.out.print(element + "  ");
            }
            System.out.print("}");
        }



    public static void reverseWordsInArray(String[] array) {
        for (String s : array) {
            String reverse = new StringBuilder(s).reverse().toString();
            System.out.print(reverse + "\n");

        }
    }
    public static void countSymbolsInArray(String[] array){
        for (String s : array) {
            System.out.print(s + " Съдържа " + s.length() + " символа \n");
        }

    }
    public static void countRepeatingWords(String[] array) {
        System.out.print("Повторения: ");
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].toLowerCase();
            for (int j = 0; j < array.length; j++) {
                int  count = 1;
                for (int z = j + 1; z < array.length; z++) {
                    if (array[j].equals(array[z])) {
                        count++;
                        array[z] = "0";
                    }
                }
                if (count > 1 && !array[j].equals("0")) System.out.println(count + " -> " + array[j]);
            }
        }
    }

}
