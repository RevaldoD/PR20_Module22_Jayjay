package com;

import io.cucumber.java.After;
import io.cucumber.java.Before;


public class Hooks extends Driver {

    @Before
    public void beforeSetup() {
        super.getDriver();
    }

    @After
    public void afterSetup() {
        driver.quit();
    }

}
