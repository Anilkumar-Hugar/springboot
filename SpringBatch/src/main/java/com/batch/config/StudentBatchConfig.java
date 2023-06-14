package com.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.batch.entity.Student;
import com.batch.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class StudentBatchConfig {

	private StudentRepository repository;

	@Bean
	public FlatFileItemReader<Student> reader() {
		return new FlatFileItemReaderBuilder<Student>().name("coffeeItemReader")
				.resource(new ClassPathResource("student.csv")).delimited()
				.names(new String[] { "id","course","name" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Student>() {
					{
						setTargetType(Student.class);
					}
				}).build();
	}

	@Bean
	public LineMapper<Student> lineMapper() {
		DefaultLineMapper<Student> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id","course","name");
		BeanWrapperFieldSetMapper<Student> mapper = new BeanWrapperFieldSetMapper<>();
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(mapper);
		return lineMapper;
	}

	@Bean
	public JdbcBatchItemWriter<Student> itemWriter(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Student>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO STUDENT (ID,NAME,COURSE) " + "VALUES(:ID,:NAME,:COURSE)")
				.dataSource(dataSource).build();
	}

	@Bean
	public CustomProcessor processor() {
		return new CustomProcessor();
	}

	@Bean
	public RepositoryItemWriter<Student> writer() {
		RepositoryItemWriter<Student> writer = new RepositoryItemWriter<>();
		writer.setRepository(repository);
		writer.setMethodName("save");
		return writer;
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<Student> itemWriter, JobRepository jobRepository,
			PlatformTransactionManager manager) {
		return new StepBuilder("step1", jobRepository).<Student, Student>chunk(10, manager).processor(processor())
				.reader(reader()).writer(itemWriter).build();
	}

	@Bean
	public Job runJob(JdbcBatchItemWriter<Student> writer, JobRepository jobRepository, Step step1,
			PlatformTransactionManager manager) {
		return new JobBuilder("student", jobRepository).incrementer(new RunIdIncrementer())
				.flow(step1(writer, jobRepository, manager)).end().build();
	}
}
