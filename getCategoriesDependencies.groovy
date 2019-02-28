import by.velcom.core.model.CollapsibleBlockModel
import de.hybris.platform.catalog.CatalogVersionService
import de.hybris.platform.catalog.model.CatalogVersionModel
import de.hybris.platform.category.model.CategoryModel
import de.hybris.platform.core.model.product.ProductModel
import de.hybris.platform.servicelayer.model.ModelService
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery
import de.hybris.platform.servicelayer.search.FlexibleSearchService
import org.apache.commons.collections.CollectionUtils
import org.apache.commons.lang.StringUtils

import static Constants.*
import static Constants.flexibleSearchService
import static Constants.modelService

class Constants {
    static FlexibleSearchService flexibleSearchService
    static ModelService modelService
    static CatalogVersionService catalogVersionService
}

private void runScript() {
    StringBuilder dependencies = new StringBuilder()

    Collection<CategoryModel> categories = findAllCategoriesFromCatalog()
//    Collection<CategoryModel> categories = findAllCategoriesFromCatalog(Arrays.asList("b2ctariffs", "b2btariffs"))
    for (CategoryModel categoryModel : categories) {
        registerSuperCategories(categoryModel, dependencies)
        registerSubCategories(categoryModel, dependencies)
    }

    println(dependencies)
}

private void registerSubCategories(CategoryModel categoryModel, StringBuilder dependencies) {
    def subcategories = categoryModel.getCategories()
    for (CategoryModel subCategory : subcategories) {
        addRegisterStatement(dependencies, categoryModel.getCode(), subCategory.getCode());
        registerSubCategories(subCategory, dependencies)
    }
}

private void registerSuperCategories(CategoryModel categoryModel, StringBuilder dependencies) {
    def supercategories = categoryModel.getSupercategories()
    for (CategoryModel superCategory : supercategories) {
        addRegisterStatement(dependencies, superCategory.getCode(), categoryModel.getCode());
        registerSuperCategories(superCategory, dependencies)
    }
}

private StringBuilder addRegisterStatement(StringBuilder dependencies, String code, String superCategoryCode) {
    dependencies.append("registerRelation(graph, ").append("\"").append(code).append("\", \"").append(superCategoryCode).append("\");").append("\r\n")
}

private static List<String> subCategoriesCodes(CategoryModel categoryModel) {
    Collection<CategoryModel> subCategories = categoryModel.getAllSubcategories()
    List<String> result = new ArrayList<>(subCategories.size())
    for (CategoryModel subCategory : subCategories) {
        result.add(subCategory.getCode())
    }
    return result;
}

private static List<String> getSuperCategoriesCodes(CategoryModel categoryModel) {
    Collection<CategoryModel> supercategories = categoryModel.getAllSupercategories()
    List<String> result = new ArrayList<>(supercategories.size())
    for (CategoryModel superCategory : supercategories) {
        result.add(superCategory.getCode())
    }
    return result;
}

private static Collection<CategoryModel> findAllCategoriesFromCatalog(List<String> categoriesInScope = null) {
    FlexibleSearchQuery flexibleSearchQuery
    if (CollectionUtils.isNotEmpty(categoriesInScope)) {
        flexibleSearchQuery = new FlexibleSearchQuery("select {pk} from {Category} where {code} in (?codes)")
        flexibleSearchQuery.addQueryParameter("codes", categoriesInScope)
    } else {
        flexibleSearchQuery = new FlexibleSearchQuery("select {pk} from {Category}")
    }
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