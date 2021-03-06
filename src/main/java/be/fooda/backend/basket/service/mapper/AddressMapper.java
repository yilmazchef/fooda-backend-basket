package be.fooda.backend.basket.service.mapper;

import be.fooda.backend.basket.model.entity.AddressEntity;
import be.fooda.backend.basket.model.dto.CreateAddressRequest;
import be.fooda.backend.basket.model.dto.UpdateAddressRequest;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface AddressMapper {

    AddressEntity toEntity(CreateAddressRequest from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AddressEntity toEntity(UpdateAddressRequest from, @MappingTarget AddressEntity to);

    CreateAddressRequest toCreate(AddressEntity from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UpdateAddressRequest toUpdate(AddressEntity from, @MappingTarget UpdateAddressRequest to);

}
