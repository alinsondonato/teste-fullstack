package br.com.alinson.testeunimed.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alinson.testeunimed.service.impl.UserService;

@EnableWebSecurity
public class SecutiryConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private PasswordEncoder encoder;
	
	@Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, userService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(encoder);
    }
    
    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            	.antMatchers(HttpMethod.GET, "/h2-console/**").permitAll()
	            .antMatchers("/beneficiarios/**").hasAnyRole("USER", "ADMIN")
	            .antMatchers("/planos/**").hasAnyRole("USER", "ADMIN")
            .and()
            	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        	.and()
        		.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class).cors()
    		.and().headers().frameOptions().sameOrigin();
        ;
    }
}
