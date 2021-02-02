package be.fooda.backend.basket.service.mapper;

import be.fooda.backend.basket.model.entity.PaymentEntity;
import be.fooda.backend.basket.model.create.PaymentCreate;
import be.fooda.backend.basket.model.update.PaymentUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface PaymentMapper {

    PaymentEntity toEntity(PaymentCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PaymentEntity toEntity(PaymentUpdate from, @MappingTarget PaymentEntity to);

    PaymentCreate toCreate(PaymentEntity from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PaymentUpdate toUpdate(PaymentEntity from, @MappingTarget PaymentUpdate to);
}
