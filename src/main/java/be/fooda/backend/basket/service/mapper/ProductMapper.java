package be.fooda.backend.basket.service.mapper;

import be.fooda.backend.basket.model.entity.ProductEntity;
import be.fooda.backend.basket.model.create.ProductCreate;
import be.fooda.backend.basket.model.update.ProductUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ProductMapper {

    ProductEntity toEntity(ProductCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductEntity toEntity(ProductUpdate from, @MappingTarget ProductEntity to);

    ProductCreate toCreate(ProductEntity from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductUpdate toUpdate(ProductEntity from, @MappingTarget ProductUpdate to);
}
