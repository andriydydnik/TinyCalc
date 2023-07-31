package org.example;

public class WeatherCalc {

    private WeatherProvider provider;

    public WeatherCalc(final WeatherProvider provider) {
        this.provider = provider;
    }

    public boolean checkProviderIsAvailable() {
        return
                provider.getLastActualData() != null;
    }
}
