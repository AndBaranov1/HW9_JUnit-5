package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;

public class SimpleWebTest {

    @BeforeEach
    void setup() {
         Selenide.open("https://alfabank.ru/");
    }

    @ValueSource(strings = {
            "Кредит", "Инвестиции"
    })
    @ParameterizedTest(name = "При поиске на сайте альфа-банка {0} отобразится текст {0}")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("WEB")
    })
    void menuCreditAndMortgageMoney(String testData) {
        $(".aPwyr > svg").click();
        $(".c3pCr").setValue(testData).pressEnter();
        $(".b-serp-item:nth-child(1) .b-serp-item__title yass-span").shouldHave(Condition.text(testData));
    }


    @CsvSource(value  = {
            "Кредит, Кредит наличными от 4%: рассчитать и оформить онлайн",
            "Инвестиции, Приложение Альфа-Инвестиции: скачать на iOS, Android"
    })
    @ParameterizedTest(name = "При поиске на сайте альфа-банка {0} отобразится текст {1}")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("WEB")
    })
    void menuCreditMoneyCsvSource(String testData, String expectedResult) {
        $(".aPwyr > svg").click();
        $(".c3pCr").setValue(testData).pressEnter();
        $(".b-serp-item:nth-child(1) .b-serp-item__title yass-span").shouldHave(Condition.text(expectedResult));
    }


    static Stream<String> argumentsStream() {
        return Stream.of("Кредит", "Инвестиции");
    }

    @MethodSource("argumentsStream")
    @ParameterizedTest(name = "При поиске на сайте альфа-банка {0} отобразится текст {1}")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("WEB")
    })
    void menuCreditAndMortgageMoneyMethodSource(String testData) {
        $(".aPwyr > svg").click();
        $(".c3pCr").setValue(testData).pressEnter();
        $(".b-serp-item:nth-child(1) .b-serp-item__title yass-span").shouldHave(Condition.text(testData));
    }
}