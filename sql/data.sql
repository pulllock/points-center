-- channel
INSERT INTO channel (id, create_time, update_time, code, name) VALUES (1, now(), now(), 'incentive', '激励');

-- points_config
INSERT INTO points_config (id, create_time, update_time, channel_code, name, description, status, type, expiration_rule, stock) VALUES (1, now(), now(), 'incentive', '激励发放永久积分', null, 1, 1, null, null);
