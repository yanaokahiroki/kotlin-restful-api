package com.restful.api.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * ファビコンコントローラー
 *
 * @author yanaokahiroki
 */
@Controller
class FaviconController {
  @GetMapping("favicon.ico")
  @ResponseBody
  fun returnNoFavicon() {
    // faviconがないために表示される404エラーを回避する
  }
}
