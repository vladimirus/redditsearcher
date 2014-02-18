package com.redditsearcher.web.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
    private HomeController controller;
    @Mock
    private ModelMap model;

    @Before
    public void before() {
        this.controller = new HomeController();
    }

    @Test
    public void home() {

        // when
        String actualView = controller.home(model);

        // then
        assertEquals("home", actualView);
    }
}
