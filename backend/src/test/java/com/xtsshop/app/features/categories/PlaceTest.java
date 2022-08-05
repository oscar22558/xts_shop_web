package com.xtsshop.app.features.categories;

import com.xtsshop.app.DependencyTestConfig;
import com.xtsshop.app.LoadDatabaseTestConfig;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.repositories.CategoryJpaRepository;
import com.xtsshop.app.TestCase;
import com.xtsshop.app.features.categories.models.CategoryRequest;
import com.xtsshop.app.features.orders.data.FakeNewUserDataSet;
import com.xtsshop.app.features.orders.data.FakeOrderDataSetBuilder;
import com.xtsshop.app.features.orders.data.FakeOrderedItemDataSet;
import com.xtsshop.app.form.categories.CategoryForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PlaceTest extends TestCase {

	private String route = "/api/categories";
	@Autowired
	private CategoryJpaRepository repository;
	@Test
	void testCaseNormal() throws Exception {
		int count = repository.findAll().size();
		CategoryForm form = new CategoryForm("food", null);
		mvc
			.perform(requestBuilder(HttpMethod.POST, this.route)
					.content(mapper.writeValueAsString(form))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
			)
			.andDo(print())
			.andExpect(status().isCreated());
		assertEquals(1, repository.findAll().size() - count);
	}

	@Test
	void testCaseSubCategory() throws Exception {
		int count = repository.findAll().size();
		long parentId = repository.findAllTopLevel().get(0).getId();
		CategoryRequest form = new CategoryRequest("cake", parentId);
		mvc
				.perform(requestBuilder(HttpMethod.POST, route)
						.content(mapper.writeValueAsString(form))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
				)
				.andDo(print())
				.andExpect(status().isCreated());
		assertEquals(1, repository.findAll().size() - count);
		Category parent = repository.findAll().stream().max(Comparator.comparingInt(a -> (int) a.getId())).orElseThrow().getParent();
		assertEquals(parentId, parent.getId());
	}
}
