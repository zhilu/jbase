package shi.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/login").permitAll()//1��·����/login·��������
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") //2��½ҳ��
                .defaultSuccessUrl("/chat") //3��½�ɹ�ת���ҳ��
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    //4
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    		auth
                .inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("wyf").password(new BCryptPasswordEncoder().encode("wyf")).roles("USER")
                .and().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("wisely").password(new BCryptPasswordEncoder().encode("wisely")).roles("USER")
                .and().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("USER");
    }
    //5���Ծ�̬��Դ������
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**");
    }

}
