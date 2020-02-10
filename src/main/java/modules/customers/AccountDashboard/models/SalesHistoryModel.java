package modules.customers.AccountDashboard.models;

import org.skyve.CORE;
import org.skyve.domain.Bean;
import org.skyve.metadata.view.model.chart.ChartBuilder;
import org.skyve.metadata.view.model.chart.ChartData;
import org.skyve.metadata.view.model.chart.ChartModel;
import org.skyve.persistence.DocumentQuery;
import org.skyve.persistence.Persistence;
import org.skyve.persistence.DocumentQuery.AggregateFunction;
import org.skyve.util.Binder;

import modules.customers.AccountDashboard.AccountDashboardExtension;
import modules.sales.domain.Opportunity;

public class SalesHistoryModel extends ChartModel<AccountDashboardExtension> {

	private static final long serialVersionUID = 8435911631280873293L;

	@Override
	public ChartData getChartData() {
		Persistence persistence = CORE.getPersistence();
		DocumentQuery query = persistence.newDocumentQuery(Opportunity.MODULE_NAME, Opportunity.DOCUMENT_NAME);
		query.getFilter().addEquals(Binder.createCompoundBinding(Opportunity.accountPropertyName, Bean.DOCUMENT_ID), getBean().getAccount().getBizId());

		
		ChartBuilder cb = new ChartBuilder();
		cb.with(query);
		cb.category(Opportunity.topicPropertyName);
		cb.value(Opportunity.totalPropertyName, AggregateFunction.Sum);
		
		ChartData chartData = cb.build("Sales History","Total");
		return chartData;
	}

}
