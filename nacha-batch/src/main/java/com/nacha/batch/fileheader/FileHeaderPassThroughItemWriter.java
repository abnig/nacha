package com.nacha.batch.fileheader;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.nacha.domain.file.FileHeader;

@Component("fileHeaderPassThroughItemWriter")
@StepScope
public class FileHeaderPassThroughItemWriter implements ItemWriter<FileHeader>, StepExecutionListener {

	Logger logger = LoggerFactory.getLogger(getClass());

	private FileHeader fileHeader = null;

	@Override
	public void write(List<? extends FileHeader> items) throws Exception {
		if (items != null && items.size() > 0) {
			fileHeader = new FileHeader(items.get(0));
		}
		logger.info("Read file Header record " + fileHeader.toString());
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {

	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if (this.fileHeader != null) {
			stepExecution.getExecutionContext().put("fileHeader", this.fileHeader);
			logger.info("Added file Header record to execution context with key='fileHeader' " + fileHeader.toString());
			return ExitStatus.COMPLETED;
		} else {
			logger.info("Failed to read File Header/adding it to execution context with key=fileHeader ");
			return ExitStatus.FAILED;
		}

	}

}
