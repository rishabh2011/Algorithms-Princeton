import static org.junit.Assert.*;
import org.junit.Test;

public class TestPercolation {

    @Test
    public void testIsOpen(){
        Percolation p = new Percolation(3);
        p.open(1, 1);
        p.open(3, 1);
        assertTrue(p.isOpen(1, 1));
        assertTrue(p.isOpen(3, 1));
        assertFalse(p.isOpen(1, 2));
        assertEquals(2, p.numberOfOpenSites());

        p.open(2, 1);
        p.open(1, 1);
        p.open(3, 1);
        assertEquals(3, p.numberOfOpenSites());
    }

    @Test
    public void testIsFull(){
        Percolation p = new Percolation(3);
        p.open(1, 1);
        p.open(3, 1);
        p.open(2, 1);
        p.open(3, 3);
        assertTrue(p.isFull(1, 1));
        assertFalse(p.isFull(1, 2));
        assertTrue(p.isFull(2, 1));
        assertTrue(p.isFull(3, 1));
        assertFalse(p.isFull(3, 3));
        assertEquals(4, p.numberOfOpenSites());

        p.open(1, 3);
        assertTrue(p.isFull(1, 3));
    }

    @Test
    public void testPercolates(){
        Percolation p = new Percolation(3);
        p.open(1, 1);
        p.open(3, 1);
        p.open(2, 1);
        assertTrue(p.percolates());
        assertEquals(3, p.numberOfOpenSites());
    }


}
