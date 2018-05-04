package com.springboot.caion.client.test;


import com.springboot.caion.client.ClientApp;
import com.springboot.caion.client.remoteController.HelloRemote;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/*
 * springBoot测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = ClientApp.class)
public class BootClientTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    HelloRemote helloRemote;

    @Test
    public void test() throws Exception{
        System.out.println(helloRemote);
//        System.out.println(helloRemote.hello("312"));
    }
}
