package modules.customers.AccountDashboard;

import java.util.stream.Collectors;

import org.skyve.CORE;
import org.skyve.domain.Bean;
import org.skyve.domain.types.DateTime;
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

	public void setDates() {
		if (getRecentOpportunity() != null) {
			setDateOpportunity(lastCreated(Opportunity.DOCUMENT_NAME).getInteractionTime());
		}
		if (getRecentQuote() != null) {
			setDateQuote(lastCreated(Quote.DOCUMENT_NAME).getInteractionTime());
		}
		if (getRecentOrder() != null) {
			setDateOrder(lastCreated(Order.DOCUMENT_NAME).getInteractionTime());
		}
		if (getRecentInvoice() != null) {
			setDateInvoice(lastCreated(Invoice.DOCUMENT_NAME).getInteractionTime());
		}
	}
	
	public void setNumbers() {
		setNoOpportunity(numberDocuments(Opportunity.MODULE_NAME, Opportunity.DOCUMENT_NAME));
		setNoQuote(numberDocuments(Quote.MODULE_NAME, Quote.DOCUMENT_NAME));
		setNoOrder(numberDocuments(Order.MODULE_NAME, Order.DOCUMENT_NAME));
		setNoInvoice(numberDocuments(Invoice.MODULE_NAME, Invoice.DOCUMENT_NAME));
	}
	
	// returns number of documents for this account
	public int numberDocuments(String moduleName, String documentName) {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(moduleName, documentName);
		switch (documentName) {
		case "Opportunity":
			query.getFilter().addEquals(Binder.createCompoundBinding(Opportunity.accountPropertyName, Bean.DOCUMENT_ID), this.getAccount().getBizId());
			break;
		case "Quote":
			query.getFilter().addEquals(Binder.createCompoundBinding(Quote.accountPropertyName, Bean.DOCUMENT_ID), this.getAccount().getBizId());
			break;
		case "Order":
			query.getFilter().addEquals(Binder.createCompoundBinding(Order.accountPropertyName, Bean.DOCUMENT_ID), this.getAccount().getBizId());
			break;
		case "Invoice":
			query.getFilter().addEquals(Binder.createCompoundBinding(Invoice.accountPropertyName, Bean.DOCUMENT_ID), this.getAccount().getBizId());
			break;
		default:
			break;
		}
		return query.beanResults().size();
	}

	// returns the last interaction made for a document for this account
	public Interaction lastCreated(String documentName) {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(Interaction.MODULE_NAME, Interaction.DOCUMENT_NAME);
		query.getFilter().addIn(Interaction.DOCUMENT_ID, getAccount().getInteractions().stream()
				.map(i -> i.getBizId())
				.collect(Collectors.toList()));
		query.getFilter().addEquals(Interaction.typePropertyName, "Other");
		query.getFilter().addEquals(Interaction.titlePropertyName, "Created New " + documentName);
		query.addBoundOrdering(Interaction.interactionTimePropertyName, SortDirection.descending);
		return query.beanResult();
	}
	
	// returns the last auto update interaction
	public Interaction lastUpdated() {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(Interaction.MODULE_NAME, Interaction.DOCUMENT_NAME);
		query.getFilter().addIn(Interaction.DOCUMENT_ID, getAccount().getInteractions().stream()
				.map(i -> i.getBizId())
				.collect(Collectors.toList()));
		
		query.getFilter().addNotEquals(Interaction.typePropertyName, "Other");
		query.addBoundOrdering(Interaction.interactionTimePropertyName, SortDirection.descending);
		return query.beanResult();
	}
	
	// returns the days since an interaction was made
	public long daysSinceInteraction(Interaction interaction) {
		DateTime latest = interaction.getInteractionTime();
		DateTime current = new DateTime();
		return (current.getTime() - latest.getTime())/(24 * 60 * 60 * 1000);
	}
	
	// returns the most recently edited lead
	public LeadExtension getRecentLead() {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(Lead.MODULE_NAME, Lead.DOCUMENT_NAME);
		query.getFilter().addEquals(Binder.createCompoundBinding(Lead.contactDetailsPropertyName, Bean.DOCUMENT_ID), this.getAccount().getPrimaryContact().getBizId());
		query.addBoundOrdering(Lead.LOCK_NAME, SortDirection.descending);
		return query.beanResult();
	}

	// returns the most recently edited account
	public AccountExtension getRecentAccount() {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(Account.MODULE_NAME, Account.DOCUMENT_NAME);
		query.addBoundOrdering(Account.LOCK_NAME, SortDirection.descending);
		return query.beanResult();
	}
	
	// returns the most recently edited opportunity
	public OpportunityExtension getRecentOpportunity() {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(Opportunity.MODULE_NAME, Opportunity.DOCUMENT_NAME);
		query.getFilter().addEquals(Binder.createCompoundBinding(Opportunity.accountPropertyName, Bean.DOCUMENT_ID), this.getAccount().getBizId());
		query.addBoundOrdering(Opportunity.LOCK_NAME, SortDirection.descending);
		return query.beanResult();
	}
	
	// returns the most recently edited quote
	public QuoteExtension getRecentQuote() {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(Quote.MODULE_NAME, Quote.DOCUMENT_NAME);
		query.getFilter().addEquals(Binder.createCompoundBinding(Quote.accountPropertyName, Bean.DOCUMENT_ID), this.getAccount().getBizId());
		query.addBoundOrdering(Quote.LOCK_NAME, SortDirection.descending);
		return query.beanResult();		
	}
	
	// returns the most recently edited order
	public OrderExtension getRecentOrder() {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(Order.MODULE_NAME, Order.DOCUMENT_NAME);
		query.getFilter().addEquals(Binder.createCompoundBinding(Order.accountPropertyName, Bean.DOCUMENT_ID), this.getAccount().getBizId());
		query.addBoundOrdering(Order.LOCK_NAME, SortDirection.descending);
		return query.beanResult();	
	}
	
	// returns the most recently edited invoice
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
		String contactClass, leadClass, accountClass, opportunityClass, quoteClass, orderClass, invoiceClass;
		contactClass = leadClass = accountClass = opportunityClass = quoteClass = orderClass = invoiceClass = "notCurrent";
		
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
		markup.append("<li class=" + contactClass + " onclick=\"location.href='"+ contactUrl + "';\"> "
				+ "Contact Details </li>");
		markup.append("<li class=" + leadClass + " onclick=\"location.href='"+ leadUrl + "';\"> Lead </li>");
		markup.append("<li class=" + accountClass + " onclick=\"location.href='"+ accountUrl + "';\"> "
				+ "Account </li>");
		markup.append("<li class=" + opportunityClass + " onclick=\"location.href='"+ opportunityUrl + "';\">"
				+ " Opportunity </li>");
		markup.append("<li class=" + quoteClass + " onclick=\"location.href='"+ quoteUrl + "';\"> Quote </li>");
		markup.append("<li class=" + orderClass + " onclick=\"location.href='"+ orderUrl + "';\"> Order </li>");
		markup.append("<li class=" + invoiceClass + " onclick=\"location.href='"+ invoiceUrl + "';\"> "
				+ "Invoice </li>");
		markup.append("</ul></div>");
		
		return markup.toString();
	}
	
	@Override
	public String getActionTemplate() {
		StringBuilder markup = new StringBuilder();
		
		if (lastUpdated() != null && daysSinceInteraction(lastUpdated()) > 14 ) {
			markup.append(makeCommunicationTemplate(getAccount().getAccountName()));
		}
		if (getRecentOpportunity() == null) {
			markup.append(makeNewTemplate(Opportunity.DOCUMENT_NAME));
		}
		if (getRecentQuote() == null) {
			markup.append(makeNewTemplate(Quote.DOCUMENT_NAME));
		}
		if (getRecentOrder() == null) {
			markup.append(makeNewTemplate(Order.DOCUMENT_NAME));
		}
		if (getRecentInvoice() == null) {
			markup.append(makeNewTemplate(Invoice.DOCUMENT_NAME));
		}
		
		if (getRecentOpportunity() != null) {
			if (daysSinceInteraction(lastCreated(Opportunity.DOCUMENT_NAME)) > 30) {
				markup.append(makeReuseTemplate(Opportunity.DOCUMENT_NAME));
			}
			if (getRecentQuote() != null) {
				if (daysSinceInteraction(lastCreated(Quote.DOCUMENT_NAME)) > 30) {
					markup.append(makeReuseTemplate(Quote.DOCUMENT_NAME));
				}
				if (getRecentOrder() != null) {
					if (daysSinceInteraction(lastCreated(Order.DOCUMENT_NAME)) > 30) {
						markup.append(makeReuseTemplate(Order.DOCUMENT_NAME));
					}
					if (getRecentInvoice() != null) {
						if (daysSinceInteraction(lastCreated(Invoice.DOCUMENT_NAME)) > 30) {
							markup.append(makeReuseTemplate(Invoice.DOCUMENT_NAME));
						}
						else {
							markup.append(makeNoTemplate());
						}
					}
				}
			}
		}		
		return markup.toString();
	}
	
	// helper method to return the communication action markup for an account name
	public String makeCommunicationTemplate(String accountName) {
		StringBuilder markup = new StringBuilder();
		markup.append("<div class='actionContainer'>");
		markup.append("<div class='actionIcon'>");
		markup.append("<span class='fa fa-plus'></span></div>");
		markup.append("<div class='actionInfo'>");    
		markup.append("<span class='actionTitle'>Make New Communication</span>");
		markup.append("<span></br><p class='updateDescription'> You haven't recorded "
				+ "an interaction with " + accountName + " for over two weeks, make a new communication "
				+ "and record it as an interaction. </p>");    
		markup.append("</div></div>");
		return markup.toString();
	}
	
	// helper method to return the new action markup for a document name
	public String makeNewTemplate(String documentName) {
		String vowels = "aeiou";
		StringBuilder markup = new StringBuilder();
		markup.append("<div class='actionContainer' onclick=\"location.href='"+ makeActionLink(documentName) + "';\">");
		markup.append("<div class='actionIcon'>");
		markup.append("<span class='" + makeIcon(documentName) + "'></span></div>");
		markup.append("<div class='actionInfo'>");    
		markup.append("<span class='actionTitle'>Make New "+ documentName +"</span>");
		if (vowels.indexOf(Character.toLowerCase(documentName.charAt(0))) != -1 ) {
			markup.append("<span></br><p class='actionDescription'> This account does not yet have an "+ 
						documentName +", click here to make a new " + documentName + ".</p>");  
		}
		else {
			markup.append("<span></br><p class='actionDescription'> This account does not yet have a "+
						documentName +", click here to make a new " + documentName + ".</p>");  
		}
		markup.append("</div></div>");
		return markup.toString();
	}
	
	// helper method to return the reuse action markup for a document name
	public String makeReuseTemplate(String documentName) {
		StringBuilder markup = new StringBuilder();
		markup.append("<div class='actionContainer'>");
		markup.append("<div class='actionIcon'>");
		markup.append("<span class='" + makeIcon(documentName) + "'></span></div>");
		markup.append("<div class='actionInfo'>");    
		markup.append("<span class='actionTitle'>Create A New "+ documentName +"</span>");
		markup.append("<span></br><p class='actionDescription'>You have not created a new "+ documentName +""
							+ " for "+ getAccount().getAccountName() +" for over a month, create a new "+ documentName +""
							+ "and save it to this account.</p>");  
		markup.append("</div></div>");
		return markup.toString();
	}	
	
	//helper method to return no action markup
	public String makeNoTemplate() {
		StringBuilder markup = new StringBuilder();
		markup.append("<div class='actionContainer'>");
		markup.append("<div class='actionIcon'>");
		markup.append("<span class='fa fa-info-circle'></span></div>");
		markup.append("<div class='actionInfo'>");    
		markup.append("<span class='actionTitle'>No Suggested Actions</span>");
		markup.append("<span></br><p class='actionDescription'>There are no suggested actions for " + 
							getAccount().getAccountName() + " at this time.</p>");  
		markup.append("</div></div>");
		return markup.toString();
	}
	
	// helper method to return an icon for a document
	public String makeIcon(String documentName) {
		String icon = "fa fa-info-circle";
		switch (documentName) {
		case "ContactDetail":
			icon = "fa fa-users";
			break;
		case "Lead":
			icon = "fa fa-phone-square";
			break;
		case "Account":
			icon = "fa fa-book";
			break;
		case "Opportunity":
			icon = "fa fa-fax";
			break;
		case "Quote":
			icon = "fa fa-files-o";
			break;
		case "Order":
			icon = "fa fa-file-text";
			break;
		case "Invoice":
			icon = "fa fa-envelope";
			break;
		default:
			break;
		}
		return icon;
	}
	
	// helper function to return action link
	public String makeActionLink(String documentName) {
		String link = "";
		switch (documentName) {
		case "ContactDetail":
			link = Util.getDocumentUrl(ContactDetail.MODULE_NAME, ContactDetail.DOCUMENT_NAME);
			break;
		case "Lead": 
			link = Util.getDocumentUrl(Lead.MODULE_NAME, Lead.DOCUMENT_NAME);
			break;
		case "Account":
			link = Util.getDocumentUrl(Account.MODULE_NAME, Account.DOCUMENT_NAME);
			break;
		case "Opportunity":
			link = Util.getDocumentUrl(Opportunity.MODULE_NAME, Opportunity.DOCUMENT_NAME);
			break;
		case "Quote":
			link = Util.getDocumentUrl(Quote.MODULE_NAME, Quote.DOCUMENT_NAME);
			break;
		case "Order":
			link = Util.getDocumentUrl(Order.MODULE_NAME, Order.DOCUMENT_NAME);
			break;
		case "Invoice":
			link = Util.getDocumentUrl(Invoice.MODULE_NAME, Invoice.DOCUMENT_NAME);
			break;
		default:
			break;
		}
		return link;
	}
}
