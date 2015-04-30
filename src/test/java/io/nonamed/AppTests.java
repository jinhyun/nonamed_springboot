package io.nonamed;

import io.nonamed.dao.department.DeptRepository;
import io.nonamed.dao.organization.OrganRepository;
import io.nonamed.dao.user.UserRepository;
import io.nonamed.domain.department.Dept;
import io.nonamed.domain.organization.*;
import io.nonamed.domain.user.User;
import io.nonamed.service.organization.OrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.junit.*;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class AppTests {
    @Autowired
    DeptRepository deptRepository;
    @Autowired
    OrganRepository organRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrganService organService;

    RestTemplate template = new TestRestTemplate();
    String indexUrl = "http://localhost:8080";

    @Test
    public void testRequest() throws Exception {
        assertThat(template.getForObject(indexUrl, String.class),
                containsString("Nonamed World"));
    }

}