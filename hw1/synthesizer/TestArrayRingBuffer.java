package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(2);
        assertTrue(arb.isEmpty());
        arb.enqueue(10);
        arb.enqueue(5);
        assertTrue(arb.isFull());
        assertEquals(arb.dequeue(), 10);
        assertEquals(arb.dequeue(), 5);
        assertTrue(arb.isEmpty());

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
