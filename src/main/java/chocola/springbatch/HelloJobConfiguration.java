package chocola.springbatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
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

    @Bean
    public Job helloJob() {
        return jobBuilderFactory
                .get("helloJob")
                .start(helloFirstStep())
                .next(helloSecondStep())
                .build();
    }

    private Step helloFirstStep() {
        return stepBuilderFactory
                .get("helloFirstStep")
                .tasklet(new CustomTasklet())
                .allowStartIfComplete(true)
                .build();
    }

    private Step helloSecondStep() {
        return stepBuilderFactory
                .get("helloSecondStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>> Hello Spring Batch Second Step <<<");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
