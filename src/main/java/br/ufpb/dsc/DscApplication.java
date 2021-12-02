package br.ufpb.dsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.ufpb.dsc.filters.TokenFilter;

@SpringBootApplication
public class DscApplication {

	@Bean
    public FilterRegistrationBean<TokenFilter> filterJwt() {
        FilterRegistrationBean<TokenFilter> filtroJwt = new FilterRegistrationBean<TokenFilter>();
        filtroJwt.setFilter(new TokenFilter());
        filtroJwt.addUrlPatterns("/subject/likes/*", "/subject/{id}/*", "/auth/usuarios/*");
        return filtroJwt;
    }

	public static void main(String[] args) {
		SpringApplication.run(DscApplication.class, args);
	}

}
