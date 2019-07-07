package br.com.itau.tasks.domain.task;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
@Entity
@Table(name = "TASK", schema = "TSK")
@ToString(of = {"id", "creationDate", "status", "description", "points"})
public class Task {
	
	@NotNull
	@Column(name = "CREATION_DATE")
	private final LocalDateTime creationDate;
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Getter
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private Status status;
	
	@Getter
	@NotNull
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Getter
	@NotNull
	@Column(name = "POINTS")
	private Integer points;
	
	/**
	 * @param description
	 */
	public Task(final String description, final Integer points) {
		this(Status.PENDING, description, points);
	}
	
	public Task(final Status status, final String description, final Integer points) {
		this.creationDate = LocalDateTime.now();
		this.status = status;
		this.description = description;
		this.points = points;
	}
	
	private Task() {
		this(null, null, null);
	}
	
	/**
	 * @return
	 */
	public Long getCreationDateAsTimestamp() {
		return creationDate.atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
	}
	
	/**
	 * @return
	 */
	public String getStatusAsString() {
		return status.toString();
	}
	
	/**
	 * @param task
	 *
	 * @return
	 */
	public Task update(final Task task) {
		if (!status.equals(Status.COMPLETED)) {
			this.status = task.getStatus();
			this.description = task.getDescription();
			this.points = task.getPoints();
			return this;
		}
		throw new InvalidStatusTaskException(String.format("Invalid state of Task with ID '%s' to update", id));
	}
	
}
