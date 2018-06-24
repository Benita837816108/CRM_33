﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<TITLE>添加客户</TITLE>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
	<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
		  rel=stylesheet>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/my.js"></script>
	<script type="text/javascript">
        $(document).ready(function(){

            loadSelect("006","level","cust_level.dict_id", <s:if test="customer.cust_level!=null"><s:property value="customer.cust_level.dict_id" /></s:if>);
            loadSelect("001","industry","cust_industry.dict_id", <s:if test="customer.cust_industry!=null"><s:property value="customer.cust_industry.dict_id" /></s:if>);
            loadSelect("009","source","cust_source.dict_id" ,<s:if test="customer.cust_source!=null"><s:property value="customer.cust_source.dict_id" /></s:if>);


        });
	</script>

	<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
<FORM id=form1 name=form1
	  action="${pageContext.request.contextPath }/customer_update.action"
	  method=post enctype="multipart/form-data">
	<input type="hidden" name="custId" value="${customer.custId }"/>

	<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
		<TBODY>
		<TR>
			<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
							  border=0></TD>
			<TD width="100%" background=${pageContext.request.contextPath }/images/new_020.jpg
				height=20></TD>
			<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
							  border=0></TD>
		</TR>
		</TBODY>
	</TABLE>
	<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
		<TBODY>
		<TR>
			<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
					src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
			<TD vAlign=top width="100%" bgColor=#ffffff>
				<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
					<TR>
						<TD class=manageHead>当前位置：客户管理 &gt; 修改客户</TD>
					</TR>
					<TR>
						<TD height=2></TD>
					</TR>
				</TABLE>
				<TABLE cellSpacing=0 cellPadding=5  border=0>
					<TR>
						<td>客户名称：</td>
						<td>
							<s:textfield cssClass="textbox" id="sChannel2" cssStyle="WIDTH: 180px" maxlength="50" name="cust_name"></s:textfield>

						</td>
						<td>客户级别 ：</td>
						<td id="level" >
						</td>
					</TR>
					<TR>
						<td>信息来源 ：</td>
						<td id="source">
						</td>
						<td>客户行业：</td>
						<td id="industry">
						</td>
					</TR>

					<TR>


						<td>固定电话 ：</td>
						<td>
							<s:textfield cssClass="textbox" id="sChannel2" cssStyle="WIDTH: 180px" maxlength="50" name="cust_phone"></s:textfield>

						</td>
						<td>移动电话 ：</td>
						<td>
							<s:textfield cssClass="textbox" id="sChannel2" cssStyle="WIDTH: 180px" maxlength="50" name="cust_mobile"></s:textfield>
						</td>
					</TR>

					<TR>
						<td>客户照片 ：</td>
						<td colspan="3">
							<input type="file" name="upload"/>
							<br>
							<!-- 存放图片的路径 -->
							<s:hidden name="cust_image"></s:hidden>
							<!-- 修改功能，存放一个id -->
							<s:hidden name="cust_id"></s:hidden>
							<%-- <img alt="图片" src="${pageContext.request.contextPath }/images/5.jpg"/> --%>
							<s:fielderror></s:fielderror>
							<s:actionerror/>
						</td>
					</TR>
					<tr>
						<td rowspan=2>
							<INPUT class=button id=sButton2 type=submit
								   value=" 保存 " name=sButton2>
						</td>
					</tr>
				</TABLE>


			</TD>
			<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
				<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
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
