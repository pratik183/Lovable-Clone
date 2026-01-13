package com.lovable.com.lovable.mapper;

import com.lovable.com.lovable.dto.member.MemberResponse;
import com.lovable.com.lovable.entity.ProjectMember;
import com.lovable.com.lovable.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "projectRole", constant = "OWNER")
    MemberResponse toProjectMemberResponseFromOwner(User owner); // Build owner response when creating project

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember); // Map membership entity to DTO

}
