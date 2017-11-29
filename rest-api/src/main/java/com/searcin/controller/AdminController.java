package com.searcin.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.searcin.constant.AssetType;
import com.searcin.constant.ImageType;
import com.searcin.dto.AddressesDto;
import com.searcin.dto.AreasDto;
import com.searcin.dto.AssetsDto;
import com.searcin.dto.CategoriesDto;
import com.searcin.dto.ClassRangeDto;
import com.searcin.dto.ContactsDto;
import com.searcin.dto.ServicesDto;
import com.searcin.dto.SubCategoriesDto;
import com.searcin.dto.VendorsDto;
import com.searcin.entity.Addresses;
import com.searcin.entity.Areas;
import com.searcin.entity.Categories;
import com.searcin.entity.Services;
import com.searcin.entity.SubCategories;
import com.searcin.entity.Vendors;
import com.searcin.exception.AwsS3Exception;
import com.searcin.exception.FieldNotFoundException;
import com.searcin.exception.InvalidFieldException;
import com.searcin.exception.InvalidFileTypeException;
import com.searcin.exception.NotFoundException;
import com.searcin.exception.OutOfLimitException;
import com.searcin.exception.ResourceNotFoundException;
import com.searcin.service.AddressesService;
import com.searcin.service.AreasService;
import com.searcin.service.AssetsService;
import com.searcin.service.CategoriesService;
import com.searcin.service.ClassRangeService;
import com.searcin.service.ContactsService;
import com.searcin.service.IngestionService;
import com.searcin.service.ServicesService;
import com.searcin.service.SubCategoriesService;
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
	private AddressesService addressesService;

	@Autowired
	private AreasService areasService;

	@Autowired
	private CategoriesService categoriesService;

	@Autowired
	private ContactsService contactsService;

	@Autowired
	private ServicesService servicesService;

	@Autowired
	private SubCategoriesService subCategoriesService;

	@Autowired
	private VendorsService vendorsService;

	@Autowired
	private ClassRangeService classRangeService;

	@Autowired
	private AssetsService assetsService;
	
	@Autowired
	private IngestionService ingestionService;

	// address-api
	@JsonView(Views.AddressAudit.class)
	@RequestMapping(value = "/vendor/{id}/address", method = RequestMethod.GET)
	public AddressesDto addressByVendor(@PathVariable Integer id) {
		return convert.toDto(addressesService.findByVendorId(id));
	}

	@JsonView(Views.AddressAudit.class)
	@RequestMapping(value = "/vendor/{id}/address/save", method = RequestMethod.POST)
	public AddressesDto saveAddress(@PathVariable Integer id, @RequestBody AddressesDto addressesDto) {

		if (addressesDto.getArea() == null)
			throw new FieldNotFoundException("Please select an area!");

		if (addressesDto.getLng() != null && (addressesDto.getLng() > 180 || addressesDto.getLng() < -180))
			throw new IllegalStateException("The longitude of a point must be between -180 and 180");

		if (addressesDto.getLat() != null && (addressesDto.getLat() > 90 || addressesDto.getLat() < -90))
			throw new IllegalStateException("The latitude of a point must be between -90 and 90");

		log.info("Saving an address for : " + id);
		Addresses address = convert.toEntity(addressesDto);
		address.setVendor(Optional.ofNullable(id).map(item -> new Vendors(item))
				.orElseThrow(() -> new FieldNotFoundException("Please select a vendor!")));
		return convert.toAddressDto(addressesService.save(address));
	}

	// area-api
	@JsonView(Views.AreaListId.class)
	@RequestMapping(value = "/areas", method = RequestMethod.GET)
	public Page<AreasDto> areas(Pageable page) {
		return areasService.findPage(page).map(new Converter<Areas, AreasDto>() {
			@Override
			public AreasDto convert(Areas area) {
				return convert.toDto(area);
			}			
		});
	}
	
	@JsonView(Views.AreaListId.class)
	@RequestMapping(value = "/areas/all", method = RequestMethod.GET)
	public List<AreasDto> areas() {
		return areasService.findAll().stream().map(item -> convert.toDto(item))
					.collect(Collectors.toList());
	}

	@JsonView(Views.AreaAudit.class)
	@RequestMapping(value = "/area/{id}", method = RequestMethod.GET)
	public AreasDto area(@PathVariable Integer id) {
		return convert.toDto(areasService.findById(id));
	}

	@JsonView(Views.AreaAudit.class)
	@RequestMapping(value = "/area/save", method = RequestMethod.POST)
	public AreasDto saveArea(@RequestBody AreasDto areasDto) {
		log.info("Saving an area named : " + areasDto.getName());
		return convert.toDto(areasService.save(convert.toEntity(areasDto)));
	}

	@RequestMapping(value = "/area/{id}/delete", method = RequestMethod.GET)
	public void deleteArea(@PathVariable Integer id) {
		log.info("An area has been deleted with id : " + id);
		Areas area = areasService.findById(id);
		if(area != null)
			areasService.delete(area);
		else
			throw new NotFoundException("Area not found!");
	}

	// category-api
	@JsonView(Views.CategoryListId.class)
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public Page<CategoriesDto> categories(Pageable pageable) {
		return categoriesService.findPage(pageable).map(new Converter<Categories, CategoriesDto>() {
			@Override
			public CategoriesDto convert(Categories category) {
				return convert.toDto(category);
			}			
		});
	}
	
	@JsonView(Views.CategoryListId.class)
	@RequestMapping(value = "/categories/all", method = RequestMethod.GET)
	public List<CategoriesDto> categories() {
		return categoriesService.findAll().stream().map(item -> convert.toDto(item))
					.collect(Collectors.toList());
	}

	@JsonView(Views.CategoryAudit.class)
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public CategoriesDto category(@PathVariable Integer id) {
		return convert.toDto(categoriesService.findById(id));
	}

	@JsonView(Views.CategoryAudit.class)
	@RequestMapping(value = "/category/save", method = RequestMethod.POST)
	public CategoriesDto saveCategory(@RequestBody CategoriesDto categoryDto) {
		return Optional.ofNullable(categoryDto.getName())
				.map(item -> convert.toDto(categoriesService.save(convert.toEntity(categoryDto))))
				.orElseThrow(() -> new FieldNotFoundException("Name cannot be null!"));
	}

	@RequestMapping(value = "/category/{id}/delete", method = RequestMethod.GET)
	public void deleteCategory(@PathVariable Integer id) {
		log.info("Deleting a category with id " + id);
		categoriesService.delete(id);
	}

	// class - range - api
	@RequestMapping(value = "/classrange")
	public List<ClassRangeDto> classRange() {
		return classRangeService.findAll().stream().map(classRange -> convert.toDto(classRange))
				.collect(Collectors.toList());
	}

	// contact -api
	@JsonView(Views.ContactAudit.class)
	@RequestMapping(value = "/vendor/{id}/contact/save", method = RequestMethod.POST)
	public ContactsDto saveContact(@PathVariable Integer id, @RequestBody ContactsDto contactsDto) {

		if (contactsDto.getEmail() != null && validate.email(contactsDto.getEmail()) == false)
			throw new InvalidFieldException("Please enter a valid email");

		if (contactsDto.getMobile() != null && validate.mobile(contactsDto.getMobile()) == false)
			throw new InvalidFieldException("Please enter a valid mobile");

		if (contactsDto.getPhone() != null && validate.phone(contactsDto.getPhone()) == false)
			throw new InvalidFieldException("Please enter a valid phone");

		log.info("Saving/Updating a contact for vendor id : " + id);
		return convert.toDto(contactsService.save(convert.toEntity(contactsDto, id)));
	}

	@JsonView(Views.ContactAudit.class)
	@RequestMapping(value = "/vendor/{id}/contact", method = RequestMethod.GET)
	public ContactsDto contactByVendor(@PathVariable Integer id) {
		return convert.toDto(contactsService.findByVendor(id));
	}

	// services-api
	@JsonView(Views.ServiceListId.class)
	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public Page<ServicesDto> services(Pageable pageable) {
		return servicesService.findPage(pageable).map(new Converter<Services, ServicesDto>() {
			@Override
			public ServicesDto convert(Services service) {
				return convert.toDto(service);
			}			
		});
	}
	
	@JsonView(Views.ServiceListId.class)
	@RequestMapping(value = "/services/all", method = RequestMethod.GET)
	public List<ServicesDto> services() {
		return servicesService.findAll().stream().map(item -> convert.toDto(item))
					.collect(Collectors.toList());
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
		log.info("Deleting a service : " + id);
		servicesService.deleteById(id);
	}

	// sub-categories-api
	@JsonView(Views.SubCategoryListId.class)
	@RequestMapping(value = "/category/{id}/subcategories", method = RequestMethod.GET)
	public Page<SubCategoriesDto> subCategoriespage(@PathVariable Integer id, Pageable pageable) {
		return subCategoriesService.findByCategoryId(id, pageable).map(new Converter<SubCategories, SubCategoriesDto>() {
			@Override
			public SubCategoriesDto convert(SubCategories subCategory) {
				return convert.toDto(subCategory);
			}			
		});
	}
	
	@JsonView(Views.SubCategoryListId.class)
	@RequestMapping(value = "/category/{id}/subcategories/all", method = RequestMethod.GET)
	public List<SubCategoriesDto> subCategories(@PathVariable Integer id) {
		return subCategoriesService.findByCategoryId(id).stream().map(item -> convert.toDto(item))
					.collect(Collectors.toList());
	}
		
	@JsonView(Views.SubCategoryAudit.class)
	@RequestMapping(value = "/category/{id}/subcategory/save", method = RequestMethod.POST)
	public SubCategoriesDto saveSubCategory(@RequestBody SubCategoriesDto subCategoriesDto, @PathVariable Integer id) {		
		log.info("Saving/Updating a sub category : " + id);
		SubCategories subCategory = convert.toEntity(subCategoriesDto);
		subCategory.setCategory(new Categories(id));
		return convert.toDto(subCategoriesService.save(subCategory));
	}

	@RequestMapping(value = "/subcategory/{id}/delete", method = RequestMethod.GET)
	public void deleteSubCategory(@PathVariable Integer id) {
		log.info("Deleting a sub category id : " + id);
		subCategoriesService.delete(id);
	}

	@JsonView(Views.SubCategoryAudit.class)
	@RequestMapping(value = "/subcategory/{id}", method = RequestMethod.GET)
	public SubCategoriesDto subCategory(@PathVariable Integer id) {
		return convert.toDto(subCategoriesService.findById(id));
	}

	// vendors-api

	@JsonView(Views.VendorListId.class)
	@RequestMapping(value = "/vendors", method = RequestMethod.GET)
	public Page<VendorsDto> vendorsList(Pageable pageable) {
		return vendorsService.findNames(pageable).map(new Converter<Vendors, VendorsDto>() {
			@Override
			public VendorsDto convert(Vendors vendor) {
				return convert.toDto(vendor);
			}			
		});
	}
	
	@JsonView(Views.VendorListId.class)
	@RequestMapping(value = "/vendors/all", method = RequestMethod.GET)
	public List<VendorsDto> vendorsList() {
		return vendorsService.findNames().stream().map(item -> convert.toDto(item))
					.collect(Collectors.toList());
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
			throw new FieldNotFoundException("Name cannot be null!");

		if (vendorsDto.getCategory() == null || vendorsDto.getCategory().getId() == null)
			throw new FieldNotFoundException("Category id cannot be null!");

		if (vendorsDto.getSubCategory() == null || vendorsDto.getSubCategory().getId() == null)
			throw new FieldNotFoundException("Sub category id cannot be null!");

		if (vendorsDto.getClassRange() == null || vendorsDto.getClassRange().getId() == null)
			throw new FieldNotFoundException("Select any class range!");

		log.info("Saving/Updating vendor : " + vendorsDto.getName());
		return convert.toDto(vendorsService.save(convert.toEntity(vendorsDto)));
	}

	@RequestMapping(value = "/vendor/{id}/delete", method = RequestMethod.GET)
	public void deleteVendor(@PathVariable Integer id) {
		log.info("Deleting a vendor with id : " + id);
		vendorsService.delete(id);
		log.info("Deleting vendor assets : " + id);
		assetsService.deleteAll(id);
	}

	@RequestMapping(value = "/vendor/{id}/{type}/upload", method = RequestMethod.POST)
	public void upload(@RequestParam("file") MultipartFile[] multipartFiles, @PathVariable Integer id,
			@PathVariable String type) {
		if (!assetsService.isValidAssetType(type)) {
			log.error("Invalid Asset type!");
			throw new InvalidFileTypeException("Invalid Asset Type!");
		}

		if (!assetsService.isWithinLimit(multipartFiles.length, id, type)) {
			log.error("Out of limit for vendor: " + id);
			throw new OutOfLimitException("Limit exceeded");
		}
		Arrays.stream(multipartFiles).filter(multipartFile -> !StringUtils.isEmpty(multipartFile.getOriginalFilename()))
				.forEach(multipartFile -> {
					String fileType = multipartFile.getContentType();
					if (fileType.equals(ImageType.JPG.getValue()) || fileType.equals(ImageType.PNG.getValue())) {
						log.info("Valid image : " + multipartFile.getOriginalFilename());
					} else {
						log.error("Invalid Images...");
						throw new InvalidFileTypeException("Image format should be JPG or PNG!");
					}
				});
		try {
			log.info("Uploading image files to s3 bucket for the vendor id : " + id);
			assetsService.upload(multipartFiles, id, type, SecurityContextHolder.getContext().getAuthentication().getName());
			updateAssetES(id, type);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new AwsS3Exception("Image server has connectivity issue!");
		}
	}

	@JsonView(Views.AssetAudit.class)
	@RequestMapping(value = "/vendor/{id}/{type}", method = RequestMethod.GET)
	public List<AssetsDto> vendorImages(@PathVariable Integer id, @PathVariable String type) {
		if (!assetsService.isValidAssetType(type)) {
			log.error("Invalid Asset type!");
			throw new InvalidFileTypeException("Invalid Asset Type!");
		}
		try {
			log.info("Accessing S3 bucket");
			return assetsService.list(id, type).stream()
					.map(item -> new AssetsDto(item.getKey(),
							assetsService.getHost() + item.getKey(),
							assetsService.getUploadedBy(item), 
							item.getLastModified()))
					.collect(Collectors.toList());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new AwsS3Exception("Image server has connectivity issue!");
		}
	}

	@RequestMapping(value = "/vendor/{id}/{type}/delete")
	public void deleteVendorImage(@PathVariable Integer id, @PathVariable String type, @RequestParam String key) {
		try {
			log.info("Accessing S3 bucket");
			assetsService.delete(key);
			log.info("Successfully deleted " + key);
			updateAssetES(id, type);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new AwsS3Exception("Image server has connectivity issue!");
		}
	}

	@JsonView(Views.VendorDetailId.class)
	@RequestMapping(value = "/vendor/{id}/detail", method = RequestMethod.GET)
	public VendorsDto detail(@PathVariable Integer id) {
		try {
			log.info("Getting detail for the vendor" + id);
			VendorsDto detail = convert.toDto(vendorsService.detail(id));
			log.info("Getting aws images..........");
			detail.setLogo(assetsService.list(id, AssetType.LOGO.getValue()).stream()
					.map(item -> new AssetsDto(item.getKey(),
							assetsService.getHost() + item.getKey(),
							assetsService.getUploadedBy(item), 
							item.getLastModified()))
					.collect(Collectors.toList()));
			detail.setImages(assetsService.list(id, AssetType.GALLERY.getValue()).stream()
					.map(item -> new AssetsDto(item.getKey(),
							assetsService.getHost() + item.getKey(),
							assetsService.getUploadedBy(item), 
							item.getLastModified()))
					.collect(Collectors.toList()));
			return detail;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResourceNotFoundException("Requested vendor not found!");
		}
	}
	
	//update es
	private void updateAssetES(Integer id, String type) {
		ingestionService.save(assetsService.list(id, type).stream()
				.map(item -> assetsService.getHost() + item.getKey())
				.collect(Collectors.toList()), type, id);
	}
}
