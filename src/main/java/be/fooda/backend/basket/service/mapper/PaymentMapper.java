package be.fooda.backend.basket.service.mapper;

import be.fooda.backend.basket.model.entity.PaymentEntity;
import be.fooda.backend.basket.model.dto.CreatePaymentRequest;
import be.fooda.backend.basket.model.dto.UpdatePaymentRequest;

import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface PaymentMapper {

    PaymentEntity toEntity(CreatePaymentRequest from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PaymentEntity toEntity(UpdatePaymentRequest from, @MappingTarget PaymentEntity to);

    CreatePaymentRequest toCreate(PaymentEntity from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UpdatePaymentRequest toUpdate(PaymentEntity from, @MappingTarget UpdatePaymentRequest to);
}
