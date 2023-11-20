UPDATE adm_category SET internal_id = 517 WHERE category_id = 10517;
UPDATE adm_category SET internal_id = 518 WHERE category_id = 10518;
UPDATE adm_category SET internal_id = 519 WHERE category_id = 10519;
UPDATE adm_category SET internal_id = 520 WHERE category_id = 10520;
UPDATE adm_category SET internal_id = 521 WHERE category_id = 10521;

UPDATE adm_category SET parent_category_id = 10517 WHERE category_id IN (10518, 10519, 10520, 10521);

UPDATE adm_category SET internal_id = 570 WHERE category_id = 10570;
