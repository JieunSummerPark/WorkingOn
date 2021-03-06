import java.util.Scanner;

public class AdditionGame_13 {

	public static Scanner input = new Scanner(System.in);

	public static String userName;

	public static int whichCalculation;

	public static int ranNum1;
	public static int ranNum2;
	public static int result;
	public static int answer;

	public static int roundCounter;
	public static int howManyGames = 10;

	public static int heartCounter = 3;

	public static int correctCounter = 0;
	public static int wrongCounter = 0;
	public static double scorePercentage;

	public static double startTime;
	public static double stopTime;
	public static double answeringTime;
	public static double totalTime = 0;

	public static char keepGoingCheck;
	public static boolean keepGoing;
	public static boolean gameOver;

	public static void main(String[] args) {

		System.out.print("Please enter your name: ");
		userName = input.nextLine();

		accounceTheRule();

		keepGoing = true;
		while(keepGoing) {

			askToChooseCalculation();
			whichCalculation = input.nextInt();

			gameOver = false;

			if (whichCalculation!=1 && whichCalculation!=2 && whichCalculation!=3 && whichCalculation!=4) {
				System.out.println("Try 1, 2, 3, or 4.");
				break;
			}

			System.out.println("\n");
			announceTheGameStart();
			System.out.println("\n");

			for (roundCounter = 1; roundCounter <= howManyGames; roundCounter++) {
				startTime = System.currentTimeMillis();
				computing();
				stopTime = System.currentTimeMillis();
				answeringTime = (stopTime - startTime) / 1000;
				totalTime += answeringTime;
				if (heartCounter <= 0) {
					announceGameOver();
					gameOver = true;
					break;
				}
			}

			announceResult();
			annountHEARTGAINorCHEERUP();

			if (heartCounter>30) {
				System.out.println(userName + "WON!");
				break;
			}

			// RESET
			correctCounter = 0;
			wrongCounter=0;
			totalTime = 0;

			System.out.print(""
					+ "\nWould you like to try again?"
					+ "\nPress any key to try again OR Press '0' to stop : ");
			keepGoingCheck = input.next().charAt(0);
			if(keepGoingCheck == '0') {
				announceBYE();
				keepGoing = false;
				break;
			} else if (gameOver == true) {
				heartCounter=3;
				System.out.println("\n\n\n" + userName + "'S HEART HAS RESET.");
			}
		} // End of While Loop


		input.close();
	} /////////////////////////////////////////////////////////////////////////////////////// THE END of MAIN METHOD


	private static void accounceTheRule() {
		System.out.println(""
				+ "\nFor now, you have 3 hearts."
				+ "\nYou will get or lose your hearts depends on your result."
				+ "\n(result = time and the number of wrong)."
				+ "\nWhen you lose all of your heart, you will lose."
				+ "\nwhen you get 30 heart or more than that, YOU WILL WIN THIS GAME.");
	}

	private static void askToChooseCalculation() {
		if(gameOver == true)
			System.out.println("");
		System.out.print(""
				+ "\nWhich calculation do you want to do?"
				+ "\n1. Addition"
				+ "\n2. Subtraction"
				+ "\n3. Multiplication"
				+ "\n4. Division"
				+ "\n\nPlease choose : ");
	}

	private static void announceTheGameStart() {
		switch(whichCalculation) {
		case 1: System.out.println("\n\n"
				+ "\n---------------------------------------------------------"
				+ "\n-------------------AdditionGame-START--------------------"
				+ "\n---------------------------------------------------------\n\n\n");
		break;
		case 2: System.out.println("\n\n"
				+ "\n---------------------------------------------------------"
				+ "\n-----------------SubtractionGame-START-------------------"
				+ "\n---------------------------------------------------------\n\n\n");
		break;
		case 3: System.out.println("\n\n"
				+ "\n---------------------------------------------------------"
				+ "\n----------------MultiplicationGame-START-----------------"
				+ "\n---------------------------------------------------------\n\n\n");
		break;
		case 4: System.out.println("\n\n"
				+ "\n---------------------------------------------------------"
				+ "\n-------------------DivisionGame-START--------------------"
				+ "\n---------------------------------------------------------\n\n\n");
		break;
		}
	}

