package com.asiainfo.dmcweb.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.dmcweb.base.BaseController;
import com.asiainfo.dmcweb.constant.Constants;
import com.asiainfo.dmcweb.entity.SysBtn;
import com.asiainfo.dmcweb.entity.SysMenu;
import com.asiainfo.dmcweb.entity.TreeNode;
import com.asiainfo.dmcweb.entity.User;
import com.asiainfo.dmcweb.image.RandomGraphic;
import com.asiainfo.dmcweb.service.IService;
import com.asiainfo.dmcweb.util.SessionUtils;
import com.asiainfo.dmcweb.util.TreeUtil;

import net.sf.json.JSONObject;

/**
 * 主界面控制器
 * @author Administrator
 *
 */
@Controller("main")
public class MainController extends BaseController {
	
	@Autowired
	private IService service;

	/**
	 * 获取随机图片，就是用于登陆时的验证码图片
	 */
	@ResponseBody
	@RequestMapping(value = "/RandomImage")
	public void getRandomImage(HttpServletRequest request, HttpServletResponse resp) {
		// 设置输出内容为图像，格式为jpeg
		resp.setContentType("image/png");
		try {
			// 将内容输出到响应客户端对象的输出流中，生成的图片中包含4个字符
			String securityCode = RandomGraphic.createInstance(4).drawNumber(
					// String securityCode =
					// RandomGraphic.createInstance(4).drawAlpha(
					RandomGraphic.GRAPHIC_PNG, resp.getOutputStream());
			// 将字符串的值保留在session中，便于和用户手工输入的验证码比较，比较部分不是本文讨论重点，故略
			if (SessionUtils.getValidateCode(request) != null)
				SessionUtils.removeValidateCode(request);
			SessionUtils.setAttr(request, Constants.SESSION_VALIDATECODE, securityCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/toLogin")
	public JSONObject  toLogin( @RequestBody JSONObject jsonObj,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(!jsonObj.getString("verifyCode").equals(SessionUtils.getValidateCode(request)))
				return sendFailureMessage(response, "验证码错误！");
		JSONObject jsonData = new JSONObject();
		jsonData.put("email", jsonObj.getString("email"));
		jsonData.put("pwd",jsonObj.getString("pwd"));
		JSONObject userjson = service.loginCheck(jsonData);
		System.out.println(userjson.toString());
		if(!userjson.getBoolean(Constants.SUCCESS))
			return sendFailureMessage(response, userjson.getString(Constants.DATA));
		if(userjson.get("user")==null)
			return sendFailureMessage(response, "账号或密码错误！");
		System.out.println("########登录USER"+userjson.get("user"));
		SessionUtils.setUser(request,(User)JSONObject.toBean(JSONObject.fromObject(userjson.get("user")), User.class));
		SessionUtils.setLoginFlag(request,Constants.IS_LOGIN);
		sendSuccessMessage(response, "登录成功.");
		return sendSuccessMessage(response, "登录成功！");
	}
	
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public void  logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
		SessionUtils.setAttr(request, Constants.LOGOUT_FLAG, true);
		response.sendRedirect(Constants.LOGIN_PAGE);
	}
//	
//	 
//	
//	/**
//	 * 修改密码
//	 * @param url
//	 * @param classifyId
//	 * @return
//	 * @throws Exception 
//	 */
//	@RequestMapping("/modifyPwd")
//	public void modifyPwd(String oldPwd,String newPwd,HttpServletRequest request,HttpServletResponse response) throws Exception{
//		User user = SessionUtils.getUser(request);
//		if(user == null){
//			sendFailureMessage(response, "对不起,登录超时.");
//			return;
//		}
//		User bean  = sysUserService.queryById(user.getId());
//		if(bean.getId() == null || DELETED.YES.key == bean.getDeleted()){
//			sendFailureMessage(response, "对不起,用户不存在.");
//			return;
//		}
//		if(StringUtils.isBlank(newPwd)){
//			sendFailureMessage(response, "密码不能为空.");
//			return;
//		}
//		//不是超级管理员，匹配旧密码
//		if(!MethodUtil.ecompareMD5(oldPwd,bean.getPwd())){
//			sendFailureMessage(response, "旧密码输入不匹配.");
//			return;
//		}
//		bean.setPwd(MethodUtil.MD5(newPwd));
//		sysUserService.update(bean);
//		sendSuccessMessage(response, "Save success.");
//	}
//	
	/**
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@RequestMapping("/main") 
	public ModelAndView  main(HttpServletRequest request)throws Exception{
		User user = SessionUtils.getUser(request);
		List<SysMenu> rootMenus  = service.getRootMenu();// 查询所有根节点
		List<SysMenu>  childMenus  =  service.getChildMenu();//查询所有子节点
		List<SysBtn> sysBtnList = service.getSysBtn();//获取可用按钮
		
		System.out.println(childMenus.toString());
		//权限过滤子菜单,按钮
		filter(childMenus,sysBtnList,user);
		Map context = new HashMap();
		context.put("user", user);
		context.put("menuList", treeMenu(rootMenus,childMenus));
		context.put("btnList", sysBtnList);
		return forword("main/main",context); 
	}
	
	private void filter(List<SysMenu> childMenus,List<SysBtn> sysBtnList,User user) {
		
				String menu_list = user.getMenu_list();
				String btn_list = user.getBtn_list();
				String role = user.getRole();
				String _menu_list = "-"+menu_list+"-";
				String _btn_list = "-"+btn_list+"-";
				
				//超级管理员拥有所有权限
				if(Constants.SUPER_ADMIN.equalsIgnoreCase(role))return;
				
				if(childMenus!=null)
				{
					for( Iterator<SysMenu> iterator = childMenus.iterator();iterator.hasNext();)
					{
						SysMenu childmenu  =  iterator.next();
						boolean isExist = _menu_list.indexOf("-"+childmenu.getId()+"-")>=0;
						if(!isExist)iterator.remove();
					}
				}
				if(sysBtnList!=null)
				{
					for( Iterator<SysBtn> iterator = sysBtnList.iterator();iterator.hasNext();)
					{
						SysBtn sysBtn  =  iterator.next();
						boolean isExist = _btn_list.indexOf("-"+sysBtn.getId()+"-")>=0;
						if(!isExist)iterator.remove();
					}
				}
			
	}

	/**
	 * 构建树形数据
	 * @return
	 */
	private List<TreeNode> treeMenu(List<SysMenu> rootMenus,List<SysMenu> childMenus){
		TreeUtil util = new TreeUtil(rootMenus,childMenus);
		return util.getTreeNode();
	}
	
//	/**
//	 * @param url
//	 * @param classifyId
//	 * @return
//	 */
//	@RequestMapping("/test") 
//	public ModelAndView  test(HttpServletRequest request)throws Exception{
//		User user = SessionUtils.getUser(request);
//		Map context = new HashMap();
//		context.put("user", user);
//		return forword("test/test",context); 
//	}
	
//	@ResponseBody
//	@RequestMapping("/theme") 
//	public JSONArray  theme(HttpServletRequest request)throws Exception{
//		JSONArray jd = new JSONArray();
//		String dir = request.getSession().getServletContext().getRealPath("/")+URLUtils.get("theme");
//		String readFile = FileUtil.ReadFile(dir);
//		return jd.fromObject(readFile); 
//	}
	
}
