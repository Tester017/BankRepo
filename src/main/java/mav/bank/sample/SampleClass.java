package mav.bank.sample;

import java.lang.reflect.Method;

import mav.bank.library.functions.CommonDef;

public class SampleClass {

	public static void main(String[] args) {
		
		Method[] method = CommonDef.class.getDeclaredMethods();
		System.out.println("Number of Methods: "+method.length);
		for (Method methods:method) {
			System.out.println(methods);
		}
		
	}

}