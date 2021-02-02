package be.fooda.backend.basket.service.mapper;

import be.fooda.backend.basket.model.entity.ContactEntity;
import be.fooda.backend.basket.model.create.ContactCreate;
import be.fooda.backend.basket.model.update.ContactUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ContactMapper {

    ContactEntity toEntity(ContactCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContactEntity toEntity(ContactUpdate from, @MappingTarget ContactEntity to);

    ContactCreate toCreate(ContactEntity from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContactUpdate toUpdate(ContactEntity from, @MappingTarget ContactUpdate to);

}
