package com.mainMethod;

import java.util.Scanner;

import com.facadePattern.CourseImpl;

public class MainClassFacadePattern {
	public static void main(String[] args) {
		int choice;
		do {
			System.out.println("1.BSC COURSE\n2.BCOM COURSE\n3.BBA COURSE\n4.EXIT\n");
			System.out.println("enter your choice");
			Scanner scanner=new Scanner(System.in);
			 choice=scanner.nextInt();
			 CourseImpl courseImpl=new CourseImpl();
			 switch(choice) {
			 case 1:
				 courseImpl.bscCourse();
				 break;
			 case 2:
				 courseImpl.bcomCourse();
				 break;
			 case 3:
				 courseImpl.bbaCourse();
				 
				 break;
			 default:
				 System.out.println("entered wrong choice..");
				 return;
				 
			 }
		}while(choice!=4);
	}
}
