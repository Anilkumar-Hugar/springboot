package com.organizationManagement.entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "organization")
public class Organization {
	@EmbeddedId
	private OrganizationKeyClass organizationKeyClass;
	private String OrganizationDetails;

	@Version
	private long version;
//	public Organization(OrganizationKeyClass organizationKeyClass,String name,long version) {
//		
//	}
	
}
