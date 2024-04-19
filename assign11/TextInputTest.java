package assign11;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextInputTest {
	private String str1;
	private String str2;
	private String str3;
	private String str4;

	@BeforeEach
	void setUp() throws Exception {
		str1 = "src/assign11/Baa, baa black sheep.txt";
		str2 = "src/assign11/abc.txt";
		str3 = "src/assign11/helloWorld.txt";
		str4 = "src/assign11/trickyFormatting.txt";
	}

	@Test
	void testStr3() {
		TextInput input3 = new TextInput(new File(str3));
		assertEquals(input3.randomText("hello", 0), "");
		assertEquals(input3.randomText("hello", 2), "hello world");
		assertEquals(input3.randomText("world", 2), "world world");
		assertEquals(input3.randomText("hello", 4), "hello world hello world");
		assertEquals(input3.randomText("world", 4), "world world world world");
		
		assertEquals(input3.mostLikelyText("hello", 0), "");
		assertEquals(input3.mostLikelyText("hello", 2), "hello world");
		assertEquals(input3.mostLikelyText("world", 2), "world world");
		assertEquals(input3.mostLikelyText("hello", 4), "hello world hello world");
		assertEquals(input3.mostLikelyText("world", 4), "world world world world");
	}
}
