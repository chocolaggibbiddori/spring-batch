package chocola.springbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobExecutionListener jobExecutionListener;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory
                .get("helloJob")
                .start(step1())
                .next(step2())
                .listener(jobExecutionListener)
                .build();
    }

    private Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> RepeatStatus.FINISHED)
                .build();
    }

    private Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> null)
                .build();
    }
}
