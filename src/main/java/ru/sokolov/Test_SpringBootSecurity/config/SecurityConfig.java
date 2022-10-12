package ru.sokolov.Test_SpringBootSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.sokolov.Test_SpringBootSecurity.services.PersonDetailsService;
import ru.sokolov.Test_SpringBootSecurity.services.PersonService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonService personService1, PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }


    //настройка авторизации, страницы логина
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/auth/login","/error", "/auth/registration").permitAll()   // разрешенные странички всем
                .anyRequest().authenticated()   // все остальные доступны только прошедшим авторизацию
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login") // метод который приходит из POST формы с вводом логина и пароля
                .defaultSuccessUrl("/", true)
                .failureUrl("/auth/login?error")// при неправильном логине или пароле отправит на страницу логина с параметром error
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login");
    }

    //Настраивает аунтификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
