package com.study.springstudy.core.chap03;

import com.study.springstudy.core.chap03.config.AppConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelDiTest {

    @Test
    void diTest() {
        AppConfig config = new AppConfig();

        Hotel hotel = config.hotel();
        hotel.inform();
    }

}