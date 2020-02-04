package modules.customers.AccountDashboard;

import org.skyve.CORE;
import org.skyve.domain.Bean;
import org.skyve.metadata.SortDirection;
import org.skyve.persistence.DocumentQuery;
import org.skyve.persistence.Persistence;
import org.skyve.util.Binder;
import org.skyve.util.Util;

import modules.customers.Account.AccountExtension;
import modules.customers.Interaction.InteractionExtension;
import modules.customers.domain.Account;
import modules.customers.domain.AccountDashboard;
import modules.customers.domain.ContactDetail;
import modules.customers.domain.Interaction;
import modules.customers.domain.Interaction.Type;
import modules.sales.Lead.LeadExtension;
import modules.sales.Opportunity.OpportunityExtension;
import modules.sales.Order.OrderExtension;
import modules.sales.Quote.QuoteExtension;
import modules.sales.domain.Invoice;
import modules.sales.domain.Lead;
import modules.sales.domain.Opportunity;
import modules.sales.domain.Order;
import modules.sales.domain.Quote;

public class AccountDashboardExtension extends AccountDashboard {

	private static final long serialVersionUID = 2026179169670513211L;
	
	public LeadExtension getRecentLead() {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(Lead.MODULE_NAME, Lead.DOCUMENT_NAME);
		query.getFilter().addEquals(Binder.createCompoundBinding(Lead.contactDetailsPropertyName, Bean.DOCUMENT_ID), this.getAccount().getPrimaryContact().getBizId());
		query.addBoundOrdering(Lead.LOCK_NAME, SortDirection.descending);
		return query.beanResult();
	}

	public AccountExtension getRecentAccount() {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(Account.MODULE_NAME, Account.DOCUMENT_NAME);
		query.addBoundOrdering(Account.LOCK_NAME, SortDirection.descending);
		return query.beanResult();
	}
	
	public OpportunityExtension getRecentOpportunity() {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(Opportunity.MODULE_NAME, Opportunity.DOCUMENT_NAME);
		query.getFilter().addEquals(Binder.createCompoundBinding(Opportunity.accountPropertyName, Bean.DOCUMENT_ID), this.getAccount().getBizId());
		query.addBoundOrdering(Opportunity.LOCK_NAME, SortDirection.descending);
		return query.beanResult();
	}
	
	
	public QuoteExtension getRecentQuote() {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(Quote.MODULE_NAME, Quote.DOCUMENT_NAME);
		query.getFilter().addEquals(Binder.createCompoundBinding(Quote.accountPropertyName, Bean.DOCUMENT_ID), this.getAccount().getBizId());
		query.addBoundOrdering(Quote.LOCK_NAME, SortDirection.descending);
		return query.beanResult();		
	}
	
	
	public OrderExtension getRecentOrder() {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(Order.MODULE_NAME, Order.DOCUMENT_NAME);
		query.getFilter().addEquals(Binder.createCompoundBinding(Order.accountPropertyName, Bean.DOCUMENT_ID), this.getAccount().getBizId());
		query.addBoundOrdering(Order.LOCK_NAME, SortDirection.descending);
		
		return query.beanResult();	
	}
	
	
	public Invoice getRecentInvoice() {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(Invoice.MODULE_NAME, Invoice.DOCUMENT_NAME);
		query.getFilter().addEquals(Binder.createCompoundBinding(Invoice.accountPropertyName, Bean.DOCUMENT_ID), this.getAccount().getBizId());
		query.addBoundOrdering(Invoice.LOCK_NAME, SortDirection.descending);
		
		return query.beanResult();
	}
	
