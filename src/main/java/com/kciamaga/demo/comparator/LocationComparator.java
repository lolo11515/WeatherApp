package com.kciamaga.demo.comparator;


import com.kciamaga.demo.dto.WeatherDto;
import java.util.Comparator;

public class LocationComparator implements Comparator<WeatherDto> {

    @Override
    public int compare(WeatherDto o1, WeatherDto o2) {
        double o1Value = (o1.getWindSpeed() * 3) + o1.getAverageTemperature();
        double o2Value = (o2.getWindSpeed() * 3) + o2.getAverageTemperature();

        return Double.compare(o1Value, o2Value);
    }
}
