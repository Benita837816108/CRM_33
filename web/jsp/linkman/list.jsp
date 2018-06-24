<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<TITLE>联系人列表</TITLE>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
	<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
		  rel=stylesheet>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
	<SCRIPT language=javascript>
        function changePage(pageNum) {
            //1 将页码的值放入对应表单隐藏域中
            $("#currentPageInput").val(pageNum);
            //2 提交表单
            $("#pageForm").submit();
        };

        function changePageSize(pageSize) {
            //1 将页码的值放入对应表单隐藏域中
            $("#pageSizeInput").val(pageSize);
            //2 提交表单
            $("#pageForm").submit();
        };

	</SCRIPT>

	<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
<FORM id="customerForm" name="customerForm"
	  action="${pageContext.request.contextPath }/linkMan_findByPage.action"
	  method=post>

	<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
		<TBODY>
		<TR>
			<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
							  border=0></TD>
			<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
				height=20></TD>
			<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
							  border=0></TD>
		</TR>
		</TBODY>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
		<TBODY>
		<TR>
			<TD width=15 background="${pageContext.request.contextPath }/images/new_022.jpg"><IMG
					src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
			<TD vAlign=top width="100%" bgColor=#ffffff>
				<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
					<TR>
						<TD class=manageHead>当前位置：联系人管理 &gt; 联系人列表</TD>
					</TR>
					<TR>
						<TD height=2></TD>
					</TR>
				</TABLE>
				<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
					   width="100%" align=center border=0>
					<TBODY>
					<TR>
						<TD height=25>
							<TABLE cellSpacing=0 cellPadding=2 border=0>
								<TBODY>
								<TR>
									<TD>联系人名称：</TD>
									<TD>
										<s:textfield cssClass="textbox" id="lkm_name" cssStyle="WIDTH: 80px" maxlength="50" name="lkm_name"></s:textfield>
									</TD>
									<TD>联系人性别：</TD>
									<TD>
										<s:radio list="#{'1':'男','2':'女'}" cssClass="textbox" id="lkm_gender" cssStyle="WIDTH: 80px" name="lkm_gender"></s:radio>
									</TD>

									<TD><INPUT class=button id=sButton2 type=submit
											   value=" 筛选 " name=sButton2></TD>
								</TR>
								</TBODY>
							</TABLE>
						</TD>
					</TR>

					<TR>
						<TD>
							<TABLE id=grid
								   style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
								   cellSpacing=1 cellPadding=2 rules=all border=0>
								<TBODY>
								<TR
										style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
									<TD>联系人名称</TD>
									<TD>性别</TD>
									<TD>办公电话</TD>
									<TD>手机</TD>
									<TD>所属客户</TD>
									<TD>操作</TD>
								</TR>
								<s:if test="list!=null && list.size()>0">

									<s:iterator value="list">
										<TR
												style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
											<TD><s:property value="lkm_name"/></TD>
											<TD>
												<s:if test="lkm_gender==1">
													男
												</s:if>
												<s:else>
													女
												</s:else>
											</TD>
											<TD><s:property value="lkm_phone"/></TD>
											<TD><s:property value="lkm_mobile"/></TD>
											<TD><s:property value="customer.cust_name"/></TD>
											<TD>
												<a href="${pageContext.request.contextPath }/linkMan_updateUI.action?lkm_id=<s:property value='lkm_id'/>">修改</a>
												&nbsp;&nbsp;
												<a href="${pageContext.request.contextPath }/linkMan_delete.action?lkm_id=<s:property value='lkm_id'/>">删除</a>
											</TD>
										</TR>
									</s:iterator>
								</s:if>

								</TBODY>
							</TABLE>
						</TD>
					</TR>

					<TR>
						<TD><SPAN id=pagelink>
											<DIV
													style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B><s:property value="totalCount"/> </B>]条记录,[<B><s:property
													value="totalPage"/></B>]页
												,每页显示 <%-- changePageSize($('#pageSizeSelect option').filter(':selected').val()) --%>
												<select name="pageSize"
														onchange="changePageSize($('#pageSizeSelect option:selected').val())"
														id="pageSizeSelect">
													<option value="3" <s:property
															value="pageSize==3?'selected':''"/> >3</option>
													<option value="5" <s:property
															value="pageSize==5?'selected':''"/> >5</option>
													<option value="10" <s:property
															value="pageSize==10?'selected':''"/> >10</option>
												</select>
												条
												[<A onclick="changePage(<s:property value='currentPage-1'/>)">前一页</A>]
												<B><s:property value="currentPage"/></B>
												[<A onclick="changePage(<s:property value='currentPage+1'/>)">后一页</A>]
												到
												<input type="text" size="3" id="page" name="page"
													   value="<s:property value="currentPage" />"/>
												页

												<input type="button" value="Go" onclick="changePage($('#page').val())"/>
											</DIV>
									</SPAN></TD>
					</TR>
					</TBODY>
				</TABLE>
			</TD>
			<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
					src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
		</TR>
		</TBODY>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
		<TBODY>
		<TR>
			<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
							  border=0></TD>
			<TD align=middle width="100%"
				background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
			<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
							  border=0></TD>
		</TR>
		</TBODY>
	</TABLE>
</FORM>
</BODY>
</HTML>
