package it.contrader.authenticationservice.mapper;

import it.contrader.authenticationservice.dto.UserDTO;
import it.contrader.authenticationservice.model.Role;
import it.contrader.authenticationservice.model.Role.ERole;
import it.contrader.authenticationservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "usertype", source = "roles", qualifiedByName = "rolesToString")
    UserDTO toUserDTO(User user);

    @Mapping(target = "roles", source = "usertype", qualifiedByName = "stringToRoles")
    User toUser(UserDTO userDTO);

    @Named("rolesToString")
    default String rolesToString(Set<Role> roles) {
        String usertype = null;
        if (ERole.ROLE_ADMIN.name().equals(((Role)roles.toArray()[0]).getRole().name())) {
            usertype = "ADMIN";
        } else if (ERole.ROLE_USER.name().equals(((Role)roles.toArray()[0]).getRole().name())) {
            usertype = "USER";
        } else if (ERole.ROLE_AMMINISTRATORE.name().equals(((Role)roles.toArray()[0]).getRole().name())) {
            usertype = "AMMINISTRATORE";
        }
        return  usertype;
    }

    @Named("stringToRoles")
    default Set<Role> stringToRoles(String usertype) {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(null, Role.ERole.valueOf(usertype)));
        return roleSet;
    }

    default List<User> toUserList(List<UserDTO> list) {
        List<User> userList = new ArrayList<>();
        for (UserDTO userDTO : list) {
            userList.add(toUser(userDTO));
        }
        return userList;
    }

    default List<UserDTO> toUserDTOList(List<User> list) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : list) {
            userDTOList.add(toUserDTO(user));
        }
        return userDTOList;
    }

    default Page<UserDTO> toUserDTOPage(List<UserDTO> userDTOList, int pageNumber, int pageSize) {
        return new PageImpl<>(userDTOList, PageRequest.of(pageNumber, pageSize), userDTOList.size());
    }
}
