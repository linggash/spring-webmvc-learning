package com.linggash.spring_webmvc_learning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSocialMediaRequest {

    private String name;

    private String location;

}
