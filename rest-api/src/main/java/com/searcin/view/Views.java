package com.searcin.view;

public interface Views {
	public static interface AddressDetailId extends Dto.Address.id, Dto.Address.detail {}
	public static interface AddressDetail extends Dto.Area.name, Dto.Address.detail {}
	public static interface AddressAudit extends Dto.Address.id, Dto.Area.name, Dto.Area.id, Dto.Address.audit {}
	
	public static interface AreaListId extends Dto.Area.id, Dto.Area.name {}
	public static interface AreaList extends Dto.Area.name {}
	public static interface AreaAudit extends Dto.Area.id, Dto.Area.audit {}
	
	public static interface AssetList extends Dto.Asset.url {}
	public static interface AssetListKey extends Dto.Asset.key, Dto.Asset.url {}
	public static interface AssetAudit extends AssetListKey, Dto.Asset.audit {}
	
	public static interface CategoryListId extends Dto.Category.id, Dto.Category.name {}
	public static interface CategoryList extends Dto.Category.name {}
	public static interface CategoryAudit extends Dto.Category.id, Dto.Category.audit {}
	
	public static interface SubCategoryListId extends Dto.SubCategory.id, Dto.SubCategory.name {}
	public static interface SubCategoryList extends Dto.SubCategory.name {}
	public static interface SubCategoryAudit extends Dto.SubCategory.id, Dto.SubCategory.audit {}
	
	public static interface ContactDetailId extends Dto.Contact.id, Dto.Contact.detail {}
	public static interface ContactDetail extends Dto.Contact.detail {}
	public static interface ContactAudit extends Dto.Contact.id, Dto.Contact.audit {}
	
	public static interface ServiceListId extends Dto.Service.id, Dto.Service.name {}
	public static interface ServiceDescId extends Dto.Service.id, Dto.Service.desc {}
	public static interface ServiceAudit extends Dto.Service.id, Dto.Service.audit {}
	
	public static interface VendorListId extends Dto.Vendor.id, Dto.Vendor.name {}
	public static interface VendorBasicId extends Views.VendorListId, Dto.Vendor.description, Dto.Vendor.hierarchy,
													Dto.Vendor.classrange, Views.CategoryList, Views.SubCategoryList, 
													Dto.ClassRange.name, Dto.Vendor.services, Dto.Service.name, Dto.Service.id{}
	public static interface VendorAudit extends Views.VendorBasicId, Dto.Category.id, Dto.SubCategory.id, Dto.ClassRange.id, 
													Dto.Service.id, Dto.Vendor.audit {}
	public static interface VendorDetailId extends Views.VendorBasicId, Dto.Vendor.address, Dto.Vendor.contact, 
													Dto.Vendor.logo, Dto.Vendor.images, Dto.Asset.url {}
	public static interface VendorPanel extends Dto.Vendor.id, Dto.Vendor.name, Dto.Vendor.logo, Dto.Asset.url,
													Dto.Vendor.description, Dto.Vendor.address, Dto.Address.name, Dto.Address.location,
													Dto.Address.basic {}
}

