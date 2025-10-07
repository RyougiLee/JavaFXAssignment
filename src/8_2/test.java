import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class Calculator {
    private int result;

    public void clear() {
        result = 0;
    }
    public void add(int n) {
        result += n;
    }
    public void sub(int n) {
        result -= n;
    }
    public void mul(int n) {
        result *= n;
    }
    public void div(int n) {
        result /= n;
    }
    public int giveResult() {
        return result;
    }
}

class CalculatorTest {
    private static Calculator c;

    @BeforeAll
    static void setupBeforeClass() throws Exception {
        c = new Calculator();   // same object in every test
    }
    @BeforeEach
    void setUp() {
        c.clear();              // clear the calculator before every test
    }
    @Test
    void testClear() {
        System.out.println("Clear");
        c.clear();
        assertEquals(0, c.giveResult(), "Clearing was not successful");
    }
    @Test
    void testAdd() {
        System.out.println("Add");
        c.add(2);
        assertEquals(2, c.giveResult(), "Addition was not succesful");
        c.add(2);
        assertEquals(4, c.giveResult(), "Addition was not succesful");
    }
    @Test
    void testSub() {
        fail("Not yet implemented");
    }
    @Test
    void testMul() {
        fail("Not yet implemented");
    }
    @Test
    void testDiv() {
        fail("Not yet implemented");
    }
    @Test
    void testGetResult() {
        fail("Not yet implemented");
    }
}
