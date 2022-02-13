import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HomeTask2 {

    @BeforeAll
    static void beforeAll(){
        Configuration.baseUrl = "https://demoqa.com";  //общий адрес
        Configuration.startMaximized = true;  //масштабизацаия браузера на максимальный размер

    }
    @Test
    void successFillTest(){
        open("/automation-practice-form");  //конкретный адрес

        $(".main-header").shouldHave(text("Practice Form"));  //ассерт с текстовыми элементами
        $(byText("Student Registration Form"));  //ассерт заголовка

        $("#firstName").setValue("Testirovchikkk");  //Ввод фамилии
        $("#lastName").setValue("Test");  //Ввод имени
        $("#userEmail").setValue("Test@mail.ru");  //Ввод почты

        $(byText("Other")).click();  //Выбор пола
        $("#userNumber").setValue("9031812772");  //Ввод номера телефона

        //Блок "Дата рождения"
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("February");
        $(".react-datepicker__year-select").selectOption("1995");
        $$(".react-datepicker__day").find(text("27")).click();

        $("#subjectsInput").setValue("English").pressEnter();  //Предмет

        //Хобби
        $(byText("Sports")).click();
        $(byText("Reading")).click();
        $(byText("Music")).click();

        $("#uploadPicture").scrollTo().uploadFromClasspath("img/1.jpeg");  //Загрузка файла

        $("#currentAddress").setValue("Some address");  //Адрес

        //Выбор штата и города
        $("#state").click();
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Panipat")).click();

        //Отправка
        $("#submit").scrollTo().click();

        //Ассерт
        $(".table-responsive").shouldHave(
                text("Testirovchikkk Test"),
                text("test@mail.ru"),
                text("Other"),
                text("9031812772"),
                text("27 February,1995"),
                text("English"),
                text("Sports, Reading, Music"),
                text("1.jpeg"),
                text("Some address"),
                text("Haryana Panipat")
        );
        //Закрыть модальное окно
        $("#closeLargeModal").click();
    }
}
