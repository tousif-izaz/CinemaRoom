package cinema;
import java.util.Arrays;
import java.util.Scanner;
public class Cinema {
    
    public static void showSeats(int rows, int cols, boolean booked[][]){
        System.out.println("Cinema:");
        System.out.print("  ");
        for(int i = 1; i<= cols; i++) System.out.print(i + " ");
        System.out.println();
        for(int i = 1; i<= rows; i++){
            System.out.print(i + " ");
            for(int j=0; j<cols;j++){
                if(booked[i-1][j]){
                    System.out.print("B ");
                }else {
                    System.out.print("S ");
                }
            }
            System.out.println();
        }
    }
    public static int[] booking(int rows, int cols){
        int total = rows * cols;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        //System.out.print(">  ");
        int rownum = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        //System.out.print(">  ");
        int colnum = scanner.nextInt();
        if(rownum > rows || colnum > cols){
            System.out.println("Wrong input!");
            return new int[]{-99,-1};
        }
        int tickPrice;
        if(total<=60){
            tickPrice = 10;
        }else {
            if(rownum <= rows/2){
                tickPrice = 10;
            } else{
                tickPrice = 8;
            }
        }
        return new int[]{rownum-1, colnum-1, tickPrice};
    }


    public static void main(String[] args) {
        // Write your code here
        final int rows;
        final int cols;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        System.out.print(">  ");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        System.out.print(">  ");
        cols = Integer.parseInt(scanner.next());
        int tickets = 0;
        int total = rows * cols;
        int totalIncome;
        int currentIncome = 0;
        if(total<=60){
            totalIncome = total * 10;
        } else {
            totalIncome = (rows/2) * cols * 10 + (rows - rows/2) * cols * 8;
        }
        boolean[][] booked =  new boolean[rows][cols];
        for(boolean[] b:booked){
            Arrays.fill(b, false);
        }
        int input = 1;
        while(input != 0){
            System.out.println("1. Show the seats\n"+
            "2. Buy a ticket\n"+
            "3. Statistics\n"+
            "0. Exit");
            System.out.print(">  ");
            input = scanner.nextInt();
            switch (input){
                case 0:
                    break;
                case 1:
                    showSeats(rows,cols,booked);
                    break;
                case 2:
                    while(true){
                        int[] newBooking = booking(rows, cols);
                        if(newBooking[0] == -99){
                            continue;
                        } else if(booked[newBooking[0]][newBooking[1]]){
                            System.out.println("That ticket has already been purchased!");
                            continue;
                        } else {
                            System.out.printf("Ticket price: $%d\n",newBooking[2]);
                            booked[newBooking[0]][newBooking[1]] = true;
                            tickets++;
                            currentIncome += newBooking[2];
                            break;
                        }
                    }
                    break;


                case 3:
                    System.out.printf("Number of purchased tickets: %d\n",tickets);
                    System.out.println("Percentage: " + String.format("%.2f",(double)(tickets * 100)/total) + "%");
                    System.out.printf("Current income: $%d\n", currentIncome);
                    System.out.printf("Total income: $%d\n",totalIncome);
                    break;
                default:
                    System.out.println("\"Error! Wrong command!");
                    break;
            }
        }





        
    }
}