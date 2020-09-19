package cn.dustlight.oauth2.uim.controllers;

import cn.dustlight.oauth2.uim.Constants;
import cn.dustlight.oauth2.uim.entities.v1.users.UimUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "资源业务")
@RequestMapping(value = "/api/res", produces = Constants.ContentType.APPLICATION_JSON)
public interface ResourceController {

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('READ_USER') and #oauth2.clientHasRole('READ_USER')")
    UimUser getUser();

}
