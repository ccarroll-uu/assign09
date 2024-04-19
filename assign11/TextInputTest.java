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
		str1 = "Baa, baa black sheep.txt";
		str2 = "abc.txt";
		str3 = "helloWorld.txt";
		str4 = "trickyFormatting.txt";
	}

	@Test
	void testStr3() {
		TextInput input3 = new TextInput(new File(str3));
		assertEquals(input3.randomText("hello", 0), "");
		input3.randomText("hello", 2);
		input3.randomText("hello", 4);
		assertEquals(input3.mostLikelyText("hello", 0), "");
		input3.mostLikelyText("helloWorld", 2);
		input3.mostLikelyText("helloWorld", 4);
	}
}

