package com.searcin.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "#{ESConfig.indexName}", type = "subcategories")
public class ESSubCategories {
	
	@Id
	private Integer id;
	
	@JsonProperty("name")
	@MultiField(
			mainField = @Field(type=FieldType.String, store = true, analyzer = "case_insensitive"),
			otherFields = {
					@InnerField(suffix="key", index = FieldIndex.analyzed, 
							searchAnalyzer = "english",  indexAnalyzer = "english",
						    store = true, type = FieldType.String)
			})
	private String name;
	
	
	@JsonProperty("category_id")
	private Integer categoryId;
	
	public ESSubCategories() {
		
	}
	
	public ESSubCategories(Integer id, String name, Integer categoryId) {
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
}
