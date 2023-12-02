/*
 * Model class in order to fetch the details of each
 * the pages which has to be displayed on the Header of the page.
 * Author can author the root path, from where the pages need to be fetched.
 */
package com.adobe.aem.assessment.singtel.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.day.cq.commons.inherit.InheritanceValueMap;
@Model(adaptables = { SlingHttpServletRequest.class,
        Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MegamenuModel {

    @Inject
    private static PageManager pageManager;

    public List<NavigationItem> getItems() {
        return items;
    }

    public void setItems(List<NavigationItem> items) {
        this.items = items;
    }

    private List<NavigationItem> items;
    @Inject
    InheritanceValueMap pageProperties;
    @Inject
    private String navigationRoot;
    @PostConstruct
    protected void init() {
        if (StringUtils.isNotBlank(navigationRoot)) {
            Page currentPage = pageManager.getPage(navigationRoot);
            items = buildNavigation(currentPage);
        }
    }

    //Gives us the details of each of the pages, like title, url and the siblings
    public List<NavigationItem> buildNavigation(Page currentPage) {
        List<NavigationItem> navigationItems = new ArrayList<>();
        Iterator<Page> siblings = currentPage.listChildren();
        while (siblings.hasNext()) {
            Page sibling = siblings.next();
            if (sibling.isValid()) {
                navigationItems.add(new NavigationItem(sibling));

            }
        }
        return navigationItems;
    }

    public class NavigationItem {
        private final String title;
        private final String url;
        private String hideInNav;
        private String hideAllSubPages;
        private List<NavigationItem> children;

        public NavigationItem(Page page) {
            this.title = page.getTitle();
            this.url = page.getPath() + ".html";
            Page subPage = pageManager.getPage(page.getPath());

            if(StringUtils.isNotEmpty(subPage.getProperties().get("hideAllSubpages",String.class))) {
                this.hideAllSubPages = pageProperties.getInherited("hideAllSubpages", "true");
            }
            if(StringUtils.isNotEmpty(subPage.getProperties().get("hideInNav",String.class))){
                this.hideInNav = pageProperties.getInherited("hideInNav", "true");
            }
            if (page.listChildren().hasNext()) {
                this.children = buildNavigation(page);
            }
        }

        // Getters for title, url, isActive, and children


        public String getTitle() {
            return title;
        }
        public String getUrl() {
            return url;
        }
        public List<NavigationItem> getChildren() {
            return children;
        }
        public String getHideInNav() {
            return hideInNav;
        }
        public String getHideAllSubPages() {
            return hideAllSubPages;
        }

    }
}
