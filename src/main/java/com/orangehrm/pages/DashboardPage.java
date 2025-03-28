package com.orangehrm.pages;

import com.microsoft.playwright.Page;

public class DashboardPage {
    private final Page page;
    private final String dashboardHeader = "h6.oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module";

    public DashboardPage(Page page) {
        this.page = page;
    }

    public String getHeaderText() {
        return page.textContent(dashboardHeader);
    }

    public boolean isAtDashboard() {
        return getHeaderText().equalsIgnoreCase("Dashboard");
    }
}
