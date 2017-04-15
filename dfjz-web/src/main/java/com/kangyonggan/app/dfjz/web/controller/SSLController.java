package com.kangyonggan.app.dfjz.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangyonggan
 * @since 2017/4/15 0015
 */
@RestController
@RequestMapping(".well-known/acme-challenge")
public class SSLController {

    @RequestMapping("y1WhC3cdHAFNWeNwigLlWxVBVLJdWTW7R4a591IqnEA")
    public String one() {
        return "sFeQZnTv-wjzr0FAwLceuz2VOy-wwIvsczHrRIPwOzU";
    }

    @RequestMapping("WcH7OZsgtg-chfkEzsEYAA12brKsG5pCv2DShVdqA9I")
    public String two() {
        return "sFeQZnTv-wjzr0FAwLceuz2VOy-wwIvsczHrRIPwOzU";
    }

}
