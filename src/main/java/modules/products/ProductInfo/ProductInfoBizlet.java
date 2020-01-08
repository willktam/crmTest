package modules.products.ProductInfo;

import org.skyve.metadata.model.document.Bizlet;

import modules.admin.ModulesUtil;
import modules.products.domain.ProductInfo;

public class ProductInfoBizlet extends Bizlet<ProductInfo> {

	private static final long serialVersionUID = -6574005084185623684L;
	
	@Override
	public ProductInfo newInstance(ProductInfo bean) throws Exception {
		// 
		bean.setProductId(ModulesUtil.getNextDocumentNumber("PRO", ProductInfo.MODULE_NAME, ProductInfo.DOCUMENT_NAME, ProductInfo.productIdPropertyName, 8));
		return super.newInstance(bean);
	}

}
