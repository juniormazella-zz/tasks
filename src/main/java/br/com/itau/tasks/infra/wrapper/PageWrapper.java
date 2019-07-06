package br.com.itau.tasks.infra.wrapper;

import br.com.itau.tasks.infra.serializer.PageWrapperSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 02/07/2019
 */
@AllArgsConstructor
@JsonSerialize(using = PageWrapperSerializer.class)
public class PageWrapper<E> {
	
	private final Page<E> page;
	
	/**
	 * @return
	 */
	public List<E> getContent() {
		final List<E> content = page.getContent();
		return Collections.unmodifiableList(content);
	}
	
	/**
	 * @return
	 */
	public Integer getNumber() {
		return page.getNumber();
	}
	
	/**
	 * @return
	 */
	public Integer getTotalOfPages() {
		return page.getTotalPages();
	}
	
}
