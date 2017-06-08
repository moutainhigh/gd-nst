农速通项目说明(201607018)
一、项目模块
		项目名称				项目描述		用途
		gd-nst-pom 			主目录		公共jar、配置文件参数、打包编译
		nst-api				接口(jar)	各个模块dto、vo、接口、实体类、工具类
		nst-srv-admin		服务层(war)	后台管理模块服务实现类
		nst-srv-order		服务层(war)	订单模块服务实现类
		nst-srv-member		服务层(war)	会员模块服务实现类
		nst-srv-pay			服务层(war)	支付服务实现类
		nst-srv-source		服务层(war)	货源、车源服务实现类
		nst-srv-right		服务层(war)	权限服务实现类，例如：登陆服务
		nst-web-admin		控制层(war)	后台管理 Controller
		nst-web-api			控制层(war)	rest http接口 Controller,请根据业务包名区分
	备注：Controller层可调用任意server服务，admin后台管理除外
		  server层服务之间不能相互调用
		  业务均写在server层,Controller层只做数据返回(均返回对象:ApiResult.java)
		 请写接口的人员务必自测接口，通过junit测试，或者http请求测试(推荐使用jmeter)
	
二、导入
		首先下载源码gd-nst-pom,右键导入import-->Maven-->Existing maven prijects
		勾选上述项目名称，确定即可
	
三、配置
		编码：UTF-8
		JDK：1.7
		依赖：各个项目均依赖nst-api
	
四、包名规范
		根包名：cn.gdeng.nst
		子模块包命名规则 cn.gdeng.nst.子模块名称，例如：
												cn.gdeng.nst.api
												cn.gdeng.nst.server
												cn.gdeng.nst.web
		角色：货主(consigner)，司机(driver)，信息部(agent)
五、nst-api规范
		查询条件对象DTO：cn.gdeng.nst.dto.项目名称.模块名称，例如：
										cn.gdeng.nst.api.dto.order
										cn.gdeng.nst.api.dto.pay
		返回结果对象VO：cn.gdeng.nst.dto.项目名称.模块名称，例如：
										cn.gdeng.nst.api.vo.order
										cn.gdeng.nst.api.vo.pay
		实体对象：cn.gdeng.nst.entity.数据库名
		枚举对象：cn.gdeng.nst.entity.数据库名.enums,请将数据库中字段状态放如此，
					例如：`useStatus` char(2) DEFAULT '0' COMMENT '使用状态 1已使用 2未使用'
		接口对象：cn.gdeng.nst.server.模块名称
					例如：	cn.gdeng.nst.api.server.order
							cn.gdeng.nst.admin.server.pay
		公共工具类：cn.gdeng.nst.util.分类名
		
六、nst-srv-*规范
		DAO对象：cn.gdeng.nst.server.dao
				 cn.gdeng.nst.server.dao.impl
		服务接口实现类：cn.gdeng.nst.api.server.order.impl
						cn.gdeng.nst.admin.server.pay.impl
		工具类：均写在nst-api中
		
七、nst-web规范
		拦截器：cn.gdeng.nst.web.interceptor
		控制层:cn.gdeng.nst.web.controller.模块名称
				例如:cn.gdeng.nst.web.controller.order
					cn.gdeng.nst.web.controller.pay
		工具类：均写在nst-api中
		
八、项目调试
	nst-srv-* nst-web-* 可放在同一tomcat启动,建议 server和web分开启动
		