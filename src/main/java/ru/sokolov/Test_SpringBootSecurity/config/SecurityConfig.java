package ru.sokolov.Test_SpringBootSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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


    //настройка авторизации, страницы логина
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()   //  отключение защиты от межсайтовой подделки запросов
                .authorizeRequests()
                .antMatchers("/auth/login","/error").permitAll()   // разрешенные странички всем
                .anyRequest().authenticated()   // все остальные доступны только прошедшим авторизацию
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login") // метод который приходит из POST формы с вводом логина и пароля
                .defaultSuccessUrl("/", true)
                .failureUrl("/auth/login?error");// при неправильном логине или пароле отправит на страницу логина с параметром error
    }

    //Настраивает аунтификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


}
