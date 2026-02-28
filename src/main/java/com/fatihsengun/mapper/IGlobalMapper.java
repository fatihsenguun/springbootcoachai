package com.fatihsengun.mapper;

import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IGlobalMapper {

    DtoUser toDtoUser(User user);

}
