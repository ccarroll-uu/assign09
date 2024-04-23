
package comprehensive;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextInputTest {
	private String str1;
	private String str2;
	private String str3;
	private String str4;
	private String str5;

	@BeforeEach
	void setUp() throws Exception {
		str1 = "src/comprehensive/Baa, baa black sheep.txt";
		str2 = "src/comprehensive/abc.txt";
		str3 = "src/comprehensive/helloWorld.txt";
		str4 = "src/comprehensive/trickyFormatting.txt";
		str5 = "src/comprehensive/abc2.txt";
	}

	@Test
	void testStr3() {
		TextInput input3 = new TextInput(new File(str3));
		assertEquals(input3.randomText("hello", 0), "");
		assertEquals(input3.randomText("hello", 1), "hello");
		assertEquals(input3.randomText("hello", 2), "hello world");
		assertEquals(input3.randomText("world", 2), "world world");
		assertEquals(input3.randomText("hello", 4), "hello world hello world");
		assertEquals(input3.randomText("world", 4), "world world world world");
		
		assertEquals(input3.mostLikelyText("hello", 0), "");
		assertEquals(input3.mostLikelyText("hello", 1), "hello");
		assertEquals(input3.mostLikelyText("hello", 2), "hello world");
		assertEquals(input3.mostLikelyText("world", 2), "world world");
		assertEquals(input3.mostLikelyText("hello", 4), "hello world hello world");
		assertEquals(input3.mostLikelyText("world", 4), "world world world world");
		
		assertEquals(input3.kMostProbableWords("hello", 0), "");
		assertEquals(input3.kMostProbableWords("hello", 1), "world");
		assertEquals(input3.kMostProbableWords("hello", 3), "world");
		assertEquals(input3.kMostProbableWords("world", 1), "");
	}
	
	@Test
	void testStr2() {
		TextInput input2 = new TextInput(new File(str2));
		assertEquals(input2.mostLikelyText("a", 0), "");
		assertEquals(input2.mostLikelyText("a", 2), "a b");
		assertEquals(input2.mostLikelyText("b", 2), "b a");
		assertEquals(input2.mostLikelyText("d", 3), "d a b");
		assertEquals(input2.mostLikelyText("a", 4), "a b a b");
		assertEquals(input2.mostLikelyText("b", 4), "b a b a");
		assertEquals(input2.mostLikelyText("z", 4), "z z z z");
		
		System.out.println(input2.randomText("d", 10));
		System.out.println(input2.randomText("z", 8));
		
		assertEquals(input2.kMostProbableWords("a", 5), "b c d e f");
	}
	
	@Test
	void testStr1() {
		TextInput input1 = new TextInput(new File(str1));
		assertEquals(input1.mostLikelyText("for", 3), "for my dame");
		assertEquals(input1.mostLikelyText("baa", 4), "baa baa baa baa");
		assertEquals(input1.mostLikelyText("and", 6), "and one for my dame and");
		System.out.println(input1.randomText("baa", 10));
	}
	
	@Test
	void testStr4() {
		TextInput input4 = new TextInput(new File(str4));
		assertEquals(input4.mostLikelyText("things", 3), "things yup things");
		assertEquals(input4.mostLikelyText("this", 3), "this file iirc");
		assertEquals(input4.mostLikelyText("few", 6), "few them punctuation or formatting things");
	}
	
	@Test
	void testStr5() {
		TextInput input5 = new TextInput(new File(str5));
		assertEquals(input5.mostLikelyText("a", 0), "");
		assertEquals(input5.mostLikelyText("a", 2), "a b");
		assertEquals(input5.mostLikelyText("b", 2), "b a");
		assertEquals(input5.mostLikelyText("d", 3), "d a b");
		assertEquals(input5.mostLikelyText("a", 4), "a b a b");
		assertEquals(input5.mostLikelyText("b", 4), "b a b a");
		
		System.out.println(input5.randomText("a", 10));
	}
}
