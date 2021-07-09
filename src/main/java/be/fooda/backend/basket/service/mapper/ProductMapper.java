package be.fooda.backend.basket.service.mapper;

import be.fooda.backend.basket.model.dto.ProductResponse;
import be.fooda.backend.basket.model.entity.ProductEntity;
import be.fooda.backend.basket.model.dto.CreateProductRequest;
import be.fooda.backend.basket.model.dto.UpdateProductRequest;

import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ProductMapper {

    ProductEntity toEntity(CreateProductRequest from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductEntity toEntity(UpdateProductRequest from, @MappingTarget ProductEntity to);

    CreateProductRequest toCreate(ProductEntity from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UpdateProductRequest toUpdate(ProductEntity from, @MappingTarget UpdateProductRequest to);

    ProductResponse toResponse(ProductEntity entity);
}
