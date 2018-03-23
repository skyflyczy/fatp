<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="bjui-leftside">
    <div id="bjui-sidebar-s">
        <div class="collapse"></div>
    </div>
    <div id="bjui-sidebar">
        <div class="toggleCollapse"><h2><i class="fa fa-bars"></i> 导航栏 <i class="fa fa-bars"></i></h2><a href="javascript:;" class="lock"><i class="fa fa-lock"></i></a></div>
        <div class="panel-group panel-main" data-toggle="accordion" id="bjui-accordionmenu" data-heightbox="#bjui-sidebar" data-offsety="26">

            <div class="panel panel-default">
                <div class="panel-heading panelContent">
                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse0" class="active"><i class="fa fa-caret-square-o-down"></i>&nbsp;定向债权</a></h4>
                </div>
                <div id="bjui-collapse0" class="panel-collapse panelContent collapse in">
                    <div class="panel-body" >
                        <ul id="bjui-tree0" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                        	<li data-id="proRegister" data-pid="0">项目登记</li>
                            <li data-id="proRegister_1" data-pid="proRegister" data-url="<%=request.getContextPath()%>/debt/proissue/review/list.do"
                             data-tabid="proRegister_1" data-fresh="true" data-reloadWarn="true">登记审核管理</li>
                            <li data-id="proTrader" data-pid="0">项目交易</li>
                        	<li data-id="proTrader_1" data-pid="proTrader" data-url="<%=request.getContextPath()%>/debt/proissue/cfmright/prolist.do" 
                        			data-tabid="proTrader_1" data-fresh="true" data-reloadWarn="true">交易审核管理</li>
                        	<li data-id="proTrader_2" data-pid="proTrader" data-url="<%=request.getContextPath()%>/debt/proissue/result/prolist.do" 
                        			data-tabid="proTrader_2" data-fresh="true" data-reloadWarn="true">交易结果管理</li>
                        	<li data-id="proSettle" data-pid="0">项目结算</li>
                        	<li data-id="proSettle_3" data-pid="proSettle" data-url="<%=request.getContextPath()%>/debt/proissue/settle/prolist.do" 
                        			data-tabid="proSettle_3" data-fresh="true" data-reloadWarn="true">项目结算管理</li>
                        	<li data-id="proRepay" data-pid="0">项目还款</li>
                        	<li data-id="repay_1" data-pid="proRepay" data-url="<%=request.getContextPath()%>/debt/proclaims/repay/recentlylist.do" 
                        			data-tabid="repay_1" data-fresh="true" data-reloadWarn="true">近期还款提示</li>
                        	<li data-id="repay_2" data-pid="proRepay" data-url="<%=request.getContextPath()%>/debt/proclaims/repay/overlist.do" 
                        			data-tabid="repay_2" data-fresh="true" data-reloadWarn="true">逾期还款提示</li>
                        	<li data-id="repay_3" data-pid="proRepay" data-url="<%=request.getContextPath()%>/debt/proclaims/repay/list.do" 
                        			data-tabid="repay_3" data-fresh="true" data-reloadWarn="true">还款查询</li>
                        	<li data-id="proPayInvest" data-pid="0">项目兑付</li>
                        	<li data-id="payinvest_1" data-pid="proPayInvest" data-url="<%=request.getContextPath()%>/debt/proclaims/payinvest/recentlylist.do" 
                        			data-tabid="payinvest_1" data-fresh="true" data-reloadWarn="true">近期兑付提示</li>
                        	<li data-id="payinvest_1" data-pid="proPayInvest" data-url="<%=request.getContextPath()%>/debt/proclaims/payinvest/overlist.do" 
                        			data-tabid="payinvest_1" data-fresh="true" data-reloadWarn="true">逾期兑付提示</li>
                        	<li data-id="payinvest_1" data-pid="proPayInvest" data-url="<%=request.getContextPath()%>/debt/proclaims/payinvest/list.do" 
                        			data-tabid="payinvest_1" data-fresh="true" data-reloadWarn="true">兑付查询</li>
                        	<li data-id="proQuery" data-pid="0">项目查询</li>
                        	<li data-id="query_1" data-pid="proQuery" data-url="<%=request.getContextPath()%>/debt/prosearch/search.do" 
                        			data-tabid="query_1" data-fresh="true" data-reloadWarn="true">项目综合查询</li>  
                        </ul>
                    </div>
                </div>
                <div class="panelFooter"><div class="panelFooterContent"></div></div>
            </div>
            
            <div class="panel panel-default">
                <div class="panel-heading panelContent">
                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse1"><i class="fa fa-caret-square-o-down"></i>&nbsp;机构间金融资产</a></h4>
                </div>
                <div id="bjui-collapse1" class="panel-collapse panelContent collapse">
                    <div class="panel-body" >
                        <ul id="bjui-tree1" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                        	<li data-id="proRegister" data-pid="0">项目登记</li>
                            <li data-id="proRegister_1" data-pid="proRegister" data-url="<%=request.getContextPath()%>/fasset/proissue/review/list.do"
                             data-tabid="proRegister_1" data-fresh="true" data-reloadWarn="true">登记审核管理</li>
                            <li data-id="proTrader" data-pid="0">项目交易</li>
                        	<li data-id="proTrader_1" data-pid="proTrader" data-url="<%=request.getContextPath()%>/fasset/proissue/cfmright/prolist.do" 
                        			data-tabid="proTrader_1" data-fresh="true" data-reloadWarn="true">交易审核管理</li>
                        	<li data-id="proTrader_2" data-pid="proTrader" data-url="<%=request.getContextPath()%>/fasset/proissue/result/prolist.do" 
                        			data-tabid="proTrader_2" data-fresh="true" data-reloadWarn="true">交易结果管理</li>
                        	<li data-id="proSettle" data-pid="0">项目结算</li>
                        	<li data-id="proSettle_1" data-pid="proSettle" data-url="<%=request.getContextPath()%>/fasset/proissue/settle/bidlist-payoffline.do" 
                        			data-tabid="proSettle_1" data-fresh="true" data-reloadWarn="true">转让到款确认</li>
                        	<li data-id="proSettle_3" data-pid="proSettle" data-url="<%=request.getContextPath()%>/fasset/proissue/settle/list.do" 
                        			data-tabid="proSettle_3" data-fresh="true" data-reloadWarn="true">项目结算管理</li>
                        	<li data-id="proQuery" data-pid="0">项目查询</li>
                        	<li data-id="query_1" data-pid="proQuery" data-url="<%=request.getContextPath()%>/fasset/prosearch/search.do" 
                        			data-tabid="query_1" data-fresh="true" data-reloadWarn="true">项目综合查询</li>  
                        </ul>
                    </div>
                </div>
                <div class="panelFooter"><div class="panelFooterContent"></div></div>
            </div>
            
            <div class="panel panel-default">
                <div class="panel-heading panelContent">
                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse2"><i class="fa fa-caret-square-o-down"></i>&nbsp;资产收益权</a></h4>
                </div>
                <div id="bjui-collapse2" class="panel-collapse panelContent collapse">
                    <div class="panel-body" >
                        <ul id="bjui-tree2" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                        	<li data-id="proRegister" data-pid="0">项目登记</li>
                            <li data-id="proRegister_1" data-pid="proRegister" data-url="<%=request.getContextPath()%>/debt/proissue/review/list.do"
                             data-tabid="proRegister_1" data-fresh="true" data-reloadWarn="true">登记审核管理</li>
                            <li data-id="proTrader" data-pid="0">项目交易</li>
                        	<li data-id="proTrader_1" data-pid="proTrader" data-url="<%=request.getContextPath()%>/debt/proissue/cfmright/prolist.do" 
                        			data-tabid="proTrader_1" data-fresh="true" data-reloadWarn="true">交易审核管理</li>
                        	<li data-id="proTrader_2" data-pid="proTrader" data-url="<%=request.getContextPath()%>/debt/proissue/result/prolist.do" 
                        			data-tabid="proTrader_2" data-fresh="true" data-reloadWarn="true">交易结果管理</li>
                        	<li data-id="proSettle" data-pid="0">项目结算</li>
                        	<li data-id="proSettle_3" data-pid="proSettle" data-url="<%=request.getContextPath()%>/debt/proissue/settle/prolist.do" 
                        			data-tabid="proSettle_3" data-fresh="true" data-reloadWarn="true">项目结算管理</li>
                        	<li data-id="proQuery" data-pid="0">项目查询</li>
                        	<li data-id="query_1" data-pid="proQuery" data-url="<%=request.getContextPath()%>/debt/prosearch/search.do" 
                        			data-tabid="query_1" data-fresh="true" data-reloadWarn="true">项目综合查询</li>
                        </ul>
                    </div>
                </div>
                <div class="panelFooter"><div class="panelFooterContent"></div></div>
            </div>
            
            <div class="panel panel-default">
                <div class="panel-heading panelContent">
                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse5" class=""><i class="fa fa-caret-square-o-down"></i>&nbsp;会员/客户管理</a></h4>
                </div>
                <div id="bjui-collapse5" class="panel-collapse panelContent collapse">
                    <div class="panel-body" >
                        <ul id="bjui-tree5" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                           	<li data-id="member" data-pid="0">会员账户</li>
                        	<li data-id="member_1" data-pid="member" data-url="<%=request.getContextPath()%>/user/member/list.do" 
                        			data-tabid="member_1" data-fresh="true" data-reloadWarn="true">会员注册管理</li>
                        	<li data-id="member_2" data-pid="member" data-url="<%=request.getContextPath()%>/user/member/checklist.do" 
                        			data-tabid="member_2" data-fresh="true" data-reloadWarn="true">会员审核</li>
                        	<li data-id="member_3" data-pid="member" data-url="<%=request.getContextPath()%>/user/member/search.do" 
                        			data-tabid="member_3" data-fresh="true" data-reloadWarn="true">会员账户查询</li>
                           	<li data-id="custom" data-pid="0">客户账户</li>
                        	<li data-id="custom_1" data-pid="custom" data-url="<%=request.getContextPath()%>/user/custom/checklist.do" 
                        			data-tabid="custom_1" data-fresh="true" data-reloadWarn="true">客户审核管理</li>  
                        	<li data-id="custom_2" data-pid="custom" data-url="<%=request.getContextPath()%>/user/custom/search.do" 
                        			data-tabid="custom_2" data-fresh="true" data-reloadWarn="true">客户账户查询</li>
                        	<li data-id="custom_3" data-pid="custom" data-url="<%=request.getContextPath()%>/user/bankcard/userlist.do" 
                        			data-tabid="custom_3" data-fresh="true" data-reloadWarn="true">银行账户管理</li>
                            <li data-id="asset" data-pid="0">会员资产</li>
                            <li data-id="asset_0" data-pid="asset" data-url="<%=request.getContextPath()%>/bizmoney/asset/investorlist.do"
                           	  data-tabid="asset_0" data-fresh="true" data-reloadWarn="true">投资者资产列表</li>
                            <li data-id="asset_1" data-pid="asset" data-url="<%=request.getContextPath()%>/bizmoney/asset/borrowerlist.do"
                           	  data-tabid="asset_1" data-fresh="true" data-reloadWarn="true">融资方资产列表</li>
                        </ul>
                    </div>
                </div>
                <div class="panelFooter"><div class="panelFooterContent"></div></div>
            </div>
                        
            <div class="panel panel-default">
                <div class="panel-heading panelContent">
                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse6" class=""><i class="fa fa-caret-square-o-down"></i>&nbsp;资金/费用记录</a></h4>
                </div>
                <div id="bjui-collapse6" class="panel-collapse panelContent collapse">
                    <div class="panel-body" >
                        <ul id="bjui-tree6" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                        	<li data-id="proMoney" data-pid="0">项目资金列表</li>
                        	<li data-id="money_1" data-pid="proMoney" data-url="<%=request.getContextPath()%>/moneyflow/applylist.do" 
                        			data-tabid="money_1" data-fresh="true" data-reloadWarn="true">认购列表</li>   
                        	<li data-id="money_2" data-pid="proMoney" data-url="<%=request.getContextPath()%>/moneyflow/releaselist.do" 
                        			data-tabid="money_2" data-fresh="true" data-reloadWarn="true">放款列表</li>   
                        	<li data-id="money_3" data-pid="proMoney" data-url="<%=request.getContextPath()%>/moneyflow/drawbacklist.do" 
                        			data-tabid="money_3" data-fresh="true" data-reloadWarn="true">退款列表</li>   
                        	<li data-id="money_4" data-pid="proMoney" data-url="<%=request.getContextPath()%>/moneyflow/repaylist.do" 
                        			data-tabid="money_4" data-fresh="true" data-reloadWarn="true">还款列表</li> 
                        	<li data-id="money_5" data-pid="proMoney" data-url="<%=request.getContextPath()%>/moneyflow/payinvestlist.do" 
                        			data-tabid="money_5" data-fresh="true" data-reloadWarn="true">兑付列表</li>  
                            <li data-id="fee" data-pid="0">费用列表</li>
                            <li data-id="fee_0" data-pid="fee" data-url="<%=request.getContextPath()%>/bizmoney/fee/advisorlist.do" 
                          	   data-tabid="fee_0" data-fresh="true" data-reloadWarn="true">顾问费用列表</li>
                            <li data-id="fee_1" data-pid="fee" data-url="<%=request.getContextPath()%>/bizmoney/fee/ptlist.do" 
                          	   data-tabid="fee_1" data-fresh="true" data-reloadWarn="true">平台费用列表</li>
                            <li data-id="feepay" data-pid="0">费用结算</li>
                            <li data-id="feepay_0" data-pid="feepay" data-url="<%=request.getContextPath()%>/fee/settle/memberfeepayapplylist.do" 
                          	   data-tabid="feepay_0" data-fresh="true" data-reloadWarn="true">会员费用结算</li>
                            <li data-id="feepay_1" data-pid="feepay" data-url="<%=request.getContextPath()%>/fee/settle/ptfeepayapplylist.do" 
                          	   data-tabid="feepay_1" data-fresh="true" data-reloadWarn="true">平台费用结算</li>
                          	   <!-- 
                            <li data-id="feepay" data-pid="0">服务费划付查询</li>
                            <li data-id="feepay_0" data-pid="feepay" data-url="<%=request.getContextPath()%>/demo/list.do?vm=user/userload" 
                           	  data-tabid="user_0" data-fresh="true" data-reloadWarn="true">融资费用划付查询</li>
                            <li data-id="feepay_1" data-pid="feepay" data-url="<%=request.getContextPath()%>/demo/list.do?vm=user/userload" 
                            	 data-tabid="user_1" data-fresh="true" data-reloadWarn="true">投资服务划付查询</li>
                             -->
                        </ul>
                    </div>
                </div>
                <div class="panelFooter"><div class="panelFooterContent"></div></div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading panelContent">
                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse7" class=""><i class="fa fa-caret-square-o-down"></i>&nbsp;系统管理</a></h4>
                </div>
                <div id="bjui-collapse7" class="panel-collapse panelContent collapse">
                    <div class="panel-body" >
                        <ul id="bjui-tree7" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                        	<li data-id="meeting" data-pid="0">菜单管理</li>
                        	<li data-id="meeting_1" data-pid="meeting" data-url="<%=request.getContextPath()%>/sys/menu/setmenu.do?appId=2" 
                        		 data-tabid="meeting_1" data-fresh="true" data-reloadWarn="true">菜单设置</li>
                            <li data-id="role" data-pid="0">角色/操作员管理</li>
                        	<li data-id="role_0" data-pid="role" data-url="<%=request.getContextPath()%>/sys/role/list.do" 
                        		 data-tabid="role_0" data-fresh="true" data-reloadWarn="true">角色管理</li>
                        	<li data-id="role_1" data-pid="role" data-url="<%=request.getContextPath()%>/member/operator/list.do" 
                        		 data-tabid="role_1" data-fresh="true" data-reloadWarn="true">操作员管理</li>
                        	<li data-id="other" data-pid="0">数据字典</li>
                            <li data-id="other_0" data-pid="other" data-url="<%=request.getContextPath()%>/sys/param/list.do" 
                           	  data-tabid="other_0" data-fresh="true" data-reloadWarn="true">系统参数</li>
                           	<li data-id="other_0" data-pid="other" data-url="<%=request.getContextPath()%>/systype/getprojecttypes.do" 
                           	  data-tabid="other_0" data-fresh="true" data-reloadWarn="true">项目类型</li>
                            <li data-id="other_0" data-pid="other" data-url="<%=request.getContextPath()%>/systypememberpledge/list.do" 
                           	  data-tabid="other_0" data-fresh="true" data-reloadWarn="true" id="systypememberpledge">质押物类型</li>
                            <li data-id="other_1" data-pid="other" data-url="<%=request.getContextPath()%>/systype/industrylist.do" 
                           	  data-tabid="other_1" data-fresh="true" data-reloadWarn="true">行业列表</li>
                        	<li data-id="other_2" data-pid="other" data-url="<%=request.getContextPath()%>/systype/projectflowlist.do" 
                        		 data-tabid="other_2" data-fresh="true" data-reloadWarn="true">项目流程模板</li>
                            <li data-id="other_3" data-pid="other" data-url="<%=request.getContextPath()%>/systype/bankchannellist.do" 
                           	  data-tabid="other_3" data-fresh="true" data-reloadWarn="true">通道银行列表</li>
                        	<li data-id="other_5" data-pid="other" data-url="<%=request.getContextPath()%>/systype/userorglist.do" 
                        		 data-tabid="other_5" data-fresh="true" data-reloadWarn="true">会员组织类型</li>
                        	<li data-id="other6" data-pid="other" data-url="<%=request.getContextPath()%>/systype/repaylist.do" 
                        		 data-tabid="other_6" data-fresh="true" data-reloadWarn="true">还款方式类型</li>
                        	<li data-id="other7" data-pid="other" data-url="<%=request.getContextPath()%>/systype/codeseqlist.do" 
                        		 data-tabid="other_7" data-fresh="true" data-reloadWarn="true">序列号编码规则</li>
                        </ul>
                    </div>
                </div>
                <div class="panelFooter"><div class="panelFooterContent"></div></div>
            </div>
                
            
        </div>
    </div>
</div>
