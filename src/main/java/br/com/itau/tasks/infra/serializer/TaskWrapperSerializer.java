package br.com.itau.tasks.infra.serializer;

import br.com.itau.tasks.domain.task.Task;
import br.com.itau.tasks.infra.wrapper.TaskWrapper;

import java.io.IOException;

/**
 * Class comments go here...
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 01/07/2019
 */
public class TaskWrapperSerializer extends AbstractSerializer<TaskWrapper> {

    private final TaskSerializer taskSerializer = getBean(TaskSerializer.class);

    @Override
    public void serialize(final TaskWrapper taskWrapper, final JsonWriter jsonWriter) throws IOException {
        final Task task = taskWrapper.getTask();
        taskSerializer.serialize(task, jsonWriter);
    }

}
