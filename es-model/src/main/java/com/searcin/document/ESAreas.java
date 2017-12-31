package com.searcin.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "#{ESConfig.indexName}", type = "areas")
public class ESAreas {
	@Id
	private Integer id;
	
	@JsonProperty("name")
	@MultiField(
			mainField = @Field(type=FieldType.String, store = true, analyzer = "case_insensitive"),
			otherFields = {
					@InnerField(suffix="key", index = FieldIndex.analyzed, 
							searchAnalyzer = "standard",  indexAnalyzer = "standard",
						    store = true, type = FieldType.String)
			})
	private String name;
	
	public ESAreas() {
		
	}

	public ESAreas(Integer id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "ESAreas [id=" + id + ", name=" + name + "]";
	}	
	
}
