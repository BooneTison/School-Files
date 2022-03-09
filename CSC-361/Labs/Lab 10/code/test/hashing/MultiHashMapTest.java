package hashing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class MultiHashMapTest
{
    //
	// TODO: Add tests
	//

	@Test
	void test_resize_up_and_down()
	{
		MultiHashMap<Integer, String> map = new MultiHashMap<Integer, String>();
		List<String> values = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
		final int LAST_KEY = 100000;

		//
		// SIZING UP
		//
		for (int KEY = 1; KEY <= LAST_KEY; KEY++)
		{
			for (String value : values)
			{
				map.put(KEY, value);
			}
		}

		// Sizes
		assertEquals(values.size() * LAST_KEY, map.size());

		//
		// SIZING DOWN
		//
		for (int KEY = 1; KEY <= LAST_KEY; KEY++)
		{
			map.deleteAll(KEY);
		}

		// Sizes
		assertEquals(0, map.size());	
	}
}
