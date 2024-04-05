package com.dardeus.demos.ai.tti.config;

import org.springframework.ai.stabilityai.api.StabilityAiImageOptions;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@RegisterReflectionForBinding({StabilityAiImageOptions.class})
public class GlobalConfiguration {
}
