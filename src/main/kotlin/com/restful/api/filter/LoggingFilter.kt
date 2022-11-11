package com.restful.api.filter

import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * ログフィルター
 *
 * @author yanaokahiroki
 */
class LoggingFilter : OncePerRequestFilter() {
  private val log = LoggerFactory.getLogger(LoggingFilter::class.java)

  override fun doFilterInternal(
    request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
  ) {
    val stopWatch = StopWatch()
    stopWatch.start()

    filterChain.doFilter(request, response)

    stopWatch.stop()

    log.info(
      "${request.method}\t${request.requestURI}\t${response.status}\t${stopWatch.totalTimeMillis}ms"
    )
  }
}
