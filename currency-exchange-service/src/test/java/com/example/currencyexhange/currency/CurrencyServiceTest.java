package com.example.currencyexhange.currency;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static com.example.currencyexhange.currency.helper.CurrencyTestHelper.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class CurrencyServiceTest {


    private CurrencyService underTest;

    @Mock
    private CurrencyRepository currencyRepositoryMock;

    @Before
    public void setUp() {
        underTest = new CurrencyService(currencyRepositoryMock, SIMPLE_BENEFIT);
    }

    @Test
    public void shouldGetRateFromUpstreamService() {
        given(currencyRepositoryMock.getRate(ANY_FROM_CCY, ANY_TO_CCY)).willReturn(10.0);

        BigDecimal actualExchangePrice = underTest.exchangeCurrencies(ANY_FROM_CCY, ANY_TO_CCY, ANY_EXCHANGE_QTY);

        assertEquals(100.1, actualExchangePrice.doubleValue(), 0);
    }

    @Test
    public void shouldGetCurrencyName() {
        given(currencyRepositoryMock.getCurrencyName(ANY_TO_CCY)).willReturn(ANY_TO_CCY_NAME);

        String actualCurrencyName = underTest.getNameOfCurrency(ANY_TO_CCY);

        assertEquals(ANY_TO_CCY_NAME, actualCurrencyName);
    }

}
