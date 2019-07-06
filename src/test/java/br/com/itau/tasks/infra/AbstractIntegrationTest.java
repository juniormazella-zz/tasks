package br.com.itau.tasks.infra;

import br.com.itau.tasks.infra.util.DefaultApplicationContextHolder;
import net.minidev.json.JSONValue;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.ext.h2.H2Connection;
import org.junit.After;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MvcResult;
import org.xml.sax.InputSource;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.dbunit.Assertion.assertEqualsByQuery;
import static org.junit.Assert.fail;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 02/07/2019
 */
public abstract class AbstractIntegrationTest {
	
	private static IDatabaseConnection databaseConnection;
	private final String SCHEMA = "TSK";
	
	protected IDataSet getDataSet(final String dataset) {
		try {
			final InputSource source = new InputSource(AbstractIntegrationTest.class.getResourceAsStream(dataset));
			final FlatXmlProducer producer = new FlatXmlProducer(source, false, true);
			return new FlatXmlDataSet(producer);
		} catch (final Exception exception) {
			throw new RuntimeException("Cannot read the dataset file " + dataset + "!", exception);
		}
	}
	
	@Before
	public void configureTest() throws SQLException, DatabaseUnitException {
		final Connection connection = DefaultApplicationContextHolder.getBean(JdbcTemplate.class).getDataSource().getConnection();
		databaseConnection = new H2Connection(connection, SCHEMA);
	}
	
	@After
	public void afterClass() throws SQLException {
		databaseConnection.close();
	}
	
	/**
	 * This method return {@link MvcResult} content as string
	 *
	 * @param mvcResult
	 * 		object of result call
	 *
	 * @return response content as {@link String}
	 *
	 * @throws UnsupportedEncodingException
	 * 		can be throw parsing content to string
	 */
	protected String getMvcResultAsString(final MvcResult mvcResult) throws UnsupportedEncodingException {
		return mvcResult.getResponse().getContentAsString();
	}
	
	/**
	 * This method return a file as string
	 *
	 * @param nameFile
	 * 		with folder starting without /
	 *
	 * @return file content as {@link String}
	 *
	 * @throws UnsupportedEncodingException
	 * 		can be throw parsing content to string
	 */
	protected String getJsonFileAsString(final String nameFile) throws UnsupportedEncodingException {
		final InputStream resourceAsStream = getClass().getResourceAsStream(String.format("/%s", nameFile));
		return JSONValue.parse(new InputStreamReader(resourceAsStream, "UTF-8")).toString();
	}
	
	protected void verifyDatasetForTable(final String fileName, final String tableName, final String query, final String[] ignoredColumns)
			throws Exception {
		assertEqualsByQuery(getDataSet("/expected/datasets/" + fileName), getConnection(), query, tableName,
				ignoredColumns);
	}
	
	private IDatabaseConnection getConnection() {
		return databaseConnection;
	}
	
	/**
	 * Metodo auxiliar para a construcao de arquivos de expectativa para DBUnit. Nao deletar esse metodo se nao estiver sendo utilizado, pois na
	 * realidade, ele deve ser temporario. Enquanto a classe {@link DBUnitExpectationBuilder} estiver sendo usada, os testes irao falhar.
	 *
	 * @return instancia da classe {@link DBUnitExpectationBuilder}
	 */
	protected DBUnitExpectationBuilder getDBUnitExpectationBuilder(final String fileName) {
		return new DBUnitExpectationBuilder(fileName);
	}
	
	/**
	 * A classe {@link DBUnitExpectationBuilder} e um utilitario para auxiliar na geracao do arquivo de expectativa para o DBUnit.
	 *
	 * @author Luiz Azevedo
	 * @version 1.0 25/08/2017
	 */
	protected class DBUnitExpectationBuilder {
		
		private final Map<String, List<String>> mappedQueries = new HashMap<>();
		private final String fileName;
		
		private DBUnitExpectationBuilder(final String fileName) {
			this.fileName = fileName;
		}
		
		public DBUnitExpectationBuilder addExpectation(final String tableName, final String query) {
			if (mappedQueries.containsKey(tableName)) {
				mappedQueries.get(tableName).add(query);
			} else {
				mappedQueries.put(tableName, Collections.singletonList(query));
			}
			return this;
		}
		
		public void build() throws Exception {
			final QueryDataSet partialDataSet = new QueryDataSet(getConnection());
			for (final Map.Entry<String, List<String>> entry : mappedQueries.entrySet()) {
				for (final String query : entry.getValue()) {
					partialDataSet.addTable(entry.getKey(), query);
				}
			}
			FlatXmlDataSet.write(partialDataSet, new FileOutputStream(fileName));
			fail("Esse e um metodo temporario, para a geracao de um arquivo de expectativa. Portanto, para que um teste de integracao passe, a escrita do arquivo create_task.xml deve ser removida");
		}
		
	}
	
}
