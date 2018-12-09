import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GradeBookToDev {

    private static Scanner scanner = new Scanner(System.in);
    private static final int NO_STUDENTS = 10;
    private static final int NO_OF_ASSESSMENTS = 3;
    private static int[][] grades;

    public static void main(String[] args) {

        try {
            // initialise the array
            grades = new int[NO_STUDENTS][NO_OF_ASSESSMENTS];
            populateScores();
            showMenu();
        } catch (Exception ex) {
            System.out.println("oops");
        }
    }

    public static void populateScores() {
        Random random = new Random();
        for (int outer = 0; outer < grades.length; outer++) {
            for (int inner = 0; inner < grades[outer].length; inner++) {
                // generate random number 0.100
                grades[outer][inner] = random.nextInt(101);
            }
        }
    }

    public static void showMenu() {
        boolean quit = false;
        int input;

        do{
            try{
                System.out.println("\nGrade book");
                System.out.println("1.\tShow all results");
                System.out.println("2.\tShow lowest score");
                System.out.println("3.\tShow highest score");
                System.out.println("4.\tAverage score");
                System.out.println("5.\tUpdate student score");
                System.out.println("6.\tExit");
                System.out.println("Enter an option:");
                input = scanner.nextInt();

                if(input == 1){
                    showResults();
                    quit = false;
                } else if (input == 2){
                    showLowestScore();
                    quit = false;
                } else if (input == 3){
                    showHighestScore();
                    quit = false;
                } else if (input == 4){
                    showAverageScore();
                    quit = false;
                } else if (input == 5){
                    doUpdateScore();
                    quit = false;
                } else if (input == 6) {
                    quit = true;
                    System.out.println("Thanks for using the program");
                } else if (input < 0 || input > 6) {
                    System.out.println("Please enter a number from 1 to 6 only");
                    quit = false;
                }

            } catch (InputMismatchException ex){
                System.out.println("Please enter only numbers");
                quit = false;
                scanner.next();
            } catch (ArrayIndexOutOfBoundsException ex){
                System.out.println("\nThe array is out of bounds");
                quit = false;
                scanner.next();
            } catch (Exception ex){
                System.out.println("\nAn error has occurred, please try your selection again");
                quit = false;
                scanner.next();
            }

        } while (!quit);

    }

    private static void doUpdateScore() {
        System.out.println("Please insert the Student number you wish to update: ");
        int studentNum = scanner.nextInt()-1;
        System.out.println("Please insert the assessment ID you wish to update: ");
        int assessNum = scanner.nextInt()-1;
        System.out.println("Please insert the new score");
        int newScore = scanner.nextInt();

        grades[studentNum][assessNum] = newScore;
        System.out.println("The score has been updated");

    }

    private static int findHighestScore() {
        int highest = grades[0][0];
        for(int i = 0; i< grades.length; i++){
            for(int j = 0; j < grades[i].length; j++)
                if(grades[i][j] > highest){
                    highest = grades[i][j];
                }
        }
        return highest;

    }

    private static void showHighestScore() {
        System.out.println("Lowest score is "+ findHighestScore());

    }

    private static void showAverageScore() {
        double total = 0.0;
        double average = 0.0;
        for(int i = 0; i < grades.length; i++){
            System.out.printf("Student %2d: ", i +1);
            for(int j = 0; j < grades[i].length; j++){
                total += grades[i][j];
            }
            average = total / (double) NO_OF_ASSESSMENTS;
            System.out.printf("%.2f", average);
            total =0.0;
            System.out.println();
        }

    }

    private static int findLowestScore() {
        int lowest = grades[0][0];
        for(int i = 0; i< grades.length; i++){
            for(int j = 0; j < grades[i].length; j++)
                if(grades[i][j] < lowest){
                    lowest = grades[i][j];
                }
        }
        return lowest;

    }

    public static void showLowestScore(){
        System.out.println("Lowest score is "+ findLowestScore());
    }

    public static void showResults(){
        for(int i = 0; i < grades.length; i++){
            System.out.printf("Student %2d: ", i +1);
            for(int j = 0; j < grades[i].length; j++){
            System.out.printf("%-4d",grades[i][j]);
            }
            System.out.println();
        }

    }
    
    


}