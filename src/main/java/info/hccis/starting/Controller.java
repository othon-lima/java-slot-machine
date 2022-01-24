package info.hccis.starting;

import info.hccis.starting.bo.SlotMachine;
import info.hccis.util.CisUtility;

/**
 *
 * @author bjmaclean
 * @since Oct 19, 2021
 */
public class Controller
{
    public static final String MENU = "CIS Menu" + System.lineSeparator()
            + "A- to bet (single line)" + System.lineSeparator()
            + "B- to bet (multiple line)" + System.lineSeparator()
            + "Q- to quit" + System.lineSeparator();

    public static void main(String[] args)
    {
        int lineCount = 0;
        
        System.out.println("Welcome to the CIS Slot Machine!");
        
        SlotMachine slotMachine = new SlotMachine();
        slotMachine.setMoneyAvailable(CisUtility.getInputDouble("Enter the amount you would like to start with:"));   

        String option = "";

        while (!option.equalsIgnoreCase("Q"))
        {
            option = CisUtility.getInputString(MENU);
            option = option.toUpperCase();
            switch (option)
            {
                case "A":
                {
                    slotMachine.setLineCount(1);
                    slotMachine.askForBetAmount();
                    slotMachine.playLine();
                    slotMachine.display();
                    break;
                }
                
                case "B":
                {
                    lineCount = slotMachine.askForLineAmount();
                    slotMachine.askForBetAmount();
                    
                    while(lineCount > 0)
                    {
                        slotMachine.playLine();
                        lineCount--;
                    }
                    
                    slotMachine.display();
                    
                    break;
                }
                
                case "Q":
                {
                    System.out.println("You are leaving with " + CisUtility.toCurrency(slotMachine.getMoneyAvailable()));
                    System.out.println("Goodbye!");
                    break;
                }
                
                default:
                {
                    System.out.println("Invalid option");
                }
            }
        }
    }
}
