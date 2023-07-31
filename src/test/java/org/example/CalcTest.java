package org.example;

import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalcTest {
    private final static SimpleDateFormat sdf = new SimpleDateFormat("HH:MM:SS");

    /**
     * Утілітарний меод класу для отримання строкового відображення поточного часу в логах
     *
     * @return Поточний час в строковому представленні
     */
    private static String currentTimeString() {
        Date date = new Date(System.currentTimeMillis());
        return sdf.format(date);
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before test suit has started - single time at the begin: " +
                currentTimeString());
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After test suit has done - single time at the and: " +
                currentTimeString());
    }

    @Before
    public void before() {
        System.out.println("Before each test: " +
                currentTimeString());
    }

    @After
    public void after() {
        System.out.println("After each test: " +
                currentTimeString());
    }

    @Test
    public void testSum() {
        //  Given - всі дії по ініціалізації оточення юніту, який ми окремо тестуємо тест сьюітом

        Calc calc = new Calc();

        //  When - всі дії по підготовці та беспоседеньому виклику методу, який ми окремо тестуємо цим конкреним тестом

        final int value1 = -15;
        final int value2 = 10;
        final int value3 = 100;

        final int result = calc.sum(value1, value2, value3);

        //  Then - всі очікування щодо результатів роботи методу, який ми окремо тестуємо цим конкреним тестом

        final int excpected = (value1 + value2 % 2) / (value3 % 3);
        assertEquals(excpected, result);
    }

    @Test
    public void testSumDevByZero() {
        //  Given

        Calc calc = new Calc();

        //  When & Then - ми не можемо оримати результат з методу, коли в ньому виникло виключення, тому ці дві секції
        //  зєднюються в одну для акого випадку

        final int value1 = 0;
        final int value2 = 0;
        final int value3 = 0;

        assertThrows(IllegalArgumentException.class,
                () -> calc.sum(value1, value2, value3));
    }

    //  Це тест негаивного сценарію, бо він перевіряю поведінку модуля за межами описаних в вимогах умов, зокрема, коли
    //  вхідні параметри не підпадають під умови вимог
    @Test
    public void testValuesOutOfRange() {
        //  Given

        Calc calc = new Calc();

        //  When

        final int value1 = -100;
        final int value2 = 10;
        final int value3 = 100;

        final int result = calc.sum(value1, value2, value3);

        //  Then

        final int excpected = (value1 + value2 % 2) / (value3 % 3);
        assertEquals(excpected, result);
    }

    @Test
    public void testSqrt() {
        //  Given

        Calc calc = new Calc();

        final int sqrtMockedValue = 9;

        CalcExtra ce = mock(CalcExtra.class);   //  За допомогою моків ми ізолюємо модуль, який тестуємо, від зовнішніх звязків

        //  таким чином ми фіксуємо поведінку конкретного метода
        // (який ми очікуємо буде засосований в замоканому обєкті) щоб гарантувати що отримаємо саме це значення в нашому тесті
        //
        //  Викорисання меоду мокінгу дозволяє гарантуваи що ми тестуємо ільки свій модуль - без урахування деталей його
        //  залежностей
        when(ce.sqrt(anyInt())).thenReturn(sqrtMockedValue);

        //  When

        //  звичайний виклик методу, який ми окремо тестуємо цим конкреним тестом, але в решті-решт цей меод, згідно до
        //  реалізації (calc.sqrt -> CalcExtra::sqrt), залежний від замоканого нами обєкту, тому кінцевий виклик буде
        //  затермінований нашою мок-реалізаціює
        final int result = calc.sqrt(ce, 4);

        //  Then

        final int excpected = sqrtMockedValue;
        assertEquals(excpected, result);
    }
}