	public void createInteraction(final Type type, final String description) {
		InteractionExtension interaction = Interaction.newInstance();
		interaction.setTitle(String.format("New %s", type.toDescription()));
		interaction.setType(type);
		interaction.setDescription(description);
		getAccount().getInteractions().add(interaction);
	}
	
	
	@Override
	public String getFlowbar() {
		String contactClass = "notCurrent";
		String leadClass = "notCurrent";
		String accountClass = "notCurrent";
		String opportunityClass = "notCurrent";
		String quoteClass = "notCurrent";
		String orderClass = "notCurrent";
		String invoiceClass = "notCurrent";
		
		String contactUrl = Util.getDocumentUrl(ContactDetail.MODULE_NAME, ContactDetail.DOCUMENT_NAME);
		String leadUrl = Util.getDocumentUrl(Lead.MODULE_NAME, Lead.DOCUMENT_NAME);
		String accountUrl = Util.getDocumentUrl(Account.MODULE_NAME, Account.DOCUMENT_NAME);
		String opportunityUrl = Util.getDocumentUrl(Opportunity.MODULE_NAME, Opportunity.DOCUMENT_NAME);
		String quoteUrl = Util.getDocumentUrl(Quote.MODULE_NAME, Quote.DOCUMENT_NAME);
		String orderUrl = Util.getDocumentUrl(Order.MODULE_NAME, Order.DOCUMENT_NAME);
		String invoiceUrl = Util.getDocumentUrl(Invoice.MODULE_NAME, Invoice.DOCUMENT_NAME);
		
		
		
		if (getAccount().getPrimaryContact() != null) {
			contactUrl = Util.getDocumentUrl(ContactDetail.MODULE_NAME, ContactDetail.DOCUMENT_NAME, getAccount().getPrimaryContact().getBizId());
			contactClass = "current";
		}
		if (getRecentLead() != null) {
			leadUrl = Util.getDocumentUrl(Lead.MODULE_NAME, Lead.DOCUMENT_NAME, getRecentLead().getBizId());
			leadClass = "current";
		}
		if (getRecentAccount() != null) {
			accountUrl = Util.getDocumentUrl(Account.MODULE_NAME, Account.DOCUMENT_NAME, getAccount().getBizId());
			accountClass = "current";
			
			if (getRecentOpportunity() != null) {
				opportunityUrl = Util.getDocumentUrl(Opportunity.MODULE_NAME, Opportunity.DOCUMENT_NAME, getRecentOpportunity().getBizId());
				opportunityClass = "current";
			}
			if (getRecentQuote() != null) {
				quoteUrl = Util.getDocumentUrl(Quote.MODULE_NAME, Quote.DOCUMENT_NAME, getRecentQuote().getBizId());
				quoteClass = "current";
			}
			if (getRecentOrder() != null) {
				orderUrl = Util.getDocumentUrl(Order.MODULE_NAME, Order.DOCUMENT_NAME, getRecentOrder().getBizId());
				orderClass = "current";
			}
			if (getRecentInvoice() != null) {
				invoiceUrl = Util.getDocumentUrl(Invoice.MODULE_NAME, Invoice.DOCUMENT_NAME, getRecentInvoice().getBizId());
				invoiceClass = "current";
			}
		}

		
		
		StringBuilder markup = new StringBuilder();
		markup.append("<div class=\"flowbar-wrapper\">");
		markup.append("<ul class=\"flowbar\">");
		markup.append("<li class=" + contactClass + " onclick=\"location.href='"+ contactUrl + "';\"> Contact Details </li>");
		markup.append("<li class=" + leadClass + " onclick=\"location.href='"+ leadUrl + "';\"> Lead </li>");
		markup.append("<li class=" + accountClass + " onclick=\"location.href='"+ accountUrl + "';\"> Account </li>");
		markup.append("<li class=" + opportunityClass + " onclick=\"location.href='"+ opportunityUrl + "';\"> Opportunity </li>");
		markup.append("<li class=" + quoteClass + " onclick=\"location.href='"+ quoteUrl + "';\"> Quote </li>");
		markup.append("<li class=" + orderClass + " onclick=\"location.href='"+ orderUrl + "';\"> Order </li>");
		markup.append("<li class=" + invoiceClass + " onclick=\"location.href='"+ invoiceUrl + "';\"> Invoice </li>");
		markup.append("</ul></div>");
		
		return markup.toString();
	}
	
}
