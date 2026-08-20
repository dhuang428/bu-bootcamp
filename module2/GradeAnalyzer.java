import java.io.*; 
import java.util.ArrayList;
 
public class GradeAnalyzer {
    // make these global so can capture in readScores method and use in writeReport method
    static int scoresProcessed = 0;
    static int invalidLinesSkipped = 0;

    // make these global so can capture in Main and use in writeReport method
    static int currentScore;
    static int countA = 0;
    static int countB = 0;        
    static int countC = 0;
    static int countD = 0;
    static int countF = 0;    

    public static void main(String[] args) {
        String inputFilename = "scores.txt";
        // String inputFilename = "scoresEmpty.txt";
        // String inputFilename = "scoresInvalid.txt";
        // String inputFilename = "scoresSingle.txt";

        double avgScore;
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;

        String outputFilename = "report.txt";

        // Step 1: read scores from file
        ArrayList<Integer> scoresFromFile = new ArrayList<Integer>();  // create new ArrayList object to store scores
        scoresFromFile = readScores(inputFilename); // get the scores from the file

        if(scoresFromFile.isEmpty()) {  // handle scenario if input file is empty or has no numbers
            System.out.println("There are no scores to process. No report will be generated.");
        }
        else {  // only try to calculate average, highest, lowest numbers if there were numbers in the file
            // Step 2: calculate statistics
            avgScore = calculateAverage(scoresFromFile);    // get average score
            System.out.println(String.format("Average score: %.2f", avgScore));

            // highest = calculateHighest(scoresFromFile);     // Commenting out because did not need method for getting highest number
            for(int i = 0; i < scoresFromFile.size(); i++) {
                // if highest number is less than the current number...
                if(highest < scoresFromFile.get(i)) {
                    highest = scoresFromFile.get(i); // ...then set the highest number to the current number
                }   // there is no else needed here

                // System.out.println("Main - current num is: " + scoresFromFile.get(i));
                // System.out.println("Main - highest so far is: " + highest);
            }
            System.out.println("Highest score: " + highest);

            // lowest = calculateLowest(scoresFromFile);       // Commenting out because did not need method for getting lowest number
            for(int i = 0; i < scoresFromFile.size(); i++) {
                // if lowest number is greater than the current number...
                if(lowest > scoresFromFile.get(i)) {
                    lowest = scoresFromFile.get(i); // ...then set the lowest number to the current number
                }   // there is no else needed here

                // System.out.println("Main - current num is: " + scoresFromFile.get(i));
                // System.out.println("Main - lowest so far is: " + lowest);
            }
            System.out.println("Lowest score: " + lowest);

            // Count the Grade Bands
            for(int i = 0; i < scoresFromFile.size(); i++) {
                currentScore = scoresFromFile.get(i);

                if(currentScore >= 90) {
                    countA = countA + 1;
                }
                else if(currentScore >= 80 && currentScore <= 89) {
                    countB = countB + 1;
                }
                else if(currentScore >= 70 && currentScore <= 79) {
                    countC = countC + 1;
                }
                else if(currentScore >= 60 && currentScore <= 69) {
                    countD = countD + 1;
                }
                else if(currentScore < 60) {
                    countF = countF + 1;
                }                             
            }

            System.out.println(String.format("A (90-100): %d", countA));
            System.out.println(String.format("B (80-89): %d", countB));            
            System.out.println(String.format("C (70-79): %d", countC));
            System.out.println(String.format("D (60-69): %d", countD));
            System.out.println(String.format("F (below 60): %d", countF));

            // Step 3: write and print report
            writeReport(scoresFromFile,avgScore,highest,lowest,outputFilename);
        }
    } 
 
    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        // your code here
        System.out.println("Processing file...");
        ArrayList<Integer> scoresToReturn = new ArrayList<Integer>(); // create new ArrayList object

        // start reading from the file and populating the ArrayList object
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int n;

            while((line = reader.readLine()) != null) { // loop until we hit the end of the file
                try {                  
                    if((line.trim()).isEmpty()) {   // if trimmed line is empty then skip it
                        System.out.println("readScores - WARNING: skipping blank line");
                        invalidLinesSkipped = invalidLinesSkipped + 1;  // keep overall count of number of invalid records skipped from the input file
                    }
                    else {  // if line has some value then add it to the ArrayList if it is a number
                        n = Integer.parseInt(line.trim());
                        scoresToReturn.add(n); // If parseInt does not fail, then add to the ArrayList
                        System.out.println("readScores - number added to ArrayList: " + n);
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("readScores - WARNING: parseInt NumberFormatException. Value '" + line + "' not added to ArrayList.");
                    invalidLinesSkipped = invalidLinesSkipped + 1;  // keep overall count of number of invalid records skipped from the input file                  
                }
                
                n = 0;  // reset n to zero before next loop. Should be fine even if number in file is zero.            
                scoresProcessed = scoresProcessed + 1;  // keep overall count of number of records processed from the input file
            }
        }
        catch (IOException e) {
            System.out.println("readScores - hit IOException: " + e.getMessage());
        }

