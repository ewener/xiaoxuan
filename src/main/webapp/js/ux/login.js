var login = {
		
		FORM_ID:"#loginForm",
		SUBMIT_BTN:"#ibtnenter",
		IMG_ID:"#vc-pic",
		INPUT_EMAIL:"#inputEmail",
		INPUT_PWD:"#inputPassword",
		INPUT_CODE:"#inputVericode",
		MODAL:"#myModal",
			
		toLogin:function(){
				if($(login.INPUT_EMAIL).val()==''){
					login.dialog("请输入用户名！");
					return;
				}
				if($(login.INPUT_PWD).val()==''){
					login.dialog("请输入密码！");
					return;
				}
				if($(login.INPUT_CODE).val()==''){
					login.dialog("请输入验证码！");
					return;
				}
			var param = {email:$(login.INPUT_EMAIL).val(),pwd:$(login.INPUT_PWD).val(),verifyCode:$(login.INPUT_CODE).val()/*,browser:browser,os:os*/};
			$.ajax({  
	            type: "post",  
	            async: false, //同步和异步的参数
	            url: "/dmcWeb/toLogin",       
	            data: JSON.stringify(param),     
	            dataType : "json",
				contentType: "application/json; charset=utf-8",
	            success: function(data) {
	                if(data.success)
	                {
	                	window.location.href="/dmcWeb/main";
	                }
	                else
	                {
	                	login.dialog(data.msg);
	                }
	            },  
	            error: function(data) {  
	            	login.dialog("系统繁忙！");
	            }  
	        })  
		},
		dialog:function(msg)
		{
			var $modalBody = $(login.MODAL+" .modal-body");
			$modalBody.empty();
			$modalBody.append("<p>"+msg+"</p>")
			$(login.MODAL).modal({
				keyboard:true
			})
		},
		keyDown:function(){
			//支持IE和火弧的键盘事件
			$(document).keydown(function(e){
				var theEvent = window.event || e;
				code = theEvent.keyCode || theEvent.which;
				if(code == 13){ //回车键
					$(login.SUBMIT_BTN).click();
				}
			});
		},
		loadVrifyCode:function(){//刷新验证码
			var _url = "RandomImage?time="+new Date().getTime();
			$(login.IMG_ID).attr('src',_url);
		},
		init:function(){
			if(window.top != window.self){
				window.top.location =  window.self.location;
			}
			//验证码图片绑定点击事件
			$(login.IMG_ID).click(login.loadVrifyCode);
			$(login.SUBMIT_BTN).click(login.toLogin);
			this.keyDown();
		}
	}


$(function(){
	login.init();
});		