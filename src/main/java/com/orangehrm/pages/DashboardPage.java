package com.orangehrm.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class DashboardPage {
    private final Page page;

    private final String userDropdown = "span.oxd-userdropdown-tab";
    private final String dashboardHeader = "h6.oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module";

    // Left Menu Elements & XPaths
    private final String closeMenu = "//button[@class='oxd-icon-button oxd-main-menu-button']";
    private final String searchBar = "//input[@placeholder='Search']";
    private final String adminTab = "a[href='/web/index.php/admin/viewAdminModule']";
    private final String pimTab = "a[href='/web/index.php/pim/viewPimModule']";
    private final String leaveTab = "a[href='/web/index.php/leave/viewLeaveModule']";
    private final String timeTab = "a[href='/web/index.php/time/viewTimeModule']";
    private final String recruitmentTab = "a[href='/web/index.php/recruitment/viewRecruitmentModule']";
    private final String myinfoTab = "a[href='/web/index.php/pim/viewMyDetails']";
    private final String performenceTab = "a[href='/web/index.php/performance/viewPerformanceModule']";
    private final String dashboardTab = "a[href='/web/index.php/dashboard/index']";
    private final String directoryTab = "a[href='/web/index.php/directory/viewDirectory']";
    private final String maintenanceTab = "a[href='/web/index.php/maintenance/viewMaintenanceModule']";
    private final String claimTab = "a[href='/web/index.php/claim/viewClaimModule']";
    private final String buzzTab = "a[href='/web/index.php/buzz/viewBuzz']";


    public DashboardPage(Page page) {
        this.page = page;
    }

    public void loginVerification(){

        try {
            page.waitForSelector(userDropdown, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
            System.out.println("Login was successful. User dropdown is visible.");
        } catch (Exception e) {
            System.out.println("Login failed. User dropdown is not visible.");
            throw new RuntimeException("Login verification failed: User dropdown not found.", e);
        }

    }

}
