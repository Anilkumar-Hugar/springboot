package com.mainMethod;

import com.prototypepattern.PersonImpl;

public class MainClass {
	public static void main(String[] args) {

		PersonImpl impl = new PersonImpl(1, "anil", "hyd");
		System.out.println(impl.showData());
		System.out.println("==================");
		PersonImpl impl2 = (PersonImpl) impl.getClone();
		System.out.println(impl2.showData());
	}
}
