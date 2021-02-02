package be.fooda.backend.basket.service.mapper;

import be.fooda.backend.basket.model.entity.AddressEntity;
import be.fooda.backend.basket.model.create.AddressCreate;
import be.fooda.backend.basket.model.update.AddressUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface AddressMapper {

    AddressEntity toEntity(AddressCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AddressEntity toEntity(AddressUpdate from, @MappingTarget AddressEntity to);

    AddressCreate toCreate(AddressEntity from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AddressUpdate toUpdate(AddressEntity from, @MappingTarget AddressUpdate to);

}
