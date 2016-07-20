var INDEX={
		TABS_ID:"#tab-box",
		TREE_ID:"#tree-box",
		UI:"#easyuitheme",
		CM_THEME:"#combobox_themes"
},
main = function() {
	return {
		logout:function(){
			window.location = '/dmcWeb/logout';
		},
		treeSelect : function() {
			var _this = $(this);
			var title = _this.text();
			var url = _this.attr('id');
			main.addTab(title, url);
			return false;
		},
		treeInit : function() {
			var $items = $(INDEX.TREE_ID).find(".menu-item span");
			$items.bind('click', this.treeSelect);
		},
		addTab : function(_title, _url) {
			var boxId = INDEX.TABS_ID;
			if ($(boxId).tabs('exists', _title)) {
				var tab = $(boxId).tabs('getTab', _title);
				var index = $(boxId).tabs('getTabIndex', tab);
				$(boxId).tabs('select', index);
//				if (tab && tab.find('iframe').length > 0) {
//					var _refresh_ifram = tab.find('iframe')[0];
//					_refresh_ifram.contentWindow.location.href = _url; // 刷新报表
//				}
			} else {
				var _content = "<iframe scrolling='auto'  frameborder='0' marginwidth='5' marginheight='5'  src='"
						+ _url + "' style='width:100%; height:100%'></iframe>";
				$(boxId).tabs('add', {
					title : _title,
					content : _content,
					closable : true,
					tools: [{
			                iconCls: 'icon-mini-refresh',
			                handler: function() { main.refresh_tab(_title, _url); }
			            }]
				});
		        
			}
		},
		refresh_tab: function(title, url) {
	        var tab = $(INDEX.TABS_ID).tabs('select', title).tabs('getTab', title);
//	        if (/^https?:\/\//.test(url)) {
	            $(INDEX.TABS_ID).tabs('update', {
	                tab: tab, options: { content: tab.tabs().panel('options').content }
	            });
//	        } 
//	        else { tab.panel('refresh', url); }
	    },
		menuHover : function() {
			// 菜单鼠标进入效果
			$('.menu-item').hover(function() {
				$(this).stop().animate({
					paddingLeft : "25px"
				}, 200, function() {
					$(this).addClass("orange");
				});
			}, function() {
				$(this).stop().animate({
					paddingLeft : "15px"
				}, function() {
					$(this).removeClass("orange");
				});
			});
		},
		modifyPwd : function() {
			var pwdForm = $("#pwdForm");
			if (pwdForm.form('validate')) {
				var parentId = $('#search_parentId').val();
				$("#edit_parentId").val(parentId)
				common.saveForm(pwdForm, function(data) {
					$('#modify-pwd-win').dialog('close');
					pwdForm.resetForm();
				});
			}
		},
		 full_screen_view: function() {
		        $('#mainbody').requestFullScreen();
		    },
		    expand_view: function() {
		        if ($('#left_layout').layout().panel('options').collapsed) {
		            // $('body').layout('expand', 'south');
		            $('body').layout('expand', 'north');
		            $('body').layout('expand', 'west');
		        } else {
		            $('body').layout('collapse', 'south');
		            $('body').layout('collapse', 'north');
		            $('body').layout('collapse', 'west');
		        }
		    },
		    close_tab: function() {
		        var $index_tabs = $(INDEX.TABS_ID);
		        var curr_tab = $index_tabs.tabs('getSelected');
		        var curr_index = $index_tabs.tabs('getTabIndex', curr_tab);

		        if ($index_tabs.tabs('getTab', curr_index).panel('options').closable) {
		            $index_tabs.tabs('close', curr_index);
		        }
		    },
		    close_other_tabs: function() {
		        var $index_tabs = $(INDEX.TABS_ID);

		        var all_tabs = $index_tabs.tabs('tabs');
		        var count = all_tabs.length;

		        var curr_tab = $index_tabs.tabs('getSelected');
		        var curr_index = $index_tabs.tabs('getTabIndex', curr_tab);
		        var curr_title = $index_tabs.tabs('getTab', curr_index).panel('options').title;
		        while (count--) {
		            if (count !== curr_index && $index_tabs.tabs('getTab', count).panel('options').closable) {
		                $index_tabs.tabs('close', count);
		            }
		        }
		        $index_tabs.tabs('select', curr_title);
		    },
		    close_left_tabs: function() {},
		    close_right_tabs: function() {},
		    close_all_tabs: function() {
		        var $index_tabs = $(INDEX.TABS_ID);
		        var all_tabs = $index_tabs.tabs('tabs');
		        var count = all_tabs.length;
		        while(count--) {
		            if ($index_tabs.tabs('getTab', count).panel('options').closable) {
		                $index_tabs.tabs('close', count);
		            }
		        }
		    },
		theme:function(){
			$(INDEX.CM_THEME).combobox({
			    url:'theme',
			    method:"post",
			    valueField:'path',
			    textField:'name',
			    onLoadSuccess: function() { $(this).combobox('setValue', 'metro-standard')},
			    onSelect: function(record) {
//		            var $link = $(INDEX.UI);
			    	var $link = $('link:first');
		            var arr = $link.attr('href').split('/');
		            arr[arr.length - 2] = record.path;
		            $link.attr('href', arr.join('/'))
		            
			    }
			});
		},
		timeSpan:function(){
			var interval = function(){
				$('#timeSpan').text($.date.toLongDateTimeString(new Date()))
			};
			interval();
			window.setInterval(interval,1000);
		},
		main_page:function(){
			var boxId = INDEX.TABS_ID;
			if ($(boxId).tabs('exists', 'Welcome')) {
				$(boxId).tabs('select', 'Welcome')
			}
		},
		init : function() {
			this.treeInit();
			this.menuHover();
//			this.theme();
			this.timeSpan();
			// 修改密码绑定事件
//			$('#modify-pwd-btn').click(function() {
//				$('#modify-pwd-win').dialog('open');
//			});
//			$('#btn-pwd-close').click(function() {
//				$('#modify-pwd-win').dialog('close');
//			});
//			$('#btn-pwd-submit').click(this.modifyPwd);
			$('#modify-authority-btn').click(function() {
				main.addTab($(this).text(),'/dmcWeb/auth');
			});
			
		}
	};
}();

$(function() {
	main.init();
});