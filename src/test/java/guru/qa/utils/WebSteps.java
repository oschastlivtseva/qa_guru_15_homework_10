package guru.qa.utils;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {

    @Step("Open github page with allure issues: {url}")
    public void openPageWithIssues(String url) {
        open(url);
    }

    @Step("Sort issues by oldest")
    public void sortIssuesByOldest() {
        $("[data-ga-click='Issues, Table filter, Sort']").click();
        $(byText("Oldest")).click();
    }

    @Step("Wait until issues are sorted")
    public void waitForSorting() {
        String currentUrl = "";
        while (!currentUrl.contains("created-asc")) {
            currentUrl  = WebDriverRunner.getWebDriver().getCurrentUrl();
        }
    }

    @Step("Filter issues by closed status")
    public void filterIssuesByClosed() {
        $("[data-ga-click='Issues, Table state, Closed']", 1).click();
    }

    @Step("Check that issue's name is '{name}'")
    public void checkIssueName(String name) {
        $("#issue_5_link").shouldHave(text(name));
    }
}
