package chocola.springbatch;

import java.time.LocalDateTime;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;

public class CustomJobParametersIncrementer implements JobParametersIncrementer {

    @Override
    public JobParameters getNext(JobParameters parameters) {
        String id = LocalDateTime.now().toString();
        return new JobParametersBuilder()
                .addString("id", id)
                .toJobParameters();
    }
}
