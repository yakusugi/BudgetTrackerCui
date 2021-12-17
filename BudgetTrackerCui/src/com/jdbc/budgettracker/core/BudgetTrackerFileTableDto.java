package com.jdbc.budgettracker.core;

import java.io.FileInputStream;
import java.sql.Blob;
import java.util.Date;

public class BudgetTrackerFileTableDto {
	private int id;
	private FileInputStream pdfFile;

	public BudgetTrackerFileTableDto() {
		super();
	}

	public BudgetTrackerFileTableDto(int id, Blob pdfFile) {
		super();
		this.id = id;
		this.pdfFile = (FileInputStream) pdfFile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FileInputStream getPdfFile() {
		return pdfFile;
	}

	public void setPdfFile(FileInputStream input) {
		this.pdfFile = input;
	}

}
