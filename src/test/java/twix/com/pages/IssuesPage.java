package twix.com.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;


public class IssuesPage {

    private final SelenideElement
            itemInput = $("input.header-search-input"),
            listFoundRepositories = $(".repo-list"),
            itemIssues = $("a#issues-tab");

    @Step("Открываем главную страницу")
    public IssuesPage openBasePage(String url) {
        open(url);

        return this;
    }

    @Step("Ищем репозиторий {repos}")
    public IssuesPage searchRepo(String repos) {
        itemInput.click();
        itemInput.sendKeys(repos);
        itemInput.pressEnter();

        return this;
    }

    @Step("Переходим в найденный репозиторий {repos}")
    public IssuesPage clickByFoundRepo(String repos) {
        listFoundRepositories.$(linkText(repos)).click();

        return this;
    }

    @Step("Открываем раздел Issues")
    public IssuesPage clickByChapterIssue() {
        itemIssues.click();

        return this;
    }

    @Step("Проверяем наличие задания с названием {nameIssue}")
    public IssuesPage checkIssueByName(String nameIssue) {
        $(withTagAndText("a", nameIssue)).shouldBe(exist);

        return this;
    }
}
