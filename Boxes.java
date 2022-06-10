/* Boxes.java
 * David Smith 
 * 06/08/2022
 *
 *  www.javatpoint.com - Used to figure out the String.format() method.
 */

import java.util.Scanner;

/* This class will take a phrase chosen by the user and enclose it in a box of 
* made up of a character also chosen by the user. */
public class Boxes {
    
    /* Runs a program to display a phrase chosen by the user within a box made up 
     * of a character also chosen by the user.
     */
    public static void main(String[] args){
        
        //Instantiate a scanner to accept user input
        Scanner sc = new Scanner(System.in);

        //Prompt for and store the user input for the phrase as a string.
        System.out.println("Please enter a phrase: ");
        String phrase = sc.nextLine();
        System.out.println();//Blank line for spacing
        
        //Prompt and store the user input for border character as a char from index 0.
        System.out.println("Enter a character: ");
        char borderChar = sc.next().charAt(0);
        System.out.println();//Blank line for spacing
        
        //close the scanner because we are done with it
        sc.close();
        
        //This is the maximum width to be displayed on a single line within the box
        int maxStringWidth = 30; 
        
        //Format the phrase for ease of use
        phrase = formatPhrase(phrase, maxStringWidth);
        
        //The next three lines will print the box top to bottom
        solidCharLine(phrase.length(), maxStringWidth, borderChar);
        printMessage(phrase, maxStringWidth, borderChar);
        solidCharLine(phrase.length(), maxStringWidth, borderChar);
        
        //Print out the exit message
        gameOverMan();
    }    
    
    /*
     * Checks if the phrase is more than maxStringWidth and whether it is 
     * not divisible by maxStringWidth. If both these conditions are true
     * will append blank spaces to the phrase so that it can be divided better
     * when printing.
     * 
     * phraseToFormat - is the phrase the user chose at the start of the program
     * maxStringWidth - is the longest a line in the box can be default of 30
     * 
     * Will return the original phrase unless the phrase needed empty spaces
    */
    public static String formatPhrase(String phraseToFormat, int maxStringWidth){
        
        /*
         * This checks if the chosen phrase is greater than maxStringWidth if it
         * is not the if short circuits and returns the string as it was passed 
         * to the function, no alteration is needed. 
         * 
         * If the string is longer it then checks that the phrase is divisible by 
         * maxStringWidth. This is needed so that the last line of the phrase when
         * printed will be the same length as the previous lines which will all
         * be maxStringWidth long.
         * 
         * The tempForFormat string is concatenation to create a format string 
         * with the correct number of spaces to be added. This is necessary for 
         * the String.format() method. 
         * 
         * The string looks like %-##s this where the number signs are actually 
         * numbers and are the number of spaces that need to be added. 
         * 
         */
        if(phraseToFormat.length() > maxStringWidth && phraseToFormat.length() % maxStringWidth != 0){
            int neededSpaces = maxStringWidth - (phraseToFormat.length() % maxStringWidth);
            String tempForFormat = "%-" + (phraseToFormat.length() + neededSpaces) + "s";
            
            return String.format(tempForFormat, phraseToFormat);
        }

        return phraseToFormat;
    }
    
    /*
     * Prints the phrase chosen by the user within a box
     *  
     * phraseToPrint - The phrase chosen by the user at the start of the program
     * maxStringWidth - The longest a line of text in the box can be, defaults to 30
     * borderChar - The char chosen by the user to be used as the boxes border
     */
    public static void printMessage(String phraseToPrint, int maxStringWidth, char borderChar){
        
        //Check if the phrase is short enough to print on a single line and do so 
        //if it is, if not print a substring of the phrase with a length of maxStringWidth
        //before removing that portion of the phrase and calling printMessage again.
        if(phraseToPrint.length() <= maxStringWidth){
            System.out.printf("%c %s %c\n", borderChar, phraseToPrint, borderChar);
        }else{ 
            System.out.printf("%c %s %c\n", borderChar, 
                                phraseToPrint.substring(0, maxStringWidth),
                                borderChar);
            phraseToPrint = phraseToPrint.substring(maxStringWidth);
            printMessage(phraseToPrint, maxStringWidth, borderChar);
        }
    }

    /*
     * Prints a number of the chosen character to a single line. The number of 
     * characters printed is the smaller value of the length of the phrase and
     * the maxStringWidth. This allows the box to be smaller than 34 chars wide.
     * 
     * widthOfBoxContents - This is the length of the phrase chosen by the user
     * maxStringWidth - This is the maximum length for a single line within the box
     * borderChar - This is the character to be printed and is chosen by the user
     */
    public static void solidCharLine(int widthOfBoxContents, int maxStringWidth, char borderChar){
        
        int spacesForBorder = 4; //This is the space needed to make the border
        
        /*Set the widthOfBoxContents to the lower value, this will allow a smaller
         * phrase to be printed in an appropriately sized box
         */
        widthOfBoxContents = Math.min(widthOfBoxContents, maxStringWidth);
        
        /* Use a loop to print a certain number of the chosen char to the screen
         * The number printed is the number of spaces needed for the border plus 
         * either the length of the phrase or the maxStringWidth, whichever is smaller
         */
        for(int i = 0; i < (widthOfBoxContents + spacesForBorder); i++){
            System.out.print(borderChar);
        }
        System.out.println();//Blank line to move to next line
    }

    //This method outputs the Game over message
    public static void gameOverMan(){
        System.out.println();
        System.out.println();
        System.out.println("GAME OVER MAN!");
    }
}
