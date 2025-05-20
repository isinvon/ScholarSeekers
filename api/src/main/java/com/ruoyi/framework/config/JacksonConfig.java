package com.ruoyi.framework.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ruoyi.project.common.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
// public class JacksonConfig {
//     @Bean
//     public ObjectMapper objectMapper() {
//         ObjectMapper mapper = new ObjectMapper();
//         SimpleModule module = new SimpleModule();
//         // 注册枚举反序列化器
//         module.addDeserializer(User.Status.class, new User.StatusDeserializer());
//         module.addDeserializer(User.Role.class, new User.RoleDeserializer());
//         mapper.registerModule(module);
//         mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//         return mapper;
//     }
// }



// --- 枚举反序列化示例 -------

// package com.ruoyi.project.common.domain;
//
// import com.fasterxml.jackson.core.JsonParser;
// import com.fasterxml.jackson.databind.DeserializationContext;
// import com.fasterxml.jackson.databind.JsonDeserializer;
// import java.io.IOException;
//
// public class StatusDeserializer extends JsonDeserializer<User.Status> {
//     @Override
//     public User.Status deserialize(JsonParser p, DeserializationContext ctxt)
//             throws IOException {
//         int value = p.getIntValue();
//         for (User.Status status : User.Status.values()) {
//             if (status.getValue() == value) {
//                 return status;
//             }
//         }
//         throw new IllegalArgumentException("无效的状态值: " + value);
//     }
// }