        // System.out.println("readScores - number of items added to ArrayList: " + scoresToReturn.size());
        System.out.println(String.format("Total scores processed: %d", scoresProcessed));
        System.out.println(String.format("Invalid lines skipped: %d", invalidLinesSkipped));
        return scoresToReturn;
    }
 
    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        // your code here
        double calcAvg;
        double sum = 0.0;   // variable has to be double (not int) because otherwise decimals in average will be messed up

        if(scores.size() == 0) {    // if ArrayList is empty
            calcAvg = 0.0;  // then set to return 0.0 later
        }
        else {  // if ArrayList has values
            //System.out.println("calculateAverage - Number of items in ArrayList is: " + scores.size());
    
            for(int i = 0; i < scores.size(); i++) {
                // System.out.println("calculateAverage - for loop. i is: " + Integer.toString(i));
                // System.out.println("calculateAverage - ArrayList value: " + scores.get(i));

                sum = sum + scores.get(i);  // add up all the numbers in the ArrayList
            }

            //System.out.println("calculateAverage - Sum is: " + sum);
        
            calcAvg = sum / scores.size();  // calculate the average
            //System.out.println("calculateAverage - calcAvg is " + sum + " divided by " + scores.size() + " = " + calcAvg);
        }

        return calcAvg;
    }

    /*
    Commenting out because did not need methods for getting highest and lowest numbers but tried it anyways

    // Returns the highest of a list of scores, or 0 if the list is empty
    public static int calculateHighest(ArrayList<Integer> scores) {
        int calcHighest = 0;

        if(scores.size() == 0) {    // if ArrayList is empty
            calcHighest = 0;  // then set to return 0 later
        }
        else {  // if ArrayList has values
            System.out.println("calculateHighest - Number of items in ArrayList is: " + scores.size());
    
            for(int i = 0; i < scores.size(); i++) {
                // System.out.println("calculateHighest - for loop. i is: " + i);
                // System.out.println("calculateHighest - ArrayList value: " + scores.get(i));

                // if highest number is less than the current number...
                if(calcHighest < scores.get(i)) {
                    calcHighest = scores.get(i); // then set the highest number to the current number
                }   // there is no else needed here

                System.out.println("calculateHighest - current num is: " + scores.get(i));
                System.out.println("calculateHighest - highest so far is: " + calcHighest);
            }

            System.out.println("calculateHighest - highest is: " + calcHighest);     
        }

        return calcHighest;
    }

    // Returns the lowest of a list of scores, or 0 if the list is empty
    public static int calculateLowest(ArrayList<Integer> scores) {
        int calcLowest = scores.get(0); // start with lowest value being the first score in the ArrayList so you can compare later

        if(scores.size() == 0) {    // if ArrayList is empty
            calcLowest = 0;  // then set to return 0 later
        }
        else {  // if ArrayList has values
            System.out.println("calculateLowest - Number of items in ArrayList is: " + scores.size());
    
            for(int i = 0; i < scores.size(); i++) {
                // System.out.println("calculateLowest - for loop. i is: " + i);
                // System.out.println("calculateLowest - ArrayList value: " + scores.get(i));

                // if lowest number is greater than the current number...
                if(calcLowest > scores.get(i)) {
                    calcLowest = scores.get(i); // then set the lowest number to the current number
                }   // there is no else needed here

                System.out.println("calculateLowest - current num is: " + scores.get(i));
                System.out.println("calculateLowest - lowest so far is: " + calcLowest);
            }

            System.out.println("calculateLowest - lowest is: " + calcLowest);     
        }

        return calcLowest;
    }
    */
 
    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   String outputFile) {
        // your code here
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(String.format("=== Grade Analysis Report ===%n"));

            writer.write(String.format("Total scores processed: %d%n",scoresProcessed));

            writer.write(String.format("Invalid lines skipped: %2d%n%n", invalidLinesSkipped));

            writer.write(String.format("Average score: %.2f%n", avg));

            writer.write(String.format("Highest score: %d%n", high));
            
            writer.write(String.format("Lowest score: %3d%n%n", low));
            
            writer.write(String.format("Grade distribution:%n"));

            writer.write(String.format("  A (90-100): %3d%n", countA));
            writer.write(String.format("  B (80-89): %4d%n", countB));
            writer.write(String.format("  C (70-79): %4d%n", countC));
            writer.write(String.format("  D (60-69): %4d%n", countD));                                    
            writer.write(String.format("  F (below 60): %1d%n", countF));            
        } catch (IOException e) {
            System.out.println("Could not write file: " + e.getMessage());
        }
    }
}