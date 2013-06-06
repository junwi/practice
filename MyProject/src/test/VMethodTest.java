package test;

public class VMethodTest {
	public static void main(String[] args) {
		new Child();
	}
}

class Father {
	
	private int i = 1;
	
	Father() {
		init();
	}

	protected void init() {
		System.out.println("Init in Father. Token is " + i);
	}
}

class Child extends Father {
	private int i = 2;
	
	protected void init() {
		System.out.println("Init in Child. Token is " + i);
	}
}