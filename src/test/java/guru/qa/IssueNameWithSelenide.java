package guru.qa;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class IssueNameWithSelenide {

    @Test
    @Feature("Github Issues")
    @Story("Issue's name")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Check issue's name")
    @Owner("oschastlivtseva")
    public void checkIssueNameWithSelenide() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/allure-framework/allure2/issues");
        $("[data-ga-click='Issues, Table filter, Sort']").click();
        $(byText("Oldest")).click();
        String currentUrl = "";
        while (!currentUrl.contains("created-asc")) {
            currentUrl  = WebDriverRunner.getWebDriver().getCurrentUrl();
        }
        $("[data-ga-click='Issues, Table state, Closed']", 1).click();
        $("#issue_5_link").shouldHave(text("Demo issue"));
    }
}
