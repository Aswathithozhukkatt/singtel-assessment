package com.adobe.aem.assessment.singtel.core.models;

import com.adobe.aem.assessment.singtel.core.models.MegamenuModel;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MegamenuModelTest {

    @Mock
    private SlingHttpServletRequest request;

    @Mock
    Resource resource;

    @Mock
    private static PageManager pageManager;

    @InjectMocks
    MegamenuModel megamenuModel = new MegamenuModel();

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

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        Whitebox.setInternalState(navigationItem, "title", "title");

    }

    @Test
    public void testInitWithValidNavigationRoot() {

        ///List<MegamenuModel.NavigationItem> expectedItems = new ArrayList<>();
        List<MegamenuModel.NavigationItem> navigationItems = new ArrayList<>();

        when(currentPage.listChildren()).thenReturn(mock(Iterator.class));
        when(currentPage.isValid()).thenReturn(true);


        megamenuModel.init();
       // assertEquals(currentPage,"currentPage");
        Mockito.lenient().when(pageManager.getPage(navigationRoot)).thenReturn(currentPage);
        Mockito.lenient().when(megamenuModel.buildNavigation(currentPage)).thenReturn(items);
       // verify(currentPage).listChildren();
        //verify(currentPage, times(2)).isValid();

        assertEquals(expectedItems, megamenuModel.getItems()); 
       // assertEquals("title", navigationItem.getTitle());

    }

    /*@Test
    public void testInitWithInvalidNavigationRoot() {
        String invalidNavigationRoot = "/content/invalidpage";

        Page invalidPage = mock(Page.class);
        when(pageManager.getPage(invalidNavigationRoot)).thenReturn(invalidPage);

        when(invalidPage.listChildren()).thenReturn(mock(Iterator.class));
        when(invalidPage.isValid()).thenReturn(false);

       // megamenuModel.setNavigationRoot(invalidNavigationRoot);
        megamenuModel.init();

        verify(invalidPage, never()).listChildren();
        verify(invalidPage).isValid();

        assertEquals(null, megamenuModel.getItems());
    }
*/
    // Add more tests to achieve 80% coverage

}
