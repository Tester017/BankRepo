package mav.bank.sample;

import java.lang.reflect.Method;

import mav.bank.library.functions.CommonDef;

public class SampleClass {

	public static void main(String[] args) {

		Class thisClass[] = { CommonDef.class };
		for (Class cl : thisClass) {
			Method declaredMethod[] = cl.getDeclaredMethods();
			System.out.println("Number of Methods: "+declaredMethod.length);
			for (Method a : declaredMethod) {
				System.out.println(a);
			}
		}

	}

}