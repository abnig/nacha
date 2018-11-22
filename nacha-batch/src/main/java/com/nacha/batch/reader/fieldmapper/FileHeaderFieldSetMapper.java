package com.nacha.batch.reader.fieldmapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import com.nacha.domain.file.FileHeader;
/**
 * 
 * 
 Indicator, File ID (Modifier), File creation date, File creation time, Total trxn, Total creditamount, Total Debit amount ,Batch Count 
1,T, 181024,1045, 14, 1400,0,6 
 Integer indicator, String fileId, Date creationDate, Date creationTime,Integer totalTransactionCount, BigDecimal totalCreditAmount,BigDecimal totalDebitAmount, Integer batchCount
 * @author abnig19
 *
 */
@Component("fileHeaderFieldSetMapper")
public class FileHeaderFieldSetMapper implements FieldSetMapper<FileHeader> {
	 
    public FileHeader mapFieldSet(FieldSet fieldSet) {
        FileHeader csvFileHeader = new FileHeader(
        		fieldSet.readInt(0), 
        		fieldSet.readString(1), 
        		fieldSet.readDate(2, "yyMMdd"), 
        		fieldSet.readDate(3, "HHmm"),
        		fieldSet.readInt(4), 
        		fieldSet.readBigDecimal(5),
    			fieldSet.readBigDecimal(6),
    			fieldSet.readInt(7));
        return csvFileHeader;
    }
}