package ee461lgroup10.productivityapplication;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {
    @Test
    public void getName() throws Exception {
        Task test1 = new Task(0, "Task 1", "04/26/17");
        assertEquals("Task 1", test1.getName());
    }

    @Test
    public void getDate() throws Exception {
        Task test2 = new Task(0, "Task 2", "04/26/17");
        assertEquals("04/26/17", test2.getDate());
    }

    @Test
    public void getID() throws Exception {
        Task test3 = new Task(1, "Testing", "04/26/17");
        assertEquals(1, test3.getId());
    }

}