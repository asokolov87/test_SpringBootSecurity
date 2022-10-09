package ru.sokolov.Test_SpringBootSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.sokolov.Test_SpringBootSecurity.services.PersonService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonService personService;

    @Autowired
    public SecurityConfig(PersonService personService) {
        this.personService = personService;
    }

    //Настраивает аунтификацию
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


}
