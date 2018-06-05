package com.springboot.myapp.test;


import com.springboot.base.context.SpringContextHolder;
import com.springboot.base.utils.StringUtils;
import com.springboot.myapp.aop.WebLogAspect;
import com.springboot.myapp.app.ServiceApp;
import com.springboot.myapp.config.MyConfig;
import com.springboot.myapp.service.SpringBootService;
import com.springboot.myapp.web.SpringBootController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/*
 * springBoot测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = ServiceApp.class)
public class BootTest {
    static {
        WebLogAspect.TEST = true;
    }

    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new SpringBootController()).build();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private SpringBootService springBootService;

    @Test
    public void webTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/base/camel2Undeline?str=myName")).andDo(mvcResult -> {
            MockHttpServletRequest request = mvcResult.getRequest();
            WebLogAspect.printRequest(request);

            MockHttpServletResponse response = mvcResult.getResponse();
            WebLogAspect.printResponse(response);
        });
    }

    @Test
    public void test() throws Exception{
        MyConfig bean = SpringContextHolder.getBean(MyConfig.class);
        System.out.println(bean);
    }

    public static void main(String[] args) {
        String str = StringUtils.abbr("abcedeee",5);
        System.out.println(str);
    }
}
