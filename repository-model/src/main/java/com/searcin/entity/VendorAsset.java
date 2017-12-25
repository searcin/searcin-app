package com.searcin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.searcin.listener.VendorAssetListener;

@Entity
@EntityListeners(VendorAssetListener.class)
@Table(name = "vendor_asset")
public class VendorAsset extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private Vendors vendor;

	@Column(name = "asset_type")
	private String assetType;

	@Column(name = "asset_key")
	private String assetKey;
	
	@Column(name = "metadata")
	private String metadata;

	public VendorAsset() {

	}

	public VendorAsset(Vendors vendor, String assetType, String assetKey, String metadata) {
		this.vendor = vendor;
		this.assetType = assetType;
		this.assetKey = assetKey;
		this.metadata = metadata;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Vendors getVendor() {
		return vendor;
	}

	public void setVendor(Vendors vendor) {
		this.vendor = vendor;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getAssetKey() {
		return assetKey;
	}

	public void setAssetKey(String assetKey) {
		this.assetKey = assetKey;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}


}
