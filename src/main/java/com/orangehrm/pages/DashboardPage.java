package com.orangehrm.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.*;
import java.util.HashMap;
import java.util.Map;

public class DashboardPage {
    private final Page page;
    private static final Logger logger = LoggerFactory.getLogger(DashboardPage.class);

    private final String userDropdown = "span.oxd-userdropdown-tab";
    private final String closeMenu = "//button[@class='oxd-icon-button oxd-main-menu-button']";
    private final String searchBar = "//input[@placeholder='Search']";
    private final String optAbout = "//a[text()='About']";
    private final String optSupport = "a[href='/web/index.php/help/support']";
    private final String optChangePassword = "a[href='/web/index.php/pim/updatePassword']";
    private final String optLogout = "a[href='/web/index.php/auth/logout']";

    private final Map<String, String> tabSelectors = new HashMap<String, String>() {{
        put("Admin", "a[href='/web/index.php/admin/viewAdminModule']");
        put("PIM", "a[href='/web/index.php/pim/viewPimModule']");
        put("Leave", "a[href='/web/index.php/leave/viewLeaveModule']");
        put("Time", "a[href='/web/index.php/time/viewTimeModule']");
        put("Recruitment", "a[href='/web/index.php/recruitment/viewRecruitmentModule']");
        put("My Info", "a[href='/web/index.php/pim/viewMyDetails']");
        put("Performance", "a[href='/web/index.php/performance/viewPerformanceModule']");
        put("Dashboard", "a[href='/web/index.php/dashboard/index']");
        put("Directory", "a[href='/web/index.php/directory/viewDirectory']");
        put("Maintenance", "a[href='/web/index.php/maintenance/viewMaintenanceModule']");
        put("Claim", "a[href='/web/index.php/claim/viewClaimModule']");
        put("Buzz", "a[href='/web/index.php/buzz/viewBuzz']");
    }};


    public DashboardPage(Page page) {
        this.page = page;
    }

    public void loginVerification(){

        try {
            page.waitForSelector(userDropdown, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        } catch (Exception e) {
            System.out.println("Login failed. User dropdown is not visible.");
            throw new RuntimeException("Login verification failed: User dropdown not found.", e);
        }

    }

    public void navigateToTab(String tabName) {
        String tabSelector = tabSelectors.get(tabName);
        if (tabSelector != null) {
            try {
                page.waitForSelector(tabSelector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
                page.click(tabSelector);
                logger.info("Navigated to tab: {}", tabName);
            } catch (Exception e) {
                logger.error("Failed to navigate to {}", tabName, e);
                throw new RuntimeException("Tab navigation failed for: " + tabName, e);
            }
        } else {
            logger.error("Tab not found: {}", tabName);
            throw new IllegalArgumentException("Tab name not recognized: " + tabName);
        }
    }

    public void logout(){

        page.click(userDropdown);
        page.click(optLogout);

    }

}
