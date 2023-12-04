package com.adobe.aem.assessment.singtel.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MegamenuModelTest {

    @Mock
    private SlingHttpServletRequest request;

    @Mock
    Resource resource;

    @Mock
    private PageManager pageManager;

    @InjectMocks
    private MegamenuModel megamenuModel;

    @Mock
    Page currentPage;
    @Mock
    MegamenuModel.NavigationItem navigationItem;

    @Mock
    List<MegamenuModel.NavigationItem> expectedItems;

    String navigationRoot = "/content/assessment/us/en";

    private List<MegamenuModel.NavigationItem> items;

    @Mock
    List<MegamenuModel.NavigationItem> buildNavigation;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testBuildNavigation() {

        Page mockPage = mock(Page.class);

        Iterator<Page> mockSiblings = mock(Iterator.class);
        when(mockSiblings.hasNext()).thenReturn(true,false);

        Page siblingPage = mock(Page.class);
        when(mockSiblings.next()).thenReturn(siblingPage);// No children for simplicity

        when(mockPage.listChildren()).thenReturn(mockSiblings);

        // Test the buildNavigation method
        List<MegamenuModel.NavigationItem> navigationItems = megamenuModel.buildNavigation(mockPage);

    }
}

