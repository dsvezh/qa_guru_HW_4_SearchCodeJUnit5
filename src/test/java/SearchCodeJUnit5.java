import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchCodeJUnit5 {
    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = true;
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void searchJunitFiveExample() {
//        Откройте страницу Selenide в Github
        open("https://github.com/selenide/selenide");

//        Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();

//        Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("[data-filterable-for=wiki-pages-filter]").shouldHave(text("SoftAssertions"));

//        Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $("h1.flex-auto").shouldHave(text("SoftAssertions"));
        $(".markdown-body").shouldHave(text("Using JUnit5 extend test class:"));
        $(".markdown-body").shouldHave(text(
                "@ExtendWith({SoftAssertsExtension.class})\n" +
                        "class Tests {\n" +
                        "  @Test\n" +
                        "  void test() {\n" +
                        "    Configuration.assertionMode = SOFT;\n" +
                        "    open(\"page.html\");\n" +
                        "\n" +
                        "    $(\"#first\").should(visible).click();\n" +
                        "    $(\"#second\").should(visible).click();\n" +
                        "  }\n" +
                        "}"
        ));
        $(".markdown-body").shouldHave(text("Or register extension inside test class:"));
        $(".markdown-body").shouldHave(text(
                "class Tests {\n" +
                        "  @RegisterExtension \n" +
                        "  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();\n" +
                        "\n" +
                        "  @Test\n" +
                        "  void test() {\n" +
                        "    Configuration.assertionMode = SOFT;\n" +
                        "    open(\"page.html\");\n" +
                        "\n" +
                        "    $(\"#first\").should(visible).click();\n" +
                        "    $(\"#second\").should(visible).click();\n" +
                        "  }\n" +
                        "}"
        ));
    }
}