package modules.customers.AccountDashboard.models;

import java.util.stream.Collectors;

import org.skyve.CORE;
import org.skyve.metadata.SortDirection;
import org.skyve.metadata.view.model.chart.ChartBuilder;
import org.skyve.metadata.view.model.chart.ChartData;
import org.skyve.metadata.view.model.chart.ChartModel;
import org.skyve.metadata.view.model.chart.OrderBy;
import org.skyve.metadata.view.model.chart.TemporalBucket;
import org.skyve.metadata.view.model.chart.TemporalBucket.TemporalBucketType;
import org.skyve.persistence.DocumentQuery;
import org.skyve.persistence.DocumentQuery.AggregateFunction;
import org.skyve.persistence.Persistence;
import modules.customers.AccountDashboard.AccountDashboardExtension;
import modules.customers.domain.Interaction;

public class RecentActivityModel extends ChartModel<AccountDashboardExtension> {

	private static final long serialVersionUID = -1576341368775206965L;

	@Override
	public ChartData getChartData() {
		
		Persistence persistence = CORE.getPersistence();
		
		DocumentQuery query = persistence.newDocumentQuery(Interaction.MODULE_NAME, Interaction.DOCUMENT_NAME);
		query.getFilter().addIn(Interaction.DOCUMENT_ID, getBean().getAccount().getInteractions().stream()
				.map(i -> i.getBizId())
				.collect(Collectors.toList()));
		
		ChartBuilder cb = new ChartBuilder();
		
		cb.with(query);
		cb.category(Interaction.interactionTimePropertyName, new TemporalBucket(TemporalBucketType.dayMonthYear));
		cb.value(Interaction.DOCUMENT_ID, AggregateFunction.Count);
		cb.top(14, OrderBy.category, SortDirection.descending, false);
		cb.orderBy(OrderBy.category, SortDirection.ascending);
		
		ChartData chartData = cb.build("Recent Activity","Number of Interactions");
		return chartData;
	}

}
