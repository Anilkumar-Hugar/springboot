package com.mainMethod;

import com.adapterDesignPattern.Library;
import com.adapterDesignPattern.StudentImpl;

public class MainClassAdapter {
public static void main(String[] args) {
	Library library=new StudentImpl();
	library.giveDetails();
	System.out.println(library.getBooks());
}
}
