-- create tables

-- items
CREATE TABLE `items` (
                         `id` bigint NOT NULL,
                         `item_code` varchar(500) DEFAULT NULL,
                         `item_name` varchar(500) DEFAULT NULL,
                         `description` varchar(500) DEFAULT NULL,
                         `item_type_id` bigint DEFAULT NULL,
                         `info` text,
                         `location` text,
                         `cost` bigint DEFAULT NULL,
                         `purchased_timestamp` timestamp NULL DEFAULT NULL,
                         `created_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         `status` varchar(200) NOT NULL DEFAULT 'AVAILABLE',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- item change log

CREATE TABLE `sonar`.`item_change_logs` (
                                            `id` bigint NOT NULL,
                                            `item_code` varchar(500) DEFAULT NULL,
                                            `item_name` varchar(500) DEFAULT NULL,
                                            `description` varchar(500) DEFAULT NULL,
                                            `info` text,
                                            `location` text,
                                            `cost` bigint DEFAULT NULL,
                                            `created_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                            `status` varchar(200) NOT NULL DEFAULT 'AVAILABLE',
                                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- item type


CREATE TABLE `sonar`.`item_types` (
                                      `id` bigint NOT NULL,
                                      `name` varchar(500) DEFAULT NULL,
                                      `description` varchar(500) DEFAULT NULL,
                                      `info` text,
                                      `location` text,
                                      `created_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                      `updated_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- user

CREATE TABLE `sonar`.`users` (
                                 `id` bigint NOT NULL,
                                 `name` varchar(500) DEFAULT NULL,
                                 `email` varchar(500) DEFAULT NULL,
                                 `phone_number` BIGINT DEFAULT NULL,
                                 `created_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 `updated_timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;