	private static void computing() {
		ranNum1 = (int)(Math.random() * 10 + 1 );
		ranNum2 = (int)(Math.random() * 10 + 1 );

		switch(whichCalculation) {
		case 1: Addition(); break;
		case 2: Subtraction(); break;
		case 3: Multiplication(); break;
		case 4: Division(); break;
		}

		answer = input.nextInt();
		if (answer == result) {
			++correctCounter;
			System.out.println("Correct!");
		} else if (answer != result) {
			--heartCounter; ++wrongCounter;
			System.out.println("It's wrong.\nThe correct answer is " + result + "!");
			System.out.println("You lost 1 heart. Now " + userName + " has " + heartCounter + " hearts.");
		}
		System.out.println("");
	}

	private static void announceGameOver() {
		System.out.println("\n\n"
				+ "\n---------------------------------------------------------"
				+ "\n-------------------------GameOver------------------------"
				+ "\n---------------------------------------------------------\n\n\n");
	}

	private static void announceBYE() {
		System.out.println("\n\n"
				+ "\n---------------------------------------------------------"
				+ "\n-------------------ThankYou-AND-GoodBye------------------"
				+ "\n---------------------------------------------------------");
	}

	private static void announceResult() {
		scorePercentage = (correctCounter/(double)howManyGames) * 100;
		System.out.println(userName + "'s score is " + correctCounter + " out of " + howManyGames + ".");
		System.out.println(userName + "'s score is " + String.format("%.2f", scorePercentage) + "%.");
		System.out.println(userName + " took " + String.format("%.2f", totalTime) + " seconds.");
		System.out.println(userName + "'s average seconds per answer is " + String.format("%.2f", totalTime/howManyGames) + " seconds.");
	}

	private static void annountHEARTGAINorCHEERUP() {
		System.out.println("");
		if (gameOver == false) {
			// GIVE MORE HEARTS WHEN USER WRONG ONLY A FEW TIMES
			if (wrongCounter < 3) {
				// SCORE PERCENTAGE
				if (scorePercentage >= 90) {
					heartCounter += 3;
					System.out.println(userName + "'s score is above 90%! " + userName + " get 3 more hearts.");
				}
				// TIME CONSUME
				if ((totalTime/howManyGames) < 2) {
					heartCounter += 1;
					System.out.println(userName + " is pretty fast! " + userName + " get 1 more heart.");
				}
				System.out.println("\nNow " + userName + " has " + heartCounter + " hearts.");
			}

			// CHEER UP THE USER WHEN THE USER WRONG MANY TIMES
			else if (wrongCounter >5) {
				System.out.println("You got wrong a lot this time...!");
				if (heartCounter > 0) {
					System.out.println("But don't give up! " + userName + " can do it!");
				} else {
					System.out.println("Better luck next time...!");
				}
			}
		}

	}




	private static void Addition() {
		result = ranNum1 + ranNum2;
		System.out.print(roundCounter + ") " + ranNum1 + " + " + ranNum2 + " = ");
	}
	private static void Subtraction() {
		maxFirst(ranNum1, ranNum2);
		result = ranNum1 - ranNum2;
		System.out.print(roundCounter + ") " + ranNum1 + " - " + ranNum2 + " = ");
	}
	private static void Multiplication() {
		result = ranNum1 * ranNum2;
		System.out.print(roundCounter + ") " + ranNum1 + " X " + ranNum2 + " = ");	
	}
	private static void Division() {
		maxFirst(ranNum1, ranNum2);
		result = ranNum1 / ranNum2;
		System.out.print(roundCounter + ") " + ranNum1 + " / " + ranNum2 + " = ");
	}
	private static void maxFirst(int ranNum12, int ranNum22) {
		int max;
		if (ranNum2 > ranNum1) {
			max = ranNum2;
			ranNum2 = ranNum1;
			ranNum1 = max;
		}
	}

}  /////////////////////////////////////////////////////////////////////////////////////// THE END of CLASS