// 	by Paul Nathan and Michael Meluso, lab 8

package tcnj.nathanp1;

import java.util.Scanner;
import javafoundations.*;

public class Processor 
{
	private LinkedBinaryTree<String> tree;

	public Processor()
	{
		tree = new LinkedBinaryTree<>();
	}

	public void run()
	{
		createTree();
		takeInput();
	}

	private void takeInput()
	{
		LinkedBinaryTree<String> currentNode = tree;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please answer the following questions with either y or n.");
		System.out.println(currentNode.getRootElement().toString());

		while(currentNode.size() > 1)
		{
			String input =  scanner.nextLine();
			
			if(input.equalsIgnoreCase("y"))
				currentNode = currentNode.getRight();
			else if(input.equalsIgnoreCase("n"))
				currentNode = currentNode.getLeft();
			else
				System.out.println("Invalid input, please enter y or n");

			System.out.println(currentNode.getRootElement().toString());
		}
	}
	
	private void createTree()
	{
		String s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17, s18,
					s19, s20, s21, s22, s23, s24, s25, s26, s27, s28, s29, s30, s31, s32, s33, s34, s35;
					
		LinkedBinaryTree<String> t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16,
					t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34;
					
		s1 = "Saying no to all the questions aren't fun. You're boring";
		s2 = "You probably want to get that looked at, that's too messed up for a decision tree.";
		s3 = "Liar! The dead can't type! Burn the user!";
		s4 = "Quick! Drink some alkaseltzer. Did your stomach explode and kill you?";
		s5 = "Is your stomach moving?";
		s6 = "Better get ready for a literally magical birth, then.";
		s7 = "Then congrats, you're having a pony!";
		s8 = "How do you feel with mystal creatures flying out of you?";
		s9 = "Do you want to be pregnant?";
		s10 = "At the present time, we do not have records of pregnancies with sixth dimentional beings.";
		s11 = "Then you must go to see The Doctor!";
		s12 = "Are you a lizard person, living beneath the surface?";
		s13 = "You're....the queen alien? I'm more amazed that you're able to use a computer, because now the human race is screwed.";
		s14 = "Go home, alien, you're drunk.";
		s15 = "Are you taking this for a person whose face you hugged recently?";
		s16 = "Are you from outerspace?";
		s17 = "Then if you've been horsing around with a lady friend, congratulations!";
		s18 = "Are you a seahorse or a member of its family?";
		s19 = "No, you do not have a baby...if you needed me to tell you that you might have problems.";
		s20 = "The only normal answer here! Congratulations.";
		s21 = "Do you have womanly parts?";
		s22 = "Well, then you lied about being human. Unless you've been...experimented on. Can't really help you there.";
		s23 = "Do you reproduce asexually?";
		s24 = "Then you're possibly an idiot for asking me.";
		s25 = "Even an expert system doesn't know! ABORT....the program.";
		s26 = "Is your stomach abnormally large?";
		s27 = "Then I assume they were rubbing their hands together menacingly. I hope your baby doesn't come out like an alien that destroy s humanity, for all of our sakes.";
		s28 = "Well, it's about a fifty fifty shot that your baby is either a superhro, or your baby's skin changes color every five seconds.";
		s29 = "While the scientists were working with you, were they laughing maniacally?";
		s30 = "Have you been the subject of human-embryo experimentation lately?";
		s31 = "Whelp, you're going to give birth to an alien that will bust of your belly. Don't drink any seltzer water.";
		s32 = "Have you been on any spaceships recently?";
		s33 = "Are you a woman?";
		s34 = "Are you human?";
		s35 = "Is your stomach bigger than usual?";
		
		t2 = new LinkedBinaryTree<String>(s2);
		t3 = new LinkedBinaryTree<String>(s3);
		t4 = new LinkedBinaryTree<String>(s4, t2, t3);
		
		t1 = new LinkedBinaryTree<String>(s1);
		t5 = new LinkedBinaryTree<String>(s5, t1, t4);
		
		t6 = new LinkedBinaryTree<String>(s6);
		t7 = new LinkedBinaryTree<String>(s7);
		t8 = new LinkedBinaryTree<String>(s8, t6, t7);
		
		t9 = new LinkedBinaryTree<String>(s9, t5, t8);
		
		// Right tree GO
		
		t10 = new LinkedBinaryTree<String>(s10);
		t11 = new LinkedBinaryTree<String>(s11);
		t12 = new LinkedBinaryTree<String>(s12, t10, t11);
		
		t13 = new LinkedBinaryTree<String>(s13);
		t14 = new LinkedBinaryTree<String>(s14);
		t15 = new LinkedBinaryTree<String>(s15, t13, t14);
		
		t16 = new LinkedBinaryTree<String>(s16, t12, t15);
		
		t17 = new LinkedBinaryTree<String>(s17);
		t18 = new LinkedBinaryTree<String>(s18, t16, t17);
		
		t19 = new LinkedBinaryTree<String>(s19);
		t20 = new LinkedBinaryTree<String>(s20);
		t21 = new LinkedBinaryTree<String>(s21, t19, t20);
		
		t22 = new LinkedBinaryTree<String>(s22);
		t23 = new LinkedBinaryTree<String>(s23, t21, t22);
		
		t24 = new LinkedBinaryTree<String>(s24);
		t25 = new LinkedBinaryTree<String>(s25);
		t26 = new LinkedBinaryTree<String>(s26, t24, t25);
		
		t27 = new LinkedBinaryTree<String>(s27);
		t28 = new LinkedBinaryTree<String>(s28);
		t29 = new LinkedBinaryTree<String>(s29, t27, t28);
		
		t30 = new LinkedBinaryTree<String>(s30, t26, t29);
		
		t31 = new LinkedBinaryTree<String>(s31);
		t32 = new LinkedBinaryTree<String>(s32, t30, t31);
		
		t33 = new LinkedBinaryTree<String>(s33, t24, t32);
		
		t34 = new LinkedBinaryTree<String>(s34, t19, t33);
		tree = new LinkedBinaryTree<String>(s35, t9, t34);
		
		// Preorder numbering on paper to keep track of what goes where
	}
}
		
		
		
		
		
		
		
		
		
		