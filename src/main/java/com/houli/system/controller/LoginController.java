package com.houli.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.houli.common.annotation.Log;
import com.houli.common.config.Constant;
import com.houli.common.controller.BaseController;
import com.houli.common.domain.Tree;
import com.houli.common.utils.HttpServletUtils;
import com.houli.common.utils.MD5Utils;
import com.houli.common.utils.Result;
import com.houli.common.utils.ShiroUtils;
import com.houli.system.domain.MenuDO;
import com.houli.system.service.MenuService;
import com.houli.system.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@Api(value="用户登录接口",tags= {"用户登录接口"})
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MenuService menuService;
	@Autowired
	UserService userService;
	
	/**
	 * 进入登录页
	 * @return
	 */
	@ApiIgnore
	@GetMapping({ "/", "" })
	String welcome(Model model) {
		return "login";
	}

	/**
	 * 进入登录页
	 * @return
	 */
	@GetMapping("/login")
	@ResponseBody
	Object login(HttpServletRequest request, HttpServletResponse response) {
		// HttpSession httpSession = request.getSession();
		// logger.info("HttpSession为：" + httpSession.getClass());
		
		if (HttpServletUtils.jsAjax(request)) {
			//设置消息状态码，便于在js中error中获取
			response.setStatus(Constant.STATUS_TIMEOUT);
			return Result.error(Constant.STATUS_TIMEOUT, "登录超时，请重新登录!");
        } else {
        	return new ModelAndView("login");
        }
		
		
	}
	
	
	/**
	 * 登录验证
	 * @return
	 */
	@PostMapping("/login")
	@ResponseBody
	@ApiOperation("登录验证")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query", name = "username", value = "用户登录名称", required = true, dataType = "String"),
		@ApiImplicitParam(paramType="query", name = "password", value = "用户登录密码", required = true, dataType = "String")
	})
	Result ajaxLogin(String username, String password) {
		try {
			//这里编写用户名和密码验证
//			Map<String, Object> map = new HashMap<>(16);
//			map.put("username", username);
//			UserDO user = userService.list(map).get(0);
//			String pwd = MD5Utils.encrypt(password);
//			if (user.getPassword().equals(pwd)) {
//				logger.debug("登录成功！");
//				return Result.ok();
//			} else {
//				logger.error("用户名或者密码错误！");
//				return Result.error("用户名或者密码错误！");
//			}
			
			password = MD5Utils.encrypt(username, password);
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();
			try {
				subject.login(token);
				return Result.ok();
			} catch (AuthenticationException e) {
				logger.error(e.getMessage());
				return Result.error(e.getMessage());
			}
			
			
		} catch (Exception e) {
			return Result.error("用户或密码错误");
		}
	}
	
	/**
	 * 登录验证成功进入首页
	 * @param model
	 * @return
	 */
	@Log("登录成功进入首页")
	@GetMapping({ "/index" })
	@ApiIgnore
	String index(Model model) {
		//菜单权限
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		model.addAttribute("username", getUser().getUsername());
		model.addAttribute("userId", getUser().getUserId());
		return "index";
	}
	
	/**
	 * 进入403无权限页
	 * @return
	 */
	@GetMapping("/403")
	@ApiIgnore
	@ResponseBody
	Object noPermission(HttpServletRequest request, HttpServletResponse response) {
		if (HttpServletUtils.jsAjax(request)) {
			//设置消息状态码，便于在js中error中获取
			response.setStatus(Constant.STATUS_NOT_AUTH);
			return Result.error(Constant.STATUS_NOT_AUTH, "您无权访问，请联系管理员!");
        } else {
        	return new ModelAndView("403");
        }
	}

	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

}
