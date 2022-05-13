package dev.woofer.anagramstrainerbot;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> threeLetterWords = new ArrayList<>();
    public static ArrayList<String> fourLetterWords = new ArrayList<>();
    public static ArrayList<String> fiveLetterWords = new ArrayList<>();
    public static ArrayList<String> sixLetterWords = new ArrayList<>();
    public static ArrayList<String> sevenLetterWords = new ArrayList<>();

    public static int wordEntries = 0;
    public static String characters;

    public static void main(String[] args) {
        File anagramsWords = new File("/Users/chris/Documents/IdeaProjects/anagrams-trainer-bot/anagrams-words.txt");
        try {
            Scanner anagramsWordsScanner = new Scanner(anagramsWords);
            Scanner inputScanner = new Scanner(System.in);
            while (true) {
                System.out.println("Please enter the 7 characters from the game.");
                characters = inputScanner.nextLine();
                if (characters.length() > 7) {
                    System.out.println("Too many characters were provided!");
                } else if (characters.length() < 7) {
                    System.out.println("Too few characters were provided!");
                } else {
                    break;
                }
            }

            AnagramsCharacterList inputCharList = new AnagramsCharacterList(characters);

            while (anagramsWordsScanner.hasNextLine()) {
                String currentWord = anagramsWordsScanner.nextLine();
                AnagramsCharacterList currentWordAsCharList = new AnagramsCharacterList(currentWord);
                if (currentWordAsCharList.canBeMadeUpBy(inputCharList)) {
                    switch (currentWord.length()) {
                        case 3:
                            threeLetterWords.add(currentWord);
                            break;
                        case 4:
                            fourLetterWords.add(currentWord);
                            break;
                        case 5:
                            fiveLetterWords.add(currentWord);
                            break;
                        case 6:
                            sixLetterWords.add(currentWord);
                            break;
                        case 7:
                            sevenLetterWords.add(currentWord);
                            break;
                    }
                }
            }
            printData();
            if (dataContainsSpellingOfWord(characters)) {
                System.out.println("Inputted word was already found in save data.");
            } else {
                System.out.println("Save data? (y/n)");
                while (true) {
                    String response = inputScanner.nextLine().toLowerCase();
                    if (response.equals("y")) {
                        saveData();
                        break;
                    } else if (response.equals("n")) {
                        break;
                    } else {
                        System.out.println("You must enter \"y\" to save the data or \"n\" to skip!");
                    }
                }
            }
            anagramsWordsScanner.close();
            inputScanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String pointsToPercentOfTotalPoints(int points, int totalPoints) {
        return (double) (Math.round((double) (points) / totalPoints * 100 * 10)) / 10 + "% of points";
    }

    public static String wordsToPercentOfTotalWords(int words, int totalWords) {
        return (double) (Math.round((double) (words) / totalWords * 100 * 10)) / 10 + "% of words";
    }

    private static void printData() {
        int sevenLetterPoints = sevenLetterWords.size() * 3000;
        int sixLetterPoints = sixLetterWords.size() * 2000;
        int fiveLetterPoints = fiveLetterWords.size() * 1200;
        int fourLetterPoints = fourLetterWords.size() * 400;
        int threeLetterPoints = threeLetterWords.size() * 100;
        int totalPoints = sevenLetterPoints + sixLetterPoints + fiveLetterPoints + fourLetterPoints + threeLetterPoints;
        int totalWords = sevenLetterWords.size() + sixLetterWords.size() + fiveLetterWords.size() + fourLetterWords.size() + threeLetterWords.size();
        System.out.println(characters.toUpperCase());
        System.out.println("    Possible points: " + totalPoints);
        System.out.println("    Number of words: " + totalWords);
        System.out.println("    Seven letter words: " + sevenLetterWords.size() + " (" + pointsToPercentOfTotalPoints(sevenLetterPoints, totalPoints) + ", " + wordsToPercentOfTotalWords(sevenLetterWords.size(), totalWords)  + ")");
        System.out.println("        " + sevenLetterPoints + " possible points");
        if (sevenLetterPoints > 0) {
            System.out.println("        " + sevenLetterWords);
        }
        System.out.println("    Six letter words: " + sixLetterWords.size() + " (" + pointsToPercentOfTotalPoints(sixLetterPoints, totalPoints) + ", " + wordsToPercentOfTotalWords(sixLetterWords.size(), totalWords)  + ")");
        System.out.println("        " + sixLetterPoints + " possible points");
        if (sixLetterPoints > 0) {
            System.out.println("        " + sixLetterWords);
        }
        System.out.println("    Five letter words: " + fiveLetterWords.size() + " (" + pointsToPercentOfTotalPoints(fiveLetterPoints, totalPoints) + ", " + wordsToPercentOfTotalWords(fiveLetterWords.size(), totalWords)  + ")");
        System.out.println("        " + fiveLetterPoints + " possible points");
        if (fiveLetterPoints > 0) {
            System.out.println("        " + fiveLetterWords);
        }
        System.out.println("    Four letter words: " + fourLetterWords.size() + " (" + pointsToPercentOfTotalPoints(fourLetterPoints, totalPoints) + ", " + wordsToPercentOfTotalWords(fourLetterWords.size(), totalWords)  + ")");
        System.out.println("        " + fourLetterPoints + " possible points");
        if (fourLetterPoints > 0) {
            System.out.println("        " + fourLetterWords);
        }
        System.out.println("    Three letter words: " + threeLetterWords.size() + " (" + pointsToPercentOfTotalPoints(threeLetterPoints, totalPoints) + ", " + wordsToPercentOfTotalWords(threeLetterWords.size(), totalWords) + ")");
        System.out.println("        " + threeLetterPoints + " possible points");
        if (threeLetterPoints > 0) {
            System.out.println("        " + threeLetterWords);
        }
    }

    public static void saveData() {
        try {
            File anagramsData = new File("/Users/chris/Documents/IdeaProjects/anagrams-trainer-bot/anagrams-data.txt");
            Scanner inputScanner = new Scanner(System.in);
            FileWriter anagramsDataWriter = new FileWriter(anagramsData, true);
            int sevenLetterPoints = sevenLetterWords.size() * 3000;
            int sixLetterPoints = sixLetterWords.size() * 2000;
            int fiveLetterPoints = fiveLetterWords.size() * 1200;
            int fourLetterPoints = fourLetterWords.size() * 400;
            int threeLetterPoints = threeLetterWords.size() * 100;
            int totalPoints = sevenLetterPoints + sixLetterPoints + fiveLetterPoints + fourLetterPoints + threeLetterPoints;
            int totalWords = sevenLetterWords.size() + sixLetterWords.size() + fiveLetterWords.size() + fourLetterWords.size() + threeLetterWords.size();
            if (sevenLetterWords.size() > 1) {
                while (true) {
                    System.out.println("Please enter a word to store the data entry as.");
                    String dataEntryTitle = inputScanner.nextLine();
                    if (new AnagramsCharacterList(dataEntryTitle).equals(new AnagramsCharacterList(characters))) {
                        anagramsDataWriter.write((wordEntries + 1) + ". " + dataEntryTitle.toUpperCase() + "\n");
                        break;
                    } else {
                        System.out.println("The word you entered is not a spelling of the original characters entered.");
                    }
                }
            } else if (sevenLetterWords.size() == 1) {
                anagramsDataWriter.write((wordEntries + 1) + ". " + sevenLetterWords.get(0) + "\n");
            } else {
                anagramsDataWriter.write((wordEntries + 1) + ". " + characters.toUpperCase() + "\n");
            }
            anagramsDataWriter.write("    Possible points: " + totalPoints + "\n");
            anagramsDataWriter.write("    Number of words: " + totalWords + "\n");
            anagramsDataWriter.write("    Seven letter words: " + sevenLetterWords.size() + "\n");
            if (sevenLetterPoints > 0) {
                anagramsDataWriter.write("        " + sevenLetterWords + "\n");
            }
            anagramsDataWriter.write("    Six letter words: " + sixLetterWords.size() + "\n");
            if (sevenLetterPoints > 0) {
                anagramsDataWriter.write("        " + sixLetterWords + "\n");
            }
            anagramsDataWriter.write("    Five letter words: " + fiveLetterWords.size() + "\n");
            if (sevenLetterPoints > 0) {
                anagramsDataWriter.write("        " + fiveLetterWords + "\n");
            }
            inputScanner.close();
            anagramsDataWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Data was saved.");
    }

    public static boolean dataContainsSpellingOfWord(String inputWord) {
        try {
            File anagramsData = new File("/Users/chris/Documents/IdeaProjects/anagrams-trainer-bot/anagrams-data.txt");
            Scanner anagramsDataScanner = new Scanner(anagramsData);
            AnagramsCharacterList inputWordCharList = new AnagramsCharacterList(inputWord);
            while (anagramsDataScanner.hasNextLine()) {
                String currentLine = anagramsDataScanner.nextLine();
                String[] splitLine = currentLine.split(" ");
                if (splitLine.length == 2) {
                    String currentWord = splitLine[1];
                    if (currentWord.length() == 7) {
                        wordEntries++;
                        AnagramsCharacterList currentAnagramsCharList = new AnagramsCharacterList(currentWord);
                        if (currentAnagramsCharList.equals(inputWordCharList)) return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}