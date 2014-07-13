package com.netease.shijin.yitao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
    @Autowired
    TestDao dao;

	@RequestMapping("/simple")
	public @ResponseBody String simple() {
		return "Hello world!" + dao.getUser(1).name;
	}

}
