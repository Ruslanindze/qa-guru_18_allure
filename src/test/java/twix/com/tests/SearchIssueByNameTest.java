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

    private static final String REPOSITORY_NAME = "Ruslanindze/qa-guru_18_allure";
    private static final String ISSUE_NAME = "Learning allure-report";


    @Test
    public void testWithCleanSelenideAndListener() {
        // Добавлено логгирование.
        SelenideLogger.addListener("allure", new AllureSelenide());

        // Открываем главную страницу (указана в TestBase)
        open(BASE_URL);

        // Ищем репозиторий.
        $("input.header-search-input").click();
        $("input.header-search-input").sendKeys(REPOSITORY_NAME);
        $("input.header-search-input").pressEnter();

        // Переходим в найденый репозиторий.
        $("ul.repo-list").$(linkText(REPOSITORY_NAME)).click();

        // Переходим в раздел Issues
        $("a#issues-tab").click();

        // Ищем нашу задачу с именем.
        $(withTagAndText("a", ISSUE_NAME)).shouldBe(exist);
    }

    @Test
    public void testWithLambdaSteps() {
        step("Открываем главную страницу", () -> {
            open(BASE_URL);
        });

        step("Ищем репозиторий " + REPOSITORY_NAME, () -> {
            $("input.header-search-input").click();
            $("input.header-search-input").sendKeys(REPOSITORY_NAME);
            $("input.header-search-input").pressEnter();
        });

        Selenide.sleep(5000);
        step("Переходим в репозиторий " + REPOSITORY_NAME, () -> {
            $("ul.repo-list").$(linkText(REPOSITORY_NAME)).click();
        });

        step("Переходим в раздел Issues", () -> {
            $("a#issues-tab").click();
        });

        step("Ищем задачу с именем " + ISSUE_NAME, () -> {
            $(withTagAndText("a", ISSUE_NAME)).shouldBe(exist);
        });
    }

    @Test
    public void testWithAnnotatedSteps() {
        IssuesPage issuesPage = new IssuesPage();

        new IssuesPage().openBasePage(BASE_URL)
                .searchRepo(REPOSITORY_NAME)
                .clickByFoundRepo(REPOSITORY_NAME)
                .clickByChapterIssue()
                .checkIssueByName(ISSUE_NAME);
    }
}