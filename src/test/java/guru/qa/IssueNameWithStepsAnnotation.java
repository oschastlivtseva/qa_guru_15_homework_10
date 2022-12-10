package guru.qa;

import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.utils.WebSteps;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IssueNameWithStepsAnnotation {
    private static final String allureIssues = "https://github.com/allure-framework/allure2/issues";
    private static final String issueName = "Demo issue";

    @Test
    @Feature("Github Issues")
    @Story("Issue's name")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Check issue's name (with @Steps)")
    @Owner("oschastlivtseva")
    public void checkIssueNameWithStepsAnnotation() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openPageWithIssues(allureIssues);
        steps.sortIssuesByOldest();
        steps.waitForSorting();
        steps.filterIssuesByClosed();
        steps.checkIssueName(issueName);
    }
}
