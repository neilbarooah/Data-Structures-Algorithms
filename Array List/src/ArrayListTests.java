import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by neilbarooah on 27/08/16.
 */
public class ArrayListTests {

    private ArrayListInterface<String> list;
    private ArrayListInterface<Character> charList;
    private ArrayList<String> newList;

    public static final int TIMEOUT = 200;
    private static final int INITIAL_CAPACITY = ArrayListInterface.INITIAL_CAPACITY;

    @org.junit.Before
    public void setUp() {
        list = new ArrayList<String>();
        charList = new ArrayList<Character>();
        newList = new ArrayList<>();
    }

    @org.junit.Test
    public void testAddStrings() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a"); //0a
        list.addAtIndex(1, "1a"); //0a 1a
        list.addAtIndex(2, "2a"); //0a 1a 2a
        list.addAtIndex(3, "3a"); //0a 1a 2a 3a
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a");
        list.addAtIndex(6, "6a");
        list.addAtIndex(7, "7a");
        list.addAtIndex(8, "8a");
        list.addAtIndex(5, "9a");
        list.addAtIndex(3, "10a");
        list.addAtIndex(0, "11a");
        list.addAtIndex(12, "12a");


        System.out.println(list.get(4));
        System.out.println(list.get(5));
        System.out.println(list.get(6));
        System.out.println(list.get(7));
        System.out.println(list.get(8));
        System.out.println(list.get(9));

        assertEquals(13, list.size());

        Object[] expected = new Object[20];
        expected[0] = "11a";
        expected[1] = "0a";
        expected[2] = "1a";
        expected[3] = "2a";
        expected[4] = "10a";
        expected[5] = "3a";
        expected[6] = "4a";
        expected[7] = "9a";
        expected[8] = "5a";
        expected[9] = "6a";
        expected[10] = "7a";
        expected[11] = "8a";
        expected[12] = "12a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @org.junit.Test
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a"); //4a 3a 2a 1a 0a
        list.addToFront("5a");
        list.addToFront("6a");
        list.addToBack("12a");
        list.addToFront("7a");
        list.addToBack("13a");
        list.addToFront("8a");
        list.addToFront("9a");
        list.addToBack("14a");
        list.addToFront("10a");
        list.addToFront("11a");
        list.addToBack("15a");


        assertEquals(16, list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.get(4));

        Object[] expected = new Object[20];
        expected[0] = "11a";
        expected[1] = "10a";
        expected[2] = "9a";
        expected[3] = "8a";
        expected[4] = "7a";
        expected[5] = "6a";
        expected[6] = "5a";
        expected[7] = "4a";
        expected[8] = "3a";
        expected[9] = "2a";
        expected[10] = "1a";
        expected[11] = "0a";
        expected[12] = "12a";
        expected[13] = "13a";
        expected[14] = "14a";
        expected[15] = "15a";

