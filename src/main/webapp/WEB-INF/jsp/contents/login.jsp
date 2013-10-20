<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	$(document).ready(document.f.j_username.focus);
</script>

<table cellpadding="0" cellspacing="0" border="0"  style="width: 960px;height:100%; margin-left: auto; margin-right: auto;font-family: verdana;">
<tr>
		<td style="width: 200px; padding: 15px; padding-right: 0px; border: 0px; vertical-align: top;">
			<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
				<div style="width: 50%; padding-top: 20px;padding-left: 200px;">
					<div class="menu-title">
						<span style="color: #fff; font-weight: bold; font-size: 13px;">Sign in</span>
						<%-- <span style="color: #fff; float: right;">or <a class="link-login" href="${pageContext.servletContext.contextPath}/signup">Create an Account</a></span> --%>
					</div>
					<div class="menu-content" style="padding: 10px;">
						<div Class="success">${success}</div>
						<div style="margin-top: 10px;color: #ffccff;">Email</div>
						<div style="margin-top: 5px;"><input type='text' name='j_username' value='' style="width: 280px; height: 2em;" /></div>
						<div style="margin-top: 10px;color: #ffccff;">Password</div>
						<div style="margin-top: 5px;"><input type='password' name='j_password' style="width: 280px; height: 2em;" /></div>
						<c:if test="${not empty error}"><div class="errorblock" style="color: red;" style="font-family: verdana;">Invalid Username/Password.</div></c:if>
						<div style=" margin-top: 30px;">
							<span><input name="submit" type="submit" value="Sign in" /></span>
							<span style="float: right;">
								<label for="_spring_security_remember_me" class="checkboxLabel">
			                      <!--   <input type='checkbox' name='_spring_security_remember_me' id="_spring_security_remember_me" tabindex="3" value="true"/>Remember Me? -->
			                    </label>
		                    </span>
						</div>
						<%-- <div style="margin-top: 10px; text-align: right;"><a class="link" href="${pageContext.servletContext.contextPath}/forgotpass">I don't know my password</a></div> --%>
					</div>
				</div>
			</form> 
		</td>
	</tr>
</table>