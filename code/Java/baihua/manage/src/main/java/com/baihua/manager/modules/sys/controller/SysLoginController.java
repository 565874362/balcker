package com.baihua.manager.modules.sys.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baihua.core.common.utils.R;
import com.baihua.core.modules.sys.entity.SysUserEntity;
import com.baihua.manager.modules.sys.form.SysLoginForm;
import com.baihua.manager.modules.sys.service.SysCaptchaService;
import com.baihua.manager.modules.sys.service.SysUserService;
import com.baihua.manager.modules.sys.service.SysUserTokenService;


/**
 * 后台登录
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月10日 下午1:15:31
 */
@Api(tags = "后台登录")
@RestController
@RequestMapping("/sys")
public class SysLoginController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;
	@Autowired
	private SysCaptchaService sysCaptchaService;

	/**
	 * 验证码
	 */
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, String uuid)throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//获取图片验证码
		BufferedImage image = sysCaptchaService.getCaptcha(uuid);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 */
	@ApiOperation("用户登录")
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody SysLoginForm form)throws IOException {
		//boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
/*
		if(!captcha){
			return R.fail("验证码不正确");
		}
*/

		//用户信息
 		SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			return R.fail("账号或密码不正确");
		}

		//账号锁定
		if(user.getStatus() == 0){
			return R.fail("账号已被锁定,请联系管理员");
		}

		//生成token，并保存到数据库
		R r = sysUserTokenService.createToken(user.getUserId());
		return r;
	}


	/**
	 * 退出
	 */
	@PostMapping("/sys/logout")
	public R logout() {
		sysUserTokenService.logout(getUserId());
		return R.success();
	}
	
}
