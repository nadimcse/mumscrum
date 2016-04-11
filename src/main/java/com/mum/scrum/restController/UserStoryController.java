package com.mum.scrum.restController;

import com.mum.scrum.service.UserStoryService;

/**
 * Created by Razib Mollick on 4/10/2016.
 */
@RestController
public class UserStoryController {

    @Autowired
    private UserStoryService userStoryService;
}
