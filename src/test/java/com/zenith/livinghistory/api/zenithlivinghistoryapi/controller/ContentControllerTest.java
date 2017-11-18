package com.zenith.livinghistory.api.zenithlivinghistoryapi.controller;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model.ContentModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(ContentController.class)
public class ContentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContentController contentController;

    @Test
    public void createContent() throws Exception {
        given(this.contentController.create(any(ContentModel.class))).willReturn()
    }
}
