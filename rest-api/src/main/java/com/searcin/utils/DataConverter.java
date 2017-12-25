package com.searcin.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.searcin.document.ESVendors;
import com.searcin.dto.AddressesDto;
import com.searcin.dto.AreasDto;
import com.searcin.dto.AssetsDto;
import com.searcin.dto.CategoriesDto;
import com.searcin.dto.ClassRangeDto;
import com.searcin.dto.ContactsDto;
import com.searcin.dto.ImagesDto;
import com.searcin.dto.NameAuditDto;
import com.searcin.dto.NameDto;
import com.searcin.dto.ServicesDto;
import com.searcin.dto.SubCategoriesDto;
import com.searcin.dto.SuggestDto;
import com.searcin.dto.UsersDto;
import com.searcin.dto.VendorsDto;
import com.searcin.entity.Addresses;
import com.searcin.entity.Areas;
import com.searcin.entity.Categories;
import com.searcin.entity.ClassRange;
import com.searcin.entity.Contacts;
import com.searcin.entity.Roles;
import com.searcin.entity.Services;
import com.searcin.entity.SubCategories;
import com.searcin.entity.Users;
import com.searcin.entity.VendorAsset;
import com.searcin.entity.Vendors;

@Component
public class DataConverter {

	@Autowired
	private ModelMapper modelMapper;

	public VendorsDto toDto(Vendors vendor) {
		return modelMapper.map(vendor, VendorsDto.class);
	}

	public Vendors toEntity(VendorsDto vendor) {
		return modelMapper.map(vendor, Vendors.class);
	}

	public AddressesDto toDto(Addresses addresses) {
		return Optional.ofNullable(addresses).map(item -> modelMapper.map(item, AddressesDto.class))
				.orElse(new AddressesDto());
	}

	public Addresses toEntity(AddressesDto address) {
		return modelMapper.map(address, Addresses.class);
	}

	public AreasDto toDto(Areas area) {
		return modelMapper.map(area, AreasDto.class);
	}

	public Areas toEntity(AreasDto area) {
		return modelMapper.map(area, Areas.class);
	}

	public CategoriesDto toDto(Categories category) {
		return modelMapper.map(category, CategoriesDto.class);
	}

	public Categories toEntity(CategoriesDto category) {
		return modelMapper.map(category, Categories.class);
	}

	public ContactsDto toDto(Contacts contact) {
		return Optional.ofNullable(contact).map(item -> modelMapper.map(item, ContactsDto.class))
				.orElse(new ContactsDto());
	}

	public Contacts toEntity(ContactsDto contact) {
		return modelMapper.map(contact, Contacts.class);
	}

	public ServicesDto toDto(Services service) {
		return modelMapper.map(service, ServicesDto.class);
	}

	public Services toEntity(ServicesDto service) {
		return modelMapper.map(service, Services.class);
	}

	public SubCategoriesDto toDto(SubCategories subCategory) {
		return modelMapper.map(subCategory, SubCategoriesDto.class);
	}

	public SubCategories toEntity(SubCategoriesDto subCategory) {
		return modelMapper.map(subCategory, SubCategories.class);
	}

	public ClassRangeDto toDto(ClassRange classRange) {
		return modelMapper.map(classRange, ClassRangeDto.class);
	}

	// old
	public ImagesDto toImagesDto(String host, List<String> images) {
		ImagesDto imagesDto = new ImagesDto();
		imagesDto.setHost(host);
		imagesDto.setImages(images);
		return imagesDto;
	}

	public File toFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	// public Map<String, Object> toVendorObj(Vendors vendor) {
	// Map<String, Object> obj = new HashMap<>();
	// obj.put("vendor", toDto(vendor));
	// obj.put("address", toDto(vendor.getAddress()));
	// obj.put("contact", toDto(vendor.getContact()));
	// List<ServicesDto> services = vendor.getServices().stream()
	// .map(service -> toDto(service))
	// .collect(Collectors.toList());
	// obj.put("services", services);
	// return obj;
	// }

	public VendorsDto toVendorDto(Vendors vendors) {
		return modelMapper.map(vendors, VendorsDto.class);
	}

	public Vendors toVendorEntity(VendorsDto vendorsDto) {
		Vendors vendor = modelMapper.map(vendorsDto, Vendors.class);
		if (vendorsDto.getServices() != null && vendorsDto.getServices().size() > 0) {
			vendor.setServices(
					vendorsDto.getServices().stream().map(item -> toServiceEntity(item)).collect(Collectors.toList()));
		}
		return vendor;
	}

