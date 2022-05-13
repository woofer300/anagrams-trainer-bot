package dev.woofer.anagramstrainerbot;

public class AnagramsCharacterList {
    private final int[] countOfEachChar = new int[26];
    private final char[] numToLetter = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public AnagramsCharacterList(String characters) {
        String charactersAllCaps = characters.toUpperCase();
        char currentChar;
        for (int i = 0; i < charactersAllCaps.length(); i++) {
            currentChar = charactersAllCaps.charAt(i);
            switch (currentChar) {
                case 'A':
                    countOfEachChar[0]++;
                    break;
                case 'B':
                    countOfEachChar[1]++;
                    break;
                case 'C':
                    countOfEachChar[2]++;
                    break;
                case 'D':
                    countOfEachChar[3]++;
                    break;
                case 'E':
                    countOfEachChar[4]++;
                    break;
                case 'F':
                    countOfEachChar[5]++;
                    break;
                case 'G':
                    countOfEachChar[6]++;
                    break;
                case 'H':
                    countOfEachChar[7]++;
                    break;
                case 'I':
                    countOfEachChar[8]++;
                    break;
                case 'J':
                    countOfEachChar[9]++;
                    break;
                case 'K':
                    countOfEachChar[10]++;
                    break;
                case 'L':
                    countOfEachChar[11]++;
                    break;
                case 'M':
                    countOfEachChar[12]++;
                    break;
                case 'N':
                    countOfEachChar[13]++;
                    break;
                case 'O':
                    countOfEachChar[14]++;
                    break;
                case 'P':
                    countOfEachChar[15]++;
                    break;
                case 'Q':
                    countOfEachChar[16]++;
                    break;
                case 'R':
                    countOfEachChar[17]++;
                    break;
                case 'S':
                    countOfEachChar[18]++;
                    break;
                case 'T':
                    countOfEachChar[19]++;
                    break;
                case 'U':
                    countOfEachChar[20]++;
                    break;
                case 'V':
                    countOfEachChar[21]++;
                    break;
                case 'W':
                    countOfEachChar[22]++;
                    break;
                case 'X':
                    countOfEachChar[23]++;
                    break;
                case 'Y':
                    countOfEachChar[24]++;
                    break;
                case 'Z':
                    countOfEachChar[25]++;
                    break;
            }
        }
    }

    public int[] getCountOfEachChar() {
        return countOfEachChar;
    }

    public boolean canBeMadeUpBy(AnagramsCharacterList characterList) {
        int[] inputCountOfEachChar = characterList.getCountOfEachChar();
        for (int i = 0; i < 26; i++) {
            if (countOfEachChar[i] > inputCountOfEachChar[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnagramsCharacterList) {
            int[] inputCountOfEachChar = ((AnagramsCharacterList) obj).getCountOfEachChar();
            for (int i = 0; i < 26; i++) {
                if (countOfEachChar[i] != inputCountOfEachChar[i]) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            toReturn.append(numToLetter[i]).append(": ").append(countOfEachChar[i]).append(",\n");
        }
        return toReturn.toString();
    }
}
