package com.redditsearcher.web.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import com.redditsearcher.biz.manager.SearchManager;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
    private HomeController controller;
    @Mock
    private ModelMap model;
    @Mock
    private SearchManager searchManager;

    @Before
    public void before() {
        this.controller = new HomeController();
        this.controller.searchManager = searchManager;
    }

    @Test
    public void home() {

        // when
        String actualView = controller.home();

        // then
        assertEquals("home", actualView);
    }

    @Test
    public void homeWithParameter() {

        // when
        String actualView = controller.home(model, "test");

        // then
        assertEquals("home", actualView);
        verify(searchManager).search("test");
    }
}
