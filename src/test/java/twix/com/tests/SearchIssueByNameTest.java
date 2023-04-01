package twix.com.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import twix.com.pages.IssuesPage;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;


public class SearchIssueByNameTest extends TestBase {

    private static String repositoryName = "Ruslanindze/qa-guru_18_allure";
    private static String issueName = "Learning allure-report";


    @Test
    public void testWithCleanSelenideAndListener() {
        // Добавлено логгирование.
        SelenideLogger.addListener("allure", new AllureSelenide());

        // Открываем главную страницу (указана в TestBase)
        open(BASE_URL);

        // Ищем репозиторий.
        $("input.header-search-input").click();
        $("input.header-search-input").sendKeys(repositoryName);
        $("input.header-search-input").pressEnter();

        // Переходим в найденый репозиторий.
        $("ul.repo-list").$(linkText(repositoryName)).click();

        // Переходим в раздел Issues
        $("a#issues-tab").click();

        // Ищем нашу задачу с именем.
        $(withTagAndText("a", issueName)).shouldBe(exist);
    }

    @Test
    public void testWithLambdaSteps() {
        step("Открываем главную страницу", () -> {
            open(BASE_URL);
        });

        step("Ищем репозиторий " + repositoryName, () -> {
            $("input.header-search-input").click();
            $("input.header-search-input").sendKeys(repositoryName);
            $("input.header-search-input").pressEnter();
        });

        Selenide.sleep(5000);
        step("Переходим в репозиторий " + repositoryName, () -> {
            $("ul.repo-list").$(linkText(repositoryName)).click();
        });

        step("Переходим в раздел Issues", () -> {
            $("a#issues-tab").click();
        });

        step("Ищем задачу с именем " + issueName, () -> {
            $(withTagAndText("a", issueName)).shouldBe(exist);
        });
    }

    @Test
    public void testWithAnnotatedSteps() {
        IssuesPage issuesPage = new IssuesPage();

        new IssuesPage().openBasePage(BASE_URL)
                .searchRepo(repositoryName)
                .clickByFoundRepo(repositoryName)
                .clickByChapterIssue()
                .checkIssueByName(issueName);
    }
}