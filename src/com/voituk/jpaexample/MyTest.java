package com.voituk.jpaexample;

public class MyTest {

	public static void main(String[] args){
	      Vehicle v = new Car();
	       v.kmToMiles(10);
	}
	
	
//	class Vehicle1 {
//		public static void kmToMiles(){} // ошибка декларации статических методов во вложенном классе
//	}
//
//	static class Vehicle1 {
//	    public static void kmToMiles(){} // а вот так можно делать ЕСЛИ вложенный класс тоже является статическим
//  }
}

class Vehicle {
//	public static void kmToMiles(int km){
	public void kmToMiles(int km){
		System.out.println("родительский");
	}
}

class Car extends Vehicle {
//	public static void kmToMiles(int km){
	public void kmToMiles(int km){
		System.out.println("дочерний");
	}
}
