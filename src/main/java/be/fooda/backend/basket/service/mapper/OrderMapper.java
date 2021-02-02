package be.fooda.backend.basket.service.mapper;

import be.fooda.backend.basket.model.entity.OrderEntity;
import be.fooda.backend.basket.model.create.OrderCreate;
import be.fooda.backend.basket.model.update.OrderUpdate;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface OrderMapper {

    OrderEntity toEntity(OrderCreate from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderEntity toEntity(OrderUpdate from, @MappingTarget OrderEntity to);

    OrderCreate toCreate(OrderEntity from);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderUpdate toUpdate(OrderEntity from, @MappingTarget OrderUpdate to);

}
