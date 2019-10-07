drop table if exists item;
drop table if exists box;

create table box (
    id integer  primary key
  , parent_box_id integer  references box(id)
);
create table item (
    id integer  primary key
  , parent_box_id integer references box(id)
  , color varchar(100)
);

--drop alias if exists find_items_in_bpx_spec_color;
--create