package chocola.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        String jobName = chunkContext.getStepContext().getJobName();
        String stepName = contribution.getStepExecution().getStepName();

        System.out.println("jobName = " + jobName);
        System.out.println("stepName = " + stepName);

        return RepeatStatus.FINISHED;
    }
}
