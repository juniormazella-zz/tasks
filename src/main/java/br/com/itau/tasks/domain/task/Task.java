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
 * This class is the aggregate root of the application
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 *
 * @see https://martinfowler.com/bliki/DDD_Aggregate.html
 */
@Entity
@Table(name = "TASK", schema = "TSK")
@ToString(of = {"id", "creationDate", "status", "description", "points"})
public class Task {
	
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@NotNull
	@Column(name = "CREATION_DATE")
	private final LocalDateTime creationDate;
	
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
	 * Constructor for {@link Task}
	 *
	 * @param description
	 * 		this field describes the task
	 * @param points
	 * 		this field represents the size of the task in points
	 */
	public Task(final String description, final Integer points) {
		this(Status.PENDING, description, points);
	}
	
	/**
	 * Constructor for {@link Task}
	 *
	 * @param status
	 * 		this field represents the status of the task
	 * @param description
	 * 		this field describes the task
	 * @param points
	 * 		this field represents the size of the task in points
	 */
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
	 * This method convert the creation date to UTC time zone and get the milliseconds of it
	 *
	 * @return milliseconds of creation date
	 */
	public Long getCreationDateAsTimestamp() {
		return creationDate.atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
	}
	
	/**
	 * This method return {@link String} value of {@link Status}
	 *
	 * @return string of {@link Status}
	 */
	public String getStatusAsString() {
		return status.toString();
	}
	
	/**
	 * This method knows how to perform the update inside {@link Task}
	 *
	 * @param task
	 * 		object with information to update
	 *
	 * @return instance of updated object
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
