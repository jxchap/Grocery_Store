package com.grocery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
public class GrocerySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	BCryptPasswordEncoder bCryptpeasswordEncoder;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptpeasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity hts) throws Exception {
		
		hts.authorizeRequests()
        .antMatchers("/", "/home", "/getFoodItems", "/getFoodCategories","/addNewAccount").permitAll() 
//        .antMatchers("").hasAuthority("ROLE_USER") 
       .antMatchers("/admin/*").hasAuthority("ROLE_ADMIN")
        .and().logout().logoutUrl("/logout").deleteCookies("JSESSIONID").logoutSuccessUrl("/home")
        
		.and().httpBasic()
        .and().csrf().disable()
        ; 
		
		hts.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/home", true);
	}
	
	@Override
	public void configure(WebSecurity webs) {
		webs.ignoring().antMatchers("/resources/*");
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptpeasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
