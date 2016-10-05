import org.junit.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * This is a basic set of unit tests for DoublyLinkedList. Passing these does
 * NOT guarantee any grade on this assignment. This is only a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * @author The 1332 TAs
 * @version 1.0
 */
public class LinkedListStudentTests {
    private LinkedListInterface<String> list;
    private LinkedListInterface<Integer> intList;

    private DoublyLinkedList<String> secondList;

    public static final int TIMEOUT = 200;

    private static final String FOO = "foo";
    private static final String BAR = "bar";
    private static final String BAZ = "baz";
    private static final String QUX = "qux";

    @Before
    public void setUp() {
        list = new DoublyLinkedList<String>();
        secondList = new DoublyLinkedList<>();
        intList = new DoublyLinkedList<Integer>();

    }

    @org.junit.Test(timeout = TIMEOUT)
    public void testAddStrings() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex(0, "0a"); //0a
        list.addAtIndex(1, "1a"); //0a 1a
        list.addAtIndex(2, "2a"); //0a 1a 2a
        list.addAtIndex(3, "3a"); //0a 1a 2a 3a

        assertEquals(4, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());
        assertNull(current.getPrevious());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());
        assertSame(current, list.getTail());

        assertNull(current.getNext());
    }

    @org.junit.Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a");
        list.addToFront("5a"); //5a 4a 3a 2a 1a 0a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("5a", current.getData());
        assertNull(current.getPrevious());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("0a", current.getData());
        assertSame(current, list.getTail());

        assertNull(current.getNext());
    }

    @org.junit.Test(timeout = TIMEOUT)
    public void testRemoveStrings() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("2a", list.removeAtIndex(2)); //0a 1a 3a 4a 5a

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());
        assertNull(current.getPrevious());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());
        assertSame(current, list.getTail());

        assertNull(current.getNext());
    }

    @org.junit.Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a"); //0a 1a 2a 3a 4a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @org.junit.Test(timeout = TIMEOUT)
    public void testToArray() {
        String[] expectedItems = new String[10];

        for (int x = 0; x < expectedItems.length; x++) {
            expectedItems[x] = "a" + x;
            list.addToBack(expectedItems[x]);
        }

        Object[] array = list.toArray();
        assertArrayEquals(expectedItems, array);
    }

    @org.junit.Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void removeAllOccurancesNullItemPassed() {
        list.removeAllOccurrences(null);
    }

    /**
     * A helper method for various tests.
     *
     * First, this method tests isEmpty, the nullity of head/tail "pointers," and size.
     * All elements in actual are compared against the elements in expected in a for loop,
     * the traversal of which also tests the validity of the head/tail and previous/next "pointers."
     *
     * @param method - String for assertion message, e.g. "Did addToFront update size?"
     * @param expected - An array of the elements expected
     * @param actual - The actual list to be tested
     */
    private void assertDoublyLinkedListEquals(String method, Object[] expected, DoublyLinkedList<String> actual) {
        if (expected.length == 0) {
            assertTrue(actual.isEmpty());
            assertNull(secondList.getHead());
            assertNull(secondList.getTail());
            assertEquals("Did " + method + " update size?", expected.length, actual.size());
        } else {
            assertFalse(actual.isEmpty());
            assertNotNull(secondList.getHead());
            assertNotNull(secondList.getTail());
            assertEquals("Did " + method + " update size?", expected.length, actual.size());
            LinkedListNode foo = secondList.getHead();
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], foo.getData());
                if (i == 0) {
                    assertEquals(secondList.getHead(), foo); // tests nothing
                    assertNull(foo.getPrevious());
                } else {
                    assertNotNull(foo.getPrevious());
                    assertEquals(expected[i - 1], foo.getPrevious().getData());
                }
                if (i == expected.length - 1) {
                    assertEquals(secondList.getTail(), foo);
                    assertNull(foo.getNext());
                } else {
                    assertNotNull(foo.getNext());
                    assertEquals(expected[i + 1], foo.getNext().getData());
                }
                foo = foo.getNext();
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void testConstructor() {
        // Nothing to see here!
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        // test exception
        try {
            secondList.addToFront(null);
            Assert.fail();
        } catch (Exception e) {
            assertEquals("addToFront must throw java.lang.IllegalArgumentException if data is null.",
                    IllegalArgumentException.class, e.getClass());
        }
        // test function
        // test addToFront(data) for empty list
        secondList.addToFront(BAZ);
        assertDoublyLinkedListEquals("addToFront", new Object[]{BAZ}, secondList);
        // test addToFront(data) for non-empty, one element list
        secondList.addToFront(BAR);
        assertDoublyLinkedListEquals("addToFront", new Object[]{BAR, BAZ}, secondList);
        // test addToFront(data) for non-empty, two element list
        secondList.addToFront(FOO);
        assertDoublyLinkedListEquals("addToFront", new Object[]{FOO, BAR, BAZ}, secondList);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        // test exception
        try {
            list.addToBack(null);
            Assert.fail();
        } catch (Exception e) {
            assertEquals("addToBack must throw java.lang.IllegalArgumentException if data is null.",
                    IllegalArgumentException.class, e.getClass());
        }
        // test function
        // test addToBack(data) for empty list
        secondList.addToBack(FOO);
        assertDoublyLinkedListEquals("addToBack", new Object[]{FOO}, secondList);
        // test addToBack(data) for non-empty, one element list
        secondList.addToBack(BAR);
        assertDoublyLinkedListEquals("addToBack", new Object[]{FOO, BAR}, secondList);
        // test addToBack(data) for non-empty, two element list
        secondList.addToBack(BAZ);
        assertDoublyLinkedListEquals("addToBack", new Object[]{FOO, BAR, BAZ}, secondList);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        // test exceptions
        String message = "addAtIndex must throw java.lang.IndexOutOfBoundsException" +
                " if index is negative or index > size.";
        for (int i: new int[]{-1, 1}) {
            try {
                secondList.addAtIndex(i, "");
                Assert.fail();
            } catch (Exception e) {
                assertEquals(message, IndexOutOfBoundsException.class, e.getClass());
            }
        }
        message = "addAtIndex must throw java.lang.IllegalArgumentException if data is null.";
        try {
            secondList.addAtIndex(0, null);
            Assert.fail();
        } catch (Exception e) {
            assertEquals(message, IllegalArgumentException.class, e.getClass());
        }
        // test function
        // test addAtIndex(0, data) for empty list
        secondList.addAtIndex(0, BAZ);
        assertDoublyLinkedListEquals("addAtIndex", new Object[]{BAZ}, secondList);
        // test addAtIndex(0, data) for non-empty, one element list
        secondList.addAtIndex(0, BAR);
        assertDoublyLinkedListEquals("addAtIndex", new Object[]{BAR, BAZ}, secondList);
        // test addAtIndex(0, data) for non-empty, two element list
        secondList.addAtIndex(0, FOO);
        assertDoublyLinkedListEquals("addAtIndex", new Object[]{FOO, BAR, BAZ}, secondList);
        secondList = new DoublyLinkedList<>(); // reset
        // test addAtIndex(size, data) for empty list // tests nothing
        secondList.addAtIndex(0, FOO);
        // assertDoublyLinkedListEquals("addAtIndex", new Object[]{FOO}, list);
        // test addAtIndex(size, data) for non-empty, one element list
        secondList.addAtIndex(1, BAZ);
        assertDoublyLinkedListEquals("addAtIndex", new Object[]{FOO, BAZ}, secondList);
        // test addAtIndex(size, data) for non-empty, two element list
        secondList.addAtIndex(2, QUX);
        assertDoublyLinkedListEquals("addAtIndex", new Object[]{FOO, BAZ, QUX}, secondList);
        // test addAtIndex(size / 2, data) for non-empty list
        secondList.addAtIndex(1, BAR);
        assertDoublyLinkedListEquals("addAtIndex", new Object[]{FOO, BAR, BAZ, QUX}, secondList);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        // test no exceptions
        // test function
        // test removeFromFront() for empty list
        assertNull(secondList.removeFromFront());
        String dat = FOO;
        secondList.addToFront(dat);
        // test removeFromFront() for non-empty, one element list
        assertEquals(dat, secondList.removeFromFront());
        assertDoublyLinkedListEquals("removeFromFront", new String[]{}, secondList);
        secondList.addToFront(FOO);
        secondList.addToFront(BAR);
        // test removeFromFront() for non-empty, two element list
        assertEquals(BAR, secondList.removeFromFront());
        assertDoublyLinkedListEquals("removeFromFront", new String[]{FOO}, secondList);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        // test no exceptions
        // test function
        // test removeFromBack() for empty list
        assertNull(secondList.removeFromBack());
        secondList.addToFront(FOO);
        // test removeFromBack() for non-empty, one element list
        assertEquals(FOO, secondList.removeFromBack());
        assertDoublyLinkedListEquals("removeFromBack", new String[]{}, secondList);
        secondList.addToFront(BAR);
        secondList.addToFront(FOO);
        // test removeFromBack() for non-empty, two element list
        assertEquals(BAR, secondList.removeFromBack());
        assertDoublyLinkedListEquals("removeFromBack", new String[]{FOO}, secondList);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        // test exceptions
        String message = "removeAtIndex must throw java.lang.IndexOutOfBoundsException" +
                " if index is negative or index >= size.";
        for (int i: new int[]{-1, 0, 1}) {
            try {
                secondList.removeAtIndex(i);
                Assert.fail();
            } catch (Exception e) {
                assertEquals(message, IndexOutOfBoundsException.class, e.getClass());
            }
        }
        // test function
        // test removeAtIndex(0) for non-empty, one element list
        secondList.addToFront(FOO);
        assertEquals(FOO, secondList.removeAtIndex(0));
        assertDoublyLinkedListEquals("removeAtIndex", new String[]{}, secondList);
        // test removeAtIndex(0) for non-empty, two element list
        secondList.addToFront(BAR);
        secondList.addToFront(FOO);
        assertEquals(FOO, secondList.removeAtIndex(0));
        assertDoublyLinkedListEquals("removeAtIndex", new String[]{BAR}, secondList);
        // test removeAtIndex(size - 1) for non-empty, one element list // tests nothing
        assertEquals(BAR, secondList.removeAtIndex(0));
        // test removeAtIndex(size - 1) for non-empty, two element list
        secondList.addToFront(BAR);
        secondList.addToFront(FOO);
        assertEquals(BAR, secondList.removeAtIndex(1));
        assertDoublyLinkedListEquals("removeAtIndex", new String[]{FOO}, secondList);
        secondList = new DoublyLinkedList<>(); // reset
        // test removeAtIndex(size / 2) for non-empty list
        secondList.addToFront(BAZ);
        secondList.addToFront(BAR);
        secondList.addToFront(FOO);
        assertEquals(BAR, secondList.removeAtIndex(1));
        assertDoublyLinkedListEquals("removeAtIndex", new String[]{FOO, BAZ}, secondList);
    }

    /**
     * TODO - This test is incomplete
     */
    @Test(timeout = TIMEOUT)
    public void testRemoveAllOccurrences() {
        // test exception
        try {
            secondList.removeAllOccurrences(null);
            Assert.fail();
        } catch (Exception e) {
            assertEquals("removeAllOccurrences must throw java.lang.IllegalArgumentException if data is null.",
                    IllegalArgumentException.class, e.getClass());
        }
        secondList.addToFront(FOO);
        secondList.addToFront(FOO);
        assertFalse(secondList.removeAllOccurrences(BAR));
        assertTrue(secondList.removeAllOccurrences(FOO));
        System.out.println(secondList.size());
        System.out.println(secondList.getHead());
        System.out.println(secondList.getTail());
        assertDoublyLinkedListEquals("removeAllOccurrences", new String[]{}, secondList);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        // test exceptions
        String message = "get must throw java.lang.IndexOutOfBoundsException" +
                " if index is negative or index >= size.";
        for (int i: new int[]{-1, 0, 1}) {
            try {
                secondList.get(i);
                Assert.fail();
            } catch (Exception e) {
                assertEquals(message, IndexOutOfBoundsException.class, e.getClass());
            }
        }
        // test function
        secondList.addToFront("0");
        assertEquals("0", secondList.get(0));
    }

    @Test(timeout = TIMEOUT)
    public void testToAnArray() {
        // test no exceptions
        // test function
        secondList.addToFront(BAR);
        secondList.addToFront(FOO);
        assertArrayEquals(new String[]{FOO, BAR}, secondList.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void clearTest() {
        // test no exceptions
        // test function
        secondList.addToFront(FOO);
        secondList.clear();
        assertDoublyLinkedListEquals("clear", new String[]{}, secondList);
    }

    // third set

    @Test(timeout = TIMEOUT)
    public void testHeadAndTail() {
        assertNull("Head pointer is not null after init.", secondList.getHead());
        assertNull("Tail pointer is not null after init.", secondList.getTail());
        secondList.addToFront("poop");
        assertNotNull("Head pointer is null after adding item to front.", secondList.getHead());
        assertNotNull("Tail pointer is null after adding item to front.", secondList.getTail());
        secondList.removeFromFront();
        assertNull("Head pointer is not null after removing only item (from front).", secondList.getHead());
        assertNull("Tail pointer is not null after removing only item (from front)..", secondList.getTail());
        secondList.addToBack("poop");
        assertNotNull("Head pointer is null after adding item to back.", secondList.getHead());
        assertNotNull("Tail pointer is null after adding item to back.", secondList.getTail());
        secondList.removeFromBack();
        assertNull("Head pointer is not null after removing only item (from back).", secondList.getHead());
        assertNull("Tail pointer is not null after removing only item (from back).", secondList.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testingAddToFront() {
        secondList.addToFront("poop");
        assertEquals("Size did not update correctly after addToFront", 1, secondList.size());
        assertEquals("Head did not update correctly after addToFront", "poop", secondList.getHead().getData());
        assertEquals("Tail did not update correctly after addToFront", "poop", secondList.getTail().getData());

        secondList.addToFront("lol");
        assertEquals("New first element's next pointer is set correctly after addToFront.", "poop", secondList.getHead().getNext().getData());
        assertEquals("Previous first element's previous pointer is not updated after addToFront.", "lol", secondList.getHead().getNext().getPrevious().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testingAddToBack() {
        secondList.addToBack("lol");
        assertEquals("Size did not update correctly after addToBack", 1, secondList.size());
        assertEquals("Head did not update correctly after addToBack", "lol", secondList.getHead().getData());
        assertEquals("Tail did not update correctly after addToBack", "lol", secondList.getTail().getData());

        secondList.addToBack("poop");
        assertEquals("New last element's previous pointer is set correctly after addToBack.", "lol", secondList.getTail().getPrevious().getData());
        assertEquals("Previous last element's next pointer is not updated after addToBack.", "poop", secondList.getTail().getPrevious().getNext().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testingAddAtIndex() {
        secondList.addToFront("poop");
        secondList.addToBack("lol");
        secondList.addAtIndex(1, "kappa");
        assertEquals("Size did not update correctly after addAtIndex.", 3, secondList.size());
        assertEquals("Head's next pointer does not point to the right item.", "kappa", secondList.getHead().getNext().getData());
        assertEquals("Tails's previous pointer does not point to the right item.", "kappa", secondList.getTail().getPrevious().getData());
        secondList.addAtIndex(0, "poop1");
        assertEquals("Head pointer does not point to the right item after adding to index 0.", "poop1", secondList.getHead().getData());
        secondList.addAtIndex(4, "poop5");
        assertEquals("Tail pointer does not point to the right item after adding to index {@code size}.", "poop5", secondList.getTail().getData());
        assertEquals("Size did not update correctly after addAtIndex.", 5, secondList.size());
    }

    @Test(timeout = TIMEOUT)
    public void testClearAndIsEmpty() {
        assertTrue("List is not empty after init.", secondList.isEmpty());
        secondList.addToFront("poop");
        assertTrue("List is empty after adding elements.", !(secondList.isEmpty()));
        secondList.clear();
        assertTrue("List is not empty after clear.", secondList.isEmpty());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionAddToFront() {
        secondList.addToFront(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionAddToBack() {
        secondList.addToBack(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionAddAtIndex() {
        secondList.addToBack(null);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void indexTooSmallAddAtIndex() {
        secondList.addAtIndex(-1, "poop");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void indexTooLargeAddAtIndex() {
        secondList.addToFront("poop");
        secondList.addToFront("poop");
        secondList.addToFront("poop");
        secondList.addAtIndex(27, "poop");
    }

    @Test(timeout = TIMEOUT)
    public void testingToArray() {
        String[] expectedArray = new String[5];
        for (int i = 0; i < 5; i++) {
            expectedArray[i] = "poop" + i;
            secondList.addToBack("poop" + i);
        }
        assertArrayEquals("Array is not as expected.", expectedArray, secondList.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testingGet() {
        for (int i = 0; i < 5; i++) {
            secondList.addAtIndex(i, "poop" + i);
        }

        for (int i = 0; i < 5; i++) {
            assertEquals("Element at index " + i + " was not as expected.", "poop" + i, secondList.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testingRemoveFromBack() {
        for (int i = 0; i < 5; i++) {
            secondList.addAtIndex(i, "poop" + i);
        }

        assertEquals("Tail pointer does not point at the right element.", "poop4", secondList.getTail().getData());
        assertEquals("Size did not update correctly after addAtIndex", 5, secondList.size());

        secondList.removeFromBack();

        assertEquals("Tail pointer does not point at the right element after removeFromBack.", "poop3", secondList.getTail().getData());
        assertEquals("Size did not update correctly after removeFromBack.", 4, secondList.size());
        assertNull("Next pointer of new last element not updated after removeFromBack.", secondList.getTail().getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testingRemoveFromFront() {
        for (int i = 0; i < 5; i++) {
            secondList.addAtIndex(i, "poop" + i);
        }

        assertEquals("Tail pointer does not point at the right element.", "poop0", secondList.getHead().getData());
        assertEquals("Size did not update correctly after addAtIndex", 5, secondList.size());

        secondList.removeFromFront();

        assertEquals("Head pointer does not point at the right element after removeFromFront.", "poop1", secondList.getHead().getData());
        assertEquals("Size did not update correctly after removeFromFront.", 4, secondList.size());
        assertNull("Previous pointer of new first element not updated after removeFromFront.", secondList.getHead().getPrevious());
    }

    @Test(timeout = TIMEOUT)
    public void testingRemoveAtIndex() {
        for (int i = 0; i < 5; i++) {
            secondList.addAtIndex(i, "poop" + i);
        }

        assertEquals("Size did not update correctly after addAtIndex.", 5, secondList.size());

        secondList.removeAtIndex(2);
        assertEquals("Elements not in expected order after removeAtIndex.", "poop3", secondList.get(2));
        assertEquals("Size did not update correctly after removeAtIndex.", 4, secondList.size());

        secondList.removeAtIndex(0);
        assertEquals("Head pointer not updated correctly after removeAtIndex at index 0.", "poop1", secondList.getHead().getData());
        assertEquals("Size did not update correctly after removeAtIndex.", 3, secondList.size());

        secondList.removeAtIndex(2);
        assertEquals("Head pointer not updated correctly after removeAtIndex at index 0.", "poop3", secondList.getTail().getData());
        assertEquals("Size did not update correctly after removeAtIndex.", 2, secondList.size());
    }

    @Test(timeout = TIMEOUT)
    public void testingRemoveAllOccurrences() {

        assertFalse("removeAllOccurrences returned true when the list is empty.", secondList.removeAllOccurrences("poop"));

        for (int i = 0; i < 5; i++) {
            secondList.addAtIndex(i, "poop");
        }

        assertEquals("Size did not update correctly after addAtIndex.", 5, secondList.size());

        secondList.addToBack("lol2");
        secondList.addToFront("lol");

        assertEquals("Size did not update correctly after adding.", 7, secondList.size());

        assertTrue("removeAllOccurrences returned false when elements should have been removed.", secondList.removeAllOccurrences("poop"));
        assertFalse("removeAllOccurrences returned true when elements should not have been removed.", secondList.removeAllOccurrences("kappa"));
        assertEquals("Size did not update correctly after removeAllOccurrences.", 2, secondList.size());

        assertTrue("removeAllOccurrences returned false when elements should have been removed.", secondList.removeAllOccurrences("lol"));
        assertTrue("removeAllOccurrences returned false when elements should have been removed.", secondList.removeAllOccurrences("lol2"));

        assertNull("Head pointer is not null after removeAllOccurrences.", secondList.getHead());
        assertNull("Tail pointer is not null after removeAllOccurrences.", secondList.getTail());
        assertEquals("Size did not update correctly after removeAllOccurrences.", 0, secondList.size());
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void indexTooSmallRemoveAtIndex() {
        secondList.removeAtIndex(-1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void indexTooLargeRemoveAtIndex() {
        secondList.addToFront("poop");
        secondList.addToFront("poop");
        secondList.addToFront("poop");
        secondList.removeAtIndex(27);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void indexIsZeroButListIsEmpty() {
        secondList.removeAtIndex(0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionRemoveAllOccurrences() {
        secondList.removeAllOccurrences(null);
    }

    // fourth set

    private <T> int getListSize(LinkedListInterface<T> l) {
        // Compute the list size with a O(n) algorithm.
        // This isn't acceptable in the submitted code,
        //     but it's used here to avoid relying on the .size() implementation.

        int size = 0;
        LinkedListNode<T> node = l.getHead();
        while (node != null) {
            size++;
            node = node.getNext();
        }

        return size;
    }

    private <T> void assertLinkedListEquals(LinkedListInterface<T> l, T[] expected) {
        // this method also tests for consistency in next/previous references.
        assertEquals("List size is not consistent with .size()", l.size(), getListSize(l));
        assertEquals(l.size(), expected.length);

        LinkedListNode<T> node = l.getHead();
        if (l.size() == 0) {
            assertNull(node);
            assertNull(l.getTail());
            return;
        }

        assertNull(node.getPrevious());
        int i = 0;
        boolean continueLoop = node != null;
        while (continueLoop) {
            assertEquals(node.getData(), expected[i]);
            i++;
            if (node.getNext() == null) {
                continueLoop = false;
                assertEquals(l.getTail(), node);
            } else {
                node = node.getNext();
            }
        }

        // do it AGAIN, but BACKWARDS this time.
        LinkedListNode<T> node2 = l.getTail();
        assertNull(l.getTail().getNext());
        int i2 = l.size() - 1;
        boolean continueLoop2 = node2 != null;
        while (continueLoop2) {
            assertEquals(node2.getData(), expected[i2]);
            i2--;
            if (node2.getPrevious() == null) {
                continueLoop2 = false;
                assertEquals(l.getHead(), node2);
            } else {
                node2 = node2.getPrevious();
            }
        }
    }

    private void assertException(String message, Class<? extends Exception> exceptionClass, Runnable code) {
        try {
            code.run();
            Assert.fail(message);
        } catch (Exception e) {
            assertEquals(message, exceptionClass, e.getClass());
            assertNotNull("Exception messages must not be empty", e.getMessage());
            assertNotEquals("Exception messages must not be empty", e.getMessage(), "");
        }
    }

    @Before
    public void setup() {
        list = new DoublyLinkedList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testingConstructor() {
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test(timeout = TIMEOUT)
    public void testing1AddAtIndex() {
        assertException(
                "Adding a negative data element should throw an IllegalArgumentException",
                IllegalArgumentException.class,
                () -> list.addAtIndex(0,  null));

        assertException(
                "Adding at a negative index should throw an IndexOutOfBounds exception",
                IndexOutOfBoundsException.class,
                () -> list.addAtIndex(-1,  "fail"));

        assertException(
                "Adding at an index > size should throw an IndexOutOfBounds exception",
                IndexOutOfBoundsException.class,
                () -> list.addAtIndex(1, "fail"));

        list.addAtIndex(0,  "01-a");

        String[] expected = {"01-a"};
        assertLinkedListEquals(list, expected);

        list.addAtIndex(0,  "01-b");
        list.addAtIndex(0,  "01-c");

        assertException("Adding at an index > size should throw an IndexOutOfBounds exception",
                IndexOutOfBoundsException.class,
                () -> list.addAtIndex(4, "fail"));

        String[] expected2 = {"01-c", "01-b", "01-a"};
        assertLinkedListEquals(list, expected2);

        list.addAtIndex(1, "01-d");
        String[] expected3 = {"01-c", "01-d", "01-b", "01-a"};
        assertLinkedListEquals(list, expected3);

        list.addAtIndex(4, "01-e");
        String[] expected4 = {"01-c", "01-d", "01-b", "01-a", "01-e"};
        assertLinkedListEquals(list, expected4);
    }

    @Test(timeout = TIMEOUT)
    public void testing1AddToFront() {
        assertException(
                "Adding a negative data element should throw an IllegalArgumentException",
                IllegalArgumentException.class,
                () -> list.addToFront(null));

        list.addToFront("02-a");
        String[] expected = {"02-a"};
        assertLinkedListEquals(list, expected);

        list.addToFront("02-b");
        String[] expected2 = {"02-b", "02-a"};
        assertLinkedListEquals(list, expected2);
    }

    @Test(timeout = TIMEOUT)
    public void testing1AddToBack() {
        assertException(
                "Adding a negative data element should throw an IllegalArgumentException",
                IllegalArgumentException.class,
                () -> list.addToBack(null));

        list.addToBack("03-a");
        String[] expected = {"03-a"};
        assertLinkedListEquals(list, expected);

        list.addToBack("03-b");
        String[] expected2 = {"03-a", "03-b"};
        assertLinkedListEquals(list, expected2);
    }

    @Test(timeout = TIMEOUT)
    public void testing1RemoveAtIndex() {
        // depends on addToBack
        assertException(
                "Removing at a negative index should throw an IndexOutOfBoundsException",
                IndexOutOfBoundsException.class,
                () -> list.removeAtIndex(-1));

        assertException(
                "Removing at an index >= size should throw an IndexOutOfBoundsException",
                IndexOutOfBoundsException.class,
                () -> list.removeAtIndex(0));


        list.addToBack("04-a");
        list.addToBack("04-b");
        list.addToBack("04-c");
        list.addToBack("04-d");
        list.addToBack("04-e");

        assertException(
                "Removing at an index >= size should throw an IndexOutOfBoundsException",
                IndexOutOfBoundsException.class,
                () -> list.removeAtIndex(5));

        list.removeAtIndex(0);
        String[] expected = {"04-b", "04-c", "04-d", "04-e"};
        assertLinkedListEquals(list, expected);

        list.removeAtIndex(3);
        String[] expected2 = {"04-b", "04-c", "04-d"};
        assertLinkedListEquals(list, expected2);

        list.removeAtIndex(1);
        String[] expected3 = {"04-b", "04-d"};
        assertLinkedListEquals(list, expected3);

        list.removeAtIndex(0);
        list.removeAtIndex(0);

        assertException(
                "Removing at an index >= size should throw an IndexOutOfBoundsException",
                IndexOutOfBoundsException.class,
                () -> list.removeAtIndex(0));
    }

    @Test(timeout = TIMEOUT)
    public void testing1RemoveFromFront() {
        // depends on addToBack
        assertEquals(list.removeFromFront(), null);
        assertEquals(list.size(), 0);

        list.addToBack("05-a");
        list.addToBack("05-b");

        String result = list.removeFromFront();
        assertEquals(result, "05-a");

        String[] expected = {"05-b"};
        assertLinkedListEquals(list, expected);

        String result2 = list.removeFromFront();
        assertEquals(result2, "05-b");

        String[] expected2 = {};
        assertLinkedListEquals(list, expected2);

        assertEquals(list.removeFromFront(), null);
        assertEquals(list.size(), 0);
    }

    @Test(timeout = TIMEOUT)
    public void testing1RemoveFromBack() {
        // depends on addToBack
        assertEquals(list.removeFromBack(), null);
        assertEquals(list.size(), 0);

        list.addToBack("06-a");
        list.addToBack("06-b");

        String result = list.removeFromBack();
        assertEquals(result, "06-b");

        String[] expected = {"06-a"};
        assertLinkedListEquals(list, expected);

        String result2 = list.removeFromBack();
        assertEquals(result2, "06-a");

        String[] expected2 = {};
        assertLinkedListEquals(list, expected2);

        assertEquals(list.removeFromBack(), null);
        assertEquals(list.size(), 0);
    }

    @Test(timeout = TIMEOUT)
    public void testing1RemoveAllOccurrences() {
        // depends on addToBack
        assertException(
                "Passing null to removeAllOccurrences should throw an IllegalArgumentException",
                IllegalArgumentException.class,
                () -> list.removeAllOccurrences(null));

        assertFalse(list.removeAllOccurrences(""));

        list.addToBack("07-a");
        list.addToBack("07-b");
        list.addToBack("07-c");

        assertFalse(list.removeAllOccurrences("07-d"));
        String[] expected = {"07-a", "07-b", "07-c"};
        assertLinkedListEquals(list, expected);

        assertTrue(list.removeAllOccurrences("07-b"));
        String[] expected2 = {"07-a", "07-c"};
        assertLinkedListEquals(list, expected2);

        assertTrue(list.removeAllOccurrences("07-a"));
        String[] expected3 = {"07-c"};
        assertLinkedListEquals(list, expected3);

        assertTrue(list.removeAllOccurrences("07-c"));
        String[] expected4 = {};
        assertLinkedListEquals(list, expected4);

        list.addToBack("07-d");
        list.addToBack("07-d");
        list.addToBack("07-e");

        assertTrue(list.removeAllOccurrences("07-d"));
        String[] expected5 = {"07-e"};
        assertLinkedListEquals(list, expected5);

        list.addToBack("07-e");
        assertFalse(list.removeAllOccurrences("07-d"));
        String[] expected6 = {"07-e", "07-e"};
        assertLinkedListEquals(list, expected6);

        assertTrue(list.removeAllOccurrences("07-e"));
        String[] expected7 = {};
        assertLinkedListEquals(list, expected7);

        // thanks to Tyler Flynn for the idea to check equality correctly
        // I use a special object to make it more obvious what's happening here.
        LinkedListInterface<EqualObject> newList = new DoublyLinkedList<EqualObject>();
        newList.addToBack(new EqualObject());
        newList.addToBack(new EqualObject());

        assertTrue(newList.removeAllOccurrences(new EqualObject()));
        EqualObject[] expected8 = {};
        assertLinkedListEquals(newList, expected8);
    }

    @Test(timeout = TIMEOUT)
    public void testing1Get() {
        // depends on addToBack
        assertException(
                "Retrieving at a negative index should throw an IndexOutOfBoundsException",
                IndexOutOfBoundsException.class,
                () -> list.get(-1));

        assertException(
                "Retrieving at an index >= size should throw an IndexOutOfBoundsException",
                IndexOutOfBoundsException.class,
                () -> list.get(0));

        list.addToBack("08-a");
        list.addToBack("08-b");
        list.addToBack("08-c");

        assertException(
                "Retrieving at an index >= size should throw an IndexOutOfBoundsException",
                IndexOutOfBoundsException.class,
                () -> list.get(3));

        assertEquals(list.get(0), "08-a");
        assertEquals(list.get(1), "08-b");
        assertEquals(list.get(2), "08-c");

        String[] expected = {"08-a", "08-b", "08-c"};
        assertLinkedListEquals(list, expected);
    }

    @Test(timeout = TIMEOUT)
    public void testing1ToArray() {
        // depends on addToBack
        Integer[] expected = {};
        assertArrayEquals(intList.toArray(), expected);

        intList.addToBack(1);
        Integer[] expected2 = {1};
        assertArrayEquals(intList.toArray(), expected2);

        intList.addToBack(2);
        Integer[] expected3 = {1, 2};
        assertArrayEquals(intList.toArray(), expected3);

        intList.removeFromFront();
        Integer[] expected4 = {2};
        assertArrayEquals(intList.toArray(), expected4);
    }

    @Test(timeout = TIMEOUT)
    public void testing1IsEmpty() {
        // depends on addToBack
        assertTrue(list.isEmpty());

        list.addToBack("10-a");
        assertFalse(list.isEmpty());

        list.removeFromFront();
        assertTrue(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testing1Size() {
        // depends on addToBack, addToFront, removeFromBack, removeFromFront
        // .size() is mostly tested in the assertLinkedListEquals method,
        //     so this is a bigger test that tests .size() behaviour with some random operations.
        assertEquals(list.size(), 0);

        list.addToBack("10-a");
        assertEquals(list.size(), 1);

        list.removeFromFront();
        assertEquals(list.size(), 0);

        int expectedSize = 0;
        // the fun part
        for (int i = 0; i < 100; i++) {
            if (Math.random() > .5) {
                list.addToBack("" + Math.random());
            } else {
                list.addToFront("" + Math.random());
            }

            expectedSize++;
        }

        for (int i = 0; i < 1000; i++) {
            if (Math.random() > .5) {
                if (Math.random() > .5) {
                    list.addToBack("" + Math.random());
                } else {
                    list.addToFront("" + Math.random());
                }

                expectedSize++;
            } else {
                if (Math.random() > .5) {
                    list.removeFromBack();
                } else {
                    list.removeFromFront();
                }

                expectedSize--;
                if (expectedSize < 0) {
                    expectedSize = 0;
                }
            }
        }

        assertEquals(list.size(), expectedSize);
    }

    @Test(timeout = TIMEOUT)
    public void testing1Clear() {
        // depends on addToBack
        list.clear();
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);

        list.addToBack("12-a");
        list.clear();
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);

        list.addToBack("12-b");
        list.addToBack("12-c2");
        list.clear();
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);
    }

    private class EqualObject {
        @Override
        public boolean equals(Object other) {
            return true;
        }
    }

    // fifth set

    @Test(timeout = TIMEOUT)
    public void testAddException() {
        list.addToFront("Foo");
        //Testing for addAtIndex IndexOutOfBoundsException
        for (int i: new int[]{-2, -1, 2, 3}) {
            try {
                list.addAtIndex(i, "");
                Assert.fail();
            } catch (Exception e) {
                assertEquals(IndexOutOfBoundsException.class, e.getClass());
            }
        }
        //Testing for addAtIndex IllegalArgumentException
        for (int i: new int[]{0, 1}) {
            try {
                list.addAtIndex(i, null);
                Assert.fail();
            } catch (Exception e) {
                assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        //Testing for addToFront IllegalArgumentException
        try {
            list.addToFront(null);
            Assert.fail();
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
        //Testing for addToBack IllegalArgumentException
        try {
            list.addToBack(null);
            Assert.fail();
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test(timeout = TIMEOUT)
    public void test02_addAtIndex() {
        assertNull(list.getHead());
        assertNull(list.getTail());

        assertTrue(list.isEmpty());

        assertEquals(0, list.size());

        list.addAtIndex(0, "Aa");

        assertNotNull(list.getHead());
        assertNotNull(list.getTail());

        list.addAtIndex(1, "Bb");
        list.addAtIndex(2, "Cc");

        assertEquals(3, list.size());

        assertFalse(list.isEmpty());

        assertEquals(list.get(0), "Aa");
        assertEquals(list.get(1), "Bb");
        assertEquals(list.get(2), "Cc");

        assertArrayEquals(list.toArray(), new String[]{"Aa", "Bb", "Cc"});
    }

    @Test(timeout = TIMEOUT)
    public void test03_addToFront() {
        list.addToFront("Aa");  //Aa

        assertNotNull(list.getHead());
        assertNotNull(list.getTail());

        assertEquals(1, list.size());

        assertFalse(list.isEmpty());

        list.addToFront("Bb");  //Bb Aa
        list.addAtIndex(1, "Cc");   //Bb Cc Aa
        list.addAtIndex(0, "Dd");   //Dd Bb Cc Aa

        assertEquals(4, list.size());

        assertEquals(list.get(0), "Dd");
        assertEquals(list.get(1), "Bb");
        assertEquals(list.get(2), "Cc");
        assertEquals(list.get(3), "Aa");

        assertArrayEquals(list.toArray(), new String[]{"Dd", "Bb", "Cc", "Aa"});
    }

    @Test(timeout = TIMEOUT)
    public void test04_addToBack() {
        list.addToBack("Aa");   //Aa

        assertNotNull(list.getHead().getData());
        assertNotNull(list.getTail().getData());

        assertEquals(1, list.size());

        assertFalse(list.isEmpty());

        list.addToBack("Bb");   //Aa Bb
        list.addToBack("Cc");   //Aa Bb Cc
        list.addAtIndex(1, "Dd");   //Aa Dd Bb Cc

        assertArrayEquals(list.toArray(), new String[]{"Aa", "Dd", "Bb", "Cc"});

        list.addToBack("Ee");   //Aa Dd Bb Cc Ee

        assertEquals(5, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void test05_removeAtIndex() {
        try {
            list.removeAtIndex(0);
            Assert.fail();
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }

        list.addAtIndex(0, "Aa");   //Aa
        list.addToFront("Bb");  //Bb Aa
        list.addToBack("Cc");   //Bb Aa Cc

        //IndexOutOfBoundsException Check
        for (int i: new int[]{-1, 3, 4}) {
            try {
                list.removeAtIndex(i);
                Assert.fail();
            } catch (Exception e) {
                assertEquals(IndexOutOfBoundsException.class, e.getClass());
            }
        }

        assertArrayEquals(list.toArray(), new String[]{"Bb", "Aa", "Cc"});

        assertEquals(list.removeAtIndex(1), "Aa");
        assertArrayEquals(list.toArray(), new String[]{"Bb", "Cc"});

        assertEquals(2, list.size());
        assertEquals(list.removeAtIndex(1), "Cc");

        assertNotNull(list.getHead());
        assertNotNull(list.getTail());

        assertEquals(list.removeAtIndex(0), "Bb");
        assertArrayEquals(list.toArray(), new String[]{});

        assertNull(list.getHead());
        assertNull(list.getTail());

        assertTrue(list.isEmpty());

        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void test06_removeFromFront() {
        assertNull(list.removeFromFront());

        list.addAtIndex(0, "Aa");   //Aa
        list.addToFront("Bb");  //Bb Aa
        list.addToBack("Cc");   //Bb Aa Cc

        assertEquals(list.removeFromFront(), "Bb");
        assertEquals(2, list.size());
        assertEquals(list.getHead().getData(), "Aa");
        assertNull(list.getHead().getPrevious());

        assertEquals(list.removeFromFront(), "Aa");
        assertEquals(list.getHead().getData(), "Cc");
        assertEquals(list.getTail().getData(), "Cc");
        assertEquals(1, list.size());

        assertEquals(list.removeFromFront(), "Cc");
        assertArrayEquals(list.toArray(), new String[]{});

        assertNull(list.getHead());
        assertNull(list.getTail());

        assertTrue(list.isEmpty());

        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void test07_removeFromBack() {
        assertNull(list.removeFromBack());

        list.addAtIndex(0, "Aa");   //Aa
        list.addToFront("Bb");  //Bb Aa
        list.addToBack("Cc");   //Bb Aa Cc

        assertEquals(list.removeFromBack(), "Cc");  //Bb Aa
        assertEquals(2, list.size());

        assertEquals(list.removeFromFront(), "Bb");     //Aa
        assertEquals(list.size(), 1);
        assertEquals(list.getHead().getData(), "Aa");
        assertEquals(list.getTail().getData(), "Aa");

        assertEquals(list.removeFromBack(), "Aa");
        assertArrayEquals(list.toArray(), new String[]{});

        assertNull(list.getHead());
        assertNull(list.getTail());

        assertTrue(list.isEmpty());

        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void test08_removeAllOccurrences() {
        try {
            list.removeAllOccurrences(null);
            Assert.fail();
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
        }
        list.addToFront("1");   //1

        list.addToBack("3");    //1 3

        list.addToBack("3");    //1 3 3

        list.addAtIndex(3, "4");    //1 3 3 4

        list.addToBack("1");       //1 3 3 4 1


        assertEquals(5, list.size());

        //remove 3
        assertTrue(list.removeAllOccurrences("3")); //1 4 1
        assertArrayEquals(list.toArray(), new String[]{"1", "4", "1"});
        assertEquals(3, list.size());

        //remove 3
        assertFalse(list.removeAllOccurrences("3")); //1 4 1
        System.out.println(list.getHead());
        System.out.println(list.getTail());
        System.out.println(list.getHead().getNext());
        System.out.println(list.getHead().getPrevious());
        System.out.println(list.getTail().getPrevious());
        System.out.println(list.getTail().getNext());
        System.out.println(list.getHead().getNext().getNext());
        System.out.println(list.getTail().getPrevious().getPrevious());
        assertEquals(list.size(), 3);


        //remove 1
        assertTrue(list.removeAllOccurrences("1")); //4
        System.out.println(list.getHead());
        System.out.println(list.getTail());
//        assertEquals(list.getHead(), "4");

        //check for head, tail, and isEmpty
        assertNotNull(list.getHead());
        assertNotNull(list.getTail());
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());

        //remove 4
        assertTrue(list.removeAllOccurrences("4"));

        //check to see if empty
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertArrayEquals(list.toArray(), new String[]{});
    }

    @Test(timeout = TIMEOUT)
    public void test09_clear() {
        list.addToFront("Aa");

        assertNotNull(list.getHead());
        assertNotNull(list.getTail());

        assertEquals(1, list.size());

        assertFalse(list.isEmpty());

//        assertArrayEquals(list.toArray(), new String[]{"Aa"});

        assertEquals(list.get(0), "Aa");

        list.clear();

        assertNull(list.getHead());
        assertNull(list.getTail());

        assertEquals(0, list.size());

        assertTrue(list.isEmpty());

        assertArrayEquals(list.toArray(), new String[]{});
    }

    @Test(timeout = TIMEOUT)
    public void test10_getException() {
        list.addToFront("1");   //1
        list.addToBack("3");    //1 3

        assertEquals(list.get(0), "1");
        assertEquals(list.get(1), "3");
        for (int i: new int[]{-1, 2, 3}) {
            try {
                list.get(i);
                Assert.fail();
            } catch (Exception e) {
                assertEquals(IndexOutOfBoundsException.class, e.getClass());
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void test11_testNode() {
        list.addToFront("A");   //A

        assertEquals(list.getHead().getData(), "A");
        assertEquals(list.getTail().getData(), "A");

        list.addToBack("B");    //A B
        list.addAtIndex(0, "C");    //C A B

        assertEquals(list.getHead().getData(), "C");
        assertEquals(list.getHead().getNext().getData(), "A");
        assertEquals(list.getHead().getNext().getNext().getData(), "B");

        assertNull(list.getHead().getPrevious());
        assertNull(list.getTail().getNext());

        assertEquals(list.getTail().getPrevious().getData(), "A");
        assertEquals(list.getTail().getPrevious().getPrevious().getData(), "C");
    }
}
