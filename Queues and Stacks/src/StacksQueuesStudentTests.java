import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Basic tests for the array-backed stack and queue classes.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class StacksQueuesStudentTests {

    private ArrayQueue<String> arrayQueue;
    private LinkedQueue<String> linkedQueue;
    private ArrayStack<String> arrayStack;
    private LinkedStack<String> linkedStack;
    private StackInterface<Integer> stack;
    private QueueInterface<Integer> queue;
    private ArrayQueue<String> aq;
    private ArrayStack<String> as;
    private LinkedQueue<String> lq;
    private LinkedStack<String> ls;

    public static final int TIMEOUT = 200;

    @org.junit.Before
    public void setUp() {
        aq = new ArrayQueue<>();
        as = new ArrayStack<>();
        lq = new LinkedQueue<>();
        ls = new LinkedStack<>();
        arrayQueue = new ArrayQueue<String>();
        linkedQueue = new LinkedQueue<String>();
        arrayStack = new ArrayStack<String>();
        linkedStack = new LinkedStack<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPush() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        stack.push(34);
        stack.push(29);
        stack.push(48);
        stack.push(59);

        assertEquals(4, stack.size());

        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        Object[] expected = new Object[10];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPop() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        stack.push(34);
        stack.push(29);
        stack.push(48);
        stack.push(59);
        assertEquals((Integer) 59, stack.pop());

        assertEquals(3, stack.size());

        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        Object[] expected = new Object[10];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueEnqueue() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(34);
        queue.enqueue(29);
        queue.enqueue(48);
        queue.enqueue(59);

        assertEquals(4, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        Object[] expected = new Object[10];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueDequeue() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(34);
        queue.enqueue(29);
        queue.enqueue(48);
        queue.enqueue(59);
        assertEquals((Integer) 34, queue.dequeue());

        assertEquals(3, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        Object[] expected = new Object[10];
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmptyAll() {
        assertTrue("ArrayQueue not empty upon init.", aq.isEmpty());
        assertTrue("ArrayStack not empty upon init.", as.isEmpty());
        assertTrue("LinkedQueue not empty upon init.", lq.isEmpty());
        assertTrue("LinkedStack not empty upon init.", ls.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStack() {
        as.push("poop1");
        assertFalse("ArrayStack is empty after pushing.", as.isEmpty());
        assertEquals("Size did not update correctly after pushing to ArrayStack.", 1, as.size());
        as.push("poop2");
        assertEquals("Size did not update correctly after pushing to ArrayStack.", 2, as.size());

        String[] expectedArray = new String[]{"poop1", "poop2", null, null, null, null, null, null, null, null};
        assertArrayEquals("The backing array of ArrayStack did not match what was expected.", expectedArray, as.getBackingArray());

        assertEquals("Pop did not return the expected value for ArrayStack.", "poop2", as.pop());
        assertEquals("Pop did not return the expected value for ArrayStack.", "poop1", as.pop());
        assertTrue("ArrayStack not empty after popping all elements.", as.isEmpty());

        try {
            as.pop();
            assertTrue("Popping off of an empty ArrayStack did not throw a NoSuchElementException.", false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }

        try {
            as.push(null);
            assertTrue("Pushing 'null' to an ArrayStack did not throw a IllegalArgumentException.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        int expectedSize  = (int) (((int) (as.getBackingArray().length * 1.5)) * 1.5);
        expectedArray = new String[expectedSize];
        for (int i = 0; i < expectedSize - 1; i++) {
            expectedArray[i] = "poop";
            as.push("poop");
        }

        assertEquals("The length of the backing array of ArrayStack is not as expected after two resizes.", expectedSize, as.getBackingArray().length);
        assertEquals("Size did not update correctly after forcing ArrayStack to resize.", (expectedSize - 1), as.size());
        assertArrayEquals("The backing array of ArrayStack did not match what was expected.", expectedArray, as.getBackingArray());

        as.push("poop");
        assertEquals("It looks like you're preemptively resizing the backing array.", expectedSize, as.getBackingArray().length);

        for (int i = 0; i < expectedSize; i++) {
            as.pop();
        }
        assertEquals("Did you shrink the array after popping? That's not supposed to happen.", expectedSize, as.getBackingArray().length);
        assertTrue("ArrayStack not empty after popping all elements.", as.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStack() {
        ls.push("poop1");
        assertFalse("LinkedStack is empty after pushing.", ls.isEmpty());
        assertEquals("Size did not update correctly after pushing to LinkedStack.", 1, ls.size());
        ls.push("poop2");
        assertEquals("Size did not update correctly after pushing to LinkedStack.", 2, ls.size());
        assertEquals("Pop did not return the expected value for LinkedStack.", "poop2", ls.pop());
        assertEquals("Pop did not return the expected value for LinkedStack.", "poop1", ls.pop());
        assertTrue("LinkedStack not empty after popping all elements.", ls.isEmpty());

        try {
            ls.pop();
            assertTrue("Popping off of an empty LinkedStack did not throw a NoSuchElementException.", false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }

        try {
            ls.push(null);
            assertTrue("Pushing 'null' to an LinkedStack did not throw a IllegalArgumentException.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueue() {
        lq.enqueue("poop1");
        assertFalse("LinkedStack is empty after enqueueing.", lq.isEmpty());
        assertEquals("Size did not update correctly after enqueueing to LinkedStack.", 1, lq.size());
        lq.enqueue("poop2");
        assertEquals("Size did not update correctly after enqueueing to LinkedStack.", 2, lq.size());
        assertEquals("Dequeue did not return the expected value for LinkedStack.", "poop1", lq.dequeue());
        assertEquals("Dequeue did not return the expected value for LinkedStack.", "poop2", lq.dequeue());
        assertTrue("LinkedStack not empty after Dequeuing all elements.", lq.isEmpty());

        try {
            lq.dequeue();
            assertTrue("Dequeuing off of an empty LinkedStack did not throw a NoSuchElementException.", false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }

        try {
            lq.enqueue(null);
            assertTrue("Enqueuing 'null' to an LinkedStack did not throw a IllegalArgumentException.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueue() {
        aq.enqueue("poop1");
        assertFalse("ArrayStack is empty after enqueueing.", aq.isEmpty());
        assertEquals("Size did not update correctly after enqueueing to ArrayStack.", 1, aq.size());
        aq.enqueue("poop2");
        assertEquals("Size did not update correctly after enqueueing to ArrayStack.", 2, aq.size());
        assertEquals("Dequeue did not return the expected value for ArrayStack.", "poop1", aq.dequeue());
        assertEquals("Dequeue did not return the expected value for ArrayStack.", "poop2", aq.dequeue());
        assertTrue("ArrayStack not empty after Dequeuing all elements.", aq.isEmpty());

        try {
            aq.dequeue();
            assertTrue("Dequeuing off of an empty ArrayStack did not throw a NoSuchElementException.", false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }

        try {
            aq.enqueue(null);
            assertTrue("Enqueuing 'null' to an ArrayStack did not throw a IllegalArgumentException.", false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        int initialCapacity = aq.getBackingArray().length;
        int newCapacity = (int) (initialCapacity * 1.5);
        String[] expectedArray = new String[newCapacity];

        for (int i = 0; i < initialCapacity; i++) {
            aq.enqueue("poop" + i);
            expectedArray[i] = "poop" + (i + 2);
        }

        expectedArray[10] = "poop12";

        assertEquals("Size did not update correctly after enqueueing to ArrayStack.", initialCapacity, aq.size());
        assertEquals("Dequeue did not return the expected value for ArrayStack.", "poop0", aq.dequeue());
        assertEquals("Dequeue did not return the expected value for ArrayStack.", "poop1", aq.dequeue());
        assertEquals("Size did not update correctly after dequeueing to ArrayStack.", initialCapacity - 2, aq.size());

        aq.enqueue("poop10");
        assertEquals("Size did not update correctly after enqueueing to ArrayStack.", initialCapacity - 1, aq.size());
        assertEquals("Your backing array resized instead of wrapping around.", initialCapacity, aq.getBackingArray().length);

        aq.enqueue("poop11");
        aq.enqueue("poop12");

        assertArrayEquals("Your array is not in order after resizing.", expectedArray, aq.getBackingArray());
        assertEquals("ArrayStack size did not update correctly after resizing.", initialCapacity + 1, aq.size());

        aq.enqueue("poop13");
        aq.enqueue("poop14");

        assertEquals("ArrayStack size did not update correctly after enqueueing after resizing.", initialCapacity + 3, aq.size());

        aq.enqueue("poop15");
        aq.enqueue("poop16");

        assertEquals("ArrayStack size did not update correctly after enqueueing after resizing.", initialCapacity + 5, aq.size());
        assertEquals("Your backing array in ArrayQueue resized unnecessarily.", newCapacity, aq.getBackingArray().length);

        assertEquals("You did not dequeue correctely after resizing.", "poop2", aq.dequeue());
    }

    private <T> void assertLinkedQueueEquals(LinkedQueue<T> queue, T[] expecteds) {
        LinkedNode<T> current = queue.getHead();
        int i = 0;

        while (current != null) {
            assertEquals("Data in linked queue didn't match up", expecteds[i], current.getData());
            i++;

            if (current.getNext() == null) {
                assertEquals("Final element in linked queue should be the tail", current, queue.getTail());
            }

            current = current.getNext();
        }

        if (i == 0) {
            assertNull("Tail in linked queue should be the null when size is zero", queue.getTail());
        }

        assertEquals("LinkedQueue size is out of sync", i, queue.size());
        assertEquals("LinkedQueue isEmpty() does not match size", queue.size() == 0, queue.isEmpty());
    }

    private <T> void assertLinkedStackEquals(LinkedStack<T> stack, T[] expecteds) {
        LinkedNode<T> current = stack.getHead();
        int i = 0;

        while (current != null) {
            assertEquals("Data in linked queue didn't match up", expecteds[i], current.getData());
            i++;

            current = current.getNext();
        }

        assertEquals("LinkedStack size is out of sync", i, stack.size());
        assertEquals("LinkedStack isEmpty() does not match size", stack.size() == 0, stack.isEmpty());
    }

    private <T> int countNotNulls(T[] arr) {
        int sum = 0;
        for (T obj: arr) {
            if (obj != null) {
                sum++;
            }
        }

        return sum;
    }

    private <T> void assertArrayQueueEquals(ArrayQueue<T> queue, T[] expecteds) {
        assertEquals("ArrayQueue size is out of sync", countNotNulls(queue.getBackingArray()), queue.size());
        assertEquals("ArrayQueue isEmpty() does not match size", queue.size() == 0, queue.isEmpty());

        assertArrayEquals(expecteds, queue.getBackingArray());
    }

    private <T> void assertArrayStackEquals(ArrayStack<T> stack, T[] expecteds) {
        assertEquals("ArrayStack size is out of sync", countNotNulls(stack.getBackingArray()), stack.size());
        assertEquals("ArrayStack isEmpty() does not match size", stack.size() == 0, stack.isEmpty());

        assertArrayEquals(expecteds, stack.getBackingArray());
    }

    private void assertException(String message, Class<? extends Exception> exceptionClass, Runnable code) {
        try {
            code.run();
            org.junit.Assert.fail(message);
        } catch (Exception e) {
            assertEquals(message, exceptionClass, e.getClass());
            assertNotNull("Exception messages must not be empty", e.getMessage());
            assertNotEquals("Exception messages must not be empty", "", e.getMessage());
        }
    }

    @Test(timeout = TIMEOUT)
    public void test_00_constructor() {
        assertNull("Initial state of linked queue head should be null", linkedQueue.getHead());
        assertNull("Initial state of linked queue tail should be null", linkedQueue.getTail());
        assertNull("Initial state of linked stack head should be null", linkedStack.getHead());

        assertEquals("Queue backing array should be of initial size " + QueueInterface.INITIAL_CAPACITY, QueueInterface.INITIAL_CAPACITY, arrayQueue.getBackingArray().length);
        assertEquals("Stack backing array should be of initial size " + StackInterface.INITIAL_CAPACITY, StackInterface.INITIAL_CAPACITY, arrayStack.getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void test_01a_linked_queue_enqueue() {
        assertException(
                "Enqueueing a null element should throw an IllegalArgumentException",
                IllegalArgumentException.class,
                () -> linkedQueue.enqueue(null));

        linkedQueue.enqueue("01a-a");
        assertEquals(linkedQueue.getHead().getData(), "01a-a");

        linkedQueue.enqueue("01a-b");
        assertEquals(linkedQueue.getTail().getData(), "01a-b");
        assertEquals(linkedQueue.size(), 2);
        linkedQueue.enqueue("01a-c");
        assertEquals(linkedQueue.getTail().getData(), "01a-c");
        assertEquals(linkedQueue.size(), 3);
        assertLinkedQueueEquals(linkedQueue, new String[]{"01a-a", "01a-b", "01a-c"});
    }

    @Test(timeout = TIMEOUT)
    public void test_01b_array_queue_enqueue() {
        assertException(
                "Enqueueing a null element should throw an IllegalArgumentException",
                IllegalArgumentException.class,
                () -> arrayQueue.enqueue(null));

        arrayQueue.enqueue("01b-a");
        assertArrayQueueEquals(arrayQueue, new String[]{"01b-a", null, null, null, null, null, null, null, null, null});

        arrayQueue.enqueue("01b-b");
        arrayQueue.enqueue("01b-c");
        assertArrayQueueEquals(arrayQueue, new String[]{"01b-a", "01b-b", "01b-c", null, null, null, null, null, null, null});

        arrayQueue.enqueue("01b-d");
        arrayQueue.enqueue("01b-e");
        arrayQueue.enqueue("01b-f");
        arrayQueue.enqueue("01b-g");
        arrayQueue.enqueue("01b-h");
        arrayQueue.enqueue("01b-i");
        arrayQueue.enqueue("01b-j");
        arrayQueue.enqueue("01b-k");

        assertArrayQueueEquals(arrayQueue, new String[]{"01b-a", "01b-b", "01b-c", "01b-d", "01b-e", "01b-f", "01b-g", "01b-h", "01b-i", "01b-j", "01b-k", null, null, null, null});

        for (int i = 0; i < 5; i++) {
            arrayQueue.enqueue("filler");
        }

        // 10 * 1.5 * 1.5 is 22.5; make sure the implementation rounds down.
        assertEquals("Backing array resize should round down", 22, arrayQueue.getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void test_02a_linked_queue_dequeue() {
        // depends on enqueue
        assertException(
                "Dequeueing from an empty queue should throw a java.util.NoSuchElementException",
                NoSuchElementException.class,
                () -> linkedQueue.dequeue());

        linkedQueue.enqueue("02a-a");
        assertEquals("Dequeue element was incorrect", "02a-a", linkedQueue.dequeue());
        assertLinkedQueueEquals(linkedQueue, new String[]{});

        assertException(
                "Dequeueing from an empty queue should throw a java.util.NoSuchElementException",
                NoSuchElementException.class,
                () -> linkedQueue.dequeue());

        linkedQueue.enqueue("02a-b");
        linkedQueue.enqueue("02a-c");
        assertEquals("Dequeue element was incorrect", "02a-b", linkedQueue.dequeue());
        assertLinkedQueueEquals(linkedQueue, new String[]{"02a-c"});
    }

    @Test(timeout = TIMEOUT)
    public void test_02b_array_queue_dequeue() {
        // depends on enqueue
        assertException(
                "Dequeueing from an empty queue should throw a java.util.NoSuchElementException",
                NoSuchElementException.class,
                () -> arrayQueue.dequeue());

        arrayQueue.enqueue("02b-a");
        assertEquals("Dequeue element was incorrect", "02b-a", arrayQueue.dequeue());
        assertArrayQueueEquals(arrayQueue, new String[]{null, null, null, null, null, null, null, null, null, null});

        assertException(
                "Dequeueing from an empty queue should throw a java.util.NoSuchElementException",
                NoSuchElementException.class,
                () -> arrayQueue.dequeue());

        arrayQueue.enqueue("02b-b");
        arrayQueue.enqueue("02b-c");
        assertArrayQueueEquals(arrayQueue, new String[]{null, "02b-b", "02b-c", null, null, null, null, null, null, null});

        assertEquals("Dequeue element was incorrect", "02b-b", arrayQueue.dequeue());
        assertArrayQueueEquals(arrayQueue, new String[]{null, null, "02b-c", null, null, null, null, null, null, null});

        arrayQueue.enqueue("02b-d");
        arrayQueue.enqueue("02b-e");
        arrayQueue.enqueue("02b-f");
        arrayQueue.enqueue("02b-g");
        arrayQueue.enqueue("02b-h");
        arrayQueue.enqueue("02b-i");
        arrayQueue.enqueue("02b-j");
        arrayQueue.enqueue("02b-k");

        assertArrayQueueEquals(arrayQueue, new String[]{"02b-k", null, "02b-c", "02b-d", "02b-e", "02b-f", "02b-g", "02b-h", "02b-i", "02b-j"});

        arrayQueue.enqueue("02b-l");
        arrayQueue.enqueue("02b-m");

        System.out.println(arrayQueue.getBackingArray().length);
        System.out.println(arrayQueue.getBackingArray()[0]);
        System.out.println(arrayQueue.getBackingArray()[1]);
        System.out.println(arrayQueue.getBackingArray()[2]);
        System.out.println(arrayQueue.getBackingArray()[3]);
        System.out.println(arrayQueue.getBackingArray()[4]);
        System.out.println(arrayQueue.getBackingArray()[5]);
        System.out.println(arrayQueue.getBackingArray()[6]);
        System.out.println(arrayQueue.getBackingArray()[7]);
        System.out.println(arrayQueue.getBackingArray()[8]);
        System.out.println(arrayQueue.getBackingArray()[9]);
        System.out.println(arrayQueue.getBackingArray()[10]);
        assertArrayQueueEquals(arrayQueue, new String[]{"02b-c", "02b-d", "02b-e", "02b-f", "02b-g", "02b-h", "02b-i", "02b-j", "02b-k", "02b-l", "02b-m", null, null, null, null});

        assertEquals("Dequeue element was incorrect", "02b-c", arrayQueue.dequeue());
        assertEquals("Dequeue element was incorrect", "02b-d", arrayQueue.dequeue());

        assertArrayQueueEquals(arrayQueue, new String[]{null, null, "02b-e", "02b-f", "02b-g", "02b-h", "02b-i", "02b-j", "02b-k", "02b-l", "02b-m", null, null, null, null});
    }

    @Test(timeout = TIMEOUT)
    public void test_02c_array_queue_front_wraparound() {
        // depends on enqueue, dequeue

        arrayQueue.enqueue("02c-a");
        arrayQueue.dequeue();

        arrayQueue.enqueue("02c-b");
        arrayQueue.enqueue("02c-c");
        assertArrayQueueEquals(arrayQueue, new String[]{null, "02c-b", "02c-c", null, null, null, null, null, null, null});

        assertEquals(arrayQueue.dequeue(), "02c-b");
        assertArrayQueueEquals(arrayQueue, new String[]{null, null, "02c-c", null, null, null, null, null, null, null});

        arrayQueue.enqueue("02c-d");

        assertEquals("Dequeue element was incorrect", "02c-c", arrayQueue.dequeue());

        arrayQueue.enqueue("02c-e");
        arrayQueue.enqueue("02c-f");
        arrayQueue.enqueue("02c-g");
        arrayQueue.enqueue("02c-h");
        arrayQueue.enqueue("02c-i");
        arrayQueue.enqueue("02c-j");
        arrayQueue.enqueue("02c-k");
        arrayQueue.enqueue("02c-l");
        arrayQueue.enqueue("02c-m");

        assertArrayQueueEquals(arrayQueue, new String[]{"02c-k", "02c-l", "02c-m", "02c-d", "02c-e", "02c-f", "02c-g", "02c-h", "02c-i", "02c-j"});

        for (int i = 0; i < 9; i++) {
            arrayQueue.dequeue();
        }

        assertArrayQueueEquals(arrayQueue, new String[]{null, null, "02c-m", null, null, null, null, null, null, null});

        arrayQueue.enqueue("02c-n");

        assertArrayQueueEquals(arrayQueue, new String[]{null, null, "02c-m", "02c-n", null, null, null, null, null, null});

        assertEquals("Dequeue element was incorrect", "02c-m", arrayQueue.dequeue());
        assertEquals("Dequeue element was incorrect", "02c-n", arrayQueue.dequeue());

        assertArrayQueueEquals(arrayQueue, new String[]{null, null, null, null, null, null, null, null, null, null});
    }

    @Test(timeout = TIMEOUT)
    public void test_03a_linked_stack_push() {
        assertException(
                "Pushing a null element should throw an IllegalArgumentException",
                IllegalArgumentException.class,
                () -> linkedStack.push(null));

        linkedStack.push("03a-a");
        assertLinkedStackEquals(linkedStack, new String[]{"03a-a"});

        linkedStack.push("03a-b");
        linkedStack.push("03a-c");
        assertLinkedStackEquals(linkedStack, new String[]{"03a-c", "03a-b", "03a-a"});
    }

    @Test(timeout = TIMEOUT)
    public void test_03b_array_stack_push() {
        assertException(
                "Pushing a null element should throw an IllegalArgumentException",
                IllegalArgumentException.class,
                () -> arrayStack.push(null));

        arrayStack.push("03b-a");
        assertArrayStackEquals(arrayStack, new String[]{"03b-a", null, null, null, null, null, null, null, null, null});

        arrayStack.push("03b-b");
        arrayStack.push("03b-c");
        assertArrayStackEquals(arrayStack, new String[]{"03b-a", "03b-b", "03b-c", null, null, null, null, null, null, null});

        arrayStack.push("03b-d");
        arrayStack.push("03b-e");
        arrayStack.push("03b-f");
        arrayStack.push("03b-g");
        arrayStack.push("03b-h");
        arrayStack.push("03b-i");
        arrayStack.push("03b-j");
        arrayStack.push("03b-k");

        assertArrayStackEquals(arrayStack, new String[]{"03b-a", "03b-b", "03b-c", "03b-d", "03b-e", "03b-f", "03b-g", "03b-h", "03b-i", "03b-j", "03b-k", null, null, null, null});

        for (int i = 0; i < 5; i++) {
            arrayStack.push("filler");
        }

        // 10 * 1.5 * 1.5 is 22.5; make sure the implementation rounds down.
        assertEquals("Backing array resize should round down", 22, arrayStack.getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void test_04a_linked_stack_pop() {
        // depends on push
        assertException(
                "Popping from an empty stack should throw a java.util.NoSuchElementException",
                NoSuchElementException.class,
                () -> linkedStack.pop());

        linkedStack.push("04a-a");
        assertEquals("Pop element was incorrect", "04a-a", linkedStack.pop());
        assertLinkedStackEquals(linkedStack, new String[]{});

        assertException(
                "Popping from an empty stack should throw a java.util.NoSuchElementException",
                NoSuchElementException.class,
                () -> linkedStack.pop());

        linkedStack.push("04a-b");
        linkedStack.push("04a-c");
        assertEquals("Pop element was incorrect", "04a-c", linkedStack.pop());
        assertLinkedStackEquals(linkedStack, new String[]{"04a-b"});
    }

    @Test(timeout = TIMEOUT)
    public void test_04b_array_stack_pop() {
        // depends on push
        assertException(
                "Popping from an empty stack should throw a java.util.NoSuchElementException",
                NoSuchElementException.class,
                () -> arrayStack.pop());

        arrayStack.push("04b-a");
        assertEquals("Pop element was incorrect", "04b-a", arrayStack.pop());
        assertArrayStackEquals(arrayStack, new String[]{null, null, null, null, null, null, null, null, null, null});

        assertException(
                "Popping from an empty stack should throw a java.util.NoSuchElementException",
                NoSuchElementException.class,
                () -> arrayStack.pop());

        arrayStack.push("04b-b");
        arrayStack.push("04b-c");
        assertArrayStackEquals(arrayStack, new String[]{"04b-b", "04b-c", null, null, null, null, null, null, null, null});

        assertEquals("Pop element was incorrect", "04b-c", arrayStack.pop());
        assertArrayStackEquals(arrayStack, new String[]{"04b-b", null, null, null, null, null, null, null, null, null});

        arrayStack.push("04b-d");
        arrayStack.push("04b-e");
        arrayStack.push("04b-f");
        arrayStack.push("04b-g");
        arrayStack.push("04b-h");
        arrayStack.push("04b-i");
        arrayStack.push("04b-j");
        arrayStack.push("04b-k");

        assertArrayStackEquals(arrayStack, new String[]{"04b-b", "04b-d", "04b-e", "04b-f", "04b-g", "04b-h", "04b-i", "04b-j", "04b-k", null});

        arrayStack.push("04b-l");
        arrayStack.push("04b-m");

        assertArrayStackEquals(arrayStack, new String[]{"04b-b", "04b-d", "04b-e", "04b-f", "04b-g", "04b-h", "04b-i", "04b-j", "04b-k", "04b-l", "04b-m", null, null, null, null});

        assertEquals("Pop element was incorrect", "04b-m", arrayStack.pop());

        assertArrayStackEquals(arrayStack, new String[]{"04b-b", "04b-d", "04b-e", "04b-f", "04b-g", "04b-h", "04b-i", "04b-j", "04b-k", "04b-l", null, null, null, null, null});
    }
}
