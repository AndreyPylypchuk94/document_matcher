CREATE TABLE IF NOT EXISTS matching_results
(
    id                 bigserial PRIMARY KEY,
    title              text NOT NULL,
    words              text[],
    types              text[],
    type_id            bigint,
    manual_handle_date TIMESTAMP,
    trash              boolean,
    foreign key (type_id) references document_types (id)
);