<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.security.Principal"%>
<%@page import="java.util.Locale"%>
<%@page import="org.skyve.metadata.user.User"%>
<%@page import="org.skyve.util.Util"%>
<%@page import="org.skyve.impl.web.UserAgent"%>
<%@page import="org.skyve.impl.web.WebUtil"%>
<%
	String basePath = Util.getSkyveContextUrl() + "/";
	boolean mobile = UserAgent.getType(request).isMobile();
	String referer = WebUtil.getRefererHeader(request);
	Principal p = request.getUserPrincipal();
	User user = WebUtil.processUserPrincipalForRequest(request, (p == null) ? null : p.getName(), true);
	Locale locale = (user == null) ? request.getLocale() : user.getLocale();
%>
<!DOCTYPE html>
<html dir="<%=Util.isRTL(locale) ? "rtl" : "ltr"%>">
	<head>
		<!-- Standard Meta -->
	    <meta charset="utf-8" />
	    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="private,no-cache,no-store" />
		<meta http-equiv="expires" content="0" />
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

		<!-- Site Properties -->	    
		<title><%=Util.i18n("page.logout.title", locale)%></title>
		<base href="<%=basePath%>" />

		<% if (mobile) { %>
			<meta name="format-detection" content="telephone=no" />
			<meta name="format-detection" content="email=no">
		<% } %>

		<link rel="icon" type="image/png" href="images/window/skyve_fav.png" />
		<link rel="apple-touch-icon" href="images/window/skyve_fav.png">
		<link rel="stylesheet" href="semantic24/semantic.min.css">
		
		<%@include file="fragments/styles.html" %>
	</head>
	<body>
		<%
			request.logout();

			// NB invalidate the session after logging out otherwise WebLogic 12c NPEs
			HttpSession s = request.getSession(false);
			if (s != null) {
				s.invalidate();
			}

			// remove all cookies too
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					cookie.setValue("-");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		%>
		
		<div class="ui middle aligned center aligned grid">
		    <div class="column">
		    	<%@include file="fragments/logo.html" %>
		    	
		        <form class="ui large form">
		            <div class="ui segment">
		            	<div class="ui header">
		            		<%=Util.i18n("page.logout.banner", locale)%>
		            	</div>
						<% if ((referer == null) || referer.contains("/login")) { // no referer or came from the login page %>
							<a href="<%=Util.getSkyveContextUrl()%><%=Util.getHomeUri()%><%=(user == null) ? "" : (String.format("home?customer=%s", user.getCustomerName()))%>" class="ui fluid large blue submit button"><%=Util.i18n("page.login.submit.label", locale)%></a>
						<% } else { %>
							<a href="<%=referer%>" class="ui fluid large blue submit button"><%=Util.i18n("page.login.banner", locale)%></a>
						<% } %>
		            </div>
		        </form>
		    </div>
		</div>
	</body>
</html>
