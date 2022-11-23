package com.restful.api.filter

import com.fasterxml.jackson.databind.json.JsonMapper
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * ログフィルター
 *
 * 参考
 * - [SpringBootでリクエスト／レスポンス情報をログに出力するFilter実装](https://yamakisso.hatenablog.com/entry/2022/02/18/034224)
 * - [Spring Boot + Kotlin で RESTful API のログ出力](https://qiita.com/oskamathis/items/2e94e7572e9b971236b3)
 *
 * @author yanaokahiroki
 */
class LoggingFilter : OncePerRequestFilter() {
  companion object {
    private val log = LoggerFactory.getLogger(LoggingFilter::class.java)
    private val mapper = JsonMapper.builder().build()
  }

  override fun doFilterInternal(
    request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
  ) {
    doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain)
  }

  private fun doFilterWrapped(
    request: ContentCachingRequestWrapper,
    response: ContentCachingResponseWrapper,
    filterChain: FilterChain
  ) {
    try {
      filterChain.doFilter(request, response)
    } finally {
      val endpoint = logRequest(request)
      logResponse(response, endpoint)
      response.copyBodyToResponse()
    }
  }

  private fun logRequest(request: ContentCachingRequestWrapper): String {
    val query = request.queryString?.let { "?$it" } ?: ""
    val endpoint = "${request.method} ${request.requestURI}$query"

    val requestBody = getContentByte(request.contentAsByteArray)

    val json = mapper.writeValueAsString(
      object {
        val endpoint = endpoint
        val request = object {
          val body: Any? =
            if (requestBody.isNotBlank() && request.contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
              mapper.readTree(requestBody)
            } else {
              requestBody
            }
        }
      }
    )
    log.info(json)
    return endpoint
  }

  private fun logResponse(response: ContentCachingResponseWrapper, endpoint: String) {
    val status = response.status
    val statusPhrase = HttpStatus.valueOf(status).reasonPhrase

    val responseBody = getContentByte(response.contentAsByteArray)

    val json = mapper.writeValueAsString(
      object {
        val endpoint = endpoint
        val response = object {
          val status = "$status $statusPhrase"
          val body: Any? =
            if (responseBody.isNotBlank() && response.contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
              mapper.readTree(responseBody)
            } else {
              responseBody
            }
        }
      }
    )
    log.info(json)
  }

  private fun getContentByte(content: ByteArray): String {
    var contentString = ""
    contentString += String(content, StandardCharsets.UTF_8)
    return contentString
  }

  private fun wrapRequest(request: HttpServletRequest): ContentCachingRequestWrapper {
    return request as? ContentCachingRequestWrapper ?: ContentCachingRequestWrapper(request)
  }

  private fun wrapResponse(response: HttpServletResponse): ContentCachingResponseWrapper {
    return response as? ContentCachingResponseWrapper ?: ContentCachingResponseWrapper(response)
  }
}
