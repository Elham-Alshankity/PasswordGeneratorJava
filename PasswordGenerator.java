package com.mycompany.passwordgenerator;

/**
 * PasswordGenerator
 * 
 * A utility class to generate custom passwords with varying strength levels.
 * 
 * @author Elham
 * Date: April 2025
 */
public class PasswordGenerator {
   public static void main(String[] args) {
        //Test generateCustomPW method
        System.out.printf("Strong password (4 types): %s\n", generateCustomPW (4, 8));
        System.out.printf("Medium password (3 types): %s\n", generateCustomPW (3, 8));
        System.out.printf("Weak password (2 types): %s\n", generateCustomPW (2, 8));
        System.out.printf("Weak2 password (1 type): %s\n", generateCustomPW (1, 8));
        System.out.printf("Invalid input: %s\n", generateCustomPW (5, 8));
        System.out.println("");
        
        //Test shuffleArray method
        int[] arr = {0,1,2,3,4,5};
        shuffleArray (arr);
        System.out.println("Shuffled array:");
        for (int i : arr) {
            System.out.printf("%d " , i);
        }
        System.out.println("\n");
        
        //Test shuffleString method
        String word = "Word";
        System.out.println("word = " + shuffleString(word, word.length()));
    }
   
   
   /**
     * Generates a password of a specified length and desired strength level.
     * The strength is based on how many character types (uppercase, lowercase, digits, symbols) are included.
     * @param len         the total length of the password
     * @param numOfItems  the number of different character types to include (1 to 4)
     * @return            a randomly generated password that matches the desired strength level
     */
    public static String generateCustomPW (int numOfItems, int len) {
        //Validate the number of items
        if (numOfItems > 4 || numOfItems < 1)
            return "Invalid, the items must be between 1 and 4";
        
        //Declare Password components as constants
        //We split components into 4 because we'll need it to do three passwords with all different levels
        final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
        final String DIGITS = "0123456789";
        final String SYMBOLS = "!@#$%&*?+=_-"; 
        
        //Declare and initialised the return value/Password
        String password = "";
        
        /* This array refers to all the 4 character types (Uppercase, Lowercase letters, digits, and symbols)
        helps to control which components are included in the password */
        int[] charTypes = {0, 1, 2, 3};
             
        
        //Shuffle the charTypes array to ensure a random order of the 4 components 
        shuffleArray (charTypes);

        
        //First loop is ensuring the password include at least one character from each selected type
        for (int i = 0; i < numOfItems; i++) {
            int random_index;

            switch (charTypes[i]) {
                case 0:
                    random_index = (int)(Math.random()*UPPERCASE_LETTERS.length()); //range is: [0,25]
                    password += UPPERCASE_LETTERS.charAt(random_index);
                    break;
                case 1:
                    random_index = (int)(Math.random()*LOWERCASE_LETTERS.length()); //range is: [0,25]
                    password += LOWERCASE_LETTERS.charAt(random_index);
                    break;
                case 2:
                    random_index = (int)(Math.random()*DIGITS.length()); //range is: [0,9]
                    password += DIGITS.charAt(random_index);
                    break;
                case 3:
                    random_index = (int)(Math.random()*SYMBOLS.length()); //range is: [0,25]
                    password += SYMBOLS.charAt(random_index);
                    break;
                default: break;
            } 
        }
        
        
        //Second loop fill the rest of password randomly until it reach the required length
        //it will not exceed the selected number of character types
        for (int i = numOfItems; i < len; i++ ) {
            int random_index;
            int random_item = (int)(Math.random()*numOfItems); 
                 
            switch (charTypes[random_item]) {
                case 0 : 
                    random_index = (int)(Math.random()*UPPERCASE_LETTERS.length()); 
                    password += UPPERCASE_LETTERS.charAt(random_index);
                    break;
                case 1 : 
                    random_index = (int)(Math.random()*LOWERCASE_LETTERS.length());
                    password += LOWERCASE_LETTERS.charAt(random_index);
                    break;
                case 2 : 
                    random_index = (int)(Math.random()*DIGITS.length()); 
                    password += DIGITS.charAt(random_index);
                    break;
                case 3 : 
                    random_index = (int)(Math.random()*SYMBOLS.length()); 
                    password += SYMBOLS.charAt(random_index);
                    break;
                default :break;
            }
        }
        
        // We shuffle the final password to make it less predictable, since the first part adds one of each type in order
        password = shuffleString (password ,len);

        return password;
    }
    
    /**
     * Randomly shuffles the elements of the given array in place
     * 
     * This methods helps to randomize the order of character types.
     * (Uppercase, Lowercase, Digits, Symbols) to create less predictable password.
     * 
     * @param arr The array to be shuffled
     * 
     * The idea inspired from: https://youtu.be/8I37elnmZ2I?si=84kY357BPnl5PrAv 
     */
    public static void shuffleArray (int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int random_index = (int)(Math.random()*arr.length); //range is: [0,3]
            int temp = arr [random_index];
            arr [random_index] = arr [i];
            arr [i] = temp;
        }
    }
    
    
    
    /**
     * Randomly shuffles the characters of a given string to increase unpredictability.
     * 
     * The method swaps pairs of characters in the string up to half its length, using random indices.
     * Useful for enhancing the randomness of generated passwords or any string content.
     * 
     * @param text The original string to be shuffled.
     * @param len  The length of the string (typically text.length()).
     * @return     A new string with characters shuffled randomly.
     */
    public static String shuffleString (String text , int len) {
         for (int i = 0; i < len/2; i++) {
            int random_index = (int)(Math.random()*len); //range is: [0,len-1]

            if (i == random_index)
                continue;
            
            int minIndex = Math.min(i, random_index);
            int maxIndex = Math.max(i, random_index);
            
            String part1 = text.substring(0, minIndex) + text.charAt(maxIndex);
            String part2 = text.substring(minIndex+1, maxIndex);
            String part3 = text.charAt(minIndex) + text.substring(maxIndex+1);
            
            text = part1 + part2 + part3;
        }
         
         return text;
    }
}


