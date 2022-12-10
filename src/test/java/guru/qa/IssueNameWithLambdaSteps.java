package guru.qa;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class IssueNameWithLambdaSteps {
    private static final String allureIssues = "https://github.com/allure-framework/allure2/issues";
    private String currentUrl = "";
    private static final String issueName = "Demo issue";

    @Test
    @Feature("Github Issues")
    @Story("Issue's name")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Check issue's name (with lambda steps)")
    @Owner("oschastlivtseva")
    public void checkIssueNameWithLambdaSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open github page with allure issues: " + allureIssues, () -> {
            open(allureIssues);
        });
        step("Sort issues by oldest", () -> {
            $("[data-ga-click='Issues, Table filter, Sort']").click();
            $(byText("Oldest")).click();
        });
        step("Wait until issues are sorted", () -> {
            while (!currentUrl.contains("created-asc")) {
                currentUrl  = WebDriverRunner.getWebDriver().getCurrentUrl();
            }
        });
        step("Filter issues by closed status", () -> {
            $("[data-ga-click='Issues, Table state, Closed']", 1).click();
        });
        step("Check that issue's name is " + "" + issueName + "", () -> {
            $("#issue_5_link").shouldHave(text(issueName));
        });
    }
}