        assertArrayEquals(expected, list.getBackingArray());
    }

    @org.junit.Test
    public void testAddStringsBack() {
        assertEquals(0, list.size());

        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");
        list.addToBack("5a");
        list.addToBack("6a");
        list.removeAtIndex(0);
        list.addToBack("7a");
        list.addToBack("8a");
        list.addToBack("9a");
        list.addToBack("10a");
        list.removeAtIndex(4);
        list.addToBack("11a");
        list.removeFromFront();
        list.removeFromBack();

        assertEquals(8, list.size());
        Object[] expected = new Object[10];
        expected[0] = "2a";
        expected[1] = "3a";
        expected[2] = "4a";
        expected[3] = "6a";
        expected[4] = "7a";
        expected[5] = "8a";
        expected[6] = "9a";
        expected[7] = "10a";

        assertArrayEquals(expected, list.getBackingArray());
//        list.addToBack("0a");
//        list.addToBack("1a");
//        list.addToBack("2a");
//        list.addToBack("3a");
//        list.addToBack("4a"); //4a 3a 2a 1a 0a
//        list.addToBack("5a");
//        list.addToBack("6a");
//        list.addToBack("7a");
//        list.addToBack("8a");
//        list.addToBack("9a");
//        list.addToBack("10a");
//        list.addToBack("11a");
//
//
//        assertEquals(12, list.size());
//
//        Object[] expected = new Object[20];
//        expected[0] = "0a";
//        expected[1] = "1a";
//        expected[2] = "2a";
//        expected[3] = "3a";
//        expected[4] = "4a";
//        expected[5] = "5a";
//        expected[6] = "6a";
//        expected[7] = "7a";
//        expected[8] = "8a";
//        expected[9] = "9a";
//        expected[10] = "10a";
//        expected[11] = "11a";
//        assertArrayEquals(expected, list.getBackingArray());
    }

    @org.junit.Test
    public void testRemoveStrings() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("0a", list.removeAtIndex(0)); //0a 1a 3a 4a 5a
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.get(4));

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @org.junit.Test
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

    @org.junit.Test
    public void testRemoveAtIndex() throws Exception {

    }

    @org.junit.Test
    public void testRemoveFromFront() throws Exception {

    }

    @org.junit.Test
    public void testRemoveFromBack() throws Exception {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a");
        list.addAtIndex(6, "6a");
        list.addAtIndex(7, "7a");
        list.addAtIndex(8, "8a");
        list.addAtIndex(9, "9a");
        list.addAtIndex(10, "10a");
        list.addAtIndex(11, "11a");
        assertEquals("11a", list.removeFromBack());
        assertEquals(11, list.size());
        assertEquals("10a", list.removeFromBack());
        assertEquals(10, list.size());
        assertEquals("9a", list.removeFromBack());
        assertEquals(9, list.size());
        assertEquals("8a", list.removeFromBack());
        assertEquals(8, list.size());
        assertEquals("7a", list.removeFromBack());
        assertEquals(7, list.size());
        assertEquals("6a", list.removeFromBack());
        assertEquals(6, list.size());
        assertEquals("5a", list.removeFromBack());
        assertEquals(5, list.size());
        assertEquals("4a", list.removeFromBack());
        assertEquals(4, list.size());
        assertEquals("3a", list.removeFromBack());
        assertEquals(3, list.size());
        assertEquals("2a", list.removeFromBack());
        assertEquals(2, list.size());
        assertEquals("1a", list.removeFromBack());
        assertEquals(1, list.size());
        assertEquals("0a", list.removeFromBack());
        assertEquals(0, list.size());
    }

    @org.junit.Test
    public void testGet() throws Exception {

    }

    @org.junit.Test
    public void testIsEmpty() throws Exception {

    }

    @org.junit.Test
    public void testSize() throws Exception {

    }

    @org.junit.Test
    public void testClear() throws Exception {

    }

    @org.junit.Test
    public void testGetBackingArray() throws Exception {

    }

    @Test(timeout = TIMEOUT)
    public void testAlphabet() {
        assertEquals(0, charList.size());

        //Add low alphabet
        for (int i = 0; i < 26; i++) {
            charList.addToBack((char) ('a' + i));
        }

        Object[] fullLowAlpha = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        //Print array
        for (int i = 0; i < charList.size(); i++) {
            System.out.print(charList.getBackingArray()[i] + " ");
        }
        System.out.println();

        //Size check
        assertEquals(26, charList.size());

        //Vowel check
        assertEquals(charList.getBackingArray()[0], 'a');
        assertEquals(charList.getBackingArray()[4], 'e');
        assertEquals(charList.getBackingArray()[8], 'i');
        assertEquals(charList.getBackingArray()[14], 'o');
        assertEquals(charList.getBackingArray()[20], 'u');

        //Check all
        for (int i = 0; i < fullLowAlpha.length; i++) {
            assertEquals(fullLowAlpha[i], charList.getBackingArray()[i]);
        }

        //Remove the ends
        charList.removeFromFront();
        charList.removeFromBack();

        //Print array
        for (int i = 0; i < charList.size(); i++) {
            System.out.print(charList.getBackingArray()[i] + " ");
        }
        System.out.println();

        //Size Check
        assertEquals(24, charList.size());
        //Null check
        assertEquals(null, charList.getBackingArray()[25]);
        assertEquals(null, charList.getBackingArray()[26]);
        //Vowel check
        assertEquals(charList.getBackingArray()[3], 'e');
        assertEquals(charList.getBackingArray()[7], 'i');
        assertEquals(charList.getBackingArray()[13], 'o');
        assertEquals(charList.getBackingArray()[19], 'u');

        //Add the ends back
        charList.addToFront('a');
        charList.addToBack('z');

        //Print array
        for (int i = 0; i < charList.size(); i++) {
            System.out.print(charList.getBackingArray()[i] + " ");
        }
        System.out.println();

        //Size Check
        assertEquals(26, charList.size());
        //Check all
        for (int i = 0; i < fullLowAlpha.length; i++) {
            assertEquals(fullLowAlpha[i], charList.getBackingArray()[i]);
        }

        Object[] halfLowAlpha = {'a', 'c', 'e', 'g', 'i', 'k', 'm', 'o', 'q', 's', 'u', 'w', 'y',};

        //Remove odd indices
        for (int i = 1; i < charList.size(); i++) {
            charList.removeAtIndex(i);
        }

        //Print array
        for (int i = 0; i < charList.size(); i++) {
            System.out.print(charList.getBackingArray()[i] + " ");
        }
        System.out.println();

        //Check all
        for (int i = 0; i < halfLowAlpha.length; i++) {
            assertEquals(halfLowAlpha[i], charList.getBackingArray()[i]);
        }

        //Null check
        for (int i = halfLowAlpha.length; i < fullLowAlpha.length; i++) {
            assertEquals(null, charList.getBackingArray()[i]);
        }


        Object[] fullLowAndCapAlpha = {'a', 'B', 'c', 'D', 'e', 'F', 'g', 'H', 'i', 'J', 'k', 'L', 'm',
                'N', 'o', 'P', 'q', 'R', 's', 'T', 'u', 'V', 'w', 'X', 'y', 'Z'};

        //Add caps at odd indices
        for (int i = 1; i < fullLowAndCapAlpha.length; i+=2) {
            charList.addAtIndex(i, (char)('A' + i));
        }

        //Print array
        for (int i = 0; i < charList.size(); i++) {
            System.out.print(charList.getBackingArray()[i] + " ");
        }
        System.out.println();

        //Check all
        for (int i = 0; i < fullLowAndCapAlpha.length; i++) {
            assertEquals(fullLowAndCapAlpha[i], charList.getBackingArray()[i]);
        }

        charList.clear();

        //Check all
        for (int i = 0; i < 10; i++) {
            assertEquals(null, charList.getBackingArray()[i]);
        }

        assertEquals(true, charList.isEmpty());

    }

    /**
     *  A helper method for various tests.
     *
     * First, this method tests isEmpty and size.
     * All non-null elements in actual are compared against the elements in expected.
     * Using getExpectedLength, this method tests the nullity of
     * remaining elements in actual.backingArray, as well as length.
     *
     * @param method - String for assertion message, e.g. "Did addToFront update size?"
     * @param expected - An array of the non-null elements expected
     * @param actual - The actual list to be tested
     */
    private void assertArrayListEquals(String method, Object[] expected, ArrayList<String> actual) {
        if (expected.length == 0) {
            assertTrue(actual.isEmpty());
        } else {
            assertFalse(actual.isEmpty());
        }
        Object[] actualArray = actual.getBackingArray();
        assertEquals("Did " + method + " update size?", expected.length, actual.size());
        assertEquals(getExpectedLength(expected.length), actualArray.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actualArray[i]);
        }
        for (int i = expected.length; i < actualArray.length; i++) {
            assertNull(actualArray[i]);
        }
    }

    /**
     * A helper method for assertArrayListEquals
     *
     * @param i - The 0-indexed number of non-null elements in the list
     * @return - The expected length of the backingArray, accounting for null elements
     */
    private int getExpectedLength(int i) {
        int expectedLength = INITIAL_CAPACITY;
        if (i > 0) {
            expectedLength = INITIAL_CAPACITY *
                    (int) Math.ceil(new Integer(i).doubleValue() /
                            INITIAL_CAPACITY);
        }
        return expectedLength;
    }

    @Test(timeout = TIMEOUT)
    public void test_00_constructor() {
        assertEquals("A new empty ArrayList should have a backing array of size " +
                        INITIAL_CAPACITY + ".",
                INITIAL_CAPACITY, newList.getBackingArray().length);
        assertEquals("A new empty ArrayList should be size 0.", 0, newList.size());
    }

    @Test(timeout = TIMEOUT)
    public void test_01_addToFront() {
        // test exception
        try {
            newList.addToFront(null);
            Assert.fail();
        } catch (Exception e) {
            assertEquals("addToFront must throw java.lang.IllegalArgumentException if data is null.",
                    IllegalArgumentException.class, e.getClass());
        }
        // test function
        for (int i = 1; i <= INITIAL_CAPACITY + 1; i++) {
            String[] expected = new String[i];
            for (int j = i; j > 0; j--) {
                expected[i - j] = "" + j;
            }
            newList.addToFront(i + "");
            assertArrayListEquals("addToFront", expected, newList);
        }
    }

    @Test(timeout = TIMEOUT)
    public void test_02_addToBack() {
        // test exception
        try {
            newList.addToBack(null);
            Assert.fail();
        } catch (Exception e) {
            assertEquals("addToBack must throw java.lang.IllegalArgumentException if data is null.",
                    IllegalArgumentException.class, e.getClass());
        }
        // test function
        for (int i = 1; i <= INITIAL_CAPACITY + 1; i++) {
            String[] expected = new String[i];
            for (int j = i; j > 0; j--) {
                expected[j - 1] = "" + j;
            }
            newList.addToBack(i + "");
            assertArrayListEquals("addToBack", expected, newList);
        }
    }

    @Test(timeout = TIMEOUT)
    public void test_03_addAtIndex() {
        // test exceptions
        String message = "addAtIndex must throw java.lang.IndexOutOfBoundsException" +
                " if index is negative or index > size.";
        for (int i: new int[]{-1, 1}) {
            try {
                newList.addAtIndex(i, "");
                Assert.fail();
            } catch (Exception e) {
                assertEquals(message, IndexOutOfBoundsException.class, e.getClass());
            }
        }
        message = "addAtIndex must throw java.lang.IllegalArgumentException if data is null.";
        try {
            newList.addAtIndex(0, null);
            Assert.fail();
        } catch (Exception e) {
            assertEquals(message, IllegalArgumentException.class, e.getClass());
        }
        // test function
        // test addAtIndex(0, data) for empty list
        newList.addAtIndex(0, "foo");
        assertArrayListEquals("addAtIndex", new String[]{"foo"}, newList);
        newList = new ArrayList<>(); // reset
        // test addAtIndex(0, data) for partial list
        newList.addToFront("2");
        newList.addAtIndex(0, "1");
        assertArrayListEquals("addAtIndex", new String[]{"1", "2"}, newList);
        // test addAtIndex(size, data) for partial list
        newList.addAtIndex(2, "4");
        assertArrayListEquals("addAtIndex", new String[]{"1", "2", "4"}, newList);
        // test addAtIndex(size / 2, data) for partial list
        newList.addAtIndex(2, "3");
        assertArrayListEquals("addAtIndex", new String[]{"1", "2", "3", "4"}, newList);
        newList = new ArrayList<>(); // reset
        // test addAtIndex(0, data) for full list
        for (int i = 1; i <= INITIAL_CAPACITY; i++) {
            newList.addToBack("" + i);
        }
        newList.addAtIndex(0, "" + 0);
        assertArrayListEquals("addAtIndex", new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}, newList);
        newList = new ArrayList<>(); // reset
        // test addAtIndex(size, data) for full list
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            newList.addToBack("" + i);
        }
        newList.addAtIndex(10, "" + 10);
        assertArrayListEquals("addAtIndex", new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}, newList);
        newList = new ArrayList<>(); // reset
        // test addAtIndex(size / 2, data) for full list
        for (int i = 0; i <= INITIAL_CAPACITY; i++) {
            if (i != 6) {
                newList.addToBack("" + i);
            }
        }
        newList.addAtIndex(6, "" + 6);
        assertArrayListEquals("addAtIndex", new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}, newList);
    }

    @Test(timeout = TIMEOUT)
    public void test_04_removeFromFront() {
        // test no exceptions
        // test function
        assertNull(newList.removeFromFront());
        newList.addToFront("0");
        assertEquals("0", newList.removeFromFront());
        assertArrayListEquals("removeFromFront", new String[]{}, newList);
        newList.addToFront("0");
        newList.addToFront("foo");
        assertEquals("foo", newList.removeFromFront());
        assertArrayListEquals("removeFromFront", new String[]{"0"}, newList);
    }

    @Test(timeout = TIMEOUT)
    public void test_05_removeFromBack() {
        // test no exceptions
        // test function
        assertNull(newList.removeFromBack());
        newList.addToFront("0");
        assertEquals("0", newList.removeFromBack());
        assertArrayListEquals("removeFromBack", new String[]{}, newList);
        newList.addToFront("foo");
        newList.addToFront("0");
        assertEquals("foo", newList.removeFromBack());
        assertArrayListEquals("removeFromBack", new String[]{"0"}, newList);
    }

    @Test(timeout = TIMEOUT)
    public void test_06_removeAtIndex() {
        // test exceptions
        String message = "removeAtIndex must throw java.lang.IndexOutOfBoundsException" +
                " if index is negative or index >= size.";
        for (int i: new int[]{-1, 0, 1}) {
            try {
                newList.removeAtIndex(i);
                Assert.fail();
            } catch (Exception e) {
                assertEquals(message, IndexOutOfBoundsException.class, e.getClass());
            }
        }
        // test function
        // test removeAtIndex(0, data) for partial list
        newList.addToBack("0");
        newList.addToBack("1");
        assertEquals("0", newList.removeAtIndex(0));
        assertArrayListEquals("removeAtIndex", new String[]{"1"}, newList);
        // test removeAtIndex(0 == size, data) for partial list
        assertEquals("1", newList.removeAtIndex(0));
        assertArrayListEquals("removeAtIndex", new String[]{}, newList);
        // test removeAtIndex(size, data) for partial list
        newList.addToBack("0");
        newList.addToBack("1");
        assertEquals("1", newList.removeAtIndex(1));
        assertArrayListEquals("removeAtIndex", new String[]{"0"}, newList);
        // test removeAtIndex(size / 2, data) for partial list
        newList.addToBack("foo");
        newList.addToBack("1");
        assertEquals("foo", newList.removeAtIndex(1));
        assertArrayListEquals("removeAtIndex", new String[]{"0", "1"}, newList);
    }

    @Test(timeout = TIMEOUT)
    public void test_07_get() {
        // test exceptions
        String message = "get must throw java.lang.IndexOutOfBoundsException" +
                " if index is negative or index >= size.";
        for (int i: new int[]{-1, 0, 1}) {
            try {
                newList.get(i);
                Assert.fail();
            } catch (Exception e) {
                assertEquals(message, IndexOutOfBoundsException.class, e.getClass());
            }
        }
        // test function
        newList.addToBack("0");
        assertEquals("0", newList.get(0));
    }

    @Test(timeout = TIMEOUT)
    public void test_08_clear() {
        // test no exceptions
        // test function
        newList.addToBack("0");
        newList.clear();
        assertArrayListEquals("clear", new String[]{}, newList);
    }

}