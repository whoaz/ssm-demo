package org.helloz.demo;

import org.junit.Test;

import java.util.Random;
import java.util.UUID;

/**
 * Created by zhouk on 2017/7/15.
 */
public class SimpleTest {

    @Test
    public void test1() {
        String[] sex = {"F", "M"};
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            String s = sex[random.nextInt(2)];
            System.out.println(s);

        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String email = uuid.substring(0, 6);
        System.out.println(email);

    }
}