	public AddressesDto toAddressDto(Addresses addresses) {
		if (addresses != null) {
			return modelMapper.map(addresses, AddressesDto.class);
		} else
			return new AddressesDto();
	}

	public Addresses toAddressEntity(AddressesDto addressesDto, Integer id) {
		Addresses addresses = modelMapper.map(addressesDto, Addresses.class);
		Vendors vendors = new Vendors();
		vendors.setId(id);
		//addresses.setVendor(vendors);
		return addresses;
	}

	public NameDto toNameDto(Areas area) {
		return modelMapper.map(area, NameDto.class);
	}

	public Areas toAreaEntity(NameDto areaDto) {
		return modelMapper.map(areaDto, Areas.class);
	}

	public NameDto toNameDto(Categories category) {
		return modelMapper.map(category, NameDto.class);
	}

	public Categories toCategoryEntity(NameDto categoryDto) {
		return modelMapper.map(categoryDto, Categories.class);
	}

	public NameDto toNameDto(ClassRange classRange) {
		return modelMapper.map(classRange, NameDto.class);
	}

	public NameDto toNameDto(SubCategories subCategory) {
		return modelMapper.map(subCategory, NameDto.class);
	}

	public SubCategories toSubCategoryEntity(NameDto subCategoryDto, Integer id) {
		SubCategories subCategory = modelMapper.map(subCategoryDto, SubCategories.class);
		Categories category = new Categories();
		category.setId(id);
		subCategory.setCategory(category);
		return subCategory;
	}

	public NameDto toNameDto(Vendors vendor) {
		return modelMapper.map(vendor, NameDto.class);
	}

	public ContactsDto toContactDto(Contacts contacts) {
		if (contacts != null) {
			return modelMapper.map(contacts, ContactsDto.class);
		} else {
			return new ContactsDto();
		}
	}

	public Contacts toEntity(ContactsDto contactsDto, Integer id) {
		return modelMapper.map(contactsDto, Contacts.class);
		
	}

	public ServicesDto toServiceDto(Services services) {
		return modelMapper.map(services, ServicesDto.class);
	}

	public Services toServiceEntity(ServicesDto servicesDto) {
		return modelMapper.map(servicesDto, Services.class);
	}

	public Users toEntity(UsersDto userDto) {
		return modelMapper.map(userDto, Users.class);
	}

	public UsersDto toDto(Users user) {
		UsersDto userDto = new UsersDto();
		userDto = modelMapper.map(user, UsersDto.class);
		userDto.setRoles(user.getRoles().stream().map(item -> item.getName()).collect(Collectors.toList()));
		return userDto;
	}

	public NameDto toNameDto(Roles role) {
		return modelMapper.map(role, NameDto.class);
	}

	public Roles toRolesEntity(NameDto name) {
		return modelMapper.map(name, Roles.class);
	}

	public NameAuditDto toNameAuditDto(SubCategories subCat) {
		return modelMapper.map(subCat, NameAuditDto.class);
	}

	public NameAuditDto toNameAuditDto(Categories findById) {
		return modelMapper.map(findById, NameAuditDto.class);
	}

	public NameAuditDto toNameAuditDto(Areas findById) {
		return modelMapper.map(findById, NameAuditDto.class);
	}

	public Areas toAreaEntity(NameAuditDto areasDto) {
		return modelMapper.map(areasDto, Areas.class);
	}

	public Categories toCategoryEntity(NameAuditDto categoryDto) {
		return modelMapper.map(categoryDto, Categories.class);
	}

	public SubCategories toSubCategoryEntity(NameAuditDto subCategoriesDto) {
		return modelMapper.map(subCategoriesDto, SubCategories.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<SuggestDto> toDto(List<Map<String, Object>> suggest) {
		return suggest.stream().map(item -> {
			String type = (String) item.get("type");
			Map<String, Object> source = (Map<String, Object>) item.get("source");
			return new SuggestDto((Integer) source.get("id"), (String) source.get("name"), type, 
					(String) source.get("description"), (List<String>) source.get("logo"));
		}).collect(Collectors.toList());
	}

	public VendorsDto toDto(ESVendors vendor) {
		return modelMapper.map(vendor, VendorsDto.class);
	}

	public AssetsDto toDto(VendorAsset logo) {
		return new AssetsDto(logo.getId(), logo.getMetadata(), logo.getUpdatedBy(), logo.getUpdatedOn());
	}
}
