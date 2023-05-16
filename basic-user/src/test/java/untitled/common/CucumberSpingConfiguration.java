package untitled.common;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import untitled.BasicUserApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = { BasicUserApplication.class })
public class CucumberSpingConfiguration {}
