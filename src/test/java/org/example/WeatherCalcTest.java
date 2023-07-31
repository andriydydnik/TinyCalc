package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherCalcTest {

    @Mock
    WeatherProvider provider;

    @InjectMocks
    WeatherCalc wc;

    @Test
    public void testCheckProviderAvailadle() {
        //  Given

        when(provider.getLastActualData()).thenReturn(new Date());

        //  When

        boolean result = wc.checkProviderIsAvailable();

        //  Then

        assertTrue(result);
    }

    @Test
    public void testCheckProviderAvailadleTraditional() {
        //  Given

        WeatherProvider provider = mock(WeatherProvider.class);
        WeatherCalc wc = new WeatherCalc(provider);

        when(provider.getLastActualData()).thenReturn(new Date());

        //  When

        boolean result = wc.checkProviderIsAvailable();

        //  Then

        assertTrue(result);
    }

    @Test
    public void testCheckProviderUnavavailadleTraditional() {
        //  Given

        WeatherProvider provider = mock(WeatherProvider.class);
        WeatherCalc wc = new WeatherCalc(provider);

        when(provider.getLastActualData()).thenReturn(null);

        //  When

        boolean result = wc.checkProviderIsAvailable();

        //  Then

        assertFalse(result);
    }
}
