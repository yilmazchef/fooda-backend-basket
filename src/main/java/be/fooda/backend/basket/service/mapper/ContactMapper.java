package be.fooda.backend.basket.service.mapper;

import be.fooda.backend.basket.model.entity.ContactEntity;
import be.fooda.backend.basket.model.dto.CreateContactRequest;
import be.fooda.backend.basket.model.dto.UpdateContactRequest;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ContactMapper {

    ContactEntity toEntity(CreateContactRequest from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContactEntity toEntity(UpdateContactRequest from, @MappingTarget ContactEntity to);

    CreateContactRequest toCreate(ContactEntity from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UpdateContactRequest toUpdate(ContactEntity from, @MappingTarget UpdateContactRequest to);

}
