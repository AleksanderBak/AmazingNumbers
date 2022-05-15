package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static boolean even(long num) {
        return num % 2 == 0;
    }

    public static boolean buzz(long num) {
        return num % 7 == 0 || num % 10 == 7;
    }

    public static boolean duck(long num) {
        while (num > 0) {
            if (num % 10 == 0) {
                return true;
            }
            num /= 10;
        }
        return false;
    }

    public static boolean palindromic(long num) {
        String x1 = String.valueOf(num);
        String x2 = "";
        for (int i = x1.length()-1; i >= 0; i--) {
            x2 += x1.charAt(i);
        }
        return x2.equals(x1);
    }

    public static boolean gapful(long num) {
        String input = String.valueOf(num);
        if (input.length() < 3) {
            return false;
        } else {
            long div = Long.parseLong(""+input.charAt(0)+input.charAt(input.length() - 1));
            long number =  Long.parseLong(input);
            return number % div == 0;
        }
    }

    public static boolean spy(long num) {
        long product = 1;
        long sum = 0;
        while (num != 0) {
            product = product * (num % 10);
            sum += num % 10;
            num = num / 10;
        }
        return product == sum;
    }

    public static boolean square(long num) {
        double sqrt=Math.sqrt(num);
        return ((sqrt - Math.floor(sqrt)) == 0);
    }

    public static boolean sunny(long num) {
        return square(num + 1);
    }

    public static void showStats(long num1, long num2) {
        for (long i = num1; i < num1 + num2; i++) {
            String res = ""+i+" is ";

            res += Main.even(i) ? "even" : "odd";

            if (Main.buzz(i)) {
                res += ", buzz";
            }

            if (Main.duck(i)) {
                res += ", duck";
            }

            if (Main.palindromic(i)) {
                res += ", palindromic";
            }

            if (Main.gapful(i)) {
                res += ", gapful";
            }

            if (Main.spy(i)) {
                res += ", spy";
            }

            if (Main.square(i)) {
                res += ", square";
            }

            if (Main.sunny(i)) {
                res += ", sunny";
            }

            System.out.println(res);
        }
    }

    public static boolean checkType(String type, long num) {
        return switch (type) {
            case "even" -> Main.even(num);
            case "odd" -> !Main.even(num);
            case "buzz" -> Main.buzz(num);
            case "duck" -> Main.duck(num);
            case "palindromic" -> Main.palindromic(num);
            case "gapful" -> Main.gapful(num);
            case "spy" -> Main.spy(num);
            case "square" -> Main.square(num);
            case "sunny" -> Main.sunny(num);
            default -> false;
        };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing numbers!");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed");
        System.out.println("- two natural numbers and two properties to search for");
        System.out.println("- separate the parameters with one space");
        System.out.println("- enter 0 to exit.");

        boolean running = true;

        while (running) {
            boolean pause = false;
            System.out.print("Enter a request: ");
            String[] input = scanner.nextLine().split(" ");
            long num1 = Long.parseLong(input[0]);

            if (num1 < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                pause = true;
            } else if (num1 == 0) {
                running = false;
                pause = true;
            }

            if (!pause) {
                if (input.length == 2) {
                    long num2 = Long.parseLong(input[1]);

                    if (num2 < 0) {
                        System.out.println("The second parameter should be a natural number");
                    } else {
                        showStats(num1, num2);
                    }

                } else if (input.length == 3) {
                    String[] commands = new String[]{"even", "odd", "buzz", "duck", "palindromic", "gapful", "spy", "sunny", "square"};
                    long num2 = Long.parseLong(input[1]);
                    String type = input[2].toLowerCase();
                    if (Arrays.asList(commands).contains(type)) {
                        long found = 0;

                        while (found < num2) {
                            if (checkType(type, num1)) {
                                found += 1;
                                showStats(num1, 1);
                            }
                            num1 += 1;
                        }
                    } else {
                        System.out.printf("The property [%s] is wrong. \n",type);
                        System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SUNNY, SQUARE]");
                    }
                } else if (input.length == 4) {
                    String[] commands = new String[]{"even", "odd", "buzz", "duck", "palindromic", "gapful", "spy", "sunny", "square"};
                    long num2 = Long.parseLong(input[1]);
                    String type1 = input[2].toLowerCase();
                    String type2 = input[3].toLowerCase();

                    if (Arrays.asList(commands).contains(type1) && Arrays.asList(commands).contains(type2)) {
                        if ((type1.equals("odd") && type2.equals("even")) || (type1.equals("even") && type2.equals("odd"))){
                            System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", type1, type2);
                            System.out.print("There are no numbers with these properties\n");
                        } else if (type1.equals("sunny") && type2.equals("square") || type1.equals("square") && type2.equals("sunny")) {
                            System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", type1, type2);
                            System.out.print("There are no numbers with these properties\n");
                        } else if ((type1.equals("duck") && type2.equals("spy")) || (type1.equals("spy") && type2.equals("duck"))) {
                            System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", type1, type2);
                            System.out.print("There are no numbers with these properties\n");
                        } else {
                            long found = 0;

                            while (found < num2) {
                                if (checkType(type1, num1) && checkType(type2, num1)) {
                                    found += 1;
                                    showStats(num1, 1);
                                }
                                num1 += 1;
                            }
                        }

                    } else if (!Arrays.asList(commands).contains(type1) && !Arrays.asList(commands).contains(type2)) {
                        System.out.printf("The properties [%s, %s] are wrong.", type1, type2);
                        System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SUNNY, SQUARE]");
                    } else if (!Arrays.asList(commands).contains(type1)){
                        System.out.printf("The property [%s] is wrong. \n",type1);
                        System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SUNNY, SQUARE]");
                    } else if (!Arrays.asList(commands).contains(type2)){
                        System.out.printf("The property [%s] is wrong. \n",type2);
                        System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD, SUNNY, SQUARE]");
                    }

                } else {
                    long num = Long.parseLong(input[0]);
                    if (num < 0) {
                        System.out.println("The first parameter should be a natural number or zero.");
                    } else if (num == 0) {
                        running = false;
                    } else {
                        boolean even = Main.even(num);
                        boolean odd = !even;

                        System.out.printf("Properties of %d\n", num);
                        System.out.printf("Even: %b\n", even);
                        System.out.printf("Odd: %b\n", odd);
                        System.out.printf("Buzz: %b\n", Main.buzz(num));
                        System.out.printf("Duck: %b\n", Main.duck(num));
                        System.out.printf("Palindromic: %b\n", Main.palindromic(num));
                        System.out.printf("Gapful: %b\n", Main.gapful(num));
                        System.out.printf("Spy: %b\n", Main.spy(num));
                        System.out.printf("Square: %b\n", Main.square(num));
                        System.out.printf("Sunny: %b\n", Main.sunny(num));
                    }
                }

            }
        }
    }
}
