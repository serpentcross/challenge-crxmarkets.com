package com.serpentcross.examples.crx.crxtest;

import com.serpentcross.examples.crx.crxtest.views.MainView;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnswerTest {

    private MainView mainView;

    int[] firstTestData  = new int[]{ 4, 3, 2, 4 };
    int[] secondTestData = new int[]{ 3, 2, 2, 8, 8, 7, 4, 1, 5 };
    int[] thirdTestData  = new int[]{ 1, 7, 3, 7, 6, 7, 3 };

    int[] fourthTestData = new int[]{ 3, 6, 4, 4, 5, 1 };

    @Before
    public void setUp() throws Exception {
        mainView = new MainView();
    }

    // This test will pass
    @Test
    public void firstTest() {
        int expect = mainView.calculateAnswer(firstTestData);
        assertTrue("Error! Must be 3", expect == 3);
    }

    // This test will pass
    @Test
    public void secondTest() {
        int expect = mainView.calculateAnswer(secondTestData);
        assertTrue("Error! Must be 7", expect == 7);
    }

    // This test will pass
    @Test
    public void thirdTest() {
        int expect = mainView.calculateAnswer(thirdTestData);
        assertTrue("Error! Must be 5", expect == 5);
    }

    // This test will fail
    @Test
    public void fourthTest() {
        int expect = mainView.calculateAnswer(fourthTestData);
        assertTrue("Error! Must be 2", expect == 3);
    }
}
