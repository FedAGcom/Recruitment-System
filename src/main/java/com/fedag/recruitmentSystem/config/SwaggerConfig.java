package com.fedag.recruitmentSystem.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Recruitment System",
                version = "1.0",
                license = @License(
                        name = "Все права защищены компанией FedAG"
                ),
                //TODO измените контактные данные name = "Ссылка на проект в JIRA" url = "ссылка на страницу confluence команды"
                contact = @Contact(
                        name = "https://proektorium.atlassian.net/jira/software/projects/RS/boards/5",
                        url = "",
                        email = "fedagedu@gmail.com"
                )
        )
)
@Configuration
public class SwaggerConfig {


}
