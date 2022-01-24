package info.hccis.starting.bo;

import info.hccis.util.CisUtility;
import java.util.Random;

/**
 * Represents a slot machine game
 * @author Othon Lima
 * @since  Dec 20, 2021
 */
public class SlotMachine 
{
    public static final int JAVA = 1;
    public static final int JAVASCRIPT = 2;
    public static final int C_SHARP = 3;
    
    private double moneyAvailable = 0;
    private double betAmount = 0;
    private int lineCount = 0;
                
    //Constructor
    public SlotMachine()
    {
    }
      
    public double getMoneyAvailable()
    {
        return moneyAvailable;
    }

    public void setMoneyAvailable(double moneyAvailable)
    {
        this.moneyAvailable = moneyAvailable;
    }

    public double getBetAmount()
    {
        return betAmount;
    }

    public void setBetAmount(double betAmount)
    {
        this.betAmount = betAmount;
    }

    public int getLineCount()
    {
        return lineCount;
    }

    public void setLineCount(int lineCount)
    {
        this.lineCount = lineCount;
    }
    
    /**
    * Prompts the user for the bet amount and verifies
    * @author Othon Lima
    * @since  Dec 20, 2021
    */
    public void askForBetAmount()
    {
        betAmount = CisUtility.getInputDouble("Enter bet amount: ");
        
        while ((betAmount * lineCount) > moneyAvailable)
        {
            System.out.println("This is more than you can afford.");
            betAmount = CisUtility.getInputDouble("Enter bet amount: ");
        }

        moneyAvailable -= betAmount * lineCount;
    }
    
        
    /**
    * Prompts the user for the how many lines they would like to play
    * @author Othon Lima
    * @return lineCount
    * @since  Dec 20, 2021
    */
    public int askForLineAmount()
    {
        lineCount = CisUtility.getInputInt("Enter amount of lines you would like to play: ");
        
        while (lineCount <= 0)
        {
            System.out.println("Line amount needs to be greater than 0.");
            lineCount = CisUtility.getInputInt("Enter amount of lines you would like to play: ");
        }
        
        return lineCount;
    }
    
    
    /**
    * Spins the slot machine and gives the results
    * @author Othon Lima
    * @since  Dec 20, 2021
    */
    public void playLine()
    {
        int firstColumn = 0;
        int secondColumn = 0;
        int thirdColumn = 0;
        int betMultiplier = 0;
        int numberOfSpins = 0;
        double earnedAmount = 0;
        Random rand = new Random();
        
        numberOfSpins = rand.nextInt(5) + 5;
        
        while (numberOfSpins > 0)
        {
            firstColumn = getSpinColumn();
            secondColumn = getSpinColumn();
            thirdColumn = getSpinColumn();

            System.out.printf("%-13s %-13s %-13s\n", printSpin(firstColumn), printSpin(secondColumn), printSpin(thirdColumn));
            System.out.println("");
            wait(400);
            
            numberOfSpins--;
        }

        if ((firstColumn == secondColumn) && (secondColumn == thirdColumn))
        {
            switch(firstColumn)
            {
                case C_SHARP:
                {
                    betMultiplier = 3;
                    break;
                }
                
                case JAVASCRIPT:
                {
                    betMultiplier = 6;
                    break;
                }
                
                case JAVA:
                {
                    betMultiplier = 15;
                    break;
                }              
            }
            
            System.out.print("Congrats! ");
        }
        
        else
        {
            betMultiplier = 0;
        }
        
        earnedAmount = betAmount * betMultiplier;
        moneyAvailable += earnedAmount;
        
        System.out.println("You earned " + CisUtility.toCurrency(earnedAmount));
        System.out.println("");
    }
    
    /**
    * Spins 1 column of the line (there are 3 columns)
    * @author Othon Lima
    * @return value that the spin stopped on
    * @since  Dec 20, 2021
    */
    public int getSpinColumn()
    {
        int columnSpin;
        int spin;
        Random rand = new Random();
        
        //Get number between 1 and 100
        columnSpin = rand.nextInt(99) + 1;
        
        if (columnSpin >= 1 && columnSpin <= 15)
        {
            spin = 1;
        }
        
        else if (columnSpin >= 16 && columnSpin <= 50)
        {
            spin = 2;
        }
        
        //(firstLine >= 51 && firstLine <= 100)
        else
        {
            spin = 3;
        }
        
        return spin;
    }
    
    /**
    * Converts the spin number to a value that the user can assimilate
    * @author Othon Lima
    * @param spinNumber
    * @return spinName
    * @since  Dec 20, 2021
    */
    public String printSpin(int spinNumber)
    {
        String spinName = "";
        
        switch (spinNumber)
        {
            case JAVA:
                spinName = "Java";
                break;
            case JAVASCRIPT:
                spinName = "Javascript";
                break;
            case C_SHARP:
                spinName = "C#";
                break;
            default:
                break;
        }
        
        return spinName;        
    }
    
    //Wait for ms seconds
    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
    
    public void display()
    {
        //Show summary
        System.out.println(this.toString());
    }
    
    @Override
    public String toString()
    {
        String output = "You have " + CisUtility.toCurrency(moneyAvailable) + " available.";
        return output;
    }
            
}