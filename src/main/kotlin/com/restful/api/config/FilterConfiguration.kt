package com.restful.api.config

import com.restful.api.filter.LoggingFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered

/**
 * フィルター設定
 *
 * @author yanaokahiroki
 */
@Configuration
class FilterConfiguration {
  @Bean
  fun filterRegistrationBean(): FilterRegistrationBean<LoggingFilter> {
    val bean = FilterRegistrationBean<LoggingFilter>()
    bean.addUrlPatterns("/api/v1/*")
    bean.order = Ordered.HIGHEST_PRECEDENCE
    return bean
  }
}
