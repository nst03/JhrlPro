package com.fwzx.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "测试", tags = "测试")
@RestController("/test")
public class TestController {

	@ApiOperation(value = "测试接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "query", required = false)
	})
	@RequestMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET})
	public String test(@RequestParam(value = "id", required = false) String id) {
		return id;
	}

}