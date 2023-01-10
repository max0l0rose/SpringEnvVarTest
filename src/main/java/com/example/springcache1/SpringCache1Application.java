package com.example.springcache1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Controller
//@Scope(value = WebApplicationContext.SCOPE_SESSION)
//@SessionScope
//@RequestMapping("/")
@EnableCaching
@ServletComponentScan
public class SpringCache1Application {

//	@Bean
//	public CacheManager cacheManager() {
//		return new ConcurrentMapCacheManager("addresses");
//	}

//	@Autowired
//	CachingService cachingService;

//	@Autowired
//	CacheManager cacheManager;

	// ETAG!
//	@Bean
//	public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
//		return new ShallowEtagHeaderFilter();
//	}


//	@Autowired
//	A a;

	int c = 0;

	@GetMapping()
	@ResponseBody
	@Cacheable(value = "f", unless="#result.length()>10")
//	@CacheEvict
	public String f(@RequestParam int f) throws InterruptedException {

		//String s = a.fff();

		Thread.sleep(2000);
		return "C=" //+ s + " "
				       + ++c + "; f=" + f;
	}


	@GetMapping("/qqq")
	public ResponseEntity<String> qqq() throws InterruptedException {
		Thread.sleep(2000);
		return ResponseEntity.ok()
				//.cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
				.body("<a href='http://localhost:8080/qqq2'>qqqqqq2</a>");
	}

	@GetMapping("/qqq2")
	public ResponseEntity<String> qqq2() throws InterruptedException {
		Thread.sleep(2000);
		return ResponseEntity.ok()
				//.cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
				.body("<a href='http://localhost:8080/qqq'>qqqqqq</a>");
	}

	//	@Cacheable(value="books", key="#isbn.rawNumber")
//	public Book findStoryBook (ISBN isbn, boolean checkWarehouse, boolean includeUsed)
//
//	@Cacheable(value="books", key="T(classType).hash(#isbn)")
//	public Book findStoryBook (ISBN isbn, boolean checkWarehouse, boolean includeUsed)

	public static void main(String[] args) {
		SpringApplication.run(SpringCache1Application.class, args);
	}

//	@EnableWebSecurity
//	public class AppSecurityConfig extends
//			WebSecurityConfigurerAdapter {
//
//		@Override
//		protected void configure(HttpSecurity http) {
//			http
//					.headers(headers -> headers
//							.cacheControl(cache -> cache.disable())
//					);
//		}
//	}

//	@Configuration
//	@EnableWebMvcSecurity
//	public class SecurityConfig extends WebSecurityConfigurerAdapter {
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			// Prevent the HTTP response header of "Pragma: no-cache".
//			http.headers().cacheControl().disable();
//		}
//	}


//	@Bean
//	public TomcatEmbeddedServletContainerFactory tomcatFactory() {
//		TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
//		tomcatFactory.addContextCustomizers((context) -> {
//			StandardRoot standardRoot = new StandardRoot(context);
//			standardRoot.setCacheMaxSize(40 * 1024);
//		});
//		return tomcatFactory;
//	}

}


@WebFilter("/*")
class AddResponseHeaderFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
																throws IOException, ServletException
	{
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Cache-Control", "max-age=60");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}


//@NamedNativeQuery( name = "qqq", query = "SELECT 1")
//@Component
//class A {
//
//	@Cacheable(value = "f")
//	public String fff() throws InterruptedException
//	{
//		Thread.sleep(2000);
//		return "aaaaaaaaaa";
//	}
//
//}


//@Component
//class SimpleCacheCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {
//
//	@Override
//	public void customize(ConcurrentMapCacheManager cacheManager) {
//		cacheManager.setCacheNames(Arrays.asList("f"));
//	}
//}


//@EnableWebMvc
//public class MvcConfig implements WebMvcConfigurer {
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/js/**")
//				.addResourceLocations("/js/")
//				.setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
//	}
//}

