package com.customer.feedback.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CustomerSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Somehow the roles attached are not working well and hence matchers were changed something else
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/addFeedback1").hasAnyRole("USER")
				.antMatchers("/showFeedbacks1").hasAnyRole("USER", "ADMIN")
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").permitAll()
				.and().logout().permitAll();

		http.csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.inMemoryAuthentication()
				.withUser("admin").password("admin").authorities("ADMIN")
				.and()
				.withUser("demouser1").password("demouser1").authorities("USER")
				.and()
				.withUser("demouser2").password("demouser2").authorities("USER");
	}

}