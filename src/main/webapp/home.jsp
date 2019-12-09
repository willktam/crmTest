<%@ page language="java"%>
<%@ page import="java.security.Principal"%>
<%@ page import="javax.servlet.http.Cookie"%>
<%@ page import="org.skyve.CORE"%>
<%@ page import="org.skyve.metadata.user.User"%>
<%@ page import="org.skyve.metadata.repository.Repository"%>
<%@ page import="org.skyve.metadata.router.UxUi"%>
<%@ page import="org.skyve.metadata.router.UxUiSelector"%>
<%@ page import="org.skyve.metadata.view.View.ViewType"%>
<%@ page import="org.skyve.util.Util"%>
<%@ page import="org.skyve.web.WebAction"%>
<%@ page import="org.skyve.web.WebContext"%>
<%@ page import="org.skyve.impl.metadata.repository.router.Router"%>
<%@ page import="org.skyve.impl.metadata.repository.router.RouteCriteria"%>
<%@ page import="org.skyve.impl.persistence.AbstractPersistence"%>
<%@ page import="org.skyve.impl.web.AbstractWebContext"%>
<%@ page import="org.skyve.impl.web.UserAgent"%>
<%@ page import="org.skyve.impl.web.UserAgentType"%>
<%@ page import="org.skyve.impl.web.WebUtil"%>
<%@ page import="org.skyve.impl.web.faces.FacesUtil"%>
<%@ page import="org.skyve.impl.util.SQLMetaDataUtil"%>
<%@ page import="org.skyve.impl.util.UtilImpl"%>

<%
	// Stop cookie/request header injection by checking the customer name
	Repository repository = CORE.getRepository();
	String customerName = request.getParameter(AbstractWebContext.CUSTOMER_COOKIE_NAME);
	if (customerName != null) {
		// This will throw if the customerName value ain't a customer name
		try {
			repository.getCustomer(customerName);
		}
		catch (Exception e) {
			customerName = null;
		}
	}
	Cookie cookie = new Cookie(AbstractWebContext.CUSTOMER_COOKIE_NAME, 
								(customerName == null) ? "" : customerName);
	cookie.setPath("/");
	cookie.setHttpOnly(true);
	cookie.setSecure(Util.isSecureUrl());
	if (customerName == null) {
		cookie.setMaxAge(0); // remove the cookie
	}
	response.addCookie(cookie);

	String a = request.getParameter("a"); // action
	WebAction webAction = null;
	if (a != null) {
		webAction = WebAction.valueOf(a);
	}
	String m = request.getParameter("m"); // module
	String d = request.getParameter("d"); // document
	String q = request.getParameter("q"); // query
	String i = request.getParameter("i"); // id
	String c = request.getParameter("c"); // customer

	// Set the UX/UI and user agent type
	UserAgentType userAgentType = UserAgent.getType(request);
	request.setAttribute(FacesUtil.USER_AGENT_TYPE_KEY, userAgentType);
	Router router = repository.getRouter();

	RouteCriteria criteria = new RouteCriteria();
	criteria.setDocumentName(d);
	criteria.setModuleName(m);
	criteria.setQueryName(q);
	criteria.setWebAction(webAction);
	if (WebAction.e.equals(webAction)) { // editing
		criteria.setViewType((i == null) ? ViewType.create : ViewType.edit);
	}
	
	// If we make it here without a principal, then we should either continue to a public unsecured page or login.
	String userName = null;
	Principal userPrincipal = request.getUserPrincipal();
	if (userPrincipal == null) {
		customerName = (UtilImpl.CUSTOMER == null) ? c : UtilImpl.CUSTOMER;
		criteria.setCustomerName(customerName);
		if (router.isUnsecured(criteria)) {
			if (customerName == null) {
				throw new IllegalStateException("Malformed URL - this URL must have a 'c' parameter");
			}
			else {
				// This will throw if the customerName value ain't a customer name
				try {
					repository.getCustomer(customerName);
				}
				catch (Exception e) {
					throw new IllegalStateException("Malformed URL - this URL must have a 'c' parameter with a valid customer");
				}
			}
			userName = SQLMetaDataUtil.retrievePublicUserName(customerName);
			if (userName == null) {
				response.sendRedirect(response.encodeRedirectURL(Util.getHomeUrl() + "pages/noPublicUser.jsp"));
				return;
			}
			userName = customerName + "/" + userName;
		}
		else {
			response.sendRedirect(response.encodeRedirectURL(Util.getHomeUrl() + "login"));
			return;
		}
	}
	else if (customerName != null) {
		response.sendRedirect(response.encodeRedirectURL(Util.getHomeUrl()));
	}
	else {
		userName = userPrincipal.getName();
	}
	
	if (userName != null) { // we have a logged in user or at least 1 to assert
		// Get the user
		User user = (User) request.getSession().getAttribute(WebContext.USER_SESSION_ATTRIBUTE_NAME);
		if (user == null) { // if the user is not established yet (but we've logged in or have a public user...)
			AbstractPersistence persistence = AbstractPersistence.get();
			try {
				persistence.begin();
				user = WebUtil.processUserPrincipalForRequest(request, userName, true);
			}
			finally {
				if (persistence != null) {
					persistence.commit(true);
				}
			}
		}

		if ((user != null) && user.isPasswordChangeRequired()) {
			response.sendRedirect(response.encodeRedirectURL(Util.getHomeUrl() + "pages/changePassword.jsp"));
			return;
		}
		
		// Determine the UX/UI
		UxUi uxui = ((UxUiSelector) router.getUxuiSelector()).select(userAgentType, request);
		request.setAttribute(AbstractWebContext.UXUI, uxui);

		// Determine the route
		criteria.setCustomerName(user.getCustomerName());
		criteria.setDataGroupId(user.getDataGroupId());
		criteria.setUserId(user.getId());

		String outcomeUrl = router.selectOutcomeUrl(uxui.getName(), criteria);
		if (UtilImpl.COMMAND_TRACE) {
			UtilImpl.LOGGER.info(String.format("home.jsp - Route uxui=%s,c=%s,dg=%s,d=%s,m=%s,q=%s,a=%s to %s",
													uxui.getName(),
													criteria.getCustomerName(),
													criteria.getDataGroupId(),
													criteria.getDocumentName(),
													criteria.getModuleName(),
													criteria.getQueryName(),
													a,
													outcomeUrl));
		}
		if (outcomeUrl == null) {
			UtilImpl.LOGGER.severe("The route criteria " + criteria + " for uxui " + uxui + " did not produce an outcome URL");
			throw new ServletException("The route criteria " + criteria + " for uxui " + uxui + " did not produce an outcome URL");
		}
			
		// forward
		request.getRequestDispatcher(outcomeUrl).forward(request, response);
	}
%>
