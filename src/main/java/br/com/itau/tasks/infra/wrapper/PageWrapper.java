package br.com.itau.tasks.infra.wrapper;

import br.com.itau.tasks.infra.serializer.PageWrapperSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;

/**
 * This class is a value object for {@link PageWrapper}
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 02/07/2019
 */
@AllArgsConstructor
@JsonSerialize(using = PageWrapperSerializer.class)
public class PageWrapper<E> {
	
	private final Page<E> page;
	
	/**
	 * This method return the content of {@link Page}
	 *
	 * @return a page of E
	 */
	public List<E> getContent() {
		final List<E> content = page.getContent();
		return Collections.unmodifiableList(content);
	}
	
	/**
	 * This method return the value of current page of {@link Page}
	 *
	 * @return value of current page
	 */
	public Integer getCurrentPage() {
		return page.getNumber();
	}
	
	/**
	 * This method return the total of pages of current query
	 *
	 * @return value of total pages
	 */
	public Integer getTotalOfPages() {
		return page.getTotalPages();
	}
	
}
