package be.fooda.backend.basket.service.mapper;

import be.fooda.backend.basket.model.entity.OrderEntity;
import be.fooda.backend.basket.model.dto.CreateOrderRequest;
import be.fooda.backend.basket.model.dto.UpdateContactRequest;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface OrderMapper {

    OrderEntity toEntity(CreateOrderRequest from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderEntity toEntity(UpdateContactRequest from, @MappingTarget OrderEntity to);

    CreateOrderRequest toCreate(OrderEntity from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UpdateContactRequest toUpdate(OrderEntity from, @MappingTarget UpdateContactRequest to);

}
