package bst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class bstTest {
	private BinarySearchTree<Integer> bst1;
	private BinarySearchTree<String> bst2;
	
	@BeforeEach
	void setup() {
		bst1 = new BinarySearchTree<>();
		bst2 = new BinarySearchTree<>((s1, s2) -> s1.compareTo(s2));
	}
	
	@AfterEach
	void teardown() {
		bst1 = null;
		bst2 = null;		
	}
	
	@Test
	void testAdd() {
		bst1.add(1);
		bst1.add(6);
		bst1.add(11);
		bst1.add(7);
		
		assertTrue(bst1.add(8), "Returned false when add was posible");
		assertFalse(bst1.add(1), "Returned True when add was not posible");
		
		bst2.add("1");
		bst2.add("6");
		bst2.add("11");
		bst2.add("7");
		
		assertTrue(bst2.add("8"), "Returned false when add was posible");
		assertFalse(bst2.add("11"), "Returned True when add was not posible");
		
	}
	
	@Test
	void testHeight() {
		bst1.add(1);
		bst1.add(2);
		bst1.add(11);
		bst1.add(7);
		
		assertEquals(4, bst1.height());
		
		bst2.add("a");
		bst2.add("c");
		bst2.add("b");
		bst2.add("d");
		
		assertEquals(3, bst2.height());
		
	}
	
	@Test
	void testSize() {
		bst2.add("1");
		bst2.add("6");
		bst2.add("11");
		bst2.add("7");
		
		assertEquals(4, bst2.size());
		
		bst1.add(1);
		bst1.add(2);
		bst1.add(11);
		bst1.add(7);
		
		assertEquals(4, bst2.size());
		
	}
	@Test
	void testClear() {
		bst2.add("1");
		bst2.add("6");
		bst2.add("11");
		bst2.add("7");
		bst2.clear();
		
		assertEquals(0, bst2.size());
		assertEquals(0, bst2.height());
		
		bst1.add(1);
		bst1.add(2);
		bst1.add(11);
		bst1.add(7);
		
		bst1.clear();
		
		assertEquals(0, bst1.size());
		assertEquals(0, bst1.height());
	}
}