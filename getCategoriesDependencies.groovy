import by.velcom.core.model.CollapsibleBlockModel
import de.hybris.platform.catalog.CatalogVersionService
import de.hybris.platform.catalog.model.CatalogVersionModel
import de.hybris.platform.category.model.CategoryModel
import de.hybris.platform.core.model.product.ProductModel
import de.hybris.platform.servicelayer.model.ModelService
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery
import de.hybris.platform.servicelayer.search.FlexibleSearchService
import org.apache.commons.collections.CollectionUtils

import static Constants.*

class Constants {
    static FlexibleSearchService flexibleSearchService
    static ModelService modelService
    static CatalogVersionService catalogVersionService
}

private void runScript() {
    StringBuilder dependencies = new StringBuilder()

    CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("velcomProductCatalog", "Online")

    List<CategoryModel> categories = findAllCategoriesFromCatalog(catalogVersionModel)
    for (CategoryModel categoryModel : categories) {
        String code = categoryModel.getCode()
        List<String> superCategoriesCodes = getSuperCategoriesCodes(categoryModel)
        for (String superCategoryCode : superCategoriesCodes) {
            dependencies.append("registerRelation(graph, ").append("\"").append(code).append("\", \"").append(superCategoryCode).append("\");").append("\r\n");
        }
    }

    println(dependencies)
}

private static List<String> getSuperCategoriesCodes(CategoryModel categoryModel) {
    List<CategoryModel> supercategories = categoryModel.getSupercategories()
    List<String> result = new ArrayList<>(supercategories.size())
    for (CategoryModel superCategory : supercategories) {
        result.add(superCategory.getCode())
    }
    return result;
}

private static List<CategoryModel> findAllCategoriesFromCatalog(CatalogVersionModel catalogVersionModel) {
    final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery("select {pk} from {Category} where {catalogVersion} = ?catalogVersion")
    flexibleSearchQuery.addQueryParameter("catalogVersion", catalogVersionModel);
    return flexibleSearchService.<CategoryModel> search(flexibleSearchQuery).getResult();
}

private void injectDependencies() {
    flexibleSearchService = spring.getBean('flexibleSearchService')
    modelService = spring.getBean('modelService')
    catalogVersionService = spring.getBean("catalogVersionService")
}

private void perform() {
    injectDependencies()
    runScript()
}


perform()