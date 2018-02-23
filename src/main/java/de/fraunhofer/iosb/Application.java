package de.fraunhofer.iosb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * This is the main class in which server starts.
 *
 * @author Viseslav Sako
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }


}