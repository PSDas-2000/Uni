package ha01;

public class Test {

	public static void main(String[] args) {
		System.out.println(Brackets.isValid("(([[]]))"));
		System.out.println(Brackets.isValid("([)]"));
		System.out.println(Brackets.isValid("([]])"));
		System.out.println(Brackets.isValid("(()))"));
		System.out.println(Brackets.isValid("(()"));
		System.out.println(Brackets.isValid("({[])}"));
		System.out.println(Brackets.isValid("Hello :D"));
		System.out.println(Brackets.isValid("(())"));
		System.out.println(Brackets.isValid("{}"));
		System.out.println(Brackets.isValid("()"));
		System.out.println(Brackets.isValid("[]"));
		System.out.println(Brackets.isValid("[(x*y) + (y*z)]"));
	}

}
