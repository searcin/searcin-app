package com.searcin.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.dto.AddressesDto;
import com.searcin.dto.AreasDto;
import com.searcin.dto.AssetsDto;
import com.searcin.dto.CategoriesDto;
import com.searcin.dto.ClassRangeDto;
import com.searcin.dto.ContactsDto;
import com.searcin.dto.ServicesDto;
import com.searcin.dto.SubCategoriesDto;
import com.searcin.dto.VendorsDto;
import com.searcin.entity.Vendors;
import com.searcin.exception.FileNotFoundException;
import com.searcin.exception.InvalidFieldException;
import com.searcin.exception.NoContentException;
import com.searcin.exception.ValidationException;
import com.searcin.service.AreasService;
import com.searcin.service.CategoriesService;
import com.searcin.service.ClassRangeService;
import com.searcin.service.ServicesService;
import com.searcin.service.SubCategoriesService;
import com.searcin.service.TrashService;
import com.searcin.service.VendorAssetService;
import com.searcin.service.VendorsService;
import com.searcin.utils.DataConverter;
import com.searcin.utils.DataValidator;
import com.searcin.view.Views;

@RestController
@PreAuthorize("hasAnyRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

	private final Logger log = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private DataValidator validate;

	@Autowired
	private DataConverter convert;

	@Autowired
	private AreasService areasService;

	@Autowired
	private CategoriesService categoriesService;

	@Autowired
	private ServicesService servicesService;

	@Autowired
	private SubCategoriesService subCategoriesService;

	@Autowired
	private VendorsService vendorsService;

	@Autowired
	private ClassRangeService classRangeService;

	@Autowired
	private VendorAssetService vendorAssetService;

	@Autowired
	private TrashService trashService;

	// address-api
	@JsonView(Views.AddressAudit.class)
	@RequestMapping(value = "/vendor/{id}/address", method = RequestMethod.GET)
	public AddressesDto addressByVendor(@PathVariable Integer id) {
		return convert.toDto(vendorsService.findAddress(id));
	}

	@JsonView(Views.AddressAudit.class)
	@RequestMapping(value = "/vendor/{id}/address/save", method = RequestMethod.POST)
	public void saveAddress(@PathVariable Integer id, @RequestBody AddressesDto addressesDto) {

		if (addressesDto.getArea() == null)
			throw new ValidationException("Please select an area!");

		if (addressesDto.getLng() != null && (addressesDto.getLng() > 180 || addressesDto.getLng() < -180))
			throw new ValidationException("The longitude of a point must be between -180 and 180");

		if (addressesDto.getLat() != null && (addressesDto.getLat() > 90 || addressesDto.getLat() < -90))
			throw new ValidationException("The latitude of a point must be between -90 and 90");

		vendorsService.saveAddress(convert.toEntity(addressesDto), id);
	}

	@JsonView(Views.AreaListId.class)
	@RequestMapping(value = "/areas", method = RequestMethod.GET)
	public List<AreasDto> areas() {
		return areasService.findAll().stream().map(item -> convert.toDto(item)).collect(Collectors.toList());
	}

	@JsonView(Views.AreaAudit.class)
	@RequestMapping(value = "/area/{id}", method = RequestMethod.GET)
	public AreasDto area(@PathVariable Integer id) {
		return convert.toDto(areasService.findById(id));
	}

	@RequestMapping(value = "/area/save", method = RequestMethod.POST)
	public void saveArea(@RequestBody AreasDto areasDto) {
		Optional.ofNullable(areasDto.getName()).map(item -> areasService.save(convert.toEntity(areasDto)))
				.orElseThrow(() -> new ValidationException("Name cannot be empty!"));
	}

	@RequestMapping(value = "/area/{id}/trash", method = RequestMethod.GET)
	public void trashArea(@PathVariable Integer id) {
		areasService.trash(id);
	}

	@RequestMapping(value = "/area/{id}/delete", method = RequestMethod.GET)
	public void deleteArea(@PathVariable Integer id) {
		areasService.delete(id);
	}

	@RequestMapping(value = "/area/{id}/restore", method = RequestMethod.GET)
	public void restoreArea(@PathVariable Integer id) {
		areasService.restore(id);
	}

	@JsonView(Views.CategoryListId.class)
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public List<CategoriesDto> categories() {
		return categoriesService.findAll().stream().map(item -> convert.toDto(item)).collect(Collectors.toList());
	}

	@JsonView(Views.CategoryAudit.class)
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public CategoriesDto category(@PathVariable Integer id) {
		return convert.toDto(categoriesService.findById(id));
	}

	@JsonView(Views.CategoryAudit.class)
	@RequestMapping(value = "/category/save", method = RequestMethod.POST)
	public void saveCategory(@RequestBody CategoriesDto categoryDto) {
		Optional.ofNullable(categoryDto.getName()).map(item -> categoriesService.save(convert.toEntity(categoryDto)))
				.orElseThrow(() -> new ValidationException("Name cannot be empty!"));
	}

	@RequestMapping(value = "/category/{id}/trash", method = RequestMethod.GET)
	public void trashCategory(@PathVariable Integer id) {
		categoriesService.trash(id);
	}

	@RequestMapping(value = "/category/{id}/restore", method = RequestMethod.GET)
	public void restoreCategory(@PathVariable Integer id) {
		categoriesService.restore(id);
	}

	@RequestMapping(value = "/category/{id}/delete", method = RequestMethod.GET)
	public void deleteCategory(@PathVariable Integer id) {
		categoriesService.delete(id);
	}

	// class - range - api
	@RequestMapping(value = "/classrange")
	public List<ClassRangeDto> classRange() {
		return classRangeService.findAll().stream().map(classRange -> convert.toDto(classRange))
				.collect(Collectors.toList());
	}

	// contact -api
	@RequestMapping(value = "/vendor/{id}/contact/save", method = RequestMethod.POST)
	public void saveContact(@PathVariable Integer id, @RequestBody ContactsDto contactsDto) {

		if (contactsDto.getEmail() != null && validate.email(contactsDto.getEmail()) == false)
			throw new InvalidFieldException("Please enter a valid email");

		if (contactsDto.getMobile() != null && validate.mobile(contactsDto.getMobile()) == false)
			throw new InvalidFieldException("Please enter a valid mobile");

		if (contactsDto.getPhone() != null && validate.phone(contactsDto.getPhone()) == false)
			throw new InvalidFieldException("Please enter a valid phone");

		log.info("Saving/Updating a contact for vendor id : " + id);
		vendorsService.saveContact(convert.toEntity(contactsDto), id);
	}

	@JsonView(Views.ContactAudit.class)
	@RequestMapping(value = "/vendor/{id}/contact", method = RequestMethod.GET)
	public ContactsDto contactByVendor(@PathVariable Integer id) {
		return convert.toDto(vendorsService.findContact(id));
	}

	@JsonView(Views.ServiceListId.class)
	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public List<ServicesDto> services() {
		return servicesService.findAll().stream().map(item -> convert.toDto(item)).collect(Collectors.toList());
	}

	@JsonView(Views.ServiceAudit.class)
	@RequestMapping(value = "/service/{id}", method = RequestMethod.GET)
	public ServicesDto service(@PathVariable Integer id) {
		return convert.toDto(servicesService.findById(id));
	}

	@JsonView(Views.ServiceAudit.class)
	@RequestMapping(value = "/service/save", method = RequestMethod.POST)
	public ServicesDto saveService(@RequestBody ServicesDto servicesDto) {
		log.info("Saving/Updating a service : " + servicesDto.getName());
		return convert.toServiceDto(servicesService.save(convert.toEntity(servicesDto)));
	}

	@RequestMapping(value = "/service/{id}/delete", method = RequestMethod.GET)
	public void deleteService(@PathVariable Integer id) {
		servicesService.deleteById(id);
	}

	@RequestMapping(value = "/service/{id}/trash", method = RequestMethod.GET)
	public void trashService(@PathVariable Integer id) {
		servicesService.trash(id);
	}

	@RequestMapping(value = "/service/{id}/restore", method = RequestMethod.GET)
	public void restoreService(@PathVariable Integer id) {
		servicesService.restore(id);
	}

	@JsonView(Views.SubCategoryListId.class)
	@RequestMapping(value = "/category/{id}/subcategories", method = RequestMethod.GET)
	public List<SubCategoriesDto> subCategories(@PathVariable Integer id) {
		return subCategoriesService.findByCategoryId(id).stream().map(item -> convert.toDto(item))
				.collect(Collectors.toList());
	}

	@JsonView(Views.SubCategoryAudit.class)
	@RequestMapping(value = "/category/{id}/subcategory/save", method = RequestMethod.POST)
	public void saveSubCategory(@RequestBody SubCategoriesDto subCategoriesDto, @PathVariable Integer id) {
		subCategoriesService.save(convert.toEntity(subCategoriesDto), id);
	}

	@RequestMapping(value = "/subcategory/{id}/trash", method = RequestMethod.GET)
	public void trashSubCategory(@PathVariable Integer id) {
		subCategoriesService.trash(id);
	}

	@RequestMapping(value = "/subcategory/{id}/restore", method = RequestMethod.GET)
	public void restoreSubCategory(@PathVariable Integer id) {
		subCategoriesService.restore(id);
	}

	@RequestMapping(value = "/subcategory/{id}/delete", method = RequestMethod.GET)
	public void deleteSubCategory(@PathVariable Integer id) {
		subCategoriesService.delete(id);
	}

	@JsonView(Views.SubCategoryAudit.class)
	@RequestMapping(value = "/subcategory/{id}", method = RequestMethod.GET)
	public SubCategoriesDto subCategory(@PathVariable Integer id) {
		return convert.toDto(subCategoriesService.findById(id));
	}

	// vendors-api
	@JsonView(Views.VendorListId.class)
	@RequestMapping(value = "/vendors/page", method = RequestMethod.GET)
	public Page<VendorsDto> vendorsList(Pageable pageable) {
		return vendorsService.findNames(pageable).map(new Converter<Vendors, VendorsDto>() {
			@Override
			public VendorsDto convert(Vendors vendor) {
				return convert.toDto(vendor);
			}
		});
	}

	@JsonView(Views.VendorListId.class)
	@RequestMapping(value = "/vendors", method = RequestMethod.GET)
	public List<VendorsDto> vendorsList() {
		return vendorsService.findNames().stream().map(item -> convert.toDto(item)).collect(Collectors.toList());
	}

	@JsonView(Views.VendorAudit.class)
	@RequestMapping(value = "/vendor/{id}", method = RequestMethod.GET)
	public VendorsDto vendorById(@PathVariable Integer id) {
		return convert.toDto(vendorsService.findById(id));
	}

	@JsonView(Views.VendorAudit.class)
	@RequestMapping(value = "/vendor/save", method = RequestMethod.POST)
	public VendorsDto saveVendor(@RequestBody VendorsDto vendorsDto) {

		if (vendorsDto.getName() == null)
			throw new ValidationException("Name cannot be null!");

		if (vendorsDto.getCategory() == null || vendorsDto.getCategory().getId() == null)
			throw new ValidationException("Category id cannot be null!");

		if (vendorsDto.getSubCategory() == null || vendorsDto.getSubCategory().getId() == null)
			throw new ValidationException("Sub category id cannot be null!");

		if (vendorsDto.getClassRange() == null || vendorsDto.getClassRange().getId() == null)
			throw new ValidationException("Select any class range!");

		log.info("Saving/Updating vendor : " + vendorsDto.getName());
		return convert.toDto(vendorsService.save(convert.toEntity(vendorsDto)));
	}

	@RequestMapping(value = "/vendor/{id}/delete", method = RequestMethod.GET)
	public void deleteVendor(@PathVariable Integer id) {
		log.info("Deleting a vendor with id : " + id);
		vendorsService.delete(id);
	}

	@RequestMapping(value = "/vendor/{id}/trash", method = RequestMethod.GET)
	public void trashVendor(@PathVariable Integer id) {
		vendorsService.trash(id);
	}

	@RequestMapping(value = "/vendor/{id}/restore", method = RequestMethod.GET)
	public void restoreVendor(@PathVariable Integer id) {
		vendorsService.restore(id);
	}

	@RequestMapping(value = "/vendor/{id}/logo/upload", method = RequestMethod.POST)
	public void uploadVendorLogo(@RequestParam("file") Optional<MultipartFile> multipartFile,
			@PathVariable Integer id) {
		vendorAssetService.uploadLogo(id, multipartFile.map(item -> {
			if (validate.isValidImage(item))
				return item;
			else
				throw new ValidationException("Not a valid image format!");

		}).orElseThrow(() -> new FileNotFoundException("File not found!")));
	}

	@JsonView(Views.AssetAudit.class)
	@RequestMapping(value = "/vendor/{id}/logo", method = RequestMethod.GET)
	public AssetsDto vendorLogo(@PathVariable Integer id) {
		return Optional.ofNullable(vendorAssetService.getLogo(id)).map(item -> convert.toDto(item))
				.orElseThrow(() -> new NoContentException("No logo uploaded for the vendor!"));
	}

	@RequestMapping(value = "/vendor/{id}/logo/delete")
	public void deleteVendorImage(@PathVariable Integer id, @RequestParam Integer assetId) {
		vendorAssetService.deleteAssetByKey(id, assetId);
	}

	@RequestMapping(value = "/vendor/{id}/gallery/upload", method = RequestMethod.POST)
	public void uploadVendorGallery(@RequestParam("file") Optional<MultipartFile> multipartFile,
			@PathVariable Integer id) {
		vendorAssetService.uploadGallery(id, multipartFile.map(item -> {
			if (validate.isValidImage(item))
				return item;
			else
				throw new ValidationException("Not a valid image format!");
		}).orElseThrow(() -> new FileNotFoundException("File not found!")));
	}

	@JsonView(Views.AssetAudit.class)
	@RequestMapping(value = "/vendor/{id}/gallery", method = RequestMethod.GET)
	public List<AssetsDto> vendorGallery(@PathVariable Integer id) {
		return vendorAssetService.getGallery(id).stream().map(item -> convert.toDto(item)).collect(Collectors.toList());
	}

	@JsonView(Views.VendorDetailId.class)
	@RequestMapping(value = "/vendor/{id}/detail", method = RequestMethod.GET)
	public VendorsDto detail(@PathVariable Integer id) {
		log.info("Getting detail for the vendor" + id);
		VendorsDto detail = convert.toDto(vendorsService.detail(id));
		return detail;
	}

	@RequestMapping(value = "/trash", method = RequestMethod.GET)
	public List<Map<String, Object>> trashItems(Pageable pageable) {
		return trashService.getItems(pageable);
	}
}
