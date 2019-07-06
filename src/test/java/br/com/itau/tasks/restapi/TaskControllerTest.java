package br.com.itau.tasks.restapi;

import br.com.itau.tasks.infra.AbstractIntegrationTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"test"})
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.JVM)
public class TaskControllerTest extends AbstractIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Sql(scripts = {"classpath:sqls/clear-database.sql", "classpath:sqls/data.sql"}, executionPhase = BEFORE_TEST_METHOD)
	public void givenTask_onPerformPostWithTaskInformation_shouldBe_persistAndReturnUpdatedTaskWithIdAndStatusCreated() throws Exception {
		final MvcResult mvcResult =
				mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(getJsonFileAsString("jsons/new_task.json")))
						.andExpect(status().isCreated()).andReturn();
		JSONAssert.assertEquals(getJsonFileAsString("expected/jsons/created_task.json"), getMvcResultAsString(mvcResult), false);
		verifyDatasetForTable("create_task.xml", "TSK.TASK", "SELECT ID, DESCRIPTION, STATUS, POINTS FROM TSK.TASK", new String[]{"CREATION_DATE"});
	}
	
	@Test
	@Sql(scripts = {"classpath:sqls/clear-database.sql", "classpath:sqls/data.sql"}, executionPhase = BEFORE_TEST_METHOD)
	public void givenTaskMissingParameters_onPerformPostWithTaskInformation_shouldBe_returnErrorMessageAndStatusBadRequest() throws Exception {
		final MvcResult mvcResult =
				mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON)
						.content(getJsonFileAsString("jsons/new_task_missing_parameter.json")))
						.andExpect(status().isBadRequest()).andReturn();
		JSONAssert.assertEquals(getJsonFileAsString("expected/jsons/error_missing_parameter.json"), getMvcResultAsString(mvcResult), false);
	}
	
	@Test
	@Sql(scripts = {"classpath:sqls/clear-database.sql", "classpath:sqls/data.sql"}, executionPhase = BEFORE_TEST_METHOD)
	public void givenTask_onPerformGetByIdTask_shouldBe_returnValidTaskInformationAndStatusOk() throws Exception {
		final MvcResult mvcResult = mockMvc.perform(get("/tasks/2")).andExpect(status().isOk()).andReturn();
		JSONAssert.assertEquals(getJsonFileAsString("expected/jsons/task.json"), getMvcResultAsString(mvcResult), true);
	}
	
	@Test
	@Sql(scripts = {"classpath:sqls/clear-database.sql", "classpath:sqls/data.sql"}, executionPhase = BEFORE_TEST_METHOD)
	public void givenNonexistentTaks_onPerformGetByIdTask_returnErrorMessageAndStatusPreConditionFailed() throws Exception {
		final MvcResult mvcResult = mockMvc.perform(get("/tasks/50")).andExpect(status().isPreconditionFailed()).andReturn();
		JSONAssert.assertEquals(getJsonFileAsString("expected/jsons/task_id_nonexistent.json"), getMvcResultAsString(mvcResult), true);
	}
	
	@Test
	@Sql(scripts = {"classpath:sqls/clear-database.sql", "classpath:sqls/data.sql"}, executionPhase = BEFORE_TEST_METHOD)
	public void givenTask_onPerformDeleteByIdTask_shouldBe_deleteFromDatasourceAndStatusNoContent() throws Exception {
		mockMvc.perform(delete("/tasks/1")).andExpect(status().isNoContent()).andReturn();
		
		final MvcResult mvcResult = mockMvc.perform(get("/tasks")).andExpect(status().isOk()).andReturn();
		JSONAssert.assertEquals(getJsonFileAsString("expected/jsons/tasks_after_delete.json"), getMvcResultAsString(mvcResult), false);
		verifyDatasetForTable("deleted_task.xml", "TSK.TASK", "SELECT ID, DESCRIPTION, STATUS, POINTS FROM TSK.TASK", new String[]{"CREATION_DATE"});
	}
	
	@Test
	@Sql(scripts = {"classpath:sqls/clear-database.sql", "classpath:sqls/data.sql"}, executionPhase = BEFORE_TEST_METHOD)
	public void givenTasks_onPerformGet_shouldBe_returnFirstPageOfQueryAndStatusOk() throws Exception {
		final MvcResult mvcResult = mockMvc.perform(get("/tasks")).andExpect(status().isOk()).andReturn();
		JSONAssert.assertEquals(getJsonFileAsString("expected/jsons/first_page_of_tasks.json"), getMvcResultAsString(mvcResult), false);
	}
	
	@Test
	@Sql(scripts = {"classpath:sqls/clear-database.sql", "classpath:sqls/data.sql"}, executionPhase = BEFORE_TEST_METHOD)
	public void givenInvalidPageOfTasks_onPerformGet_shouldBe_returnErrorMessageAndStatusPreConditionFailed() throws Exception {
		final MvcResult mvcResult = mockMvc.perform(get("/tasks?page=6")).andExpect(status().isPreconditionFailed()).andReturn();
		JSONAssert.assertEquals(getJsonFileAsString("expected/jsons/invalid_page_of_tasks.json"), getMvcResultAsString(mvcResult), true);
	}
	
	@Test
	@Sql(scripts = {"classpath:sqls/clear-database.sql", "classpath:sqls/data.sql"}, executionPhase = BEFORE_TEST_METHOD)
	public void givenTaskWithInvalidStatus_onPerformPut_shouldBe_returnErrorMessageAndStatusBadRequest() throws Exception {
		final MvcResult mvcResult = mockMvc.perform(put("/tasks/2").contentType(MediaType.APPLICATION_JSON)
				.content(getJsonFileAsString("jsons/task_to_change_invalid_status.json"))).andExpect(status().isBadRequest()).andReturn();
		JSONAssert.assertEquals(getJsonFileAsString("expected/jsons/invalid_status.json"), getMvcResultAsString(mvcResult), true);
	}
	
	@Test
	@Sql(scripts = {"classpath:sqls/clear-database.sql", "classpath:sqls/data.sql"}, executionPhase = BEFORE_TEST_METHOD)
	public void givenTaskWithInvalidStateToUpdate_onPerformPut_shouldBe_returnErrorMessageAndStatusPreConditionFailed() throws Exception {
		final MvcResult mvcResult = mockMvc.perform(put("/tasks/4").contentType(MediaType.APPLICATION_JSON)
				.content(getJsonFileAsString("jsons/task_to_change.json"))).andExpect(status().isPreconditionFailed()).andReturn();
		JSONAssert.assertEquals(getJsonFileAsString("expected/jsons/invalid_state.json"), getMvcResultAsString(mvcResult), true);
	}
	
	@Test
	@Sql(scripts = {"classpath:sqls/clear-database.sql", "classpath:sqls/data.sql"}, executionPhase = BEFORE_TEST_METHOD)
	public void givenTask_onPerformPut_shouldBe_returnUpdatedInformationAdnStatusOk() throws Exception {
		final MvcResult mvcResult = mockMvc.perform(put("/tasks/2").contentType(MediaType.APPLICATION_JSON)
				.content(getJsonFileAsString("jsons/task_to_change.json"))).andExpect(status().isOk()).andReturn();
		JSONAssert.assertEquals(getJsonFileAsString("expected/jsons/updated_task.json"), getMvcResultAsString(mvcResult), true);
		verifyDatasetForTable("updated_task.xml", "TSK.TASK", "SELECT ID, DESCRIPTION, STATUS, POINTS FROM TSK.TASK", new String[]{"CREATION_DATE"});
	}
	
}
